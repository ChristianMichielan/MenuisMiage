/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.exceptions;

/**
 * Exception qui concerne la reception d'une commande fournisseur dans le système
 * @author QuentinDouris
 */
public class ReceptionCommandeInconnuException extends Exception {

    public ReceptionCommandeInconnuException() {
        super("La commande fournisseur réceptionnée est inconnu dans le système");
    }
    
}
