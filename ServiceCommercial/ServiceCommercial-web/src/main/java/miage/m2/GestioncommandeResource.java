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
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.m2.exceptions.CommercialConfirmRDVException;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.transientobjects.RDVCommercialTransient;

/**
 * REST Web Service
 *
 * @author ChristianMichielan
 */
@Path("gestioncommande")
@RequestScoped
public class GestioncommandeResource {

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
            return Response.status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE).build();
        }
    }
}
