/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.services;

import javax.ejb.Local;
import miage.m2.sharedmenuis.exceptions.AucunCommercialException;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialDemandeRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialInconnuException;
import miage.m2.sharedmenuis.transientobjects.RDVCommercialTransient;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON pour les rendez-vous commerciaux
 * @author QuentinDouris
 */
@Local
public interface RDVCommercialServiceLocal {
    
    /**
     * Obtenir un rendez-vous commercial selon la disponoibilité du client
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException
     * @throws AucunCommercialException 
     */
    public String obtenirRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, AucunCommercialException;

    /**
     * Confirmation d'un rendez-vous commercial obtenu
     * @param rdv
     * @return
     * @throws CommercialConfirmRDVException
     * @throws CommercialInconnuException 
     */
    public String valideRDVCommercial(RDVCommercialTransient rdv) throws CommercialConfirmRDVException, CommercialInconnuException;

}
