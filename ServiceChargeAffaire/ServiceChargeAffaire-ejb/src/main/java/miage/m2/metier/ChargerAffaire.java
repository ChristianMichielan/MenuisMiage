/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

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
}
