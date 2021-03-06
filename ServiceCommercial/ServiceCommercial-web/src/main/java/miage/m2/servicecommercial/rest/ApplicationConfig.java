/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author QuentinDouris
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(miage.m2.servicecommercial.rest.GestioncommandeResource.class);
        resources.add(miage.m2.servicecommercial.rest.PlanningcommercialResource.class);
        resources.add(miage.m2.servicecommercial.rest.RdvcommercialResource.class);
    }
    
}
