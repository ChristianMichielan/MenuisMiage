/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import javax.ejb.Local;
import miage.m2.exceptions.AucunRDVPoseur;
import miage.m2.exceptions.AucuneEquipePoseurException;
import miage.m2.exceptions.EquipePoseurInconnuException;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.exceptions.PoseurDemandeRDVException;
import miage.m2.transientobjects.RDVPoseurTransient;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON
 * @author QuentinDouris
 */
@Local
public interface RDVPoseurServiceLocal {
    
    /**
     * Obtenir un rendez-vous poseur selon la disponoibilité du client
     * @param dateDispoC
     * @return
     * @throws PoseurDemandeRDVException
     * @throws AucuneEquipePoseurException 
     */
    public String obtenirRdvPoseur(String dateDispoC) throws PoseurDemandeRDVException, AucuneEquipePoseurException;

    /**
     * Confirmation d'un rendez-vous poseur obtenu
     * @param rdv
     * @return
     * @throws PoseurConfirmRDVException
     * @throws EquipePoseurInconnuException 
     */
    public String valideRDVPoseur(RDVPoseurTransient rdv) throws PoseurConfirmRDVException, EquipePoseurInconnuException;
    
}
