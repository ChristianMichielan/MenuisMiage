/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.transientobjects;

import java.io.Serializable;

/**
 * Transient Object qui permet de notifier la réception / envoie de la commande
 * @author QuentinDouris
 */
public class CommandeTransient implements Serializable {
    private int idAffaire;
    private String refCatCmd;
    private double coteLargeurCmd;
    private double coteLongueurCmd;

    /**
     * Constructeur
     * @param idAffaire 
     */
    public CommandeTransient(int idAffaire, String refCatCmd, double coteLargeurCmd, double coteLongueurCmd) {    
        this.idAffaire = idAffaire;
        this.refCatCmd = refCatCmd;
        this.coteLargeurCmd = coteLargeurCmd;
        this.coteLongueurCmd = coteLongueurCmd;
    }

    /**
     * Retourne l'identifiant de l'affaire concernée par la commande envoyé
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire concernée par la commande envoyé
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne la référence du catalogue de la commande
     * @return 
     */
    public String getRefCatCmd() {
        return refCatCmd;
    }

    /**
     * Défini la référence du catalogue de la commande
     * @param refCatCmd 
     */
    public void setRefCatCmd(String refCatCmd) {
        this.refCatCmd = refCatCmd;
    }

    /**
     * Retourne la mesure de la cote (largeur) de la commande
     * @return 
     */
    public double getCoteLargeurCmd() {
        return coteLargeurCmd;
    }

    /**
     * Défini la mesure de la cote (largeur) de la commande
     * @param coteLargeurCmd 
     */
    public void setCoteLargeurCmd(double coteLargeurCmd) {
        this.coteLargeurCmd = coteLargeurCmd;
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
    
}
