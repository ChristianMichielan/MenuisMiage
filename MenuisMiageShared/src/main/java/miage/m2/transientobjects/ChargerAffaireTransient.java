/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.transientobjects;

import java.util.ArrayList;

/**
 * Transient Object Charger Affaire
 * @author QuentinDouris
 */
public class ChargerAffaireTransient {
    private String nom;
    private String prenom;
    private ArrayList<AffaireTransient> listeAffaire;

    /**
     * Constructeur
     * @param nom
     * @param prenom
     * @param listeAffaire 
     */
    public ChargerAffaireTransient(String nom, String prenom, ArrayList<AffaireTransient> listeAffaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeAffaire = listeAffaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<AffaireTransient> getListeAffaire() {
        return listeAffaire;
    }

    public void setListeAffaire(ArrayList<AffaireTransient> listeAffaire) {
        this.listeAffaire = listeAffaire;
    }
    
}
