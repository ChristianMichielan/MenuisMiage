/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.messagesproducer;

import javax.ejb.Local;
import miage.m2.sharedmenuis.exceptions.AffaireInconnueException;

/**
 * Interface de l'EJB qui notifi la cloture d'une affaire dans la queue Encaissement
 * @author QuentinDouris
 */
@Local
public interface EncaissementMessageBeanLocal {
    
    /**
     * Enregistre la cloture d'une affaire et notifi le système pour enregistrer l'encaissement
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    public void cloturerAffairePourEncaissement(int idAffaire) throws AffaireInconnueException;
    
}
