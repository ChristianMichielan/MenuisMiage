/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.metier;

/**
 * Classe qui représente un objet Affaire
 * @author QuentinDouris
 */
public class Affaire {
    
    private int idAffaire;
    private String nomC;
    private String prenomC;
    private String adresseC;
    private String mailC;
    private int telC;
    private String locC;
    private EtatAffaire etat;
    private ChargerAffaire chargerAffaire;

    /**
     * Constructeur d'une affaire
     * @param idAffaire
     * @param nomC
     * @param prenomC
     * @param adresseC
     * @param mailC
     * @param telC
     * @param locC
     * @param chargerAffaire
     */
    public Affaire(int idAffaire, String nomC, String prenomC, String adresseC, String mailC, int telC, String locC, ChargerAffaire chargerAffaire) {
        this.idAffaire = idAffaire;
        this.nomC = nomC;
        this.prenomC = prenomC;
        this.adresseC = adresseC;
        this.mailC = mailC;
        this.telC = telC;
        this.locC = locC;
        this.etat = EtatAffaire.ATTENTE_RDV_COMMERCIAL;
        this.chargerAffaire = chargerAffaire;
    }   
}
