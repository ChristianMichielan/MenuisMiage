/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.serviceposeur.entities.EquipePoseurs;
import miage.m2.serviceposeur.entities.RDVPoseur;

/**
 * EJB qui stocke les informations pour les RDV des equipes Poseur
 * @author QuentinDouris
 */
@Singleton
public class RDVPoseurBean implements RDVPoseurBeanLocal {
    // clé de HashMap correspond a l'id de l'affaire
    private HashMap<Integer, RDVPoseur> listeRdvPoseur;

    /**
     * Constructeur
     */
    public RDVPoseurBean(){
        this.listeRdvPoseur = new HashMap<>();
    }
    
    
    /**
     * Retourne tous les rendez-vous d'une équipe poseur (planning)
     * @param idEquipe
     * @return 
     */
    @Override
    public ArrayList<RDVPoseur> rdvPourUneEquipePoseur(int idEquipe) {
        ArrayList<RDVPoseur>  listeRdv = new ArrayList<>();
        
        for(Integer id : this.listeRdvPoseur.keySet()){
            RDVPoseur rdvCourrant= this.listeRdvPoseur.get(id);
            if(rdvCourrant.getEquipePoseur().getIdEquipe()== idEquipe){
                listeRdv.add(rdvCourrant);
            }
        }
        
        return listeRdv;
    }

    /**
     * Creer un nouveau rdv poseur pour une affaire et une equipe Poseur
     * @param date
     * @param idAffaire
     * @param equipePoseur
     * @param localisation
     * @return
     * @throws PoseurConfirmRDVException 
     */
    @Override
    public boolean creerRdvPoseur(String date, int idAffaire, EquipePoseurs equipePoseur, String localisation) throws PoseurConfirmRDVException {
        // Vérification que pour l'id de l'affaire auncun rdv poseur existe déjà
        if (this.listeRdvPoseur.get(idAffaire) != null){
            throw new PoseurConfirmRDVException();
        }
        
        // Creer un nouveau RDV Poseur
        RDVPoseur newRdvPoseur = new RDVPoseur(date, idAffaire, equipePoseur, localisation);
        this.listeRdvPoseur.put(idAffaire, newRdvPoseur);
        
        return true;
    }

    /**
     * Verifie la dispo d'une equipe Poser a une date donnee
     * @param date
     * @param equipePoseur
     * @return 
     */
    @Override
    public boolean equipePoseurDisponible(String date, EquipePoseurs equipePoseur) {
        boolean equipePoseurDispo = true;
        int cptRdv = 0;
        ArrayList<RDVPoseur> listeRdv = this.rdvPourUneEquipePoseur(equipePoseur.getIdEquipe());
        
        //Equipe Poseur disponible
        if (listeRdv.isEmpty()){
            return equipePoseurDispo;
        }
     
        // Parcours les rdv du commercial pour vérifier sa disponibilité
        while(equipePoseurDispo == true && cptRdv <listeRdv.size()){
            // Recupére le rdv courant
            RDVPoseur rdvCourrant = listeRdv.get(cptRdv);
            
            if(rdvCourrant.getDateRdvPose().equals(date)){
                equipePoseurDispo = false;
            }
            
            // Passage au rdv suivant
            cptRdv++;
        }
        
        return equipePoseurDispo;
    }

}
