/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.messagesproducer;

import javax.ejb.Local;

/**
 * Interface de l'EJB qui notifi les différents services du passage de la commande auprès du fournisseur pour une affaire
 * @author QuentinDouris
 */
@Local
public interface CommandeTransmiseFournBeanLocal {
    public void commandePasseeFournisseur(int idAffaire); 
}
