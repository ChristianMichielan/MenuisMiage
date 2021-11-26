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

    /**
     * Constructeur
     * @param idAffaire 
     */
    public CommandeTransient(int idAffaire) {
        this.idAffaire = idAffaire;
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
    
}
