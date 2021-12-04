/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.entities.Commercial;
import miage.m2.entities.RDVCommercial;
import miage.m2.exceptions.AucunCommercialException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.metier.CommercialBeanLocal;
import miage.m2.metier.RDVCommercialBeanLocal;
import miage.m2.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.transientobjects.RDVCommercialTransient;

/**
 * EJB qui gère l'encapsulation JSON
 * @author QuentinDouris
 */
@Stateless
public class RDVCommercialService implements RDVCommercialServiceLocal {

    @EJB
    private RDVCommercialBeanLocal rdvCommercialBean;

    @EJB
    private CommercialBeanLocal commercialBean;
    
    // Convertisseur JSON
    private Gson gson;

    /**
     * Constructeur
     */
    public RDVCommercialService() {
        this.gson = new Gson();
    }    
    
    /**
     * Obtenir un rendez-vous commercial selon la disponoibilité du client
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException
     * @throws AucunCommercialException 
     */
    @Override
    public String obtenirRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, AucunCommercialException {
        // Récupére tous les commerciaux présents dans le système
        ArrayList<Commercial> listeCommerciaux = this.commercialBean.obtenirLesCommerciaux();
        
        // Varaibles nécessaires pour trouver un commercial disponible dans le système
        boolean commercialDispo = false;
        int cptCommercial = 0;
        Commercial commercialDisponible = null;        
  
        // Tant que l'on a pas trouvé de commercial disponible on parcours la liste de commercial
        while(commercialDispo == false && cptCommercial < listeCommerciaux.size()) {
            commercialDisponible = listeCommerciaux.get(cptCommercial);
            commercialDispo = this.rdvCommercialBean.commercialDisponible(dateDispoC, commercialDisponible);
            
            // Passage au commercial suivant
            cptCommercial++;
        }
        
        // Aucun commercial n'est disponible a cette date
        if(commercialDispo == false) {
            throw new CommercialDemandeRDVException();
        }
        
        // Le commercial a été trouvé
        PropositionRDVCommercialTransient rdvTransient = new PropositionRDVCommercialTransient(commercialDisponible.getIdCommercial(), dateDispoC);
        return this.gson.toJson(rdvTransient);
    }

    /**
     * Confirmation d'un rendez-vous commercial obtenu
     * @param rdv
     * @return
     * @throws CommercialConfirmRDVException
     * @throws CommercialInconnuException 
     */
    @Override
    public String valideRDVCommercial(RDVCommercialTransient rdv) throws CommercialConfirmRDVException, CommercialInconnuException {
        Commercial commercial = this.commercialBean.obtenirCommercial(rdv.getIdCommercial());
        boolean commercialDispo = this.rdvCommercialBean.commercialDisponible(rdv.getDate(), commercial);
        
        // Le commercial n'est pas disponible 
        if(commercialDispo == false) {
            throw new CommercialConfirmRDVException();
        }
        
        // Le commercial est toujours disponible donc on enreistre le rdv
        this.rdvCommercialBean.creerRdvCommercial(
                rdv.getDate(), 
                commercial, 
                rdv.getLocalisation(), 
                rdv.getIdAffaire());
        
        return this.gson.toJson(rdv);
    }
    
    /**
     * Obtenir les RDV pour un commercial
     * @param idCommercial
     * @return le planning d'un commercial
     */
    @Override
    public String obtenirPlanning(int idCommercial) {
        ArrayList<RDVCommercial> listeRDV = this.rdvCommercialBean.rdvPourUnCommercial(idCommercial);
        return this.gson.toJson(listeRDV);
    }

}