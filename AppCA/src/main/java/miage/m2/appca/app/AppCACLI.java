/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appca.app;

import com.sun.enterprise.admin.remote.reader.CliActionReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import miage.m2.exceptions.APIException;
import miage.m2.exceptions.AffaireInconnueException;
import miage.m2.exceptions.ChargerAffaireInconnuException;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialDemandeRDVException;
import miage.m2.exceptions.CreerAffaireException;
import miage.m2.exposition.GestionAffaireRemote;
import miage.m2.transientobjects.AffaireTransient;
import miage.m2.transientobjects.ChargerAffaireTransient;
import miage.m2.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.transientobjects.RDVCommercialTransient;


/**
 * Classe AppCACLI qui représente les différentes opérations possibles par le CA.
 * @author QuentinDouris
 */
public class AppCACLI {
    
    private final int MENU_CHOIX_UN = 1;
    private final int MENU_CHOIX_DEUX = 2;
    private final int MENU_CHOIX_TROIS = 3;
    private final int MENU_CHOIX_QUATRE = 4;
    private final int MENU_CHOIX_CINQ = 5;
    private final int MENU_CHOIX_QUITTER_PREMIER_MENU = 2;
    private final int MENU_CHOIX_QUITTER_DEUXIEME_MENU = 6;
    private final int PREMIER_MENU_CHOIX_MAX = 2;
    private final int DEUXIEME_MENU_CHOIX_MAX = 6;
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
                        this.definirRdvCommercial();
                        break;
                    case MENU_CHOIX_QUATRE:
                        this.definirRdvPoseAffaire();
                        break;
                    case MENU_CHOIX_CINQ:
                        this.voirNotificationAffaire();
                        break;
                    case MENU_CHOIX_QUITTER_DEUXIEME_MENU:
                        this.deconnexionCA();
                        break;
                    default:
                        CLICA.afficherMessageErreur("Vous avez fait une erreur dans votre choix");
                }
            } while (choixDeuxiemeMenu != 6);
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
     * Créer une affaire dans le système avec la saisie du RDV commercial
     */
    private void creerAffaire() {
        try {
            String nomC, prenomC, adresseC, mailC, telC, loC;
            
            // Message d'accueil
            CLICA.afficherTitreChoix("creer affaire");
            CLICA.afficherInformation("Veuillez saisir les informations de la nouvelle affaire :");

            // Saisie des informations de l'affaire
            nomC = CLICA.saisirChaine(scanner, "Saisir le nom du client : ");
            prenomC = CLICA.saisirChaine(scanner, "Saisir le prénom du client : ");
            adresseC = CLICA.saisirChaine(scanner, "Saisir l'adresse du client : ");
            mailC = CLICA.saisirChaine(scanner, "Saisir le mail du client : ");
            telC = CLICA.saisirChaine(scanner, "Saisir le téléphone du client : ");
            loC = CLICA.saisirChaine(scanner, "Saisir la localisation de l'affaire : ");

            // Création de l'affaire dans le système
            int idAffaireCreee = this.gestionAffaireRemote.creerAffaire(nomC, prenomC, adresseC, mailC, telC, loC, this.chargerAffaire.getId());
            CLICA.afficherInformationFinEtape("Affaire n°" + idAffaireCreee + " créée avec succès !");
            
        } catch (ChargerAffaireInconnuException | CreerAffaireException ex) {
            CLICA.afficherMessageErreur(ex.getMessage());
        }        
    }
    
    /**
     * Consulte les différentes affaires attachées au Charger d'affaire
     */
    private void consulterLesAffaire() { 
        boolean reponse = false;
        while(reponse==false){
            // Affiche les affaire
            CLICA.afficherInformation("Liste des affaires : ");
            
            ArrayList<AffaireTransient> affaires = this.gestionAffaireRemote.affairesDuChargerAffaire(this.chargerAffaire.getId());
            
            if(affaires.isEmpty()) {
                CLICA.afficherInformation("\tAucune affaire enregistrée");
            } else {
                for(AffaireTransient item : affaires) {
                    CLICA.afficherInformation("\t" + item.toString());
                }
            }
            
            // Saisie des informations
            reponse = CLICA.yesQuestion(scanner, "Revenir au menu (y)");
        }
        CLICA.afficherInformation("");
    }
    
    /**
     * Défini le rendez-vous commercial pour une affaire
     */
    private void definirRdvCommercial() {
        String dateDispoClient;
        boolean confirmerRdv = false;
        boolean rdvEnregistre = false;
        int idAffaireSelectionnee = 0;
        PropositionRDVCommercialTransient proposition = null;
        HashMap<Integer, AffaireTransient> listeSelectionAffaire = new HashMap<>();
        AffaireTransient affaireSelectionnee = null;

        // Affiche les affaire
        CLICA.afficherInformation("Sélectionner une affaire avec son id : ");
        ArrayList<AffaireTransient> affaires = this.gestionAffaireRemote.affairesPourUnChargerAffaireRdvCommercialNonSaisi(this.chargerAffaire.getId());
        if(affaires.isEmpty()) {
            CLICA.afficherInformation("\tAucune affaire enregistrée");
        } else {
            for(AffaireTransient item : affaires) {
                listeSelectionAffaire.put(item.getIdAffaire(), item);
                CLICA.afficherInformation("\t" + item.getIdAffaire() + " - " + item.getNomC());
            }
        }
                
        // Sélectionner une affaire de la liste
        do {
            idAffaireSelectionnee = CLICA.saisirEntier(scanner, "Saisir id de l'affaire : ", affaires.size());
            affaireSelectionnee = listeSelectionAffaire.get(idAffaireSelectionnee);
        } while (affaireSelectionnee == null);
        
        // Définir le RDV commercial pour l'affaire
        CLICA.afficherInformation("Définir le rendez-vous commercial de l'affaire n° " + affaireSelectionnee.getIdAffaire());
        dateDispoClient = CLICA.saisirChaine(scanner, "Saisir la date de disponibilité du client (dd-mm-yyyy) : ");
        
        // Vérification de la disponibilité du commercial
        do {
            try {
                proposition = this.gestionAffaireRemote.demandeDisponibiliteRdvCommercial(dateDispoClient);
            } catch (CommercialDemandeRDVException | APIException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
                Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (proposition == null);

        // Confirmer la proposition du rendez-vous commercial 
        confirmerRdv = CLICA.yesNoQuestion(scanner, "Confirmer le rendez-vous du " + proposition.getDate() + " ? (y/n) ");
        if(confirmerRdv) {
            try {
                RDVCommercialTransient rdvAConfimer = new RDVCommercialTransient(proposition.getDate(), proposition.getIdCommercial(), affaireSelectionnee.getLocC(), affaireSelectionnee.getIdAffaire());
                rdvEnregistre = this.gestionAffaireRemote.validerRdvCommercial(rdvAConfimer);

                // Informe l'utilisateur et maj l'état de l'affaire auprès du Service CA
                if(rdvEnregistre) {
                    CLICA.afficherInformationFinEtape("Rendez-vous commercial pour l'affaire n° " + idAffaireSelectionnee + " CONFIRME pour le " + proposition.getDate());
                    this.gestionAffaireRemote.modifierEtatAffaireAttenteRdvCommercial(idAffaireSelectionnee);
                } else {
                    CLICA.afficherInformationFinEtape("Le rendez-vous commercial n'est plus disponible.");
                }
            } catch (CommercialConfirmRDVException | APIException | AffaireInconnueException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
                Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            CLICA.afficherInformationFinEtape("Rendez-vous non confirmé.");
        }
    }
    
    /**
     * Définir un rendez-vous de pose pour une affaire
     */
    private void definirRdvPoseAffaire() {  
        // TODO Etape 5
        CLICA.afficherInformation("TODO / définir rdv pose");
    }
    
    /**
     * Permet de consulter les notifications reçus pour une affaire 
     */
    private void voirNotificationAffaire() {
        // Ecouter le topic de notification des chargés d'affaire
        CLICA.afficherInformation("TODO / voir ce qui arrive dans le topic de notification");
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
