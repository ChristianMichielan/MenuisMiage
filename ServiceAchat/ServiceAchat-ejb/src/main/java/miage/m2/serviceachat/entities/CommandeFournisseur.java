/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.entities;

/**
 * Classe qui représente un objet commande fournisseur
 * @author QuentinDouris
 */
public class CommandeFournisseur {
    private int idAffaire;
    private int refCommandeFournisseur;

    /**
     * Constructeur
     * @param idAffaire
     * @param refCommandeFournisseur 
     */
    public CommandeFournisseur(int idAffaire, int refCommandeFournisseur) {
        this.idAffaire = idAffaire;
        this.refCommandeFournisseur = refCommandeFournisseur;
    }

    /**
     * Retourne l'identifiant de l'affaire de la commande fournisseur
     * @return 
     */
    public int getIdAffaire() {
        return idAffaire;
    }

    /**
     * Défini l'identifiant de l'affaire de la commande fournisseur
     * @param idAffaire 
     */
    public void setIdAffaire(int idAffaire) {
        this.idAffaire = idAffaire;
    }

    /**
     * Retourne la référence de la commande fournisseur
     * @return 
     */
    public int getRefCommandeFournisseur() {
        return refCommandeFournisseur;
    }

    /**
     * Défini la référence de la commande fournisseur
     * @param refCommandeFournisseur 
     */
    public void setRefCommandeFournisseur(int refCommandeFournisseur) {
        this.refCommandeFournisseur = refCommandeFournisseur;
    }
    
}
