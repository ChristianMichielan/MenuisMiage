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
import javax.jms.ObjectMessage;
import miage.m2.entities.EtatAffaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.metier.AffaireBeanLocal;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * EJB qui écoute les messages déposés dans le topic CommandeSaisie
 * @author QuentinDouris
 */
@MessageDriven(mappedName = "CommandeSaisie", activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "CommandeSaisie")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "CommandeSaisie")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class CommandeSaisieMessageBean implements MessageListener {

    @EJB
    private AffaireBeanLocal affaireBean;
    
    /**
     * Constructeur
     */
    public CommandeSaisieMessageBean() {
    }
    
    /**
     * Réceptionne les messages reçus dans le topic
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                // Lire le message reçu
                CommandeTransient object = (CommandeTransient) ((ObjectMessage) message).getObject();
                System.out.println("Message recu dans ServiceChargerAffaire (CommandeSaisi) : " + object.getIdAffaire());
                this.affaireBean.modifierEtatAffaire(object.getIdAffaire(), EtatAffaire.COMMANDE_SAISIE);
            } catch (JMSException | AffaireInconnueException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeSaisieMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
