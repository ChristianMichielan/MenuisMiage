/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicechargeraffaire.metier;

import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.servicechargeraffaire.entities.ChargerAffaire;
import miage.m2.exceptions.ChargerAffaireInconnuException;

/**
 * Bean Singleton qui stock les informations des charger d'affaires
 * @author QuentinDouris
 */
@Singleton
public class ChargerAffaireBean implements ChargerAffaireBeanLocal {
    
    private HashMap<Integer, ChargerAffaire> listeChargerAffaire;
    private int idChargerAffaire;

    /**
     * Constructeur
     */
    public ChargerAffaireBean() {
        this.listeChargerAffaire = new HashMap<>();
        this.idChargerAffaire = 1;
        
        // Initialisation d'un jeu de données de charger d'affaire
        this.initialiserDonneesChargerAffaire();
    }
    
    /**
     * Authentifie un charger d'affaire dans le système
     * @param idChargerAffaire
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    @Override
    public ChargerAffaire authentifier(int idChargerAffaire) throws ChargerAffaireInconnuException {
        if(this.listeChargerAffaire.get(idChargerAffaire) == null) {
            throw new ChargerAffaireInconnuException();
        }
        
        return this.listeChargerAffaire.get(idChargerAffaire);
    }
    
    /**
     * Retourne un charger d'affaire selon un identifiant
     * @param idChargerAffaire
     * @return
     * @throws ChargerAffaireInconnuException 
     */
    @Override
    public ChargerAffaire obtenirChargerAffaire(int idChargerAffaire) throws ChargerAffaireInconnuException {
        if(this.listeChargerAffaire.get(idChargerAffaire) == null) {
            throw new ChargerAffaireInconnuException();
        }
        
        return this.listeChargerAffaire.get(idChargerAffaire);
    }
    
    /**
     * Initialise les données des charger d'affaire dans le système
     */
    private void initialiserDonneesChargerAffaire() {
        ChargerAffaire ca1 = new ChargerAffaire(this.idChargerAffaire, "Dupon", "Toto");
        this.listeChargerAffaire.put(ca1.getIdChargerAffaire(), ca1);
        this.idChargerAffaire++;
        
        ChargerAffaire ca2 = new ChargerAffaire(this.idChargerAffaire, "Dupon", "Toto");
        this.listeChargerAffaire.put(ca2.getIdChargerAffaire(), ca2);
        this.idChargerAffaire++;
    }
    
}
