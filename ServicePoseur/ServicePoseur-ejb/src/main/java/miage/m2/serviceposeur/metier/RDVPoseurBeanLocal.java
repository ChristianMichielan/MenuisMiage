/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.metier;

import java.util.ArrayList;
import javax.ejb.Local;
import miage.m2.exceptions.AucunRDVPoseur;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.serviceposeur.entities.EquipePoseurs;
import miage.m2.serviceposeur.entities.RDVPoseur;

/**
 * Interface de l'EJB qui stocke les informations des rendez-vous poseur
 * @author QuentinDouris
 */
@Local
public interface RDVPoseurBeanLocal {
    
    public ArrayList<RDVPoseur> rdvPourUneEquipePoseur(int idEquipe);

    public boolean creerRdvPoseur(String date, int idAffaire, EquipePoseurs equipePoseur, String localisation) throws PoseurConfirmRDVException;

    public boolean equipePoseurDisponible(String date, EquipePoseurs equipePoseur);

    public void validerPose(int idAffaire) throws AucunRDVPoseur;
    
}
