/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.exceptions;

/**
 * Exception pour la validation du rendez-vous poseur
 * @author QuentinDouris
 */
public class PoseurConfirmRDVException extends Exception {
    public PoseurConfirmRDVException(){
        super("Impossible d'enregistrer le rendez-vous sélectionné.");
    }
}