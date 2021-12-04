/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.exceptions;

/**
 * Exception pour un chargé d'affaire inconnu
 * @author QuentinDouris
 */
public class ChargerAffaireInconnuException extends Exception {

    public ChargerAffaireInconnuException() {
        super("Le charger d'affaire renseigné est inconnu");
    }
    
}
