/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.appachat;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.m2.serviceachat.exposition.GestionLivraisonRemote;

/**
 * Classe RMIMenuisMiageAchat qui permet de construire l'accès RMI.
 * @author QuentinDouris
 */
public class RMIMenuisMiageAchat {
    private final static String GLASSFISH_ORB_HOST = "localhost";
    // A modifier selon la config (GlassFish admin console / Configurations / server-config / ORB / IIOP Listener / orb-listener
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String GESTION_LIVRAISON_EJB_URI = "java:global/ServiceAchat-ear/ServiceAchat-ejb-1.0-SNAPSHOT/GestionLivraison";

    private InitialContext namingContext;
    private GestionLivraisonRemote gestionLivraisonRemote;
    
    /**
     * Constructeur de RMIMenuisMiageCA
     * @throws NamingException 
     */
    public RMIMenuisMiageAchat() throws NamingException {
        // Initialisation du JNDI
        Properties jndiProperties = new Properties();
        jndiProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        jndiProperties.setProperty("org.omg.CORBA.ORBInitialHost", GLASSFISH_ORB_HOST);
        jndiProperties.setProperty("org.omg.CORBA.ORBInitialPort", GLASSFISH_ORB_PORT);
        this.namingContext = new InitialContext(jndiProperties);
        // Récupération du service
        this.gestionLivraisonRemote = (GestionLivraisonRemote) this.namingContext.lookup(GESTION_LIVRAISON_EJB_URI);
    }
    
    /**
     * Retourne la GestionLivraisonRemote
     * @return 
     */
    public GestionLivraisonRemote getAdminServiceRemote() {
        return gestionLivraisonRemote;
    }
}
