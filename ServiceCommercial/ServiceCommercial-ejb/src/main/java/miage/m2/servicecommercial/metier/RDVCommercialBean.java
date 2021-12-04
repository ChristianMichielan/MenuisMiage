/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.servicecommercial.entities.Commercial;
import miage.m2.servicecommercial.entities.RDVCommercial;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;

/**
 * EJB qui stock les rendez-vous des commerciaux
 * @author QuentinDouris
 */
@Singleton
public class RDVCommercialBean implements RDVCommercialBeanLocal {
    // La clé de cette map correspond à l'identifiant de l'affaire
    private HashMap<Integer, RDVCommercial> listeRDVCommercial;
    
    /**
     * Constructeur
     */
    public RDVCommercialBean() {
        this.listeRDVCommercial = new HashMap<>();
        //this.initialiserDonnees();
    }

    /**
     * Retourne tous les rendez-vous d'un commercial (son planning)
     * @param idCommercial
     * @return 
     */
    @Override
    public ArrayList<RDVCommercial> rdvPourUnCommercial(int idCommercial) {
        ArrayList<RDVCommercial> listeRdv = new ArrayList<>();
        
        for(Integer id : this.listeRDVCommercial.keySet()) {
            RDVCommercial currentRdv = this.listeRDVCommercial.get(id);
            if(currentRdv.getCommecial().getIdCommercial() == idCommercial) {
                listeRdv.add(currentRdv);
            }
        }
        
        return listeRdv;
    }

    /**
     * Creer un nouveau rendez-vous commercial pour une affaire et un commercial
     * @param date
     * @param commercial
     * @param localisation
     * @param idAffaire
     * @return
     * @throws CommercialConfirmRDVException 
     */
    @Override
    public boolean creerRdvCommercial(String date, Commercial commercial, String localisation, int idAffaire) throws CommercialConfirmRDVException {
        // Vérification que pour l'id de l'affaire auncun rdv commercial existe déjà
        if (this.listeRDVCommercial.get(idAffaire) != null) {
            throw new CommercialConfirmRDVException();
        }
        
        // Créer un nouveau RDV commercial et l'ajoute au planning
        RDVCommercial newRdv = new RDVCommercial(date, idAffaire, commercial, localisation);
        this.listeRDVCommercial.put(newRdv.getIdAffaire(), newRdv);
        
        return true;
    }

    /**
     * Vérifie la disponibilité d'un commercial à une date donnée
     * @param date
     * @param commercial
     * @return 
     */
    @Override
    public boolean commercialDisponible(String date, Commercial commercial) {
        boolean commercialDispo = true;
        int cptRdv = 0;
        ArrayList<RDVCommercial> listeRdv = this.rdvPourUnCommercial(commercial.getIdCommercial());
        
        // Le commercial n'a pas de rdv, il est donc disponible
        if(listeRdv.isEmpty()) {
            return commercialDispo;
        }
       
        // Parcours les rdv du commercial pour vérifier sa disponibilité
        while(commercialDispo == true && cptRdv < listeRdv.size()) {
            // Récupere le rdv courant 
            RDVCommercial rdvCourrant = listeRdv.get(cptRdv);

            if(rdvCourrant.getDateRdvCom().equals(date)){
                commercialDispo = false;
            }
            // Passage au rdv suivant
            cptRdv++;
        }
        
        return commercialDispo;
    }
    
}
