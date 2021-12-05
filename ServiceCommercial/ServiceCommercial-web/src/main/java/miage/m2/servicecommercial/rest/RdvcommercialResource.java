/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import miage.m2.sharedmenuis.exceptions.AucunCommercialException;
import miage.m2.sharedmenuis.exceptions.CommercialConfirmRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialDemandeRDVException;
import miage.m2.sharedmenuis.exceptions.CommercialInconnuException;
import miage.m2.sharedmenuis.transientobjects.RDVCommercialTransient;

/**
 * REST Web Service - Service Commercial - rdvcommercial
 *
 * @author QuentinDouris
 */
@Path("rdvcommercial")
@RequestScoped
public class RdvcommercialResource {

    // Lien avec notre service
    miage.m2.servicecommercial.services.RDVCommercialServiceLocal rdvCommercialService = lookupRDVCommercialServiceLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RdvcommercialResource
     */
    public RdvcommercialResource() {
    }

    /**
     * Obtenir un RDV pour un commercial disponible à une date définie selon la disponibilité du client
     * url : http://localhost:8080/ServiceCommercial-web/webresources/rdvcommercial/01-01-2022
     * @param date
     * @return 
     */
    @GET
    @Path("{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("date") String date) {
        try {
            return Response.ok(this.rdvCommercialService.obtenirRdvCommercial(date)).build();
        } catch (AucunCommercialException | CommercialDemandeRDVException ex) {
            Logger.getLogger(RdvcommercialResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.NO_CONTENT).build();
        }
    }
    
    /**
     * Ajoute un RDV pour un commercial
     * url : http://localhost:8080/ServiceCommercial-web/webresources/rdvcommercial/
     * @param daterdv
     * @param idcommercial
     * @param localisation
     * @param idaffaire 
     * @return  
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(@QueryParam("daterdv") String daterdv, @QueryParam("idcommercial") String idcommercial, @QueryParam("localisation") String localisation, @QueryParam("idaffaire") String idaffaire) {
        try {
            // Cast les paramètres pour les mettre dans le bon type
            int idCommercialParam = Integer.parseInt(idcommercial);
            int idAffaireParam = Integer.parseInt(idaffaire);
            
            // Création de l'objet transient
            RDVCommercialTransient rdv = new RDVCommercialTransient(daterdv, idCommercialParam, localisation, idAffaireParam);
            
            // Valide auprès du service
            return Response.ok(this.rdvCommercialService.valideRDVCommercial(rdv)).build();
        } catch (CommercialConfirmRDVException | CommercialInconnuException | NumberFormatException ex) {
            Logger.getLogger(RdvcommercialResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }
    }
    
    
    /**
     * Recherche le service configuré pour interragir avec le système
     * @return 
     */
    private miage.m2.servicecommercial.services.RDVCommercialServiceLocal lookupRDVCommercialServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (miage.m2.servicecommercial.services.RDVCommercialServiceLocal) c.lookup("java:global/ServiceCommercial-ear/ServiceCommercial-ejb-1.0-SNAPSHOT/RDVCommercialService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
