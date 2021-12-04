/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.servicecommercial.entities.Commercial;
import miage.m2.exceptions.AucunCommercialException;
import miage.m2.exceptions.CommercialInconnuException;

/**
 * EJB qui stocke les informations des commerciaux
 * @author QuentinDouris
 */
@Singleton
public class CommercialBean implements CommercialBeanLocal {
    
    private HashMap<Integer, Commercial> listeCommercial;
    private int idCommercial;

    /**
     * Constructeur
     */
    public CommercialBean() {
        this.listeCommercial = new HashMap<>();
        this.idCommercial = 1;
        
        this.initialiserDonneesCommercial();
    }
    
    /**
     * Recherche un commercial dans le systéme selon son identifiant
     * @param idCommercial
     * @return
     * @throws CommercialInconnuException 
     */
    @Override
    public Commercial obtenirCommercial(int idCommercial) throws CommercialInconnuException {
        // Vérifie l'existance du commercial dans le système
        if(this.listeCommercial.get(idCommercial) == null) {
            throw new CommercialInconnuException();
        }
        
        return this.listeCommercial.get(idCommercial);
    }
    
    /**
     * Retourne tous les commerciaux enregistrés dans le système
     * @return
     * @throws AucunCommercialException 
     */
    @Override
    public ArrayList<Commercial> obtenirLesCommerciaux() throws AucunCommercialException {
        ArrayList<Commercial> listeCommerciaux = new ArrayList<>();
        
        // Parcours la map et rempli la liste des commerciaux
        for(Integer id : this.listeCommercial.keySet()) {
            listeCommerciaux.add(this.listeCommercial.get(id));
        }
        
        return listeCommerciaux;
    }

    
    /**
     * Initialise les données des commerciaux dans le système
     */
    private void initialiserDonneesCommercial() {
        Commercial com1 = new Commercial(this.idCommercial, "Hoauzi", "Simon");
        this.listeCommercial.put(com1.getIdCommercial(), com1);
        this.idCommercial++;

//        Commercial com2 = new Commercial(this.idCommercial, "Tata", "Jean");
//        this.listeCommercial.put(com2.getIdCommercial(), com2);
//        this.idCommercial++;
    }

}
