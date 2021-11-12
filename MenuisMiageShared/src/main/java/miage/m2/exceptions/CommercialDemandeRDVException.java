/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour la demande de rendez-vous commercial
 * @author QuentinDouris
 */
public class CommercialDemandeRDVException extends Exception {

    public CommercialDemandeRDVException() {
        super("Aucun rendez-vous commercial n'est disponible pour la date saisie.");
    }
    
}
