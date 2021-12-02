/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedcomptable.exceptions;

/**
 * Exception Encaissement d'un cheque pour une affaire
 * @author QuentinDouris
 */
public class EncaissementException extends Exception {

    public EncaissementException() {
        super("Encaissement impossible");
    }
    
}
