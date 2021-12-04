/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedmenuis.transientobjects;

import java.io.Serializable;

/**
 * Transient Object qui permet de retourner les informations conernant le rdv Commercial à un chargé d'affaire
 * @author QuentinDouris
 */
public class RDVCommercialTransient implements Serializable {
    private String date;
    private int idCommercial;
    private String localisation;
    private int idAffaire;
    
    /**
     * Constructeur
     * @param date
     * @param idCommercial
     * @param localisation
     * @param idAffaire 
     */
    public RDVCommercialTransient(String date, int idCommercial, String localisation, int idAffaire) {
        this.date = date;
        this.idCommercial = idCommercial;
        this.localisation = localisation;
        this.idAffaire = idAffaire;
    }
    
    /**
     * Retourne la date du rendez-vous commercial
     * @return 
     */
    public String getDate() {
        return date;
    }

    /**
     * Défini la date du rendez-vous commercial
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retourne l'identifiant du commercial qui va réaliser le rendez-vous
     * @return 
     */
    public int getIdCommercial() {
        return idCommercial;
    }

    /**
     * Défini l'identifiant du commercial qui va réaliser le rendez-vous
     * @param idCommercial 
     */
    public void setIdCommercial(int idCommercial) {
        this.idCommercial = idCommercial;
    }

    /**
     * Retourne la localisation d'un rendez-vous
     * @return 
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Défini la localisation d'un rendez-vous
     * @param localisation 
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    /**
     * Retourne l'identifiant de l'affaire
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }
    
    /**
     * Défini l'identifiant de l'affaire
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }
    
}
