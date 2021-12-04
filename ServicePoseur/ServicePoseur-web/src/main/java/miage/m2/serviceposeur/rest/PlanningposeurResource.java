/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.m2.serviceposeur.services.PlanningServiceLocal;

/**
 * REST Web Service - Service Poseur - planningposeur
 *
 * @author QuentinDouris
 */
@Path("planningposeur")
@RequestScoped
public class PlanningposeurResource {

    PlanningServiceLocal planningService = lookupPlanningServiceLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlanningposeurResource
     */
    public PlanningposeurResource() {
    }

    /**
     * Obtenir les plannings pour un commercial
     * url : http://localhost:8080/ServicePoseur-web/webresources/planningposeur/1
     * @param idEquipePoseur
     * @return listeRDV
     */
    @GET
    @Path("{idEquipePoseur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("idEquipePoseur") String idEquipePoseur) {
        try {
            return Response.ok(this.planningService.obtenirPlanning(Integer.parseInt(idEquipePoseur))).build();
        } catch (NumberFormatException ex) {
            Logger.getLogger(RdvposeurResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /**
     * Recherche le service configuré pour interragir avec le système
     * @return 
     */
    private PlanningServiceLocal lookupPlanningServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PlanningServiceLocal) c.lookup("java:global/ServicePoseur-ear/ServicePoseur-ejb-1.0-SNAPSHOT/PlanningService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
