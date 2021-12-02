/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.messagesproducer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * EJB qui notifi les différents services du passage de la commande auprès du fournisseur pour une affaire
 * @author QuentinDouris
 */
@Stateless
public class CommandeTransmiseFournBean implements CommandeTransmiseFournBeanLocal {
    @Resource(mappedName = "CommandeTransmiseFourn")
    private Topic commandeTransmiseFourn;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory tPEAIConnectionFactory;

    /**
     * Créer un message JMS de la commande saisie auprès du fournisseur pour une affaire
     * @param session
     * @param messageData
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageForcommandeTransmiseFourn(Session session, Object messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    /**
     * Envoie le message JSM de la commande saisie auprès du fournisseur pour une affaire vers le topic défini
     * @param messageData
     * @throws JMSException 
     */
    private void sendJMSMessageToCommandeTransmiseFourn(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = tPEAIConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(commandeTransmiseFourn);
            messageProducer.send(createJMSMessageForcommandeTransmiseFourn(session, messageData));
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
    
    
     /**
     * Notifi les différents services du passage de la commande de l'affaire auprès des fournisseurs
     * @param idAffaire 
     */
    @Override
    public void commandePasseeFournisseur(int idAffaire) {
        try {
            this.sendJMSMessageToCommandeTransmiseFourn(idAffaire);
            System.out.println(" *** Service Achat - CommandeTransmiseFourBean : message déposé dans le topic CommandeTransmiseFourn");
        } catch (JMSException ex) {
            Logger.getLogger(CommandeTransmiseFournBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
