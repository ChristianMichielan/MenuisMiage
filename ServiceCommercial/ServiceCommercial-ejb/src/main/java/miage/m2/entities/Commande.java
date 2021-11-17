/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.entities;

/**
 * Classe qui représente un objet commande
 * @author QuentinDouris
 */
public class Commande {
    private String refCatCmd;
    private double coteLangeurCmd;
    private double coteLongueurCmd;
    private double montantNegoCmd;
    private int idAffaire;
    private Commercial commercial;

    /**
     * Constructeur
     * @param refCatCmd
     * @param coteLangeurCmd
     * @param coteLongueurCmd
     * @param montantNegoCmd
     * @param idAffaire
     * @param commercial 
     */
    public Commande(String refCatCmd, double coteLangeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, Commercial commercial) {
        this.refCatCmd = refCatCmd;
        this.coteLangeurCmd = coteLangeurCmd;
        this.coteLongueurCmd = coteLongueurCmd;
        this.montantNegoCmd = montantNegoCmd;
        this.idAffaire = idAffaire;
        this.commercial = commercial;
    }

    /**
     * Retourne la référence de la commande
     * @return 
     */
    public String getRefCatCmd() {
        return refCatCmd;
    }

    /**
     * Défini la référence de la commande
     * @param refCatCmd 
     */
    public void setRefCatCmd(String refCatCmd) {
        this.refCatCmd = refCatCmd;
    }

    /**
     * Retourne la mesure de la cote (largeur) de la commande
     * @return 
     */
    public double getCoteLangeurCmd() {
        return coteLangeurCmd;
    }

    /**
     * Défini la mesure de la cote (largeur) de la commande
     * @param coteLangeurCmd 
     */
    public void setCoteLangeurCmd(double coteLangeurCmd) {
        this.coteLangeurCmd = coteLangeurCmd;
    }

    /**
     * Retourne la mesure de la cote (longueur) de la commande
     * @return 
     */
    public double getCoteLongueurCmd() {
        return coteLongueurCmd;
    }

    /**
     * Défini la mesure de la cote (longueur) de la commande
     * @param coteLongueurCmd 
     */
    public void setCoteLongueurCmd(double coteLongueurCmd) {
        this.coteLongueurCmd = coteLongueurCmd;
    }

    /**
     * Retourne le montant négocier par le client de la commande
     * @return 
     */
    public double getMontantNegoCmd() {
        return montantNegoCmd;
    }

    /**
     * Défini le montant négocier par le client de la commande
     * @param montantNegoCmd 
     */
    public void setMontantNegoCmd(double montantNegoCmd) {
        this.montantNegoCmd = montantNegoCmd;
    }

    /**
     * Retourne l'identifiant de l'affaire de la commande
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire de la commande
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne le commercial de la commande
     * @return 
     */
    public Commercial getCommercial() {
        return commercial;
    }

    /**
     * Défini le commercial de la commande
     * @param commercial 
     */
    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }
}
