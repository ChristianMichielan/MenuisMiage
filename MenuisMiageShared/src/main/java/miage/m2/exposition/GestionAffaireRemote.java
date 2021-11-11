/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exposition;

import javax.ejb.Remote;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CreerAffaireException;
import miage.m2.transientobjects.ChargerAffaireTransient;
import miage.m2.transientobjects.RdvCommercialTransient;

/**
 * Interface Remote GestionAffaireRemote, elle représente les différentes signatures des opérations possibles par un Chargé d'Affaire depuis son poste client lourd.
 * @author QuentinDouris
 */
@Remote
public interface GestionAffaireRemote {
    
    public ChargerAffaireTransient authentifier(int idCA) throws ChargerAffaireInconnuException;
    
    public int creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String locC, int idChargerAffaire) throws ChargerAffaireInconnuException, CreerAffaireException;
    
    public RdvCommercialTransient demandeDisponibiliteRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException;
    
    public boolean validerRdvCommercial(RdvCommercialTransient rdvCommercial, String localisation, int idAffaire) throws CommercialConfirmRDVException;
    
}
