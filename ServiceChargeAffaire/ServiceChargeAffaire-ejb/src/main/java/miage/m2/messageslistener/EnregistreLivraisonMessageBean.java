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
import miage.m2.servicechargeraffaire.messagesproducer.NotificationAffaireBeanLocal;

/**
 * EJB qui écoute les messages déposés dans la queue EnregistreLivraison
 * @author QuentinDouris
 */
@MessageDriven(mappedName = "EnregistreLivraison", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EnregistreLivraisonMessageBean implements MessageListener {

    @EJB
    private NotificationAffaireBeanLocal notificationAffaireBean;

    @EJB
    private AffaireBeanLocal affaireBean;
    
    /**
     * Constructeur
     */    
    public EnregistreLivraisonMessageBean() {
    }
    
    /**
     * Receptionne les messages déposés dans la queue
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                // Lire le message reçu
                int idAffaireMessage = Integer.parseInt(((TextMessage) message).getText());
                this.affaireBean.modifierEtatAffaire(idAffaireMessage, EtatAffaire.ATTENTE_RDV_POSEUR);
                System.out.println(" *** Message recu dans ServiceChargerAffaire (EnregistreLivraison) : " + idAffaireMessage);
                
                // Notifi le charger d'affaire en charge de l'affaire du changement d'état
                this.notificationAffaireBean.notifierChargerAffaire(idAffaireMessage);
            } catch (JMSException | AffaireInconnueException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeTransmiseFournMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
