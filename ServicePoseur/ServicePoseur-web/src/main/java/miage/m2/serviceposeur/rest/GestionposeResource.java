/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceposeur.rest;

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
import miage.m2.exceptions.AucunRDVPoseur;
import miage.m2.serviceposeur.services.GestionPoseServiceLocal;

/**
 * REST Web Service - Service Poseur - gestionpose
 *
 * @author QuentinDouris
 */
@Path("gestionpose")
@RequestScoped
public class GestionposeResource {

    GestionPoseServiceLocal gestionPoseService = lookupGestionPoseServiceLocal();
    
    @Context
    private UriInfo context;

    /**
     * Constructeur
     */
    public GestionposeResource() {
    }

    /**
     * Valide la pose à la suite d'un rendez-vous poseur pour une affaire
     * url : http://localhost:8080/ServicePoseur-web/webresources/gestionpose/
     * @param idAffaire
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(@QueryParam("idaffaire") String idAffaire) {
        try {
            int idAffaireParam = Integer.parseInt(idAffaire);
            return Response.ok(this.gestionPoseService.validerPose(idAffaireParam)).build();
        } catch (AucunRDVPoseur ex) {
            Logger.getLogger(GestionposeResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /**
     * Recherche le service configuré pour interragir avec le système - GestionPoseService
     * @return 
     */
    private GestionPoseServiceLocal lookupGestionPoseServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionPoseServiceLocal) c.lookup("java:global/ServicePoseur-ear/ServicePoseur-ejb-1.0-SNAPSHOT/GestionPoseService!miage.m2.serviceposeur.services.GestionPoseServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
