/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.messageslistener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import miage.m2.servicechargeraffaire.entities.EtatAffaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.servicechargeraffaire.metier.AffaireBeanLocal;
import miage.m2.servicechargeraffaire.messagesproducer.NotificationAffaireBeanLocal;
import miage.m2.transientobjects.PoseTransient;

/**
 * EJB qui écoute les messages déposés dans la queue PoseValidee
 * @author QuentinDouris
 */
@MessageDriven(mappedName = "PoseValidee", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PoseValideeMessageBean implements MessageListener {

    @EJB
    private NotificationAffaireBeanLocal notificationAffaireBean;

    @EJB
    private AffaireBeanLocal affaireBean;
    
    /**
     * Constructeur
     */
    public PoseValideeMessageBean() {
    }
    
    /**
     * Receptionne les messages déposés dans la queue
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                // Lire le message reçu
                PoseTransient object = (PoseTransient) ((ObjectMessage) message).getObject();
                System.out.println(" *** Message recu dans ServiceChargerAffaire (PoseValidee) : " + object.getIdAffaire());
                this.affaireBean.modifierEtatAffaire(object.getIdAffaire(), EtatAffaire.POSE_VALIDEE);
                
                // Notifi le charger affaire concerné par l'évolution de l'état de l'affaire
                this.notificationAffaireBean.notifierChargerAffaire(object.getIdAffaire());
            } catch (JMSException | AffaireInconnueException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeSaisieMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
