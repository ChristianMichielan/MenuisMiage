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
    }

    /**
     * Retourne tous les rendez-vous d'un commercial (son planning)
     * @param idCommercial
     * @return 
     */
    @Override
    public ArrayList<RDVCommercial> rdvPourUnCommercial(int idCommercial) {
        ArrayList<RDVCommercial> listeRdv = new ArrayList<>();
        
        for(RDVCommercial rdv : this.listeRDVCommercial.values()) {
            if(rdv.getCommecial().getIdCommercial() == idCommercial) {
                listeRdv.add(rdv);
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
        
        // Creer un nouveau RDV commercial et ajout au planning
        RDVCommercial newRdv = new RDVCommercial(date, idAffaire, commercial, localisation);
        this.listeRDVCommercial.put(newRdv.getIdAffaire(), newRdv);
        
        return true;
    }
    
}