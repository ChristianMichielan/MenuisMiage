/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.serviceposeur.entities.RDVPoseur;
import miage.m2.serviceposeur.metier.RDVPoseurBeanLocal;

/**
 * EJB qui gère l'encapsulation JSON pour le planning des poseurs
 * @author QuentinDouris
 */
@Stateless
public class PlanningService implements PlanningServiceLocal {

    @EJB
    private RDVPoseurBeanLocal rdvPoseurBean;
    
    // Convertiseur JSON
    private Gson gson;

    /**
     * Constructeur
     */
    public PlanningService() {
        this.gson = new Gson();
    }
    
    /**
     * Retourne le planning d'une équipe
     * @param idEquipePoseur
     * @return 
     */
    @Override
    public String obtenirPlanning(int idEquipePoseur) {
        ArrayList<RDVPoseur> listeRDV = this.rdvPoseurBean.rdvPourUneEquipePoseur(idEquipePoseur);
        return this.gson.toJson(listeRDV);
    }
    
}
