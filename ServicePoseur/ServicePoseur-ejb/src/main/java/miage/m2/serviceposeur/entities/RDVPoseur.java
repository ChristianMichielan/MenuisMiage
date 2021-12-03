/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.entities;

/**
 * Classe qui représente un objet RDV Poseur
 * @author Trung
 */
public class RDVPoseur {
    private String dateRdvPose;
    private int idAffaire;
    private EquipePoseurs equipePoseur;
    private String locC;

    /**
     * Constructeur
     * @param dateRdvPose
     * @param idAffaire
     * @param equipePoseur
     * @param locC 
     */
    public RDVPoseur(String dateRdvPose, int idAffaire, EquipePoseurs equipePoseur, String locC) {
        this.dateRdvPose = dateRdvPose;
        this.idAffaire = idAffaire;
        this.equipePoseur = equipePoseur;
        this.locC = locC;
    }  
    
    /**
     * Retourne date du rdv poseur
     * @return 
     */
    public String getDateRdvPose() {
        return dateRdvPose;
    }
    
    /**
     * Défini une date du rdv poseur
     * @param dateRdvPose 
     */
    public void setDateRdvPose(String dateRdvPose) {
        this.dateRdvPose = dateRdvPose;
    }
    
    /**
     * Retourne l'id de l'affaire
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }
    
    /**
     * Défini l'id de l'affaire
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }
    
    /**
     * Retourne une equipe poseur pour le rdv
     * @return 
     */
    public EquipePoseurs getEquipePoseur() {
        return equipePoseur;
    }
    
    /**
     * Défini l'equipe poseur
     * @param equipePoseur 
     */
    public void setEquipePoseur(EquipePoseurs equipePoseur) {
        this.equipePoseur = equipePoseur;
    }
    
    /**
     * Retourne la localisation du client
     * @return 
     */
    public String getLocC() {
        return locC;
    }
    
    /**
     * Défini la localisation du client
     * @param locC 
     */
    public void setLocC(String locC) {
        this.locC = locC;
    }    
}