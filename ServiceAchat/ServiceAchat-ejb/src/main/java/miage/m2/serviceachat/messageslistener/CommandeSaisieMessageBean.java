/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.messageslistener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import miage.m2.serviceachat.messagesproducer.CommandeTransmiseFournBeanLocal;
import miage.m2.serviceachat.metier.CommandeFournisseurBeanLocal;
import miage.m2.sharedachat.exceptions.CreerCommandeFournisseurException;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * EJB qui écoute les messages déposés dans le topic CommandeSaisie
 * @author QuentinDouris
 */
@MessageDriven(mappedName = "CommandeSaisie", activationConfig = {
    @ActivationConfigProperty(propertyName = "clientIdServiceAchat", propertyValue = "CommandeSaisie")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "CommandeSaisie")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class CommandeSaisieMessageBean implements MessageListener {

    @EJB
    private CommandeFournisseurBeanLocal commandeFournisseurBean;

    @EJB
    private CommandeTransmiseFournBeanLocal commandeTransmiseFournBean;
    
    /**
     * Constructeur
     */
    public CommandeSaisieMessageBean() {
    }
    
    /**
     * Réceptionne les messages reçus dans le topic
     * On passe automatique la commande auprès du fournisseur (appel API simulé)
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                // Lire le message reçu
                CommandeTransient object = (CommandeTransient) ((ObjectMessage) message).getObject();
                System.out.println(" *** Message recu dans ServiceAchat (CommandeSaisi) : " + object.getIdAffaire());
                System.out.println(" *** [ServiceAchat - Simulation appel API Fournisseur] La commande a été passé auprès du fournisseur avec ces informations :");
                System.out.println("\t\t refCatCmd : " + object.getRefCatCmd());
                System.out.println("\t\t CoteLargeurCmd : " + object.getCoteLargeurCmd());
                System.out.println("\t\t CoteLongueurCmd : " + object.getCoteLongueurCmd());

                // Passe la commande auprès du fournisseur et l'enregsitre
                this.commandeFournisseurBean.creerCommandeFournisseur(object.getIdAffaire());
                
                // Notifi le ServiceChargerAffaire et ServiceComptable que la commande a été passé auprès du fournisseur
                this.commandeTransmiseFournBean.commandePasseeFournisseur(object.getIdAffaire());
            } catch (JMSException | CreerCommandeFournisseurException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeSaisieMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
