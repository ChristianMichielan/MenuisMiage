/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.messagesproducer;

import javax.ejb.Local;

/**
 * Interface de l'EJB qui notifi l'enregistrement de la livraison d'une commande pour une affaire dans le système
 * @author QuentinDouris
 */
@Local
public interface EnregistreLivraisonBeanLocal {
    
    public void commandeFournisseurReceptionnee(int idAffaire);
    
}
