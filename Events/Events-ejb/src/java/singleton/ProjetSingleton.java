/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import messages.Projet;


/**
 *
 * @author Amaury_PC
 */
@Singleton
@LocalBean
public class ProjetSingleton {
    
    @Resource(lookup = "Event_Demande")
    private Topic topicProjet;
    
    @Inject
    private JMSContext contextProjet;
    
    @EJB
    ProjetSingleton projet;

    private final ArrayList<Projet> projets = new ArrayList<>();
    
    
    
    public String demanderPrestation(Projet p) {
        projets.add(p);
        ObjectMessage message = contextProjet.createObjectMessage(p);
        contextProjet.createProducer().send(topicProjet, p);
        System.out.println("ref = " + p.getReference());
        return p.getReference();
    }
    
    public String annulerPrestation(String ref){
        projets.remove(projet);
        return "Prestation annulée avec succès";
    }
}
