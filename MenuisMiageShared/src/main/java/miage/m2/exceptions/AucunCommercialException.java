/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception auncun commercial enregistré dans le système
 * @author QuentinDouris
 */
public class AucunCommercialException extends Exception {

    public AucunCommercialException() {
        super("Aucun commercial enregistré dans le système.");
    }
    
}
