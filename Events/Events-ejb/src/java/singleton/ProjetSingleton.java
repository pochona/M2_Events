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
    
    
    
    public String demanderPrestation(Projet p) throws JMSException {
        projets.add(p);
        ObjectMessage message = contextProjet.createObjectMessage(p);
        message.setJMSType(Nommage.MSG_PROJET);
        contextProjet.createProducer().send(topicProjet, p);
        return p.getReference();
    }
    
    public String annulerPrestation(String ref) throws JMSException{
        projets.remove(projet);
        ObjectMessage message = contextProjet.createObjectMessage(ref);
        message.setJMSType(Nommage.MSG_ANNULATION);
        contextProjet.createProducer().send(topicProjet, ref);
        return "Prestation annulée avec succès";
    }
    
    public ArrayList retournerProjets() {
        return projets;
    }
}
