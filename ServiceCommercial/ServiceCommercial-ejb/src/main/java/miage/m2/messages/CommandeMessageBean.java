/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.messages;

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
import miage.m2.entities.Commercial;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.metier.CommandeBeanLocal;
import miage.m2.metier.CommercialBeanLocal;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * EJB qui notifi les différents services de la saisie de la commande
 * @author ChristianMichielan
 */
@Stateless
public class CommandeMessageBean implements CommandeMessageBeanLocal {

    @EJB
    private CommercialBeanLocal commercialBean;

    @EJB
    private CommandeBeanLocal commandeBean;
    
    @Resource(mappedName = "CommandeSaisie")
    private Topic commandeSaisie;

    @Resource(mappedName = "TPEAIConnectionFactory")
    private ConnectionFactory TPEAIConnectionFactory;


    /**
     * Créer un message JMS de la commande saisie
     * @param session
     * @param messageData
     * @return
     * @throws JMSException 
     */
    private Message createJMSMessageForcommandeSaisie(Session session, Object messageData) throws JMSException {
        ObjectMessage om = session.createObjectMessage();
        om.setObject((Serializable) messageData);
        return om;
    }

    /**
     * Envoie le message JSM de la commande saisie vers le topic défini
     * @param messageData
     * @throws JMSException 
     */
    private void sendJMSMessageToCommandeSaisie(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = TPEAIConnectionFactory.createConnection();
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

    /**
     * Enregistre la commande de le système est notifi les différents service par l'intermédiaire d'un JMS déposé dans un topic
     * @param refCatCmd
     * @param coteLargeurCmd
     * @param coteLongueurCmd
     * @param montantNegoCmd
     * @param idAffaire
     * @param idCommercial
     * @return
     * @throws SaisirCommandeException 
     * @throws miage.m2.exceptions.CommercialInconnuException 
     */
    @Override
    public CommandeTransient saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, int idCommercial) throws SaisirCommandeException, CommercialInconnuException {
        try {
            // Création de la commande dans le sytème
            Commercial commercial = this.commercialBean.obtenirCommercial(idCommercial);
            this.commandeBean.creerCommande(refCatCmd, coteLargeurCmd, coteLongueurCmd, montantNegoCmd, idAffaire, commercial);
            
            // Notifi les Services abonnés à la file
            CommandeTransient commandeJms = new CommandeTransient(idAffaire, refCatCmd, coteLargeurCmd, coteLongueurCmd);
            this.sendJMSMessageToCommandeSaisie(commandeJms);
            System.out.println(" *** Service Commercial - CommandeMessageBean : message déposé dans le topic CommandeSaisie");
            
            // Retourne l'objet envoyé dans le JMS
            return commandeJms;
        } catch (JMSException ex) {
            Logger.getLogger(CommandeMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // La commande n'a pas pu être saisi correctement
        throw new SaisirCommandeException();
    }
}
