/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.exposition;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.servicechargeraffaire.entities.Affaire;
import miage.m2.servicechargeraffaire.entities.ChargerAffaire;
import miage.m2.servicechargeraffaire.entities.EtatAffaire;
import miage.m2.sharedmenuis.exceptions.APIException;
import miage.m2.sharedmenuis.exceptions.AffaireInconnueException;
import miage.m2.sharedmenuis.exceptions.ChargerAffaireInconnuException;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialDemandeRDVException;
import miage.m2.sharedmenuis.exceptions.CreerAffaireException;
import miage.m2.sharedmenuis.exceptions.PoseurConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.PoseurDemandeRDVException;
import miage.m2.sharedmenuis.exposition.GestionAffaireRemote;
import miage.m2.servicechargeraffaire.metier.AffaireBeanLocal;
import miage.m2.servicechargeraffaire.metier.ChargerAffaireBeanLocal;
import miage.m2.servicechargeraffaire.messagesproducer.EncaissementMessageBeanLocal;
import miage.m2.sharedmenuis.transientobjects.AffaireTransient;
import miage.m2.sharedmenuis.transientobjects.ChargerAffaireTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVPoseurTransient;
import miage.m2.sharedmenuis.transientobjects.RDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.RDVPoseurTransient;

/**
 * Classe Stateless GestionAffaire qui représente les différentes opérations possibles par un Charger Affaire depuis son poste client lourd
 * @author QuentinDouris
 */
@Stateless
public class GestionAffaire implements GestionAffaireRemote {

    @EJB
    private EncaissementMessageBeanLocal encaissementMessageBean;

    @EJB
    private ChargerAffaireBeanLocal chargerAffaireBean;

    @EJB
    private AffaireBeanLocal affaireBean;
    
    // URL des APIs
    private final String SERVICE_COMMERCIAL_ENDPOINT = "http://localhost:8080/ServiceCommercial-web/webresources/rdvcommercial/";
    private final String SERVICE_POSEUR_ENDPOINT = "http://localhost:8080/ServicePoseur-web/webresources/rdvposeur/";
    
     /**
     * Authentification du charger d'affaire
     * @param idCA
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    @Override
    public ChargerAffaireTransient authentifier(int idCA) throws ChargerAffaireInconnuException {
        ChargerAffaire chargerAffaire = chargerAffaireBean.authentifier(idCA);
        ChargerAffaireTransient caTransient = new ChargerAffaireTransient(chargerAffaire.getIdChargerAffaire(), chargerAffaire.getNom(), chargerAffaire.getPrenom());
        
        return caTransient;
    }
    
    /**
     * Création d'une affaire dans le système
     * @param nomC
     * @param prenomC
     * @param adresseC
     * @param mailC
     * @param telC
     * @param locC
     * @param idChargerAffaire
     * @return 
     * @throws miage.m2.sharedmenuis.exceptions.ChargerAffaireInconnuException 
     * @throws miage.m2.sharedmenuis.exceptions.CreerAffaireException 
     */
    @Override
    public int creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, int idChargerAffaire) throws ChargerAffaireInconnuException, CreerAffaireException {
        ChargerAffaire ca = chargerAffaireBean.obtenirChargerAffaire(idChargerAffaire);
        Affaire affaire = affaireBean.creerAffaire(nomC, prenomC, adresseC, mailC, telC, locC, ca);
        
        return affaire.getIdAffaire();
    }

    /**
     * Demande un rendez-vous commercial auprès du service Commercial
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException 
     * @throws APIException 
     */
    @Override
    public PropositionRDVCommercialTransient demandeDisponibiliteRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, APIException {
        // Appel l'API du service commercial pour OBTENIR une possibilité de RDV
        try {
            // Configure la connexion à l'API
            System.out.println("*** ServiceChargerAffaire - GestionAffaire - req API [proposition rdv commercial] : " + SERVICE_COMMERCIAL_ENDPOINT + dateDispoC);
            URL url = new URL(SERVICE_COMMERCIAL_ENDPOINT + dateDispoC);
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            int codeResponse = connexion.getResponseCode();
            
            // Aucune disponibilité
            if (codeResponse ==  204) {
                return null;
            }
            
            // Une erreur lors de l'exécution de la requête est survenue
            if(codeResponse != 200) {
                throw new APIException(codeResponse, SERVICE_COMMERCIAL_ENDPOINT);
            }
            
            // Un rdv est disponible : lecture de la réponse de l'API
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputline;
            StringBuilder reponseJson = new StringBuilder();
            while ((inputline = buffer.readLine()) != null) {
                    reponseJson.append(inputline);
            }
            buffer.close();
            
            // Ferme la connexion
            connexion.disconnect();
            
            // Lecture du JSON
            Gson gson = new Gson();
            PropositionRDVCommercialTransient propositionRdv = gson.fromJson(reponseJson.toString(), PropositionRDVCommercialTransient.class);
            return propositionRdv;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_COMMERCIAL_ENDPOINT);
        } catch (IOException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_COMMERCIAL_ENDPOINT);
        }
    }

    /**
     * Valide un rendez-vous commercial auprès du service Commercial
     * @param rdvCommercial
     * @return
     * @throws CommercialConfirmRDVException 
     * @throws miage.m2.sharedmenuis.exceptions.APIException 
     */
    @Override
    public boolean validerRdvCommercial(RDVCommercialTransient rdvCommercial) throws CommercialConfirmRDVException, APIException {
        // Appel l'API du service commercial pour VALIDER le rdv
        try {
            // Définition des paramètres de la requête
            Map<String, Object> parametres = new LinkedHashMap<>();
            parametres.put("daterdv", rdvCommercial.getDate());
            parametres.put("idcommercial", String.valueOf(rdvCommercial.getIdCommercial()));
            parametres.put("localisation", rdvCommercial.getLocalisation());
            parametres.put("idaffaire", String.valueOf(rdvCommercial.getIdAffaire()));

            // Construction de la chaine qui va contenir les paramètres
            StringBuilder postDonnees = new StringBuilder();
            for(Map.Entry param : parametres.entrySet()) {
                if(postDonnees.length() != 0) {
                    postDonnees.append("&");
                }
                postDonnees.append(URLEncoder.encode(String.valueOf(param.getKey()), StandardCharsets.UTF_8.toString()));
                postDonnees.append('=');
                postDonnees.append(URLEncoder.encode((String) param.getValue()));
            }

            // Configure la connexion à l'API
            System.out.println("*** ServiceChargerAffaire - GestionAffaire - req API [confirme rdv commercial] : " + SERVICE_COMMERCIAL_ENDPOINT + "?" + postDonnees.toString());
            URL url = new URL(SERVICE_COMMERCIAL_ENDPOINT + "?" + postDonnees.toString());
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("POST");

            int codeResponse = connexion.getResponseCode();

            // Une erreur lors de l'exécution de la requête est survenue
            if(codeResponse != 200) {
                System.out.println("ERREUR API");
            }

            // Le rdv est confirmé : lecture de la réponse de l'API
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputline;
            StringBuilder reponseJson = new StringBuilder();
            while ((inputline = buffer.readLine()) != null) {
                reponseJson.append(inputline);
            }
            buffer.close();

            // Ferme la connexion
            connexion.disconnect();

            // Lecture du JSON
            Gson gson = new Gson();
            RDVCommercialTransient validationRdv = gson.fromJson(reponseJson.toString(), RDVCommercialTransient.class);
                        
            return validationRdv != null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_COMMERCIAL_ENDPOINT);
        } catch (IOException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_COMMERCIAL_ENDPOINT);
        }
    }

    /**
     * Retourne les affaires d'un CA
     * @param idCA
     * @return
     */
    @Override
    public ArrayList<AffaireTransient> affairesDuChargerAffaire(int idCA) {
        // Recherche les affaires d'un CA
        ArrayList<Affaire> listAffaireCA = affaireBean.affairesPourUnChargerAffaire(idCA);
        ArrayList<AffaireTransient> listAffaireCATransient = new ArrayList<>();
        
        // Construit un transient object
        for(Affaire affaire : listAffaireCA) {
            listAffaireCATransient.add(new AffaireTransient(affaire.getIdAffaire(), affaire.getNomC(), affaire.getEtat().name(), affaire.getLocC()));
        }
        
        return listAffaireCATransient;
    }

    /**
     * Retourne les affaires d'un CA qui sont attente d'un RDV Commercial
     * @param idCA
     * @return
     */
    @Override
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvCommercialNonSaisi(int idCA) {
        // Recherche les affaires d'un CA
        ArrayList<Affaire> listAffaireCA = affaireBean.affairesPourUnChargerAffaireRdvCommercialNonSaisi(idCA);
        ArrayList<AffaireTransient> listAffaireCATransient = new ArrayList<>();
        
        // Construit un transient object
        for(Affaire affaire : listAffaireCA) {
            listAffaireCATransient.add(new AffaireTransient(affaire.getIdAffaire(), affaire.getNomC(), affaire.getEtat().name(), affaire.getLocC()));
        }
        
        return listAffaireCATransient;
    }

    /**
     * Met à jour l'état de l'affaire après que le rdv commercial ait été saisi (mais pas encore réalisé)
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    @Override
    public void modifierEtatAffaireAttenteRdvCommercial(int idAffaire) throws AffaireInconnueException {
        this.affaireBean.modifierEtatAffaire(idAffaire, EtatAffaire.ATTENTE_RDV_COMMERCIAL);
    }

    /**
     * Retourne les affaires d'un CA qui sont attente d'un RDV Poseur
     * @param idCA
     * @return 
     */
    @Override
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvPoseurNonSaisi(int idCA) {
        // Recherche les affaires d'un CA
        ArrayList<Affaire> listAffaireCA = affaireBean.affairesPourUnChargerAffaireRdvPoseurNonSaisi(idCA);
        ArrayList<AffaireTransient> listAffaireCATransient = new ArrayList<>();
        
        // Construit un transient object
        for(Affaire affaire : listAffaireCA) {
            listAffaireCATransient.add(new AffaireTransient(affaire.getIdAffaire(), affaire.getNomC(), affaire.getEtat().name(), affaire.getLocC()));
        }
        
        return listAffaireCATransient;
    }

    /**
     * Demande un rendez-vous poseur auprès du service Poseur
     * @param dateDispoC
     * @return
     * @throws PoseurDemandeRDVException
     * @throws APIException 
     */
    @Override
    public PropositionRDVPoseurTransient demandeDisponibiliteRdvPoseur(String dateDispoC) throws PoseurDemandeRDVException, APIException {
        // Appel l'API du service poseur pour OBTENIR une possibilité de RDV
        try {
            // Configure la connexion à l'API
            System.out.println("*** ServiceChargerAffaire - GestionAffaire - req API [propositoin rdv poseur] : " + SERVICE_POSEUR_ENDPOINT + dateDispoC);
            URL url = new URL(SERVICE_POSEUR_ENDPOINT + dateDispoC);
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            int codeResponse = connexion.getResponseCode();
            
            // Aucune disponibilité
            if (codeResponse ==  204) {
                return null;
            }
            
            // Une erreur lors de l'exécution de la requête est survenue
            if(codeResponse != 200) {
                throw new APIException(codeResponse, SERVICE_POSEUR_ENDPOINT);
            }
            
            // Un rdv est disponible : lecture de la réponse de l'API
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputline;
            StringBuilder reponseJson = new StringBuilder();
            while ((inputline = buffer.readLine()) != null) {
                    reponseJson.append(inputline);
            }
            buffer.close();
            
            // Ferme la connexion
            connexion.disconnect();
            
            // Lecture du JSON
            Gson gson = new Gson();
            PropositionRDVPoseurTransient propositionRdv = gson.fromJson(reponseJson.toString(), PropositionRDVPoseurTransient.class);
            return propositionRdv;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_POSEUR_ENDPOINT);
        } catch (IOException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_POSEUR_ENDPOINT);
        }
    }

    /**
     * Valide un rendez-vous poseur auprès du service Poseur
     * @param rdvPoseur
     * @return
     * @throws miage.m2.sharedmenuis.exceptions.PoseurConfirmRDVException
     * @throws APIException 
     */
    @Override
    public boolean validerRdvPoseur(RDVPoseurTransient rdvPoseur) throws PoseurConfirmRDVException, APIException {
        // Appel l'API du service poseur pour VALIDER le rdv
        try {
            // Définition des paramètres de la requête
            Map<String, Object> parametres = new LinkedHashMap<>();
            parametres.put("daterdv", rdvPoseur.getDate());
            parametres.put("idequipeposeur", String.valueOf(rdvPoseur.getIdEquipePoseur()));
            parametres.put("localisation", rdvPoseur.getLocalisation());
            parametres.put("idaffaire", String.valueOf(rdvPoseur.getIdAffaire()));

            // Construction de la chaine qui va contenir les paramètres
            StringBuilder postDonnees = new StringBuilder();
            for(Map.Entry param : parametres.entrySet()) {
                if(postDonnees.length() != 0) {
                    postDonnees.append("&");
                }
                postDonnees.append(URLEncoder.encode(String.valueOf(param.getKey()), StandardCharsets.UTF_8.toString()));
                postDonnees.append('=');
                postDonnees.append(URLEncoder.encode((String) param.getValue()));
            }

            // Configure la connexion à l'API
            System.out.println("*** ServiceChargerAffaire - GestionAffaire - req API [confirme rdv poseur] : " + SERVICE_POSEUR_ENDPOINT + "?" + postDonnees.toString());
            URL url = new URL(SERVICE_POSEUR_ENDPOINT + "?" + postDonnees.toString());
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("POST");

            int codeResponse = connexion.getResponseCode();

            // Une erreur lors de l'exécution de la requête est survenue
            if(codeResponse != 200) {
                System.out.println("ERREUR API");
            }

            // Le rdv est confirmé : lecture de la réponse de l'API
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputline;
            StringBuilder reponseJson = new StringBuilder();
            while ((inputline = buffer.readLine()) != null) {
                reponseJson.append(inputline);
            }
            buffer.close();

            // Ferme la connexion
            connexion.disconnect();

            // Lecture du JSON
            Gson gson = new Gson();
            RDVPoseurTransient validationRdv = gson.fromJson(reponseJson.toString(), RDVPoseurTransient.class);
                        
            return validationRdv != null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_POSEUR_ENDPOINT);
        } catch (IOException ex) {
            Logger.getLogger(GestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
            throw new APIException(500, SERVICE_POSEUR_ENDPOINT);
        }
    }

    /**
     * Met à jour l'état de l'affaire après que le rdv poseur ait été saisi (mais pas encore réalisé)
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    @Override
    public void modifierEtatAffaireAttenteRdvPoseur(int idAffaire) throws AffaireInconnueException {
        this.affaireBean.modifierEtatAffaire(idAffaire, EtatAffaire.ATTENTE_RDV_POSEUR);
    }

    /**
     * Retourne toutes les affaire dont la pose a été effectuée (qui sont en attentes d'être cloturées)
     * @param idCA
     * @return 
     */
    @Override
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireACloturer(int idCA) {
        ArrayList<Affaire> listeAffaireACloturer = this.affaireBean.affairesPourUnChargerAffaireACloturer(idCA);
        ArrayList<AffaireTransient> listeAffaireACloturerTransient = new ArrayList<>();
        
        // Contruction de la liste des affaires à cloturer transient
        for(Affaire item : listeAffaireACloturer) {
            AffaireTransient aff = new AffaireTransient(item.getChargerAffaire().getIdChargerAffaire(), item.getNomC(), item.getEtat().name(), item.getLocC());
            listeAffaireACloturerTransient.add(aff);
        }
        
        return listeAffaireACloturerTransient;
    }

    /**
     * Enregistre la cloture d'une affaire dans le système
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    @Override
    public void cloturerAffaire(int idAffaire) throws AffaireInconnueException {
        this.encaissementMessageBean.cloturerAffairePourEncaissement(idAffaire);
    }
    
}
