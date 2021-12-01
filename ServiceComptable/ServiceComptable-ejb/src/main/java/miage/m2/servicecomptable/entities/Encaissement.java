/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecomptable.entities;

/**
 * Objet qui représente l'encaissement des chéque pour une affaire
 * @author QuentinDouris
 */
public class Encaissement {
    private int idAffaire;
    private String dateEncaissement1;
    private String dateEncaissement2;

    /**
     * Constructeur
     * @param idAffaire
     * @param dateEncaissement1 
     */
    public Encaissement(int idAffaire, String dateEncaissement1) {
        this.idAffaire = idAffaire;
        this.dateEncaissement1 = dateEncaissement1;
        this.dateEncaissement2 = null;
    }

    /**
     * Retourne l'identifiant de l'affaire concerné par l'encaissement
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire concerné par l'encaissement
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne la date de l'encaissement du premier cheque de l'affaire
     * @return 
     */
    public String getDateEncaissement1() {
        return dateEncaissement1;
    }

    /**
     * Défini la date de l'encaissement du premier cheque de l'affaire
     * @param dateEncaissement1 
     */
    public void setDateEncaissement1(String dateEncaissement1) {
        this.dateEncaissement1 = dateEncaissement1;
    }

    /**
     * Retourne la date de l'encaissement du deuxième cheque de l'affaire
     * @return 
     */
    public String getDateEncaissement2() {
        return dateEncaissement2;
    }

    /**
     * Défini la date de l'encaissement du deuxième cheque de l'affaire
     * @param dateEncaissement2 
     */
    public void setDateEncaissement2(String dateEncaissement2) {
        this.dateEncaissement2 = dateEncaissement2;
    }
}
