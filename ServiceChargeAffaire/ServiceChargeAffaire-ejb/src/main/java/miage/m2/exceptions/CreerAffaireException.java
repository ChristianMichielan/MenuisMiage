/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour la création de l'affaire
 * @author QuentinDouris
 */
public class CreerAffaireException extends Exception {

    public CreerAffaireException() {
        super("Création de l'affaire impossible.");
    }
}
