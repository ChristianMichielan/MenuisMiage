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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import miage.m2.exceptions.AucuneEquipePoseurException;
import miage.m2.exceptions.EquipePoseurInconnuException;
import miage.m2.exceptions.PoseurConfirmRDVException;
import miage.m2.exceptions.PoseurDemandeRDVException;
import miage.m2.transientobjects.RDVPoseurTransient;

/**
 * REST Web Service - Service Poseur - rdvposeur
 *
 * @author QuentinDouris
 */
@Path("rdvposeur")
@RequestScoped
public class RdvposeurResource {

    miage.m2.serviceposeur.services.RDVPoseurServiceLocal rdvPoseurService = lookupRDVPoseurServiceLocal();

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of RdvposeurResource
     */
    public RdvposeurResource() {
    }

    /**
     * Obtenir un RDV pour une equipe poseur disponible à une date définie selon la disponibilité du client
     * url : http://localhost:8080/ServicePoseur-web/webresources/rdvposeur/20-01-2022
     * @param date
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("date") String date) {
        try {
            return Response.ok(this.rdvPoseurService.obtenirRdvPoseur(date)).build();
        } catch (PoseurDemandeRDVException|AucuneEquipePoseurException ex) {
            Logger.getLogger(RdvposeurResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /**
     * Ajoute un RDV pour une equipe Poseur
     * url : http://localhost:8080/ServicePoseur-web/webresources/rdvposeur/
     * @param daterdv
     * @param idequipeposeur
     * @param localisation
     * @param idaffaire
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(@QueryParam("daterdv") String daterdv, @QueryParam("idequipeposeur") String idequipeposeur, @QueryParam("localisation") String localisation, @QueryParam("idaffaire") String idaffaire) {
        try {
            // Cast les paramètres pour les mettre dans le bon type
            int idEquipePoseurParam = Integer.parseInt(idequipeposeur);
            int idAffaireParam = Integer.parseInt(idaffaire);
            
            // Création de l'objet transient
            RDVPoseurTransient rdv = new RDVPoseurTransient(daterdv, idEquipePoseurParam, localisation, idAffaireParam);
            
            // Valide auprès du service
            return Response.ok(this.rdvPoseurService.valideRDVPoseur(rdv)).build();
        } catch (NumberFormatException | PoseurConfirmRDVException | EquipePoseurInconnuException ex) {
            Logger.getLogger(RdvposeurResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }
    }

    /**
     * Configure le service
     * @return 
     */
    private miage.m2.serviceposeur.services.RDVPoseurServiceLocal lookupRDVPoseurServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (miage.m2.serviceposeur.services.RDVPoseurServiceLocal) c.lookup("java:global/ServicePoseur-ear/ServicePoseur-ejb-1.0-SNAPSHOT/RDVPoseurService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
