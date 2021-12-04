/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.messagesproducer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import miage.m2.exceptions.AucunRDVPoseur;
import miage.m2.serviceposeur.metier.RDVPoseurBeanLocal;
import miage.m2.transientobjects.PoseTransient;

/**
 * EJB qui notifi les différents services de la validation de la pose pour une affaire à la suite d'un rendez-vous poseur
 * @author QuentinDouris
 */
@Stateless
public class PoseValideeMessageBean implements PoseValideeMessageBeanLocal {

    @EJB
    private RDVPoseurBeanLocal rdvPoseurBean;

    @Resource(mappedName = "PoseValidee")
    private Queue poseValidee;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory TPEAIConnectionFactory;

    /**
     * Enregistre la validation de la pose dans le système et notifi les services de cette validation
     * @param idAffaire
     * @return 
     * @throws miage.m2.exceptions.AucunRDVPoseur 
     */
    @Override
    public PoseTransient poseValidee(int idAffaire) throws AucunRDVPoseur {
        try {
            // Enregistre la validation dans le système
            this.rdvPoseurBean.validerPose(idAffaire);
            
            // Construction d'un transient object pour notifier le système
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            PoseTransient poseJms = new PoseTransient(idAffaire, format.format(date));
            
            // Notification du système
            this.sendJMSMessageToPoseValidee(poseJms);
            System.out.println(" *** Service Poseur - PoseValideeMessageBean : message déposé dans la queue PoseValidee");
            
            // Retourne l'objet envoyé dans le JMS            
            return poseJms;
        } catch (JMSException ex) {
            Logger.getLogger(PoseValideeMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new AucunRDVPoseur();
    }
    
    /**
     * Créer un message JMS de la pose validée
     * @param session
     * @param messageData
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageForposeValidee(Session session, Object messageData) throws JMSException {
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        return om;
    }

    /**
     * Envoie le message JSM de la pose validée vers la queue définie
     * @param messageData
     * @throws JMSException 
     */
    private void sendJMSMessageToPoseValidee(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = TPEAIConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(poseValidee);
            messageProducer.send(createJMSMessageForposeValidee(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
