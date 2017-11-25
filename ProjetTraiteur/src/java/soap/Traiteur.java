/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Caro
 */
@WebService(serviceName = "Traiteur")
@Stateless()
public class Traiteur {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserverTraiteur")
    public boolean reserverTraiteur(@WebParam(name = "refProjet") String refProjet, @WebParam(name = "dateEvent") Date dateEvent, @WebParam(name = "participants") int participants) {
        //TODO write your implementation code here:
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "annulerTraiteur")
    public boolean annulerTraiteur(@WebParam(name = "refProjet") String refProjet, @WebParam(name = "dateEvent") Date dateEvent) {
        //TODO write your implementation code here:
        return true;
    }
}
