/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.exceptions.AucuneEquipePoseurException;
import miage.m2.exceptions.EquipePoseurInconnuException;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.exceptions.PoseurDemandeRDVException;
import miage.m2.serviceposeur.entities.EquipePoseurs;
import miage.m2.serviceposeur.metier.EquipePoseursBeanLocal;
import miage.m2.serviceposeur.metier.RDVPoseurBeanLocal;
import miage.m2.transientobjects.PropositionRDVPoseurTransient;
import miage.m2.transientobjects.RDVPoseurTransient;

/**
 * EJB qui gère l'encapsulation JSON pour les rendez-vous des poseurs
 * @author QuentinDouris
 */
@Stateless
public class RDVPoseurService implements RDVPoseurServiceLocal {

    @EJB
    private RDVPoseurBeanLocal rdvPoseurBean;

    @EJB
    private EquipePoseursBeanLocal equipePoseursBean;

    // Convertisseur json
    private Gson gson;

    /**
     * Constructeur
     */
    public RDVPoseurService() {
        this.gson = new Gson();
    }
    
    /**
     * Obtenir un rendez-vous poseur selon la disponoibilité du client
     * @param dateDispoC
     * @return
     * @throws PoseurDemandeRDVException
     * @throws AucuneEquipePoseurException 
     */
    @Override
    public String obtenirRdvPoseur(String dateDispoC) throws PoseurDemandeRDVException, AucuneEquipePoseurException {
        // Récupére toutes les équipes poseurs présentes dans le système
        ArrayList<EquipePoseurs> listeEquipe = this.equipePoseursBean.obtenirLesEquipePoseur();
        
        // Varaibles nécessaires pour trouver une équipe disponible dans le système
        boolean equipeDispo = false;
        int cptEquipe = 0;
        EquipePoseurs equipeDisponible = null;        
  
        // Tant que l'on a pas trouvé d'équipe disponible on parcours la liste des équipes
        while(equipeDispo == false && cptEquipe < listeEquipe.size()) {
            
            equipeDisponible = listeEquipe.get(cptEquipe);
            equipeDispo = this.rdvPoseurBean.equipePoseurDisponible(dateDispoC, equipeDisponible);
            
            // Passage à l'équipe suivante
            cptEquipe++;
        }
        
        // Aucune équipe n'est disponible a cette date
        if(equipeDispo == false) {
            throw new PoseurDemandeRDVException();
        }
        
        // Une équipe a été trouvé
        PropositionRDVPoseurTransient rdvTransient = new PropositionRDVPoseurTransient(equipeDisponible.getIdEquipe(), dateDispoC);
        return this.gson.toJson(rdvTransient);
    }

    /**
     * Confirmation d'un rendez-vous poseur obtenu
     * @param rdv
     * @return
     * @throws PoseurConfirmRDVException
     * @throws EquipePoseurInconnuException 
     */
    @Override
    public String valideRDVPoseur(RDVPoseurTransient rdv) throws PoseurConfirmRDVException, EquipePoseurInconnuException {
        EquipePoseurs equipe = this.equipePoseursBean.obtenirEquipePoseur(rdv.getIdEquipePoseur());
        boolean equipeDispo = this.rdvPoseurBean.equipePoseurDisponible(rdv.getDate(), equipe);
        
        // L'équipe n'est pas disponible 
        if(equipeDispo == false) {
            throw new PoseurConfirmRDVException();
        }
        
        // L'équipe poseur est toujours disponible donc on enregistre le rdv
        this.rdvPoseurBean.creerRdvPoseur(
                rdv.getDate(), 
                rdv.getIdAffaire(), 
                equipe, 
                rdv.getLocalisation());
        
        return this.gson.toJson(rdv);
    }
    
}
