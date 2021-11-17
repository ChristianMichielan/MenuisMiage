/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.entities;

/**
 * Classe qui représente un objet Affaire
 * @author QuentinDouris
 */
public class Affaire {
    
    private int idAffaire;
    private String nomC;
    private String prenomC;
    private String adresseC;
    private String mailC;
    private String telC;
    private String locC;
    private EtatAffaire etat;
    private ChargerAffaire chargerAffaire;

    /**
     * Constructeur d'une affaire
     * @param idAffaire
     * @param nomC
     * @param prenomC
     * @param adresseC
     * @param mailC
     * @param telC
     * @param locC
     * @param chargerAffaire
     */
    public Affaire(int idAffaire, String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, ChargerAffaire chargerAffaire) {
        this.idAffaire = idAffaire;
        this.nomC = nomC;
        this.prenomC = prenomC;
        this.adresseC = adresseC;
        this.mailC = mailC;
        this.telC = telC;
        this.locC = locC;
        this.etat = EtatAffaire.RDV_COMMERCIAL_NON_SAISIE;
        this.chargerAffaire = chargerAffaire;
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

    /**
     * Retourne le nom du client de l'affaire
     * @return 
     */
    public String getNomC() {
        return nomC;
    }

    /**
     * Défini le nom du client de l'affaire
     * @param nomC 
     */
    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    /**
     * Retourne le prénom du client de l'affaire
     * @return 
     */
    public String getPrenomC() {
        return prenomC;
    }

    /**
     * Défini le prénom du client de l'affaire
     * @param prenomC 
     */
    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }

    /**
     * Retourne l'adresse du client de l'affaire
     * @return 
     */
    public String getAdresseC() {
        return adresseC;
    }

    /**
     * Défini l'adresse du client de l'affaire
     * @param adresseC 
     */
    public void setAdresseC(String adresseC) {
        this.adresseC = adresseC;
    }

    /**
     * Retourne le mail du client de l'affaire
     * @return 
     */
    public String getMailC() {
        return mailC;
    }

    /**
     * Défini le mail du client de l'affaire
     * @param mailC 
     */
    public void setMailC(String mailC) {
        this.mailC = mailC;
    }

    /**
     * Retourne le numéro de téléphone du client de l'affaire
     * @return 
     */
    public String getTelC() {
        return telC;
    }

    /**
     * Défini le numéro de téléphone du client de l'affaire
     * @param telC 
     */
    public void setTelC(String telC) {
        this.telC = telC;
    }

    /**
     * Retourne la localisation (adresse) du lieu ou la pose de l'affaire doit être installé
     * @return 
     */
    public String getLocC() {
        return locC;
    }

    /**
     * Défini la localisation (adresse) du lieu ou la pose de l'affaire doit être installé
     * @param locC 
     */
    public void setLocC(String locC) {
        this.locC = locC;
    }

    /**
     * Retourne l'état de l'affaire
     * @return 
     */
    public EtatAffaire getEtat() {
        return etat;
    }

    /**
     * Défini l'état de l'affaire
     * @param etat 
     */
    public void setEtat(EtatAffaire etat) {
        this.etat = etat;
    }

    /**
     * Retourne le chargé d'affaire de l'affaire
     * @return 
     */
    public ChargerAffaire getChargerAffaire() {
        return chargerAffaire;
    }

    /**
     * Défini le chargé d'affaire de l'affaire
     * @param chargerAffaire 
     */
    public void setChargerAffaire(ChargerAffaire chargerAffaire) {
        this.chargerAffaire = chargerAffaire;
    }
}
