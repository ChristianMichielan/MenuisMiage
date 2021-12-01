/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.exceptions;

/**
 * Exception créer une nouvelle commande auprès d'un fournisseur
 * @author QuentinDouris
 */
public class CreerCommandeFournisseurException extends Exception {

    public CreerCommandeFournisseurException() {
        super("Impossible de contacter le fournisseur, la commande n'a pas pu être passée");
    }
    
}
