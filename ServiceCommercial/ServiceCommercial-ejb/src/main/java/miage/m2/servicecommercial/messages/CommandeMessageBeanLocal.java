/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.messages;

import javax.ejb.Local;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * Interface de l'EJB qui notifi les différents services de la saisie de la commande
 * @author ChristianMichielan
 */
@Local
public interface CommandeMessageBeanLocal {
    public CommandeTransient saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, int idCommercial) throws SaisirCommandeException, CommercialInconnuException;
}
