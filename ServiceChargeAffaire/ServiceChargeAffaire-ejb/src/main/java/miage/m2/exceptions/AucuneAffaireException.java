/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception aucune affaire trouvée pour le chargé d'affaire
 * @author QuentinDouris
 */
public class AucuneAffaireException extends Exception {

    public AucuneAffaireException() {
        super("Aucune affaire trouvée pour le chargé d'affaire");
    }
    
}
