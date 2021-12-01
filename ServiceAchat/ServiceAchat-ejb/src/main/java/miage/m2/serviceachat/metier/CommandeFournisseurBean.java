/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.metier;

import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.serviceachat.entities.CommandeFournisseur;
import miage.m2.sharedachat.exceptions.CreerCommandeFournisseurException;

/**
 * EJB qui stocke les informations des commandes passées auprès du fournisseur
 * @author QuentinDouris
 */
@Singleton
public class CommandeFournisseurBean implements CommandeFournisseurBeanLocal {
    // La clé correspond à l'id de l'affaire rattaché à la commande
    private HashMap<Integer, CommandeFournisseur> listeCommandeFournisseur;
    private int refCommandeFournisseur;

    /**
     * Constructeur
     */
    public CommandeFournisseurBean() {
        this.listeCommandeFournisseur = new HashMap<>();
        this.refCommandeFournisseur = 1;
    }
    
    /**
     * Creer une nouvelle commande auprès du fournisseur pour l'affaire concernée
     * @param idAffaire
     * @throws CreerCommandeFournisseurException 
     */
    @Override
    public void creerCommandeFournisseur(int idAffaire) throws CreerCommandeFournisseurException {
        // Vérifie que l'affaire n'a pas déjà une commande en cours
        if(this.listeCommandeFournisseur.get(idAffaire) != null) {
            throw new CreerCommandeFournisseurException();
        }
        
        // Appel l'API du fournisseur pour passer la commande 
        int refCommandeFrnrs = this.appelAPIFournisseur();
        
        // Créer une nouvelle commande fournisseur
        CommandeFournisseur newCommandeFrns = new CommandeFournisseur(idAffaire, refCommandeFrnrs);
        this.listeCommandeFournisseur.put(newCommandeFrns.getIdAffaire(), newCommandeFrns);
    }
    
    /**
     * Méthode qui simule l'appel à l'API du fournisseur
     * Le code d'appel à cette API devrait être écrit à l'intérieur
     * @return 
     */
    private int appelAPIFournisseur() {
        int reponse = this.refCommandeFournisseur; 
        this.refCommandeFournisseur++;
        
        System.out.println("\t\t [Simulation retour API] Référence commande fournisseur générée : " + reponse);
        return reponse;
    }

}
