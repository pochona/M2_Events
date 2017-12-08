/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import messages.Nommage;
import messages.Projet;
import singleton.ProjetSingleton;
import static ws.GPListener_Confirmation.logger;

/**
 *
 * @author Amaury_PC
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = Nommage.QUEUE_SALLE)
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Nommage.QUEUE_SALLE)
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = Nommage.QUEUE_SALLE)
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class GPListener_Salle implements MessageListener {
    
    @Inject
    private JMSContext context;
    
    @EJB
    ProjetSingleton projetSingleton;
    
    @Resource(mappedName = Nommage.TOPIC_PROJET)
    private Topic topicProjet;
    
    @EJB
    private GestionProjetLocal gestionProjet;
    
    static final Logger logger = Logger.getLogger("GestionProjet");
    
    public GPListener_Salle() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            if(message.getJMSType().equals(Nommage.MSG_PROJET)){
                this.traiterDemande(message);
            } else {
                this.traiterAnnulation(message);
            }
        } catch (JMSException ex) {
            Logger.getLogger(GPListener_Demande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void traiterDemande(Message message) throws JMSException{
        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            Object obj = om.getObject();
            if (obj instanceof Projet) {
                // Récupération du projet
                Projet projet = projetSingleton.findProjet(((Projet) obj).getReference());
                logger.log(Level.INFO, "---Salle reservée, poursuite du traitement---", "Message");
                
                // Traitement
                gestionProjet.traiterSalleAttribue(projet);

               // Transmission au Topic Projet
                Message m = context.createObjectMessage(projet);
                m.setJMSType(Nommage.MSG_RESA_RESTAURATION);
                
                // Transmission au Topic Projet
                context.createProducer().send(topicProjet, m);
            }
        }
    }
    
    public void traiterAnnulation(Message message) throws JMSException{
        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            Object obj = om.getObject();
            if (obj instanceof Projet) {
                // Récupération du projet
                Projet projet = projetSingleton.findProjet(((Projet) obj).getReference());
                logger.log(Level.INFO, "---Salle annulée, poursuite du traitement---", "Message");
                // Traitement
                

                // Transmission au Topic Projet
                Message m = context.createObjectMessage(projet);
                m.setJMSType(Nommage.MSG_ANNUL_RESTAURATION);
                context.createProducer().send(topicProjet, m);
            }
        }
    }
}
