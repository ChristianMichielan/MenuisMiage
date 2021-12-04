/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.entities;

/**
 * Classe qui représente un objet Commercial
 * @author QuentinDouris
 */
public class Commercial {
    private int idCommercial;
    private String nom;
    private String prenom;

    /**
     * Constructeur
     * @param idCommercial
     * @param nom
     * @param prenom 
     */
    public Commercial(int idCommercial, String nom, String prenom) {
        this.idCommercial = idCommercial;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne l'identifiant du commercial
     * @return 
     */
    public int getIdCommercial() {
        return idCommercial;
    }

    /**
     * Défini l'identifiant du commercial
     * @param idCommercial 
     */
    public void setIdCommercial(int idCommercial) {
        this.idCommercial = idCommercial;
    }

    /**
     * Retourne le nom du commercial
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Défini le nom du commercial
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du commercial
     * @return 
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Défini le prénom du commercial
     * @param prenom 
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
