/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.transientobjects;

/**
 * Transient Object qui permet de retourner les informations conernant une proposition de rdv Poseur à un chargé d'affaire
 * @author QuentinDouris
 */
public class PropositionRDVPoseurTransient {
    private int idEquipePoseur;
    private String date;

    /**
     * Constructeur
     * @param idEquipePoseur
     * @param date 
     */
    public PropositionRDVPoseurTransient(int idEquipePoseur, String date) {
        this.idEquipePoseur = idEquipePoseur;
        this.date = date;
    }

    /**
     * Retourne l'identifiant de l'équipe poseur concernée par la proposion de rdv
     * @return 
     */
    public int getIdEquipePoseur() {
        return idEquipePoseur;
    }

    /**
     * Défini l'identifiant de l'équipe poseur concernée par la proposion de rdv
     * @param idEquipePoseur 
     */
    public void setIdEquipePoseur(int idEquipePoseur) {
        this.idEquipePoseur = idEquipePoseur;
    }

    /**
     * Retourne la date de la proposion du rdv
     * @return 
     */
    public String getDate() {
        return date;
    }

    /**
     * Défini la date de la proposion du rdv
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }
 
}
