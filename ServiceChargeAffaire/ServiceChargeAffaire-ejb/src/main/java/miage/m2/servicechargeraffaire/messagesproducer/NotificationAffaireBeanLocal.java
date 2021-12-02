/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.messagesproducer;

import javax.ejb.Local;

/**
 * Interface de l'EJB qui notifi les chargés d'affaire de l'avancement de l'état de leur affaire
 * @author QuentinDouris
 */
@Local
public interface NotificationAffaireBeanLocal {
    public void notifierChargerAffaire(int idAffaire);
}
