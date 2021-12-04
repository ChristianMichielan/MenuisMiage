/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

import java.util.ArrayList;
import javax.ejb.Local;
import miage.m2.entities.Affaire;
import miage.m2.entities.ChargerAffaire;
import miage.m2.entities.EtatAffaire;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.exceptions.CreerAffaireException;

/**
 * Interface de l'EJB qui stocke les informations des affaires
 * @author QuentinDouris
 */
@Local
public interface AffaireBeanLocal {
    
    public ArrayList<Affaire> affairesPourUnChargerAffaire(int idChargerAffaire);
    
    public ArrayList<Affaire> affairesPourUnChargerAffaireRdvCommercialNonSaisi(int idChargerAffaire);
    
    public Affaire creerAffaire(String nomC, String prenomC, String adresseC, String mailC, String telC, String locC, ChargerAffaire chargerAffaire) throws CreerAffaireException;
    
    public void modifierEtatAffaire(int idAffaire, EtatAffaire etat) throws AffaireInconnueException;

    public Affaire obtenirAffaire(int idAffaire) throws AffaireInconnueException;
    
    public ArrayList<Affaire> affairesPourUnChargerAffaireRdvPoseurNonSaisi(int idChargerAffaire);
    
    public ArrayList<Affaire> affairesPourUnChargerAffaireACloturer(int idChargerAffaire);

}
