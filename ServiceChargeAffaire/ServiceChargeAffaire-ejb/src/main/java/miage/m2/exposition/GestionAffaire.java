/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exposition;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.entities.Affaire;
import miage.m2.entities.ChargerAffaire;
import miage.m2.exceptions.AucuneAffaireException;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CreerAffaireException;
import miage.m2.metier.AffaireBeanLocal;
import miage.m2.metier.ChargerAffaireBeanLocal;
import miage.m2.transientobjects.AffaireTransient;
import miage.m2.transientobjects.ChargerAffaireTransient;
import miage.m2.transientobjects.RdvCommercialTransient;

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
    
    
     /**
     * Authentification du charger d'affaire
     * @param idCA
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    @Override
    public ChargerAffaireTransient authentifier(int idCA) throws ChargerAffaireInconnuException {
        ChargerAffaire chargerAffaire = chargerAffaireBean.authentifier(idCA);
        ArrayList<Affaire> listAffaire = new ArrayList<>();
        
        // Recherche les affaires d'un CA
        try {
            listAffaire = affaireBean.affairesPourUnChargerAffaire(idCA);
        } catch (AucuneAffaireException ex) {
            System.out.println("***** " + ex.getMessage());
        }
        
        // Construction du transient object
        ArrayList<AffaireTransient> affairesTransient = new ArrayList<>();
        
        if(!listAffaire.isEmpty()) {
            for(Affaire item : listAffaire) {
                affairesTransient.add(new AffaireTransient(item.getIdAffaire(), item.getNomC(), item.getEtat().name()));
            }
        }
        
        // Objet transient à retourner
        ChargerAffaireTransient caTransient = new ChargerAffaireTransient(chargerAffaire.getNom(), chargerAffaire.getPrenom(), affairesTransient);
        
        return caTransient;
    }
    
    /**
     * Création d'une affaire dans le système
     * @param nomC
     * @param prenomC
     * @param adresseC
     * @param mailC
     * @param locC
     * @param idChargerAffaire
     * @return 
     * @throws miage.m2.exceptions.ChargerAffaireInconnuException 
     * @throws miage.m2.exceptions.CreerAffaireException 
     */
    @Override
    public int creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String locC, int idChargerAffaire) throws ChargerAffaireInconnuException, CreerAffaireException {
        ChargerAffaire ca = chargerAffaireBean.obtenirChargerAffaire(idChargerAffaire);
        Affaire affaire = affaireBean.creerAffaire(nomC, prenomC, adresseC, mailC, 0, locC, ca);
        
        return affaire.getIdAffaire();
    }

    /**
     * Demande un rendez-vous commercial auprès du service Commercial
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException 
     */
    @Override
    public RdvCommercialTransient demandeDisponibiliteRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        // Appel l'API du service commercial pour OBTENIR une possibilité de RDV
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
    public boolean validerRdvCommercial(RdvCommercialTransient rdvCommercial, String localisation, int idAffaire) throws CommercialConfirmRDVException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        // Appel l'API du service commercial pour VALIDER le rdv
    }
    
}