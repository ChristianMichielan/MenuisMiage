/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.exceptions;

/**
 * Exception qui indique qu'il n'y a pas de rendez-vous poseur assigné à l'affaire
 * @author QuentinDouris
 */
public class AucunRDVPoseur extends Exception {

    public AucunRDVPoseur() {
        super("Aucun rendez-vous poseur enregistré pour cette affaire");
    }
    
}
