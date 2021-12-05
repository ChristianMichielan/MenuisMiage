/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.entities;

/**
 * Enumération qui reprend l'état d'une affaire
 * @author QuentinDouris
 */
public enum EtatAffaire {
    RDV_COMMERCIAL_NON_SAISIE,
    ATTENTE_RDV_COMMERCIAL,
    COMMANDE_SAISIE,
    COMMANDE_ENVOYEE_FOURNISSEUR,
    RDV_POSEUR_NON_SAISIE,
    ATTENTE_RDV_POSEUR,
    POSE_VALIDEE,
    CLOTUREE
}
