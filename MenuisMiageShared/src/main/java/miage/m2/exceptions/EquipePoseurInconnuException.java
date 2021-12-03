/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Exception pour une Equipe Poseur inconnu
 * @author Trung
 */
public class EquipePoseurInconnuException extends Exception {
    
    public EquipePoseurInconnuException(){
        super("Equipe Poseur inconnu dans le système.");
    }  
}