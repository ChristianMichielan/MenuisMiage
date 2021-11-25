/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.messages;

import javax.ejb.Local;
import javax.jms.JMSException;
import miage.m2.entities.Commercial;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;

/**
 * Interface de l'EJB qui notifi les différents services de la saisie de la commande
 * @author ChristianMichielan
 */
@Local
public interface CommandeMessageBeanLocal {
    public void saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, Commercial commercial) throws JMSException, SaisirCommandeException;
}
