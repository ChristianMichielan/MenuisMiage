/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

/**
 * Enumération qui reprend l'état d'une affaire
 * @author QuentinDouris
 */
public enum EtatAffaire {
    ATTENTE_RDV_COMMERCIAL,
    COMMANDE_SAISIE,
    COMMANDE_ENVOYEE_FOURNISSEUR,
    ATTENTE_RDV_POSSEUR,
    ATTENTE_POSE,
    POSE_VALIDEE,
    CLOTUREE
}
