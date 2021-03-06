/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.servicechargeraffaire.entities.Affaire;
import miage.m2.servicechargeraffaire.entities.ChargerAffaire;
import miage.m2.servicechargeraffaire.entities.EtatAffaire;
import miage.m2.sharedmenuis.exceptions.AffaireInconnueException;
import miage.m2.sharedmenuis.exceptions.CreerAffaireException;

/**
 * Bean Singleton qui stock les informations des affaires
 * @author QuentinDouris
 */
@Singleton
public class AffaireBean implements AffaireBeanLocal {
    // La clé correspond à l'id de l'affaire
    private HashMap<Integer, Affaire> listeAffaire;
    private int idAffaire;
    
    /**
     * Constructeur
     */
    public AffaireBean() {
        this.listeAffaire = new HashMap<>();
        this.idAffaire = 1;
    }

    /**
     * Retourne les affaire pour un chargé d'affaire
     * @param idChargerAffaire
     * @return 
     */
    @Override
    public ArrayList<Affaire> affairesPourUnChargerAffaire(int idChargerAffaire) {
        // Recherche les affaire du CA selon son id
        ArrayList toReturn = new ArrayList();
        for(Affaire affaire : this.listeAffaire.values()) {
            if(affaire.getChargerAffaire().getIdChargerAffaire() == idChargerAffaire) {
                toReturn.add(affaire);
            }
        }
        
        return toReturn;        
    }
    
    /**
     * Creer une nouvelle affaire dans le système
     * @param nomC
     * @param prenomC
     * @param adresseC
     * @param mailC
     * @param telC
     * @param locC
     * @param chargerAffaire
     * @return
     * @throws CreerAffaireException 
     */
    @Override
    public Affaire creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, ChargerAffaire chargerAffaire) throws CreerAffaireException {
        // Vérification que pour l'id de la nouvelle affaire auncune affaire existe déjà
        if (this.listeAffaire.get(this.idAffaire) != null) {
            throw new CreerAffaireException();
        }
        
        Affaire newAffaire = new Affaire(this.idAffaire, nomC, prenomC, adresseC, mailC, telC, locC, chargerAffaire);
        this.listeAffaire.put(newAffaire.getIdAffaire(), newAffaire);
        
        this.idAffaire++;
        return newAffaire;
    }

    /**
     * Modifier l'état de l'affaire
     * @param idAffaire
     * @param etat
     * @throws AffaireInconnueException 
     */
    @Override
    public void modifierEtatAffaire(int idAffaire, EtatAffaire etat) throws AffaireInconnueException {
        // Vérification de l'existance de l'affaire
        if(this.listeAffaire.get(idAffaire) == null) {
            throw new AffaireInconnueException();
        }
        
        this.listeAffaire.get(idAffaire).setEtat(etat);        
    }

    /**
     * Retourne les affaire pour un CA qui sont en attente d'un RDV Commercial
     * @param idChargerAffaire
     * @return 
     */
    @Override
    public ArrayList<Affaire> affairesPourUnChargerAffaireRdvCommercialNonSaisi(int idChargerAffaire) {
        return this.affairesChargerAffaireSelonEtat(idChargerAffaire, EtatAffaire.RDV_COMMERCIAL_NON_SAISIE);
    }
    
    /**
     * Retourne une affaire enregistrée dans le système selon son identifiant
     * @param idAffaire
     * @return
     * @throws AffaireInconnueException 
     */
    @Override
    public Affaire obtenirAffaire(int idAffaire) throws AffaireInconnueException {
         // Vérification de l'existance de l'affaire
        if(this.listeAffaire.get(idAffaire) == null) {
            throw new AffaireInconnueException();
        }
        
        return this.listeAffaire.get(idAffaire);
    }

    /**
     * Retourne les affaire pour un CA qui sont en attente d'un RDV Poseur
     * @param idChargerAffaire
     * @return 
     */
    @Override
    public ArrayList<Affaire> affairesPourUnChargerAffaireRdvPoseurNonSaisi(int idChargerAffaire) {
        return this.affairesChargerAffaireSelonEtat(idChargerAffaire, EtatAffaire.RDV_POSEUR_NON_SAISIE);
    }
    
    /**
     * Retourne les affaires à cloturer d'un charger d'affaire
     * @param idChargerAffaire
     * @return 
     */
    @Override
    public ArrayList<Affaire> affairesPourUnChargerAffaireACloturer(int idChargerAffaire) {
        return this.affairesChargerAffaireSelonEtat(idChargerAffaire, EtatAffaire.POSE_VALIDEE);
    }
    
    /**
     * Recherche les affaires dans le système pour un charger d'affaire selon un état d'avancement d'une affaire
     * @param idCA
     * @param etat
     * @return 
     */
    private ArrayList<Affaire> affairesChargerAffaireSelonEtat(int idCA, EtatAffaire etat) {
        ArrayList toReturn = new ArrayList();
        for(Affaire affaire : this.listeAffaire.values()) {
            if(affaire.getChargerAffaire().getIdChargerAffaire() == idCA && affaire.getEtat() == etat) {
                toReturn.add(affaire);
            }
        }
        
        return toReturn;
    }
    
}
