/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.transientobjects;

import java.io.Serializable;

/**
 * Transient object pose validée
 * @author QuentinDouris
 */
public class PoseTransient implements Serializable {
    private int idAffaire;
    private String dateValidation;

    /**
     * Cosntructeur
     * @param idAffaire 
     * @param dateValidation 
     */
    public PoseTransient(int idAffaire, String dateValidation) {
        this.idAffaire = idAffaire;
        this.dateValidation = dateValidation;
    }

    /**
     * Retourne l'identifiant de l'affaire concernée par la validation de la pose
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire concernée par la validation de la pose
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne la date à laquelle la pose a été enregistrée
     * @return 
     */
    public String getDateValidation() {
        return dateValidation;
    }

    /**
     * Défini la date à laquelle la pose a été enregistrée
     * @param dateValidation 
     */
    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }
    
}
