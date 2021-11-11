/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.entities;

/**
 * Classe qui représente un objet Charger Affaire
 * @author QuentinDouris
 */
public class ChargerAffaire {
    private int idChargerAffaire;
    private String nom;
    private String prenom;

    /**
     * Constructeur d'un objet charger affaire
     * @param idChargerAffaire
     * @param nom
     * @param prenom 
     */
    public ChargerAffaire(int idChargerAffaire, String nom, String prenom) {
        this.idChargerAffaire = idChargerAffaire;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne l'identifiant du charger d'affaire
     * @return 
     */
    public int getIdChargerAffaire() {
        return idChargerAffaire;
    }

    /**
     * Défni l'identifiant du charger d'affaire
     * @param idChargerAffaire 
     */
    public void setIdChargerAffaire(int idChargerAffaire) {
        this.idChargerAffaire = idChargerAffaire;
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
     * Défini le prénom du charger d'affaire
     * @param prenom 
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
}
