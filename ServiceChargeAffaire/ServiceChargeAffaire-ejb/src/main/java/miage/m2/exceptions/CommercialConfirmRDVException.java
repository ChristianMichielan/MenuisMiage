/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour la validation du rendez-vous commercial
 * @author QuentinDouris
 */
public class CommercialConfirmRDVException extends Exception {

    public CommercialConfirmRDVException() {
        super("Impossible d'enregistrer le rendez-vous commercial sélectionné.");
    }
}
