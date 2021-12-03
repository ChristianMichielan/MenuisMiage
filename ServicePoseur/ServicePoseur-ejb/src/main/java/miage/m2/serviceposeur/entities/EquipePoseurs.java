/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.entities;

/**
 * Classe qui représente une equipePoseurs
 * @author Trung
 */
public class EquipePoseurs {
    private int idEquipe;
    private String nom;
    
    /**
     * Constructeur
     * @param idEquipe
     * @param nom 
     */
    public EquipePoseurs(int idEquipe, String nom) {
        this.idEquipe = idEquipe;
        this.nom = nom;
    }
    
    /**
     * Retourne l'id de l'equipePoseur
     * @return 
     */
    public int getIdEquipe() {
        return idEquipe;
    }
    
    /**
     * Defini l'id de l'equipePoseur
     * @param idEquipe 
     */
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    
    /**
     * Retourne le nom de l'equipe Poseur
     * @return 
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Defini le nome de l'equipe Poseur
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}