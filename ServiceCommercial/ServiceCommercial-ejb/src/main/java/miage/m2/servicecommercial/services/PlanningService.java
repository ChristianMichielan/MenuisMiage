/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.services;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.exceptions.AucunPlanningCommercialException;
import miage.m2.servicecommercial.metier.RDVCommercialBeanLocal;

/**
 * EJB qui gère l'encapsulation JSON pour le planning des commerciaux
 * @author QuentinDouris
 */
@Stateless
public class PlanningService implements PlanningServiceLocal {

    @EJB
    private RDVCommercialBeanLocal rdvCommercialBean;
    
    // Convertisseur JSON
    private Gson gson;
    
    /**
     * Constructeur
     */
    public PlanningService() {
        this.gson = new Gson();
    }
    
    /**
     * Obtenir les RDV pour un commercial
     * @param idCommercial
     * @return le planning d'un commercial
     * @throws miage.m2.exceptions.AucunPlanningCommercialException
     */
    @Override
    public String obtenirPlanning(int idCommercial) throws AucunPlanningCommercialException {
        return this.gson.toJson(this.rdvCommercialBean.rdvPourUnCommercial(idCommercial));
    }

}
