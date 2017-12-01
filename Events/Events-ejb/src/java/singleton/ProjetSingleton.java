/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    @Resource(lookup = Nommage.TOPIC_DEMANDE)
    private Topic topicDemande;
    
    @Inject
    private JMSContext contextProjet;
    
    @EJB
    ProjetSingleton projet;

    private final HashMap<String, Projet> projets = new HashMap<String, Projet>();
    
    
    
    public String demanderPrestation(Projet p) {
        projets.put(p.getReference(), p);
        ObjectMessage message = contextProjet.createObjectMessage(p);
        try {
            message.setJMSType(Nommage.MSG_PROJET);
        } catch (JMSException ex) {
            Logger.getLogger(ProjetSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        contextProjet.createProducer().send(topicDemande, message);
        return p.getReference();
    }
    
    public String annulerPrestation(String ref){
        ObjectMessage message = contextProjet.createObjectMessage(ref);
        try {
            message.setJMSType(Nommage.MSG_ANNULATION);
        } catch (JMSException ex) {
            Logger.getLogger(ProjetSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        contextProjet.createProducer().send(topicDemande, message);
        return "Prestation annulée avec succès";
    }
    
    public String retournerStatut(String ref) {
        return projets.get(ref).getStatut();
    }
}
