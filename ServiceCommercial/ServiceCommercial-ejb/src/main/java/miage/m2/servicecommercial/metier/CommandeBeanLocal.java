/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.metier;

import javax.ejb.Local;
import miage.m2.servicecommercial.entities.Commercial;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;

/**
 * Interface de l'EJB qui stocke les informations des commandes saisies
 * @author QuentinDouris
 */
@Local
public interface CommandeBeanLocal {
    public void creerCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd,double montantNegoCmd, int idAffaire, Commercial commercial) throws SaisirCommandeException;
}
