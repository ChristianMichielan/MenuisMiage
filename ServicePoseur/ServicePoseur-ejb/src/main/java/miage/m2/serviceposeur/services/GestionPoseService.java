/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.exceptions.AucunRDVPoseur;
import miage.m2.serviceposeur.messagesproducer.PoseValideeMessageBeanLocal;
import miage.m2.transientobjects.PoseTransient;

/**
 * EJB qui gère l'encapsulation JSON pour la gestion des poses
 * @author QuentinDouris
 */
@Stateless
public class GestionPoseService implements GestionPoseServiceLocal {

    @EJB
    private PoseValideeMessageBeanLocal poseValideeMessageBean;

    // Convertisseur JSON
    private Gson gson;

    /**
     * Cosntructeur
     */
    public GestionPoseService() {
        this.gson = new Gson();
    }    
    
    /**
     * Valide une pose réalisée par une équipe poseur à la suite d'un rendez-vous poseur
     * @param idAffaire
     * @return
     * @throws AucunRDVPoseur 
     */
    @Override
    public String validerPose(int idAffaire) throws AucunRDVPoseur {
        PoseTransient pose = this.poseValideeMessageBean.poseValidee(idAffaire);
        return this.gson.toJson(pose);
    }
    
}
