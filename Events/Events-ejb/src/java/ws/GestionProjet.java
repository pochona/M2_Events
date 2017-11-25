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

/**
 *
 * @author Amaury_PC
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = Nommage.TOPIC_DEMANDE)
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Nommage.TOPIC_DEMANDE)
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = Nommage.TOPIC_DEMANDE)
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class GestionProjet implements MessageListener {
    
    @Inject
    private JMSContext context;
    
    @Resource(mappedName = Nommage.TOPIC_PROJET)
    private Topic topicReponse;
    
    static final Logger logger = Logger.getLogger("GestionProjet");
    
    public GestionProjet() {
    }
    
    @Override
    public void onMessage(Message message) {
        logger.log(Level.INFO, "Message reçu GestionProjet", "Message");
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;

                    // Traitement


                    // Transmission au Topic Projet
                    context.createProducer().send(topicReponse, projet);
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}