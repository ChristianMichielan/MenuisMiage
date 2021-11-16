/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.transientobjects;

import java.io.Serializable;

/**
 * Transient Object qui permet de retourner les informations conernant une proposition de rdv Commercial à un chargé d'affaire
 * @author QuentinDouris
 */
public class PropositionRDVCommercialTransient implements Serializable {
    private int idCommercial;
    private String date;

    /**
     * Constructeur
     * @param idCommercial
     * @param date 
     */
    public PropositionRDVCommercialTransient(int idCommercial, String date) {
        this.idCommercial = idCommercial;
        this.date = date;
    }

    /**
     * Retourne l'id du commercial de la proposition de rdv
     * @return 
     */
    public int getIdCommercial() {
        return idCommercial;
    }

    /**
     * Défini l'id du commercial de la proposition de rdv
     * @param idCommercial 
     */
    public void setIdCommercial(int idCommercial) {
        this.idCommercial = idCommercial;
    }

    /**
     * Retourne la date de la proposition de rdv
     * @return 
     */
    public String getDate() {
        return date;
    }

    /**
     * Défini la date de la proposition de rdv
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
