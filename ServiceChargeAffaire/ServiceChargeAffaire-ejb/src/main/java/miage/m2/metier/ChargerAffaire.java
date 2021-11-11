/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
