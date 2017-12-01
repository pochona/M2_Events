/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import messages.Nommage;
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
        try {
            message.setJMSType(Nommage.MSG_PROJET);
        } catch (JMSException ex) {
            Logger.getLogger(ProjetSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        contextProjet.createProducer().send(topicProjet, p);
        return p.getReference();
    }
    
    public String annulerPrestation(String ref){
        projets.remove(projet);
        ObjectMessage message = contextProjet.createObjectMessage(ref);
        try {
            message.setJMSType(Nommage.MSG_ANNULATION);
        } catch (JMSException ex) {
            Logger.getLogger(ProjetSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        contextProjet.createProducer().send(topicProjet, ref);
        return "Prestation annulée avec succès";
    }
    
    public ArrayList retournerProjets() {
        return projets;
    }
}
