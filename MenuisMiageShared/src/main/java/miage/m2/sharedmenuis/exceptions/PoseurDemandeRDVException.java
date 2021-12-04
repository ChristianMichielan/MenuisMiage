/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.exceptions;

/**
 * Exception pour la demande du rendez-vous poseur
 * @author QuentinDouris
 */
public class PoseurDemandeRDVException extends Exception {
    public PoseurDemandeRDVException() {
        super("Aucun rendez-vous poseur n'est disponible pour la date saisie.");
    }
}
