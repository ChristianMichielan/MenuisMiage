/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.messages;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import miage.m2.entities.Commande;
import miage.m2.entities.Commercial;

/**
 *
 * @author ChristianMichielan
 */
@Stateless
public class CommandeBean implements CommandeBeanLocal {

    @Resource(mappedName = "CommandeSaisie")
    private Topic commandeSaisie;

    @Resource(mappedName = "CommandeSaisieFactory")
    private ConnectionFactory commandeSaisieFactory;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private Message createJMSMessageForcommandeSaisie(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        return om;
    }

    private void sendJMSMessageToCommandeSaisie(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = commandeSaisieFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(commandeSaisie);
            messageProducer.send(createJMSMessageForcommandeSaisie(session, messageData));
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

    @Override
    public void saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, Commercial commercial) throws JMSException {
        Commande newCmd = new Commande(refCatCmd, coteLargeurCmd, coteLongueurCmd, montantNegoCmd, idAffaire, commercial);
        this.sendJMSMessageToCommandeSaisie(newCmd);
    }
    
    
    
}
