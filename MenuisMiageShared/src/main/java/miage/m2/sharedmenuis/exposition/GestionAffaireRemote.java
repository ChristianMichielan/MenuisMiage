/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.exposition;

import java.util.ArrayList;
import javax.ejb.Remote;
import miage.m2.sharedmenuis.exceptions.APIException;
import miage.m2.sharedmenuis.exceptions.AffaireInconnueException;
import miage.m2.sharedmenuis.exceptions.ChargerAffaireInconnuException;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialDemandeRDVException;
import miage.m2.sharedmenuis.exceptions.CreerAffaireException;
import miage.m2.sharedmenuis.exceptions.PoseurConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.PoseurDemandeRDVException;
import miage.m2.sharedmenuis.transientobjects.AffaireTransient;
import miage.m2.sharedmenuis.transientobjects.ChargerAffaireTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVPoseurTransient;
import miage.m2.sharedmenuis.transientobjects.RDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.RDVPoseurTransient;


/**
 * Interface Remote GestionAffaireRemote, elle représente les différentes signatures des opérations possibles par un Chargé d'Affaire depuis son poste client lourd.
 * @author QuentinDouris
 */
@Remote
public interface GestionAffaireRemote {
    
    /**
     * Authentification du charger d'affaire
     * @param idCA
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    public ChargerAffaireTransient authentifier(int idCA) throws ChargerAffaireInconnuException;
    
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
    public int creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, int idChargerAffaire) throws ChargerAffaireInconnuException, CreerAffaireException;
    
    /**
     * Demande un rendez-vous commercial auprès du service Commercial
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException 
     * @throws APIException 
     */
    public PropositionRDVCommercialTransient demandeDisponibiliteRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, APIException;
    
    /**
     * Valide un rendez-vous commercial auprès du service Commercial
     * @param rdvCommercial
     * @return
     * @throws CommercialConfirmRDVException 
     * @throws miage.m2.sharedmenuis.exceptions.APIException 
     */
    public boolean validerRdvCommercial(RDVCommercialTransient rdvCommercial) throws CommercialConfirmRDVException, APIException;
    
    /**
     * Retourne les affaires d'un CA
     * @param idCA
     * @return
     */
    public ArrayList<AffaireTransient> affairesDuChargerAffaire(int idCA);
    
    /**
     * Retourne les affaires d'un CA qui sont attente d'un RDV Commercial
     * @param idCA
     * @return
     */
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvCommercialNonSaisi(int idCA);
    
    /**
     * Met à jour l'état de l'affaire après que le rdv commercial ait été saisi (mais pas encore réalisé)
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    public void modifierEtatAffaireAttenteRdvCommercial(int idAffaire) throws AffaireInconnueException;
    
    /**
     * Retourne les affaires d'un CA qui sont attente d'un RDV Poseur
     * @param idCA
     * @return 
     */
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvPoseurNonSaisi(int idCA);
    
    /**
     * Demande un rendez-vous poseur auprès du service Poseur
     * @param dateDispoC
     * @return
     * @throws PoseurDemandeRDVException
     * @throws APIException 
     */
    public PropositionRDVPoseurTransient demandeDisponibiliteRdvPoseur(String dateDispoC) throws PoseurDemandeRDVException, APIException;
    
    /**
     * Valide un rendez-vous poseur auprès du service Poseur
     * @param rdvPoseur
     * @return
     * @throws miage.m2.sharedmenuis.exceptions.PoseurConfirmRDVException
     * @throws APIException 
     */
    public boolean validerRdvPoseur(RDVPoseurTransient rdvPoseur) throws PoseurConfirmRDVException, APIException;
    
    /**
     * Met à jour l'état de l'affaire après que le rdv poseur ait été saisi (mais pas encore réalisé)
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    public void modifierEtatAffaireAttenteRdvPoseur(int idAffaire) throws AffaireInconnueException;
    
    /**
     * Retourne toutes les affaire dont la pose a été effectuée (qui sont en attentes d'être cloturées)
     * @param idCA
     * @return 
     */
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireACloturer(int idCA);
    
    /**
     * Enregistre la cloture d'une affaire dans le système
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    public void cloturerAffaire(int idAffaire) throws AffaireInconnueException;
    
}
