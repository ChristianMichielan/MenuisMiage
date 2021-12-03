/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour une équipe poseur incconu
 * @author Trung
 */
public class AucuneEquipePoseurException extends Exception {

    public AucuneEquipePoseurException() {
        super("Aucune equipe Poseur enregistrée dans le système.");
    }
}
