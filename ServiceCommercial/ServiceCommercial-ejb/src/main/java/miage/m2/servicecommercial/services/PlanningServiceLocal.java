/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.services;

import javax.ejb.Local;
import miage.m2.exceptions.AucunPlanningCommercialException;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON pour le planning des commerciaux
 * @author QuentinDouris
 */
@Local
public interface PlanningServiceLocal {
    
    /**
     * Obtenir les RDV pour un commercial
     * @param idCommercial
     * @return le planning d'un commercial
     * @throws miage.m2.exceptions.AucunPlanningCommercialException
     */
    public String obtenirPlanning(int idCommercial) throws AucunPlanningCommercialException;
    
}
