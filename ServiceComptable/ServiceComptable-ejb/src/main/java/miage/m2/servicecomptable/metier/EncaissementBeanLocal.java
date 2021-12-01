/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecomptable.metier;

import javax.ejb.Local;
import miage.m2.sharedcomptable.exceptions.EncaissementException;

/**
 * Interface de l'EJB qui stocke les informations des encaissements
 * @author QuentinDouris
 */
@Local
public interface EncaissementBeanLocal {
    
    public void encaisserPremierCheque(int idAffaire) throws EncaissementException;
    
    public void encaisserDeuxiemeCheque(int idAffaire) throws EncaissementException;
    
}
