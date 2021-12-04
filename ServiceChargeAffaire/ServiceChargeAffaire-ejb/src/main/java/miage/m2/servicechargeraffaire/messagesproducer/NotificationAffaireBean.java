/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.messagesproducer;

import java.io.Serializable;
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
import javax.jms.Session;
import javax.jms.Topic;
import miage.m2.servicechargeraffaire.entities.Affaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.servicechargeraffaire.metier.AffaireBeanLocal;
import miage.m2.transientobjects.AffaireTransient;

/**
 * EJB qui notifi les chargés d'affaire de l'avancement de l'état de leur affaire
 * @author QuentinDouris
 */
@Stateless
public class NotificationAffaireBean implements NotificationAffaireBeanLocal {

    @EJB
    private AffaireBeanLocal affaireBean;

    @Resource(mappedName = "NotificationAffaire")
    private Topic notificationAffaire;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory TPEAIConnectionFactory;
    
    /**
     * Notifi le chargé d'affaire concerné par l'avancement de l'état de l'affaire
     * @param idAffaire 
     */
    @Override
    public void notifierChargerAffaire(int idAffaire) {
        try {
            // Recherche l'affaire ET le charger d'affaire rattaché à l'affaire a notifier dans le système
            Affaire affaire = this.affaireBean.obtenirAffaire(idAffaire);
            String idChargerAffaire = String.valueOf(affaire.getChargerAffaire().getIdChargerAffaire());
            
            // Objet qui sera encapslué dans le message
            AffaireTransient affaireTransient = new AffaireTransient(affaire.getIdAffaire(), affaire.getNomC(), affaire.getEtat().name(), affaire.getLocC());
                
            // Envoie le message dans le topic
            this.sendJMSMessageToNotificationAffaire(affaireTransient, idChargerAffaire);
            System.out.println(" *** Service Charger Affaire - NotificationAffaireBean : message déposé dans le topic NotificationAffaire");     
        } catch (AffaireInconnueException | JMSException ex) {
            System.out.println("Erreur : " + ex.getMessage());
            Logger.getLogger(NotificationAffaireBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Créer un message JMS de l'affaire dont l'état a changé pour notifier le charger d'affaire en charge de l'affaire de l'avancement
     * @param session
     * @param messageData
     * @param jmsType
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageFornotificationAffaire(Session session, Object messageData, String jmsType) throws JMSException {
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        om.setJMSType(jmsType);
        return om;
    }

    /**
     * Envoie le message JSM de l'affaire concernée dans le topic dédié
     * @param messageData
     * @param jmsType
     * @throws JMSException 
     */
    private void sendJMSMessageToNotificationAffaire(Object messageData, String jmsType) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = TPEAIConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(notificationAffaire);
            messageProducer.send(createJMSMessageFornotificationAffaire(session, messageData, jmsType));
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
