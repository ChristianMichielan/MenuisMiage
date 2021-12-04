/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.transientobjects;

import java.io.Serializable;

/**
 * Transient Object qui permet de retourner certaines informations d'une affaire
 * @author QuentinDouris
 */
public class AffaireTransient implements Serializable {
    private int idAffaire;
    private String nomC;
    private String etat;
    private String locC;

    /**
     * Constructeur
     * @param idAffaire
     * @param nomC
     * @param etat 
     */
    public AffaireTransient(int idAffaire, String nomC, String etat, String locC) {
        this.idAffaire = idAffaire;
        this.nomC = nomC;
        this.etat = etat;
        this.locC = locC;
    }

    /**
     * Retourne l'identifiant d'une affaire transient
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Retourne le nom du client d'une affaire transient
     * @return 
     */
    public String getNomC() {
        return nomC;
    }

    /**
     * Retourne l'état d'une affaire transient
     * @return 
     */
    public String getEtat() {
        return etat;
    }

    /**
     * Retourne la localisation de l'afffaire
     * @return 
     */
    public String getLocC() {
        return locC;
    }

    /**
     * Défini la localisation de l'affaire
     * @param locC 
     */
    public void setLocC(String locC) {
        this.locC = locC;
    }    
    
    /**
     * Redéfinition de la l'affichage d'une affaire
     * @return 
     */
    @Override
    public String toString() {
        return this.idAffaire + " - " + this.nomC + " | " + " Etat : " + this.etat;
    }
    
}
