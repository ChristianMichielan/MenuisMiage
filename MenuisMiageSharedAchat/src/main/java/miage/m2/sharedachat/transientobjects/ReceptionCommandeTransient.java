/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.transientobjects;

import java.io.Serializable;

/**
 *
 * @author QuentinDouris
 */
public class ReceptionCommandeTransient implements Serializable{
    private int idLivraisonReceptionnee;

    /**
     * Constructeur
     * @param idLivraisonReceptionnee 
     */
    public ReceptionCommandeTransient(int idLivraisonReceptionnee) {
        this.idLivraisonReceptionnee = idLivraisonReceptionnee;
    }

    /**
     * 
     * @return 
     */
    public int getIdLivraisonReceptionnee() {
        return idLivraisonReceptionnee;
    }

    /**
     * 
     * @param idLivraisonReceptionnee 
     */
    public void setIdLivraisonReceptionnee(int idLivraisonReceptionnee) {
        this.idLivraisonReceptionnee = idLivraisonReceptionnee;
    }
    
}
