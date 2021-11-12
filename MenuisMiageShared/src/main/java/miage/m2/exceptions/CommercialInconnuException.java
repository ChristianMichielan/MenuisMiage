/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour un commercial inconnu dans le système
 * @author QuentinDouris
 */
public class CommercialInconnuException extends Exception {

    public CommercialInconnuException() {
        super("Commerical inconnu dans le système.");
    }
    
}
