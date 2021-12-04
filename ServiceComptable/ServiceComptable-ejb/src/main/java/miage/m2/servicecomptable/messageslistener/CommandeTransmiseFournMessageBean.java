/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecomptable.messageslistener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import miage.m2.servicecomptable.metier.EncaissementBeanLocal;
import miage.m2.sharedcomptable.exceptions.EncaissementException;

/**
 * EJB qui écoute les messages déposés dans le topic CommandeTransmiseFourn
 * @author QuentinDouris
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "CommandeTransmiseFournComptable")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "CommandeTransmiseFourn")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "CommandeTransmiseFourn")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class CommandeTransmiseFournMessageBean implements MessageListener {

    @EJB
    private EncaissementBeanLocal encaissementBean;
    
    /**
     * Constructeur
     */
    public CommandeTransmiseFournMessageBean() {
    }
    
    /**
     * Ecoute le message sur le topic
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                // Lire le message reçu
                int idAffaireMessage = Integer.parseInt(((TextMessage) message).getText());
                
                System.out.println(" *** Message recu dans ServiceComptable (CommandeTransmiseFournisseur) : " + idAffaireMessage);
                
                // Enregistrer le premier encaissement pour l'affaire
                this.encaissementBean.encaisserPremierCheque(idAffaireMessage);                
            } catch (JMSException | EncaissementException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeTransmiseFournMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
