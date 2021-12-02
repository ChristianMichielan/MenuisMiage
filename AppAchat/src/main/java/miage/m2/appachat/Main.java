/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appachat;

import javax.naming.NamingException;

/**
 * Classe Main du client lourd des Magasinier
 * @author QuentinDouris
 */
public class Main {
     public static void main (String args[]) {
        try {
            RMIMenuisMiageAchat manager = new RMIMenuisMiageAchat();
            AppAchatCLI service = new AppAchatCLI(manager.getAdminServiceRemote());
            service.run();
        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
