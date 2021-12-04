/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.transientobjects;

import java.io.Serializable;

/**
 * Transient Object Charger Affaire
 * @author QuentinDouris
 */
public class ChargerAffaireTransient implements Serializable {
    private int id;
    private String nom;
    private String prenom;

    /**
     * Constructeur
     * @param id
     * @param nom
     * @param prenom
     */
    public ChargerAffaireTransient(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne le nom du charger d'affaire
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Défini le nom du charger d'affaire
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du charger d'affaire
     * @return 
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Défini le nom du charger d'affaire
     * @param prenom 
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne l'id du charger d'affaire
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Défini l'id du charger d'affaire
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
