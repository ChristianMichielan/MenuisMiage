/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.services;

import javax.ejb.Local;
import miage.m2.exceptions.AucunCommercialException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.transientobjects.RDVCommercialTransient;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON
 * @author QuentinDouris
 */
@Local
public interface RDVCommercialServiceLocal {
    
    public String obtenirRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, AucunCommercialException;

    public String valideRDVCommercial(RDVCommercialTransient rdv) throws CommercialConfirmRDVException, CommercialInconnuException;
}
