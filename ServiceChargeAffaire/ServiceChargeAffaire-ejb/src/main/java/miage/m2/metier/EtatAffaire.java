/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
