/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appca.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.ejb.EJBException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.m2.appca.messageslistener.NotificationAffaireListener;
import miage.m2.sharedmenuis.exceptions.APIException;
import miage.m2.sharedmenuis.exceptions.AffaireInconnueException;
import miage.m2.sharedmenuis.exceptions.ChargerAffaireInconnuException;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialDemandeRDVException;
import miage.m2.sharedmenuis.exceptions.CreerAffaireException;
import miage.m2.sharedmenuis.exceptions.PoseurConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.PoseurDemandeRDVException;
import miage.m2.sharedmenuis.exposition.GestionAffaireRemote;
import miage.m2.sharedmenuis.transientobjects.AffaireTransient;
import miage.m2.sharedmenuis.transientobjects.ChargerAffaireTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.PropositionRDVPoseurTransient;
import miage.m2.sharedmenuis.transientobjects.RDVCommercialTransient;
import miage.m2.sharedmenuis.transientobjects.RDVPoseurTransient;


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
    private final int MENU_CHOIX_SIX = 6;
    private final int MENU_CHOIX_QUITTER_PREMIER_MENU = 2;
    private final int MENU_CHOIX_QUITTER_DEUXIEME_MENU = 7;
    private final int PREMIER_MENU_CHOIX_MAX = 2;
    private final int DEUXIEME_MENU_CHOIX_MAX = 7;
    private final GestionAffaireRemote gestionAffaireRemote;
    private final Scanner scanner = new Scanner(System.in);
    private final String FACTORY_NAME = "TPEAIConnectionFactory";
    private final String DEST_NAME = "NotificationAffaire";
    
    private ChargerAffaireTransient chargerAffaire = null;
    private NotificationAffaireListener notificationAffaireListener;
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
                    case MENU_CHOIX_SIX:
                        this.cloturerAffaire();
                        break;
                    case MENU_CHOIX_QUITTER_DEUXIEME_MENU:
                        this.deconnexionCA();
                        break;
                    default:
                        CLICA.afficherMessageErreur("Vous avez fait une erreur dans votre choix");
                }
            } while (choixDeuxiemeMenu != 7);
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
                
                // Intialisation du listener pour recevoir les notifications
                this.lireNotificationAffaire();
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
        String dateDispoClient;
        boolean confirmerRdv = false;
        boolean rdvEnregistre = false;
        int idAffaireSelectionnee = 0;
        PropositionRDVPoseurTransient proposition = null;
        HashMap<Integer, AffaireTransient> listeSelectionAffaire = new HashMap<>();
        AffaireTransient affaireSelectionnee = null;

        // Affiche les affaire
        CLICA.afficherInformation("Sélectionner une affaire avec son id : ");
        ArrayList<AffaireTransient> affaires = this.gestionAffaireRemote.affairesPourUnChargerAffaireRdvPoseurNonSaisi(this.chargerAffaire.getId());
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
        
        // Définir le RDV poseur pour l'affaire
        CLICA.afficherInformation("Définir le rendez-vous poseur de l'affaire n° " + affaireSelectionnee.getIdAffaire());
        dateDispoClient = CLICA.saisirChaine(scanner, "Saisir la date de disponibilité du client (dd-mm-yyyy) : ");
        
        // Vérification de la disponibilité de l'équipe poseur
        do {
            try {
                proposition = this.gestionAffaireRemote.demandeDisponibiliteRdvPoseur(dateDispoClient);
            } catch (PoseurDemandeRDVException | APIException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
                Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (proposition == null);
        
        // Confirmer la proposition du rendez-vous poseur
        confirmerRdv = CLICA.yesNoQuestion(scanner, "Confirmer le rendez-vous du " + proposition.getDate() + " ? (y/n) ");
        if(confirmerRdv) {
            try {
                RDVPoseurTransient rdvAConfimer = new RDVPoseurTransient(proposition.getDate(), proposition.getIdEquipePoseur(), affaireSelectionnee.getLocC(), affaireSelectionnee.getIdAffaire());
                rdvEnregistre = this.gestionAffaireRemote.validerRdvPoseur(rdvAConfimer);

                // Informe l'utilisateur et maj l'état de l'affaire auprès du Service CA
                if(rdvEnregistre) {
                    CLICA.afficherInformationFinEtape("Rendez-vous poseur pour l'affaire n° " + idAffaireSelectionnee + " CONFIRME pour le " + proposition.getDate());
                    this.gestionAffaireRemote.modifierEtatAffaireAttenteRdvPoseur(idAffaireSelectionnee);
                } else {
                    CLICA.afficherInformationFinEtape("Le rendez-vous poseur n'est plus disponible.");
                }
            } catch (PoseurConfirmRDVException | AffaireInconnueException | APIException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
                Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            CLICA.afficherInformationFinEtape("Rendez-vous non confirmé.");
        }
    }
    
    /**
     * Permet de consulter les notifications reçus pour une affaire 
     */
    private void voirNotificationAffaire() {
        boolean reponseRetourMenu = false;
        while(reponseRetourMenu==false){
            // Affiche les notifications
            CLICA.afficherInformation("Liste des notifications : ");
            
            ArrayList<AffaireTransient> affaires = this.notificationAffaireListener.getListeAffaireNotification();
            
            if(affaires.isEmpty()) {
                CLICA.afficherInformation("\tAucune notification");
            } else {
                for(AffaireTransient item : affaires) {
                    CLICA.afficherInformation("\t" + item.toString());
                }
            }
            
            // Demande la suppression des notifications
            boolean reponseSupp = CLICA.yesNoQuestion(scanner, "Supprimer notifications (y/n)");
            if(reponseSupp) {
                this.notificationAffaireListener.effacerNotifications();
            }
            
            // Saisie des informations
            reponseRetourMenu = CLICA.yesQuestion(scanner, "Revenir au menu (y)");
        }
        CLICA.afficherInformation("");
    }
    
    /**
     * Permet de cloturer une affaire
     */
    private void cloturerAffaire() {
        // todo
        int idAffaireSelectionnee = 0;
        HashMap<Integer, AffaireTransient> listeSelectionAffaire = new HashMap<>();
        AffaireTransient affaireSelectionnee = null;
        boolean confirmerCloture = false;
        
        // Affiche les affaires à cloturer
        CLICA.afficherInformation("Sélectionner une affaire avec son id : ");
        ArrayList<AffaireTransient> affaires = this.gestionAffaireRemote.affairesPourUnChargerAffaireACloturer(this.chargerAffaire.getId());
        if(affaires.isEmpty()) {
            CLICA.afficherInformation("\tAucune affaire à cloturer");
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
        
        // Confirmer la cloture de l'affaire
        confirmerCloture = CLICA.yesNoQuestion(scanner, "Confirmer la cloture de l'affaire n° : " + affaireSelectionnee.getIdAffaire() + " | client :"  + affaireSelectionnee.getNomC() + " ? (y/n) ");
        if(confirmerCloture) {
            try {
                this.gestionAffaireRemote.cloturerAffaire(idAffaireSelectionnee);
                CLICA.afficherInformationFinEtape("Affaire n° " + idAffaireSelectionnee + " CLOTUIREE avec succès !");
            } catch (AffaireInconnueException ex) {
                CLICA.afficherMessageErreur(ex.getMessage());
                Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
             CLICA.afficherInformationFinEtape("Cloture de l'affaire n° " + idAffaireSelectionnee +" ANNULEE");
        }        
    }
    
    /**
     * Initialise la lecture des notifications que peut recevoir le Charger d'affaire authentifié
     */
    private void lireNotificationAffaire() {                 
        try {
            // Création du listener
            this.notificationAffaireListener = new NotificationAffaireListener();
            
            // Creation du context JNDI
            Context context = new InitialContext();

            // Récupère la Connection Factory
            ConnectionFactory factory = (ConnectionFactory) context.lookup(FACTORY_NAME);
            
            // Récupère la destination
            Destination dest = (Destination) context.lookup(DEST_NAME);

            // Création de la connexion
            Connection connection = factory.createConnection();

            // Création de la session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Génération de la chaine qui permet de filtrer les message selon leur JMSType (affaire suivi par le charger d'affaire authentifier)
            String jmsType = "JMSType IN ('" + this.chargerAffaire.getId() + "')";
            
            // Création du consomateur et du type des messages qu'il souhaite recevoir
            MessageConsumer consumer = session.createConsumer(dest, jmsType);
            
            // Enregistre un "listener" pour écouter et lire les messages
            consumer.setMessageListener(this.notificationAffaireListener);
            
            // Débute la connexion pour recevoir les messages
            connection.start();        
        } catch (NamingException | JMSException ex) {
            System.out.println("coucou erreur");
            CLICA.afficherMessageErreur(ex.getMessage());
            Logger.getLogger(AppCACLI.class.getName()).log(Level.SEVERE, null, ex);
        }
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
