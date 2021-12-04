/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.metier;

import javax.ejb.Local;
import miage.m2.servicechargeraffaire.entities.ChargerAffaire;
import miage.m2.sharedmenuis.exceptions.ChargerAffaireInconnuException;

/**
 * Interface de l'EJB qui stocke les informations des charger d'affaire
 * @author QuentinDouris
 */
@Local
public interface ChargerAffaireBeanLocal {
    
    public ChargerAffaire authentifier(int idChargerAffaire) throws ChargerAffaireInconnuException;
    
    public ChargerAffaire obtenirChargerAffaire(int idChargerAffaire) throws ChargerAffaireInconnuException;
}
