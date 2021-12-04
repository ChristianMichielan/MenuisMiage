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
 * EJB qui écoute les messages déposés dans la queue Encaissement
 * @author QuentinDouris
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Encaissement")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EncaissementMessageBean implements MessageListener {

    @EJB
    private EncaissementBeanLocal encaissementBean;
    
    /**
     * Constructeur
     */
    public EncaissementMessageBean() {
    }
    
    /**
     * Ecoute les messages sur la queue
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                // Lire le message reçu
                int idAffaireMessage = Integer.parseInt(((TextMessage) message).getText());
                
                System.out.println(" *** Message recu dans ServiceComptable (Encaissement) : " + idAffaireMessage);
                
                // Enregistrer le premier encaissement pour l'affaire
                this.encaissementBean.encaisserDeuxiemeCheque(idAffaireMessage);                
            } catch (JMSException | EncaissementException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(CommandeTransmiseFournMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
