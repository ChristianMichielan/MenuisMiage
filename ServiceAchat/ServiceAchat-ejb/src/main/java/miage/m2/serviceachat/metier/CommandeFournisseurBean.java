/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.metier;

import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.serviceachat.entities.CommandeFournisseur;
import miage.m2.serviceachat.entities.EtatCommandeFournisseur;
import miage.m2.sharedachat.exceptions.CreerCommandeFournisseurException;
import miage.m2.sharedachat.exceptions.ReceptionCommandeInconnuException;

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
        this.initialiserDonnees();
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
     * Enregistre la réception de la commande fournisseur dans le système
     * @param idLivraison
     * @return 
     * @throws ReceptionCommandeInconnuException 
     */
    @Override
    public int enregistrerReceptionCommande(int idLivraison) throws ReceptionCommandeInconnuException {
        boolean trouver = false;
        int cpt = 0;
        int idAffaireCommande = 0;
        
        // Vérifie l'existance de la commande saisie dans le système
        while (trouver == false && cpt < this.listeCommandeFournisseur.size()) {
            for(Integer idAffaire : this.listeCommandeFournisseur.keySet()) {
                // Si la commande en cours de parcours correspond à la commande receptionnée
                if(this.listeCommandeFournisseur.get(idAffaire).getRefCommandeFournisseur() == idLivraison) {
                    idAffaireCommande = idAffaire;
                    trouver = true;
                }
                cpt++;
            }
        }

        // La commande est inconnu dans le système
        if (trouver == false) {
            throw new ReceptionCommandeInconnuException();
        }
     
        // Enregistre la réception de la commande dans le système
        this.listeCommandeFournisseur.get(idAffaireCommande).setEtatCommande(EtatCommandeFournisseur.RECEPTIONNEE);
        
        return idAffaireCommande;
    }
    
    /**
     * Simule l'appel à l'API du fournisseur
     * Le code d'appel à cette API devrait être écrit à l'intérieur
     * @return 
     */
    private int appelAPIFournisseur() {
        int reponse = this.refCommandeFournisseur; 
        this.refCommandeFournisseur++;
        
        System.out.println("\t\t [Simulation retour API] Référence commande fournisseur générée : " + reponse);
        
        return reponse;
    }
    
    
    
    /**
     * Initialise les données pour tester
     */
    private void initialiserDonnees() {
        CommandeFournisseur cmd = new CommandeFournisseur(1, this.refCommandeFournisseur);
        this.listeCommandeFournisseur.put(cmd.getIdAffaire(), cmd);
        this.refCommandeFournisseur++;
    }

}
