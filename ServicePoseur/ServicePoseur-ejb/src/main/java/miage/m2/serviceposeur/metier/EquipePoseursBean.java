/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.metier;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.sharedmenuis.exceptions.AucuneEquipePoseurException;
import miage.m2.sharedmenuis.exceptions.EquipePoseurInconnuException;
import miage.m2.serviceposeur.entities.EquipePoseurs;

/**
 * EJB qui stocke les informations des equipes Poseurs
 * @author QuentinDouris
 */
@Singleton
public class EquipePoseursBean implements EquipePoseursBeanLocal {
    // La clé correspond à l'identifiant de l'équipe poseur
    private HashMap<Integer, EquipePoseurs> listePoseur;
    private int idEquipe;

    /**
     * Constructeur
     */
    public EquipePoseursBean() {
        this.listePoseur= new HashMap<>();
        this.idEquipe =1;
        
        this.initialiserDonneesPoseur();
    }
    
    /**
     * Recherche une équipe poseur dans le systéme selon son identifiant
     * @param idEquipe
     * @return
     * @throws EquipePoseurInconnuException 
     */
    @Override
    public EquipePoseurs obtenirEquipePoseur(int idEquipe) throws EquipePoseurInconnuException {
        // Vérifie l'existance de l'equipe Poseur
        if (this.listePoseur.get(idEquipe) == null){
            throw new EquipePoseurInconnuException();
        }
        return this.listePoseur.get(idEquipe);
    }

    /**
     * Retourne toutes les équipes enregistrés dans le système
     * @return
     * @throws AucuneEquipePoseurException 
     */
    @Override
    public ArrayList<EquipePoseurs> obtenirLesEquipePoseur() throws AucuneEquipePoseurException {
        ArrayList<EquipePoseurs> listeEquipePoseur = new ArrayList<>();
        
        // Rechercher les equipes poseurs dans la listePoseurs
        for(Integer id : this.listePoseur.keySet()){
            listeEquipePoseur.add(this.listePoseur.get(id));
        }
        return listeEquipePoseur;
    }
    
    /**
     * Initialise les données des poseurs dans le système
     */
    private void initialiserDonneesPoseur() {
        EquipePoseurs equipe1 = new EquipePoseurs(this.idEquipe,"Strongman");
        this.listePoseur.put(equipe1.getIdEquipe(), equipe1);
        this.idEquipe++; 
    }

}
