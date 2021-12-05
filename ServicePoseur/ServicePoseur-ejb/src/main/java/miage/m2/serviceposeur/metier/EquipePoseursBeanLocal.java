/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.metier;

import java.util.ArrayList;
import javax.ejb.Local;
import miage.m2.sharedmenuis.exceptions.AucuneEquipePoseurException;
import miage.m2.sharedmenuis.exceptions.EquipePoseurInconnuException;
import miage.m2.serviceposeur.entities.EquipePoseurs;


/**
 * Interface de l'EJB qui stocke les informations des équipes poseur
 * @author Trung
 */
@Local
public interface EquipePoseursBeanLocal {
    
    public EquipePoseurs obtenirEquipePoseur(int idEquipe)throws EquipePoseurInconnuException;

    public ArrayList<EquipePoseurs> obtenirLesEquipePoseur() throws AucuneEquipePoseurException;
    
}
