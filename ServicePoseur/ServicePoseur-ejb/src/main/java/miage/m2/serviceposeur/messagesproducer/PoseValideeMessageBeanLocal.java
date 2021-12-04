/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.messagesproducer;

import javax.ejb.Local;
import miage.m2.sharedmenuis.exceptions.AucunRDVPoseur;
import miage.m2.sharedmenuis.transientobjects.PoseTransient;

/**
 * Interface de l'EJB qui notifi les différents services de la validation de la pose pour une affaire à la suite d'un rendez-vous poseur
 * @author QuentinDouris
 */
@Local
public interface PoseValideeMessageBeanLocal {
    public PoseTransient poseValidee(int idAffaire) throws AucunRDVPoseur;
}
