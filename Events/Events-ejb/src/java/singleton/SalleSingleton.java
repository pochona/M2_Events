/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import exception.SalleException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.MapMessage;
import javax.jms.Queue;
import messages.Salle;


/**
 *
 * @author Amaury_PC
 */
@Singleton
@LocalBean
public class SalleSingleton {

    @Resource(lookup = "Event_Salle")
    private Queue queueSalle;
    
    @Inject
    private JMSContext contextSalle;
    
    @EJB
    ProjetSingleton projet;
    
    private HashMap<Integer, Salle> salles;
 
    static final Logger logger = Logger.getLogger("SalleSingleton");
    
   @PostConstruct
    public void initialiser(){
        salles = new HashMap<>();
        Salle s1 = new Salle(1, 50, Salle.SIMPLE, Salle.NON_ESTRADE);
        Salle s2 = new Salle(2, 30, Salle.SIMPLE, Salle.NON_ESTRADE);
        Salle s3 = new Salle(3, 60, Salle.SIMPLE, Salle.ESTRADE);
        Salle s4 = new Salle(4, 100, Salle.IMPORTANT, Salle.ESTRADE);
        Salle s5 = new Salle(5, 120, Salle.IMPORTANT, Salle.ESTRADE);
        Salle s6 = new Salle(6, 20, Salle.SIMPLE, Salle.ESTRADE);
        salles.put(s1.getNumeroSalle(), s1);
        salles.put(s2.getNumeroSalle(), s2);
        salles.put(s3.getNumeroSalle(), s3);
        salles.put(s4.getNumeroSalle(), s4);
        salles.put(s5.getNumeroSalle(), s5);
        salles.put(s6.getNumeroSalle(), s6);
    }
        
    public Salle recupererSalleDispo(Date d, int capacite, String typePrestation) throws SalleException {
        // Récupération de la liste des salles 
        HashMap<Integer, Salle> salleDispo = (HashMap<Integer, Salle>) salles.clone();
        Iterator itSalle = salleDispo.entrySet().iterator();
        
        // boolean qui permet de vérifier si on a trouvé une salle
        boolean salleTrouve = false;
        // salle trouvé et valide
        Salle salleCourante = null;
        // On regarde pour chaque salle si elle est dispo ou non
        while(!salleTrouve){
            if(!itSalle.hasNext()){
                throw new SalleException("Plus de salle disponible à la salle "+d);
            }
            Map.Entry pair = (Map.Entry) itSalle.next();
            salleCourante = (Salle) pair.getValue();
            
            logger.log(Level.INFO, salleCourante.afficheOccupation(), "Message");
            if(salleCourante.isDisponible(d) && salleCourante.getCapacitéMax() > capacite){
                // La salle correspond à la demande, on valide
                salleTrouve = true;
            }
        }
        
        return salleCourante;
    }
    
    public String annulerSalle(int numSalle){
        Salle s = salles.get(numSalle);
        //s.setDisponible(true);
        return "Salle annulée avec succès";
    }
    
}
