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
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.messages.CommandeMessageBeanLocal;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;

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
     * @param refCatCmd
     * @param coteLargeurCmd
     * @param coteLongueurCmd
     * @param montantNegoCmd
     * @param idAffaire
     * @param idCommercial
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(@QueryParam("refcatcmd") String refCatCmd, @QueryParam("cotelargeurcmd") String coteLargeurCmd, @QueryParam("cotelongueurcmd") String coteLongueurCmd, @QueryParam("montantnegocmd") String montantNegoCmd, @QueryParam("idaffaire") String idAffaire, @QueryParam("idcommercial") String idCommercial) {
        try {
            // Conversion des paramètres
            double coteLargeurCmdParam = Double.parseDouble(coteLargeurCmd);
            double coteLongueurCmdParam = Double.parseDouble(coteLongueurCmd);
            double montantNegoCmdParam = Double.parseDouble(montantNegoCmd);
            int idAffaireParam = Integer.parseInt(idAffaire);
            int idCommercialParam = Integer.parseInt(idCommercial);
            
            // Enregistrement de la commande dans le système
            return Response.ok(this.commandeMessageBean.saisirCommande(refCatCmd, coteLargeurCmdParam, coteLongueurCmdParam, montantNegoCmdParam, idAffaireParam, idCommercialParam)).build();
        } catch (SaisirCommandeException | CommercialInconnuException ex) {
            Logger.getLogger(RdvcommercialResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }
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
