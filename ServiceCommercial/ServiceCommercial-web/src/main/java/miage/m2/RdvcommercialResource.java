/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2;

import com.google.gson.Gson;
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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author QuentinDouris
 */
@Path("rdvcommercial")
@RequestScoped
public class RdvcommercialResource {

    miage.m2.services.RDVCommercialServiceLocal rDVCommercialService = lookupRDVCommercialServiceLocal();

    @Context
    private UriInfo context;
    
    private Gson gson;
    

    /**
     * Creates a new instance of RdvcommercialResource
     */
    public RdvcommercialResource() {
    }

    /**
     * Retrieves representation of an instance of miage.m2.RdvcommercialResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RdvcommercialResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private miage.m2.services.RDVCommercialServiceLocal lookupRDVCommercialServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (miage.m2.services.RDVCommercialServiceLocal) c.lookup("java:global/ServiceCommercial-ear/ServiceCommercial-ejb-1.0-SNAPSHOT/RDVCommercialService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
