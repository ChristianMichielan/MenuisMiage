/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.m2;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return null;
    }
}
