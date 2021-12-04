/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.messagesproducer;

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
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import miage.m2.entities.EtatAffaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.metier.AffaireBeanLocal;

/**
 * EJB qui notifi la cloture d'une affaire dans la queue Encaissement
 * @author QuentinDouris
 */
@Stateless
public class EncaissementMessageBean implements EncaissementMessageBeanLocal {

    @Resource(mappedName = "Encaissement")
    private Queue encaissement;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory TPEAIConnectionFactory;

    @EJB
    private AffaireBeanLocal affaireBean;

    /**
     * Enregistre la cloture d'une affaire et notifi le système pour enregistrer l'encaissement
     * @param idAffaire
     * @throws AffaireInconnueException 
     */
    @Override
    public void cloturerAffairePourEncaissement(int idAffaire) throws AffaireInconnueException {
        try {
            this.affaireBean.modifierEtatAffaire(idAffaire, EtatAffaire.CLOTUREE);
            this.sendJMSMessageToEncaissement(idAffaire);
            System.out.println(" *** Service Charger Affaire - EncaissementMessageBean : message déposé dans la queue Encaissement");
        } catch (JMSException ex) {
            Logger.getLogger(EncaissementMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Créer un message JMS de l'affaire dont l'état est passé à CLOTURER pour notifier le ServiceComptable de l'encaissement
     * @param session
     * @param messageData
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageForencaissement(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    /**
     * Envoie le message JSM de l'affaire concernée dans la queue dédiée
     * @param messageData
     * @throws JMSException 
     */
    private void sendJMSMessageToEncaissement(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = TPEAIConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(encaissement);
            messageProducer.send(createJMSMessageForencaissement(session, messageData));
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
