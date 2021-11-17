/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2;

import java.util.Scanner;

/**
 * Classe CLICA qui représente les interractions du CA avec la console.
 * @author QuentinDouris
 */
public class CLICA {

    /**
     * Premier menu avant connexion de l'utilisateur
     */
    public static void afficherPremierMenu() {
        System.out.println("Menu de séléction");
        System.out.println("\t1. Authentification");
        System.out.println("\t2. Quitter");
    }

    /**
     * Deuxième menu l'utilisateur authentifier peut naviguer sur l'application
     */
    public static void afficherDeuxiemeMenu() {
        System.out.println("Menu de séléction");
        System.out.println("\t1. Consulter les affaires");
        System.out.println("\t2. Créer une affaire");
        System.out.println("\t3. Définir rendez-vous commercial");
        System.out.println("\t4. Définir rendez-vous poseur ");
        System.out.println("\t5. Consulter les notifications");
        System.out.println("\t6. Quitter");
    }
    
    /**
     * Affiche un message d'accueil à l'utilisateur
     */
    public static void afficheDemarage() {
        System.out.println();
        System.out.println("***** Application client lourd des Chargés d'Affaire *****"); 
        System.out.println();
        System.out.println("**********************************************************"); 
        System.out.println("********************** MenuisMIAGE ***********************"); 
        System.out.println("**********************************************************"); 
        System.out.println();
        System.out.println("********************** Bienvenue ! ***********************"); 
        System.out.println();
    }
    
    /**
     * Affiche le titre du choix de l'utilisateur
     * @param titreSection 
     */
    public static void afficherTitreChoix(String titreSection) {
        System.out.println();
        System.out.println("***** " + titreSection.toUpperCase() + " *****");
        System.out.println();
    }
    
    /**
     * Affiche un message d'information à l'utilisateur
     * @param message 
     */
    public static void afficherInformation(String message) {
        System.out.println(message);
    }
    
    /**
     * Affiche un message d'information à l'utilisateur à la fin d'une étape
     * @param message 
     */
    public static void afficherInformationFinEtape(String message) {
        afficherInformation(message);
        System.out.println();
    }
    
    /**
     * Affiche un message d'erreur à l'utilisateur
     * @param message 
     */
    public static void afficherMessageErreur(String message) {
        System.out.println();
        System.out.println("ERREUR : " + message);
        System.out.println();
    }

    /**
     * Demande à l'utilisateur de saisir un entier
     * @param sc
     * @param message
     * @param max
     * @return l'entier saisi par l'utilsateur
     */
    public static int saisirEntier(Scanner sc, String message, int max) {
        do {
            try {
                System.out.println(message);
                int nombre = Integer.parseInt(sc.next());
                if (nombre <= 0 || nombre > max) {
                    throw new NumberFormatException("Nombre saisi incorrect");
                }
                return nombre;
            } catch (NumberFormatException ex){
                System.out.println("Erreur de saisie : " + ex.getMessage());
            }
        } while (true);
    }
    
    /**
     * Demande à l'utilisateur de saisir une chaine de caractère
     * @param sc
     * @param message
     * @return la chaine saisie par l'utilisateur
     */
    public static String saisirChaine(Scanner sc, String message) {
        do {
            System.out.println(message);
            String chaine = sc.next();
            if (chaine.isEmpty()) {
                System.out.println("Erreur de saisie : veuillez saisir au moins un caractère.");
            }
            return chaine;
        } while (true);
    }
    
    /**
     * Demande à l'utilisateur de saisir une date
     * @param sc
     * @param message
     * @return la date saisie par l'utilisateur
     */
    public static String saisirDate(Scanner sc, String message) {
        do {
            System.out.println(message + "(format YYYY-MM-DD)");
            String date = sc.next();

            return date;
        } while (true);
    }
    
    /**
     * Demande la confirmation ou la non confirmation d'une opération à l'utilisateur
     * @param sc
     * @param label
     * @return true si l'utilisateur à répondu 'y'
     */
    public static boolean yesNoQuestion(Scanner sc, String label){
        do {
            String reponse = CLICA.saisirChaine(sc, label).toLowerCase();
            switch (reponse) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Erreur de saisie : veuillez saisir 'y' ou 'n' pour répondre");
            }
        } while (true);
    }
    
    /**
     * Demande à l'utilisateur de confirmer une opération
     * @param sc
     * @param label
     * @return 
     */
    public static boolean yesQuestion(Scanner sc, String label){
        do {
            String reponse = CLICA.saisirChaine(sc, label).toLowerCase();
            if (reponse.equals("y")) {
                return true;
            } else {
                System.out.println("Erreur de saisie : veuillez saisir 'y' ou 'n' pour répondre");
            }
        } while (true);
    }

}
