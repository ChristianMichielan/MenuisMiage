/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import javax.ejb.Local;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON pour le planning des poseurs
 * @author QuentinDouris
 */
@Local
public interface PlanningServiceLocal {
    
    /**
     * Retourne le planning d'une équipe
     * @param idEquipePoseur
     * @return 
     */
    public String obtenirPlanning(int idEquipePoseur);
}
