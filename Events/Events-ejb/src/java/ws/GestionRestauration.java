/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import messages.Nommage;
import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class GestionRestauration implements MessageListener {
    
    static final Logger logger = Logger.getLogger("GestionRestauration");
    
    public GestionRestauration() {
    }
    
    @Override
    public void onMessage(Message message) {
        logger.log(Level.INFO, "Message reçu Gestion Restauration", "Message");
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;


                    // Traitement


                    // 
                    
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}