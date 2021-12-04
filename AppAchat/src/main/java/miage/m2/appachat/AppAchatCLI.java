/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appachat;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import miage.m2.sharedachat.exposition.GestionLivraisonRemote;
import miage.m2.sharedachat.exceptions.ReceptionCommandeInconnuException;
import miage.m2.sharedachat.transientobjects.ReceptionCommandeTransient;

/**
 * Classe AppAchatCLI qui représente les différentes opérations possibles par le magasinier.
 * @author QuentinDouris
 */
public class AppAchatCLI {
    private final int MENU_CHOIX_UN = 1;
    private final int MENU_CHOIX_QUITTER_PREMIER_MENU = 2;
    private final int PREMIER_MENU_CHOIX_MAX = 2;
    private final GestionLivraisonRemote gestionLivraisonRemote;
    private final Scanner scanner = new Scanner(System.in);
    private boolean menuUnQuitte = false;
    
    /**
     * Constructeur
     * @param gestionLivraisonRemote 
     */
    public AppAchatCLI(GestionLivraisonRemote gestionLivraisonRemote) {
        this.gestionLivraisonRemote = gestionLivraisonRemote;
    }
    
    /**
     * Lance l'application sur le premier menu
     */
    public void run() {
        CLIAchat.afficheDemarage();
        int choixPremierMenu = -1;
        do {
            do {
                try {
                    CLIAchat.afficherPremierMenu();
                    choixPremierMenu = CLIAchat.saisirEntier(scanner, "Votre choix : ", PREMIER_MENU_CHOIX_MAX);
                    switch (choixPremierMenu) {
                        case MENU_CHOIX_UN:
                            this.enregistrerLivraison();
                            break;
                        case MENU_CHOIX_QUITTER_PREMIER_MENU:
                            this.quitterApplication();
                            break;
                        default:
                            CLIAchat.afficherMessageErreur("Vous avez fait une erreur dans votre choix");
                    }
                    // CATCH EXCEPTION EJB
                } catch (Exception ex) {
                    CLIAchat.afficherMessageErreur(" [EJB] " + ex.getMessage());
                } finally {
                    choixPremierMenu = 2;
                }
            } while (choixPremierMenu != 2);
        } while (!menuUnQuitte);
    }
    
    /**
     * Enregistre la réception d'une livraison dans le système
     */
    private void enregistrerLivraison() {
        try {
            int idLivraisonReceptionnee;
            
            // Message d'accueil
            CLIAchat.afficherTitreChoix("enregistrer réception livraison");
            CLIAchat.afficherInformation("Veuillez saisir les informations de la livraison à enregistrer :");
            
            // Saisie du code de la livraison
            idLivraisonReceptionnee = CLIAchat.saisirEntier(scanner, "Saisir l'identifiant de la livraison : ", 99999);
            
            // Enregister la livraison dans le système
            ReceptionCommandeTransient receptionCommande = new ReceptionCommandeTransient(idLivraisonReceptionnee);
            this.gestionLivraisonRemote.enregistrerLivraison(receptionCommande);
            
            CLIAchat.afficherInformationFinEtape("Réception de la livraison n°" + idLivraisonReceptionnee + " enregsitrée avec succès !");
        } catch (ReceptionCommandeInconnuException ex) {
            CLIAchat.afficherMessageErreur(ex.getMessage());
            Logger.getLogger(AppAchatCLI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Quitte l'application
     */
    private void quitterApplication() {
        CLIAchat.afficherTitreChoix("A bientôt !");
        this.menuUnQuitte = true;
    }
    
}
