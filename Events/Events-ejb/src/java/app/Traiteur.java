package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceClient;

/**
 *
 * @author Caro
 */
@WebServiceClient(name = "Traiteur", targetNamespace = "http://traiteur/", wsdlLocation = "http://localhost:8080/TraiteurWeb/Traiteur?wsdl")

public class Traiteur {
    
//@WebService (serviceName = "Traiteur")

    private static final Logger LOG = Logger.getLogger(Traiteur.class.getName());
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserverTraiteur")
    public boolean reserverTraiteur(@WebParam(name = "refProjet") String refProjet, @WebParam(name = "dateEvent") Date dateEvent, @WebParam(name = "numInvite") int numInvite) {
        LOG.info("Traiteur commandé pour le projet " + refProjet + "qui aura lieu le " + dateEvent);
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "annulerTraiteur")
    public boolean annulerTraiteur(@WebParam(name = "refProjet") String refProjet, @WebParam(name = "dateEvent") Date dateEvent) {
        LOG.info("Traiteur decommandé ");
        return true;
    }

    
    
}
