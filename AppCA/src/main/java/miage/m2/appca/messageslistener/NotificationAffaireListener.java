/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appca.messageslistener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import miage.m2.transientobjects.AffaireTransient;

/**
 * Objet qui va écouter les messages reçu dans le topic NotificationAffaire
 * @author QuentinDouris
 */
public class NotificationAffaireListener implements MessageListener {

    private ArrayList<AffaireTransient> listeAffaireNotification;

    /**
     * Constructeur
     */
    public NotificationAffaireListener() {
        this.listeAffaireNotification = new ArrayList<>();
    }
    
    /**
     * Ecoute les messages reçus dans le topic s'ils sont adressés au Charger d'Affaire authentifier
     * @param msg 
     */
    @Override
    public void onMessage(Message msg) {
        if (msg instanceof ObjectMessage) {
            try {
                // Lire le message reçu
                AffaireTransient object = (AffaireTransient) ((ObjectMessage) msg).getObject();
                this.listeAffaireNotification.add(object);
                System.out.println(" *** Nouvelle notification pour l'affaire : " + object.getIdAffaire() + " - " + object.getNomC() + " ***");
            } catch (JMSException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(NotificationAffaireListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Retourne les affaires pour lesquelles le charger d'affaire authentifier a été notifié de l'avancement
     * @return 
     */
    public ArrayList<AffaireTransient> getListeAffaireNotification() {
        return listeAffaireNotification;
    }
    
    /**
     * Supprime les messages enregistrés dans la liste des notifications
     */
    public void effacerNotifications() {
        this.listeAffaireNotification.clear();
    }
    
}
