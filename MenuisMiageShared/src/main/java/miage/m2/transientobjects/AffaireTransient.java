/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.transientobjects;

import java.io.Serializable;

/**
 * Transient Object qui permet de retourner certaines informations d'une affaire
 * @author QuentinDouris
 */
public class AffaireTransient implements Serializable {
    private int idAffaire;
    private String nomC;
    private String etat;

    /**
     * Constructeur
     * @param idAffaire
     * @param nomC
     * @param etat 
     */
    public AffaireTransient(int idAffaire, String nomC, String etat) {
        this.idAffaire = idAffaire;
        this.nomC = nomC;
        this.etat = etat;
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
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "AffaireTransient{" + "idAffaire=" + idAffaire + ", nomC=" + nomC + ", etat=" + etat + '}';
    }
    
}
