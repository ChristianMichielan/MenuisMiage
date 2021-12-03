/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exposition;

import java.util.ArrayList;
import javax.ejb.Remote;
import miage.m2.exceptions.APIException;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CreerAffaireException;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.exceptions.PoseurDemandeRDVException;
import miage.m2.transientobjects.AffaireTransient;
import miage.m2.transientobjects.ChargerAffaireTransient;
import miage.m2.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.transientobjects.PropositionRDVPoseurTransient;
import miage.m2.transientobjects.RDVCommercialTransient;
import miage.m2.transientobjects.RDVPoseurTransient;


/**
 * Interface Remote GestionAffaireRemote, elle représente les différentes signatures des opérations possibles par un Chargé d'Affaire depuis son poste client lourd.
 * @author QuentinDouris
 */
@Remote
public interface GestionAffaireRemote {
    
    public ChargerAffaireTransient authentifier(int idCA) throws ChargerAffaireInconnuException;
    
    public int creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, int idChargerAffaire) throws ChargerAffaireInconnuException, CreerAffaireException;
    
    public PropositionRDVCommercialTransient demandeDisponibiliteRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, APIException;
    
    public boolean validerRdvCommercial(RDVCommercialTransient rdv) throws CommercialConfirmRDVException, APIException;
    
    public ArrayList<AffaireTransient> affairesDuChargerAffaire(int idCA);
    
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvCommercialNonSaisi(int idCA);
    
    public void modifierEtatAffaireAttenteRdvCommercial(int idAffaire) throws AffaireInconnueException;
    
    public ArrayList<AffaireTransient> affairesPourUnChargerAffaireRdvPoseurNonSaisi(int idCA);
    
    public PropositionRDVPoseurTransient demandeDisponibiliteRdvPoseur(String dateDispoC) throws PoseurDemandeRDVException, APIException;
    
    public boolean validerRdvPoseur(RDVPoseurTransient rdvPoseur) throws PoseurConfirmRDVException, APIException;
    
    public void modifierEtatAffaireAttenteRdvPoseur(int idAffaire) throws AffaireInconnueException;
}
