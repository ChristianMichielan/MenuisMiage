/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.sharedmenuis.exceptions;

/**
 * Exception permettant de spécifier qu'il n'y a pas de planning pour un commercial
 * @author ChristianMichielan
 */
public class AucunPlanningCommercialException extends Exception {

    public AucunPlanningCommercialException() {
        super("Aucun planning commercial n'est disponible");
    }

}
