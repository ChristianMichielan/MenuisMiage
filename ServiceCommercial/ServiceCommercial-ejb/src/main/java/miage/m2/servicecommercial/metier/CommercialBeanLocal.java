/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.metier;

import java.util.ArrayList;
import javax.ejb.Local;
import miage.m2.servicecommercial.entities.Commercial;
import miage.m2.sharedmenuis.exceptions.AucunCommercialException;
import miage.m2.sharedmenuis.exceptions.CommercialInconnuException;

/**
 * Interface de l'EJB qui stocke les informations des commerciaux
 * @author QuentinDouris
 */
@Local
public interface CommercialBeanLocal {
    
    public Commercial obtenirCommercial(int idCommercial) throws CommercialInconnuException;
    
    public ArrayList<Commercial> obtenirLesCommerciaux() throws AucunCommercialException;
     
}
