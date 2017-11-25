/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.HashMap;
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
    
    private final HashMap<Integer, Salle> salles;
 
    public SalleSingleton() {
        salles = new HashMap<>();
         Salle s1 = new Salle(1, 150, "simple", false);
         Salle s2 = new Salle(2, 100, "simple", false);
         Salle s3 = new Salle(3, 300, "simple", true);
         Salle s4 = new Salle(4, 150, "importante", true);
         Salle s5 = new Salle(5, 300, "importante", true);
         Salle s6 = new Salle(6, 150, "simple", true);
         salles.put(s1.getNumeroSalle(), s1);
         salles.put(s2.getNumeroSalle(), s2);
         salles.put(s3.getNumeroSalle(), s3);
         salles.put(s4.getNumeroSalle(), s4);
         salles.put(s5.getNumeroSalle(), s5);
         salles.put(s6.getNumeroSalle(), s6);
    }
        
    public Salle réserverSalle(int capacitéMax, String typePrestation) {
        int numeroSalle = 0;
        switch (numeroSalle) {
            
            case 2 :
                if (capacitéMax <= 100 && (typePrestation == "cocktail" || typePrestation == "lunch"));
                break;
                
            case 1 :
                 if (capacitéMax >100 && capacitéMax <= 150 && (typePrestation == "cocktail" || typePrestation == "lunch"));
                break;
                
            case 3 :
                 if (capacitéMax >150 && capacitéMax <= 300 && (typePrestation == "cocktail" || typePrestation == "lunch"));
                break;
            
            case 4 :
                 if (capacitéMax <= 150 && typePrestation == "repasAssis");
                break;
              
                
            case 5 :
                 if (capacitéMax >150 && capacitéMax <= 300 && typePrestation == "repasAssis");
                break;
                
                
            case 6 :
                 if (capacitéMax >100 && capacitéMax <= 150 && (typePrestation == "cocktail" || typePrestation == "lunch"));
                break;
        }
        Salle s = salles.get(numeroSalle);
        s.setDisponible(false);
        MapMessage message = contextSalle.createMapMessage();
        contextSalle.createProducer().send(queueSalle, s);
        return s;
    }
    
    public String annulerSalle(int numSalle){
        Salle s = salles.get(numSalle);
        s.setDisponible(true);
        return "Salle annulée avec succès";
    }
    
}
