/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.entities;

/**
 * Classe qui représente un objet RDV Commercial
 * @author QuentinDouris
 */
public class RDVCommercial {
    private String dateRdvCom;
    private int idAffaire;
    private Commercial commecial;
    private String locC;

    /**
     * Constructeur
     * @param dateRdvCom
     * @param idAffaire
     * @param commecial
     * @param locC 
     */
    public RDVCommercial(String dateRdvCom, int idAffaire, Commercial commecial, String locC) {
        this.dateRdvCom = dateRdvCom;
        this.idAffaire = idAffaire;
        this.commecial = commecial;
        this.locC = locC;
    }
    
    /**
     * Retourne la date du rendez-vous
     * @return 
     */
    public String getDateRdvCom() {
        return dateRdvCom;
    }

    /**
     * Défini la date du rendez-vous
     * @param dateRdvCom 
     */
    public void setDateRdvCom(String dateRdvCom) {
        this.dateRdvCom = dateRdvCom;
    }

    /**
     * Retourne l'identifiant de l'affaire du rendez-vous
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire du rendez-vous
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne le commercial chargé du rendez-vous
     * @return 
     */
    public Commercial getCommecial() {
        return commecial;
    }

    /**
     * Défini le commercial chargé du rendez-vous
     * @param commecial 
     */
    public void setCommecial(Commercial commecial) {
        this.commecial = commecial;
    }

    /**
     * Retourne la localisation du rendez-vous
     * @return 
     */
    public String getLocC() {
        return locC;
    }

    /**
     * Défini la localisation du rendez-vous
     * @param locC 
     */
    public void setLocC(String locC) {
        this.locC = locC;
    }
    
}
