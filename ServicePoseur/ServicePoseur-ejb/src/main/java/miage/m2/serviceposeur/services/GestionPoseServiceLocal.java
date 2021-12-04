/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import javax.ejb.Local;
import miage.m2.exceptions.AucunRDVPoseur;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON pour la gestion des poses
 * @author QuentinDouris
 */
@Local
public interface GestionPoseServiceLocal {
    
    /**
     * Valide une pose réalisée par une équipe poseur à la suite d'un rendez-vous poseur
     * @param idAffaire
     * @return
     * @throws AucunRDVPoseur 
     */
    public String validerPose(int idAffaire) throws AucunRDVPoseur;

}
