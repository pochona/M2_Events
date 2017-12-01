/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ressources;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import messages.Projet;
import singleton.ProjetSingleton;


/**
 * REST Web Service
 *
 * @author Babas
 */
@Path("reservation")
public class ReservationResource {

    ProjetSingleton projetSingleton = lookupProjetSingletonBean();
    
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
    public ArrayList getJson() {
        return projetSingleton.retournerProjets();
    }

   /**
     * POST method for updating or creating an instance of CommandeResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postJson(Projet p) {
        Projet p1 = returnProjet(p);
        String str = projetSingleton.demanderPrestation(p1);
        Gson g = new Gson();
        String json = g.toJson(str);
        return json;
    }
    
    public Projet returnProjet(Projet p) {
        String nom = p.getNom();
        String coord = p.getCoord();
        String manif = p.getManif();
        String type_manif = p.getType_manif();
        int participants = p.getParticipants();
        Date date = p.getDate();
        String tranche_deb = p.getTranche_deb();
        String tranche_fin = p.getTranche_fin();
        String type_presta = p.getType_presta();
        String cocktail_prepare = "";
        String trois_aperitifs = "";
        String blanc_sec = "";
        String champagne = "";
        String cremant = "";
        String rouge = "";
        
        if(p.getCocktail_prepare() != null) {
            cocktail_prepare = "Cocktail prepare";
        }
        if(p.getTrois_aperitifs() != null) {
            trois_aperitifs = "3 apéritifs";
        }
        if(p.getBlanc_sec() != null) {
            blanc_sec = "Blanc sec";
        }
        if(p.getChampagne() != null) {
            champagne = "Champagne";
        }
        if(p.getCremant() != null) {
            cremant = "Crémant";
        }
        if(p.getRouge() != null) {
            rouge = "Rouge";
        }
        
        System.out.println("Résultat : " + nom + " " + coord + " " + manif + " " + type_manif + " " + participants + " " + date + " " + tranche_deb + " " + tranche_fin + " " +
        type_presta + " " + cocktail_prepare + " " + trois_aperitifs + " " + blanc_sec +  " " + champagne + " " + cremant + " " + rouge);
        
        Projet p1 = new Projet(nom,coord,manif,type_manif, participants, date, tranche_deb, tranche_fin, type_presta, p.getCocktail_prepare(), p.getTrois_aperitifs(), p.getBlanc_sec()
        ,p.getCremant(), p.getChampagne(), p.getRouge());
        
        return p1;
        
    }
    
    private ProjetSingleton lookupProjetSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProjetSingleton) c.lookup("java:global/Events/Events-ejb/ProjetSingleton!singleton.ProjetSingleton");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
