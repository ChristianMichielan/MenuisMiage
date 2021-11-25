/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2.messages;

import javax.ejb.Local;
import javax.jms.JMSException;
import miage.m2.entities.Commercial;

/**
 *
 * @author ChristianMichielan
 */
@Local
public interface CommandeBeanLocal {
    public void saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, Commercial commercial) throws JMSException;
}
