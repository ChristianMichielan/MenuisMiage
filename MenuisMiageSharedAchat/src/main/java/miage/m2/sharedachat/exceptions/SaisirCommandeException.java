/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.exceptions;

/**
 * Exception Saisie d'une nouvelle commande pour une affaire
 * @author QuentinDouris
 */
public class SaisirCommandeException extends Exception {

    public SaisirCommandeException() {
        super("Saisie de la commande impossible, l'affaire à déjà une commande d'enregistrée.");
    }
    
}
