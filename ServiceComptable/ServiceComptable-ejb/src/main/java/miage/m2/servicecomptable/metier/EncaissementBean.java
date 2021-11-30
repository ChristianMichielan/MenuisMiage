/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecomptable.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.servicecomptable.entities.Encaissement;
import miage.m2.sharedcomptable.exceptions.EncaissementException;

/**
 * EJB qui stocke les informations des encaissements
 * @author QuentinDouris
 */
@Singleton
public class EncaissementBean implements EncaissementBeanLocal {
    // La clé correspond à l'id de l'affaire rattaché à la commande
    private HashMap<Integer, Encaissement> listeEncaissement;

    /**
     * Constructeur
     */
    public EncaissementBean() {
        this.listeEncaissement = new HashMap<>();
    }

    /**
     * Encaisse le premier cheque de l'affaire
     * @param idAffaire 
     * @throws miage.m2.sharedcomptable.exceptions.EncaissementException 
     */
    @Override
    public void encaisserPremierCheque(int idAffaire) throws EncaissementException {
        // Vérifie que l'affaire n'a pas d'encaissement d'enregistrée
        if(this.listeEncaissement.get(idAffaire) != null) {
            throw new EncaissementException();
        }
        
        // Récupère la date du jour
        String dateJour = this.obtenirDateDuJour();
        
         // Création de l'encaissement avec date du premier encaissement
        Encaissement newEncaissement = new Encaissement(idAffaire, dateJour);
        
        // Enregistrement de l'encaissement dans la collection
        this.listeEncaissement.put(newEncaissement.getIdAffaire(), newEncaissement);
        System.out.println("\t\t *** log : Encaissement du PREMIER chèque : " + newEncaissement.getDateEncaissement1());
    }

    /**
     * Encaisse le deuxième cheque de l'affaire
     * @param idAffaire 
     * @throws miage.m2.sharedcomptable.exceptions.EncaissementException 
     */
    @Override
    public void encaisserDeuxiemeCheque(int idAffaire) throws EncaissementException {
        // Vérifie la présence de l'affaire dans la map (au moins un encaissement de réalisé
        if(this.listeEncaissement.get(idAffaire) == null) {
            throw new EncaissementException();
        }
    
        // Récupère la date du jour
        String dateJour = this.obtenirDateDuJour();
        
        // Enregistre l'encaissement du deuxième chèque
        this.listeEncaissement.get(idAffaire).setDateEncaissement2(dateJour);
        System.out.println("\t\t *** log : Encaissement du DEUXIEME chèque : " + this.listeEncaissement.get(idAffaire).getDateEncaissement1());
    }
    
    /**
     * Retourne la date du jour
     * @return 
     */
    private String obtenirDateDuJour() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }
    
}
