/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exposition;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.entities.Affaire;
import miage.m2.entities.ChargerAffaire;
import miage.m2.exceptions.APIException;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CreerAffaireException;
import miage.m2.metier.AffaireBeanLocal;
import miage.m2.metier.ChargerAffaireBeanLocal;
import miage.m2.transientobjects.AffaireTransient;
import miage.m2.transientobjects.ChargerAffaireTransient;
import miage.m2.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.transientobjects.RDVCommercialTransient;

/**
 * Classe Stateless GestionAffaire qui représente les différentes opérations possibles par un Charger Affaire depuis son poste client lourd
 * @author QuentinDouris
 */
@Stateless
public class GestionAffaire implements GestionAffaireRemote {

    @EJB
    private ChargerAffaireBeanLocal chargerAffaireBean;

    @EJB
    private AffaireBeanLocal affaireBean;
    
    // URL de l'API du Service Commercial
    private final String SERVICE_COMMERCIAL_ENDPOINT = "http://localhost:8080/ServiceCommercial-web/webresources/rdvcommercial/";
    
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
     * @throws miage.m2.exceptions.ChargerAffaireInconnuException 
     * @throws miage.m2.exceptions.CreerAffaireException 
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
            URL url = new URL(SERVICE_COMMERCIAL_ENDPOINT + dateDispoC);
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            int codeResponse = connexion.getResponseCode();
            
            // Aucune disponibilité
            if (codeResponse ==  204) {
                return null;
            }
            
            // Une erreur lors de l'exécution de la requête est survenu
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
            
            System.out.println("résultat JSON : \n" + reponseJson.toString());
            
            // Ferme la connexion
            connexion.disconnect();
            
            // Lecture du JSON
            Gson gson = new Gson();
            PropositionRDVCommercialTransient propositionRdv = gson.fromJson(reponseJson.toString(), PropositionRDVCommercialTransient.class);
            System.out.println("résultat Objet : " + propositionRdv.getDate());
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
     * @param localisation
     * @param idAffaire
     * @return
     * @throws CommercialConfirmRDVException 
     */
    @Override
    public boolean validerRdvCommercial(RDVCommercialTransient rdvCommercial, String localisation, int idAffaire) throws CommercialConfirmRDVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        // Appel l'API du service commercial pour VALIDER le rdv
    }

    /**
     * Retourne les affaires d'un CA
     * @param idCA
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    @Override
    public ArrayList<AffaireTransient> affairesDuChargerAffaire(int idCA) throws ChargerAffaireInconnuException {
        // Recherche les affaires d'un CA
        ArrayList<Affaire> listAffaireCA = affaireBean.affairesPourUnChargerAffaire(idCA);
        ArrayList<AffaireTransient> listAffaireCATransient = new ArrayList<>();
        
        // Construit un transient object
        for(Affaire affaire : listAffaireCA) {
            listAffaireCATransient.add(new AffaireTransient(affaire.getIdAffaire(), affaire.getNomC(), affaire.getEtat().name()));
        }
        
        return listAffaireCATransient;
    }
    
}
