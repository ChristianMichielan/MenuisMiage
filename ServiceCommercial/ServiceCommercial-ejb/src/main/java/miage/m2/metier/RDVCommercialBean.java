/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.entities.Commercial;
import miage.m2.entities.RDVCommercial;
import miage.m2.exceptions.CommercialConfirmRDVException;

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
        // Vérification que pour l'id de la nouvelle affaire auncune affaire existe déjà
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
            System.out.println(" **** le commercial pas de rdv donc il est disponible !");
            return commercialDispo;
        }
        
        System.out.println(" **** le commercial a : " + listeRdv.size() + " rdv");
        
        // Parcours les rdv du commercial pour vérifier sa disponibilité
        while(commercialDispo == true && cptRdv < listeRdv.size()) {
            System.out.println("******** Parcours le rdv de rang : " + cptRdv);
            
            // Récupere le rdv courant 
            RDVCommercial rdvCourrant = listeRdv.get(cptRdv);

            if(rdvCourrant.getDateRdvCom().equals(date)){
                System.out.println("******** Le commercial n'est pas disponible à cette date !");
                commercialDispo = false;
            }
            // Passage au rdv suivant
            cptRdv++;
        }
        
        System.out.println(" ***** Satut du commercial parcouru : " + commercialDispo);
        
        
        return commercialDispo;
    }
    
    /**
     * Permet d'initialiser des données de test
     */
    private void initialiserDonnees() {
        Commercial com = new Commercial(200, "Hoauzi", "Simon");
        Commercial com2 = new Commercial(300, "Titi", "Toto");
        RDVCommercial rdvC = new RDVCommercial("12-01-01",1,com,"Toulouse");
        RDVCommercial rdvC2 = new RDVCommercial("10-02-02",2,com2,"Paris");
        RDVCommercial rdvC3 = new RDVCommercial("10-02-03",3,com,"Paris");
        this.listeRDVCommercial.put(1,rdvC);
        this.listeRDVCommercial.put(2,rdvC2);
        this.listeRDVCommercial.put(3,rdvC3);
    }
    
}
