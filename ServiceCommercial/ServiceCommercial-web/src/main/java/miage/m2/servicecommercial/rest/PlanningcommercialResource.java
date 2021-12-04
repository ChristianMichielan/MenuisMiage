/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.rest;

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
import miage.m2.exceptions.AucunPlanningCommercialException;
import miage.m2.servicecommercial.services.PlanningServiceLocal;

/**
 * REST Web Service - Service Commercial - planningcommercial
 *
 * @author ChristianMichielan
 */
@Path("planningcommercial")
@RequestScoped
public class PlanningcommercialResource {

    PlanningServiceLocal planningService = lookupPlanningServiceLocal();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlanningcommercialResource
     */
    public PlanningcommercialResource() {
    }

    /**
     * Obtenir les plannings pour un commercial
     * url : http://localhost:8080/ServiceCommercial-web/webresources/planningcommercial/1
     * @param idCommercial
     * @return listeRDV
     */
    @GET
    @Path("{idCommercial}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("idCommercial") String idCommercial) {
        try {
            return Response.ok(this.planningService.obtenirPlanning(Integer.parseInt(idCommercial))).build();
        } catch (AucunPlanningCommercialException ex) {
            Logger.getLogger(RdvcommercialResource.class.getName()).log(Level.SEVERE, null, ex);
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
            return (PlanningServiceLocal) c.lookup("java:global/ServiceCommercial-ear/ServiceCommercial-ejb-1.0-SNAPSHOT/PlanningService!miage.m2.servicecommercial.services.PlanningServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
