/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.transientobjects;

/**
 * Transient Object qui permet de retourner les informations conernant le rdv Commercial à un chargé d'affaire
 * @author QuentinDouris
 */
public class RdvCommercialTransient {
    private String date;
    private int idCommercial;

    /**
     * Constructeur
     * @param date
     * @param idCommercial 
     */
    public RdvCommercialTransient(String date, int idCommercial) {
        this.date = date;
        this.idCommercial = idCommercial;
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
    
}
