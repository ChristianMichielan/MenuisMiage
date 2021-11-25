/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.m2.messages.CommandeMessageBeanLocal;

/**
 * REST Web Service
 *
 * @author ChristianMichielan
 */
@Path("gestioncommande")
@RequestScoped
public class GestioncommandeResource {

    CommandeMessageBeanLocal commandeMessageBean = lookupCommandeMessageBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GestioncommandeResource
     */
    public GestioncommandeResource() {
    }

    /**
     * Ajoute une commande
     * url : http://localhost:8080/ServiceCommercial-web/webresources/commande/
     * @param : refCatCmd
     * @param : coteLargeurCmd
     * @param : coteLongueurCmd
     * @param : double montantNegoCmd
     * @param : idAffaire
     * @param : commercial
     * @return  
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(@QueryParam("daterdv") String daterdv, @QueryParam("idcommercial") String idcommercial, @QueryParam("localisation") String localisation, @QueryParam("idaffaire") String idaffaire) {
        // To do faire la insérer la commande dans le système et envoyer le message par l'intermédiaire du bean
        
        return null;
    }

    /**
     * Recherche le service configuré pour interragir avec le système
     * @return 
     */
    private CommandeMessageBeanLocal lookupCommandeMessageBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CommandeMessageBeanLocal) c.lookup("java:global/ServiceCommercial-ear/ServiceCommercial-ejb-1.0-SNAPSHOT/CommandeMessageBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
