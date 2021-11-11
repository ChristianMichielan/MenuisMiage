/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.exceptions;

/**
 * Exception pour la demande de rendez-vous commercial
 * @author QuentinDouris
 */
public class CommercialDemandeRDVException extends Exception {

    public CommercialDemandeRDVException() {
        super("Aucun rendez-vous commercial disponible pour la date saisie.");
    }
    
}
