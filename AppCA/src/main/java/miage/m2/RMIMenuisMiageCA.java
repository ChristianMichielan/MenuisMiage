/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2;

import miage.m2.exposition.GestionAffaireRemote;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Classe RMIMenuisMiageCA qui permet de construire l'accès RMI.
 * @author QuentinDouris
 */
public class RMIMenuisMiageCA {
    private final static String GLASSFISH_ORB_HOST = "localhost";
    // A modifier selon la config (GlassFish admin console / Configurations / server-config / ORB / IIOP Listener / orb-listener
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String GESTION_AFFAIRE_EJB_URI = "java:global/ServiceChargeAffaire-ear-1.0-SNAPSHOT/ServiceChargeAffaire-ejb-1.0-SNAPSHOT/GestionAffaire!miage.m2.exposition.GestionAffaireRemote";

    private InitialContext namingContext;
    private GestionAffaireRemote gestionAffaireRemote;
    
    /**
     * Constructeur de RMIMenuisMiageCA
     * @throws NamingException 
     */
    public RMIMenuisMiageCA() throws NamingException {
        // Initialisation du JNDI
        Properties jndiProperties = new Properties();
        jndiProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        jndiProperties.setProperty("org.omg.CORBA.ORBInitialHost", GLASSFISH_ORB_HOST);
        jndiProperties.setProperty("org.omg.CORBA.ORBInitialPort", GLASSFISH_ORB_PORT);
        this.namingContext = new InitialContext(jndiProperties);
        // Récupération du service
        this.gestionAffaireRemote = (GestionAffaireRemote) this.namingContext.lookup(GESTION_AFFAIRE_EJB_URI);
    }
    
    /**
     * Retourne la GestionAffaireRemote
     * @return 
     */
    public GestionAffaireRemote getAdminServiceRemote() {
        return gestionAffaireRemote;
    }
}
