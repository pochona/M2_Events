/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ressources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import singleton.ProjetSingleton;
import use.Projet;


/**
 * REST Web Service
 *
 * @author Babas
 */
@Path("reservation")
public class ReservationResource {

    ProjetSingleton articlesSingleton = lookupProjetSingletonBean();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservationResource
     */
    public ReservationResource() {
    }

    /**
     * Retrieves representation of an instance of ressources.ReservationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

   /**
     * POST method for updating or creating an instance of CommandeResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postJson(Projet p) {
        System.out.println("yooooooooooooooooooooo" + p.getNom());
        return Response.noContent().build();
    }
    
    private ProjetSingleton lookupProjetSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProjetSingleton) c.lookup("java:global/ProjetEvents/ProjetEvents-ejb/ProjetSingleton!singleton.ProjetSingleton");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
