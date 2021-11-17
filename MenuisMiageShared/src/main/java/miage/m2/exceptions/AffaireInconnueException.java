/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception affaire inconnu dans le système
 * @author QuentinDouris
 */
public class AffaireInconnueException extends Exception {

    public AffaireInconnueException() {
        super("Affaire inconnu dans le système.");
    }
}
