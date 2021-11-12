/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

import java.util.ArrayList;
import javax.ejb.Local;
import miage.m2.entities.Commercial;
import miage.m2.entities.RDVCommercial;
import miage.m2.exceptions.CommercialConfirmRDVException;

/**
 * Interface de l'EJB qui stocke les rendez-vous
 * @author QuentinDouris
 */
@Local
public interface RDVCommercialBeanLocal {
    
    public ArrayList<RDVCommercial> rdvPourUnCommercial(int idCommercial);

    public boolean creerRdvCommercial(String date, Commercial commercial, String localisation, int idAffaire) throws CommercialConfirmRDVException;

}
