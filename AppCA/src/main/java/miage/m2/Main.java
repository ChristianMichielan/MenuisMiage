/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2;

import javax.naming.NamingException;

/**
 * Classe Main du client lourd des CA
 * @author QuentinDouris
 */
public class Main {
    public static void main (String args[]) {
        try {
            RMIMenuisMiageCA manager = new RMIMenuisMiageCA();
            AppCACLI service = new AppCACLI(manager.getAdminServiceRemote());
            service.run();
        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
