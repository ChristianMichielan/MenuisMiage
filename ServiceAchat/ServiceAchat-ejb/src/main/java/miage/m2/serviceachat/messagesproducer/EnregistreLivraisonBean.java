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
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * EJB qui notifi l'enregistrement de la livraison d'une commande pour une affaire dans le système
 * @author QuentinDouris
 */
@Stateless
public class EnregistreLivraisonBean implements EnregistreLivraisonBeanLocal {

    @Resource(mappedName = "EnregistreLivraison")
    private Queue enregistreLivraison;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory TPEAIConnectionFactory;

    /**
     * Créer un message JMS de la commande fournisseur receptionnée
     * @param session
     * @param messageData
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageForenregistreLivraison(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    /**
     * Envoie le message JSM de la commande fournisseur receptionnée vers la queue défini
     * @param messageData
     * @throws JMSException 
     */
    private void sendJMSMessageToEnregistreLivraison(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = TPEAIConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(enregistreLivraison);
            messageProducer.send(createJMSMessageForenregistreLivraison(session, messageData));
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
     * Notifi les différents services de la réception de la commande fournisseur pour une affaire
     * @param idAffaire 
     */
    @Override
    public void commandeFournisseurReceptionnee(int idAffaire) {
        try {
            this.sendJMSMessageToEnregistreLivraison(idAffaire);
            System.out.println(" *** Service Achat - EnregistreLivraisonBean : message déposé dans la queue EnregistreLivraison");
        } catch (JMSException ex) {
            Logger.getLogger(CommandeTransmiseFournBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
