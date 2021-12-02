/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.messageslistener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import miage.m2.entities.EtatAffaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.metier.AffaireBeanLocal;

/**
 * EJB qui écoute les messages déposés dans le topic CommandeTransmiseFourn
 * @author QuentinDouris
 */
@MessageDriven(mappedName = "CommandeTransmiseFourn", activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "CommandeTransmiseFourn")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "CommandeTransmiseFourn")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class CommandeTransmiseFournMessageBean implements MessageListener {

    @EJB
    private AffaireBeanLocal affaireBean;
    
    /**
     * Constructeur
     */
    public CommandeTransmiseFournMessageBean() {
    }
    
    /**
     * Réceptionne les messages reçus dans le topic
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                // Lire le message reçu
                int idAffaireMessage = Integer.parseInt(((TextMessage) message).getText());
                this.affaireBean.modifierEtatAffaire(idAffaireMessage, EtatAffaire.COMMANDE_ENVOYEE_FOURNISSEUR);
                System.out.println(" *** Message recu dans ServiceChargerAffaire (CommandeTransmiseFournisseur) : " + idAffaireMessage);
            } catch (JMSException | AffaireInconnueException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeTransmiseFournMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
