/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2;

import java.util.Scanner;
import javax.ejb.EJBException;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exposition.GestionAffaireRemote;
import miage.m2.transientobjects.AffaireTransient;
import miage.m2.transientobjects.ChargerAffaireTransient;


/**
 * Classe AppCACLI qui représente les différentes opérations possibles par le CA.
 * @author QuentinDouris
 */
public class AppCACLI {
    
    private final int MENU_CHOIX_UN = 1;
    private final int MENU_CHOIX_DEUX = 2;
    private final int MENU_CHOIX_TROIS = 3;
    private final int MENU_CHOIX_QUATRE = 4;
    private final int MENU_CHOIX_QUITTER_PREMIER_MENU = 2;
    private final int MENU_CHOIX_QUITTER_DEUXIEME_MENU = 5;
    private final int PREMIER_MENU_CHOIX_MAX = 2;
    private final int DEUXIEME_MENU_CHOIX_MAX = 3;
    private final GestionAffaireRemote gestionAffaireRemote;
    private final Scanner scanner = new Scanner(System.in);
    private ChargerAffaireTransient chargerAffaire = null;
    private boolean menuUnQuitte = false;
    private boolean menuDeuxQuitte = false;
    
    /**
     * Constructeur de notre ServiceUsagerCLI
     * @param gestionAffaireRemote 
     */
    public AppCACLI(GestionAffaireRemote gestionAffaireRemote) {
        this.gestionAffaireRemote = gestionAffaireRemote;
    }

    /**
     * Lance l'application sur le premier menu
     */
    public void run() {
        CLICA.afficheDemarage();
        int choixPremierMenu = -1;
        do {
            do {
                try {
                    CLICA.afficherPremierMenu();
                    choixPremierMenu = CLICA.saisirEntier(scanner, "Votre choix : ", PREMIER_MENU_CHOIX_MAX);
                    switch (choixPremierMenu) {
                        case MENU_CHOIX_UN:
                            this.authentifier();
                            this.runApplicationAuthentifier();
                            break;
                        case MENU_CHOIX_QUITTER_PREMIER_MENU:
                            this.quitterApplication();
                            break;
                        default:
                            CLICA.afficherMessageErreur("Vous avez fait une erreur dans votre choix");
                    }
                } catch (EJBException ex) {
                    CLICA.afficherMessageErreur(" [EJB] " + ex.getMessage());
                } finally {
                    choixPremierMenu = 2;
                }
            } while (choixPremierMenu != 2);
        } while (!menuUnQuitte);
    }
    
    /**
     * Lance l'application pour un CA authentifié
     */
    private void runApplicationAuthentifier() {
        int choixDeuxiemeMenu;
        do {
            do {
                // Affiche le second menu à l'utilisateur (rentre dans le système en étant authentifier)
                CLICA.afficherDeuxiemeMenu();
                choixDeuxiemeMenu = CLICA.saisirEntier(scanner, "Votre choix : ", DEUXIEME_MENU_CHOIX_MAX);
                switch(choixDeuxiemeMenu) {
                    case MENU_CHOIX_UN:
                        this.consulterLesAffaire();
                        break;
                    case MENU_CHOIX_DEUX:
                        this.creerAffaire();
                        break;
                    case MENU_CHOIX_TROIS:
                        this.realiserOperationAffaire();
                        break;
                    case MENU_CHOIX_QUATRE:
                        this.voirNotificationAffaire();
                        break;
                    case MENU_CHOIX_QUITTER_DEUXIEME_MENU:
                        this.deconnexionCA();
                        break;
                    default:
                        CLICA.afficherMessageErreur("Vous avez fait une erreur dans votre choix");
                }
            } while (choixDeuxiemeMenu != 5);
        } while (!menuDeuxQuitte);
    }
    
    /**
     * Permet l'authentification du CA
     * @throws ChargerAffaireInconnuException 
     */
    private void authentifier() {
        while(this.chargerAffaire==null){
            try {
                int idCA;

                // Saisie des informations
                CLICA.afficherTitreChoix("authentification");

                idCA = CLICA.saisirEntier(scanner, "Saisir votre identifiant :", 999);
                
                // Demande d'authentification du CA
                this.chargerAffaire = gestionAffaireRemote.authentifier(idCA);
                CLICA.afficherInformation("Connexion réussie !");
                CLICA.afficherInformation("Bonjour " + this.chargerAffaire.getPrenom());
                CLICA.afficherInformation("");
            } catch (ChargerAffaireInconnuException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
            } 
        }
    }
    
    /**
     * Creer une affaire
     */
    private void creerAffaire() {
        //String 
    }
    
    /**
     * Consulte les différentes affaires attachées au Charger d'affaire
     */
    private void consulterLesAffaire() { 
        boolean reponse = false;
        while(reponse==false){
            // Affiche les affaire
            CLICA.afficherInformation("Liste des affaires : ");
            
            if(this.chargerAffaire.getListeAffaire().isEmpty()) {
                CLICA.afficherInformation("\tAucune affaire");
            } else {
                for(AffaireTransient affaire : this.chargerAffaire.getListeAffaire()) {
                    CLICA.afficherInformation("\t" + affaire.toString());
                }
            }
                
            // Saisie des informations
            reponse = CLICA.yesQuestion(scanner, "Revenir au menu (y)");
        }
        CLICA.afficherInformation("");
    }
    
    /**
     * Réaliser une opération sur une affaire (prendre rdv poseur ou cloturer l'affaire)
     */
    private void realiserOperationAffaire() {
        
    }
    
    /**
     * Permet de consulter les notifications reçus pour une affaire 
     */
    private void voirNotificationAffaire() {
        // Ecouter le topic de notification des chargés d'affaire
    }
    
    /**
     * Met fin à la session du CA
     */
    private void deconnexionCA() {
        CLICA.afficherInformationFinEtape("Vous êtes maintenant deconnecté ! A bientôt " + this.chargerAffaire.getPrenom());
        this.menuDeuxQuitte = true;
        this.chargerAffaire = null;
    }
    
    /**
     * Quitte l'application
     */
    private void quitterApplication() {
        CLICA.afficherTitreChoix("A bientôt !");
        this.menuUnQuitte = true;
    }
}
