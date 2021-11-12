/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.services;

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
    
    /**
     * Obtenir un rendez-vous commercial selon la disponoibilité du client
     * @param dateDispoC
     * @return
     * @throws CommercialDemandeRDVException
     * @throws AucunCommercialException 
     */
    @Override
    public RDVCommercialTransient obtenirRdvCommercial(String dateDispoC) throws CommercialDemandeRDVException, AucunCommercialException {
        // Récupére tous les commerciaux présents dans le système
        ArrayList<Commercial> listeCommerciaux = this.commercialBean.obtenirLesCommerciaux();
        
        // Varaibles nécessaires pour trouver un commercial disponible dans le système
        boolean commercialTrouve = false;
        boolean rdvExistant = false;
        int cptCommercial = 0;
        int cptRdv = 0;
        int idCommercialCourrant = 0;
        Commercial commercialDisponible = null;
        ArrayList<RDVCommercial> rdvPourUnCommercial = null;
        
  
        // Tant que l'on a pas trouvé de commercial disponible on parcours la liste de commercial
        while(commercialTrouve == false || cptCommercial < listeCommerciaux.size()) {
            // A chaque tour on reset l'indicateur
            rdvExistant = false;
            cptRdv = 0;
            
            // Récupére l'id du commercial courrant et ses rdv
            idCommercialCourrant = listeCommerciaux.get(cptCommercial).getIdCommercial();
            rdvPourUnCommercial = this.rdvCommercialBean.rdvPourUnCommercial(idCommercialCourrant);
            
            while(rdvExistant == false || cptRdv < rdvPourUnCommercial.size()) {
                // Récupere le rdv courant 
                RDVCommercial rdvCourrant = rdvPourUnCommercial.get(cptRdv);
                if(rdvCourrant.getDateRdvCom() == dateDispoC){
                       rdvExistant = true;
                }
                // Passage au rdv suivant
                cptRdv++;
            }
            
            // On a trouvé le commercial disponible
            if(rdvExistant == false) {
                commercialTrouve = true;
                commercialDisponible = listeCommerciaux.get(cptCommercial);
            }
            
            // Passage au commercial suivant
            cptCommercial++;
        }
        
        // Aucun commercial n'est disponible a cette date
        if(commercialTrouve == false) {
            throw new CommercialDemandeRDVException();
        }
        
        // Le commercial a été trouvé
        RDVCommercialTransient rdvTransient = new RDVCommercialTransient(dateDispoC, commercialDisponible.getIdCommercial());
        return rdvTransient;
    }

    /**
     * Confirmation d'un rendez-vous commercial obtenu
     * @param rdv
     * @return
     * @throws CommercialConfirmRDVException
     * @throws CommercialInconnuException 
     */
    @Override
    public boolean valideRDVCommercial(RDVCommercialTransient rdv) throws CommercialConfirmRDVException, CommercialInconnuException {
        Commercial commercial = this.commercialBean.obtenirCommercial(rdv.getIdCommercial());
        ArrayList<RDVCommercial> listeRdv = this.rdvCommercialBean.rdvPourUnCommercial(commercial.getIdCommercial());
        
        boolean rdvExistant = false;
        int cptRdv = 0;
        
        while(rdvExistant == false || cptRdv < listeRdv.size()) {
            // Récupere le rdv courant 
            RDVCommercial rdvCourrant = listeRdv.get(cptRdv);
            if(rdvCourrant.getDateRdvCom() == rdv.getDate()){
                   rdvExistant = true;
            }
            // Passage au rdv suivant
            cptRdv++;
        }
        
        // Le commercial n'est pas disponible 
        if(rdvExistant == true) {
            throw new CommercialConfirmRDVException();
        }
        
        return true;
    }

}
