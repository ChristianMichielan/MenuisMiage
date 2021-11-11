/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.exceptions;

/**
 * Exception pour la validation du rendez-vous commercial
 * @author QuentinDouris
 */
public class CommercialConfirmRDVException extends Exception {

    public CommercialConfirmRDVException() {
        super("Impossible d'enregistrer le rendez-vous commercial sélectionné.");
    }
}
