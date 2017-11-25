/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import exception.SalleException;
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
import messages.Salle;
import singleton.SalleSingleton;

/**
 *
 * @author Amaury_PC
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = Nommage.TOPIC_PROJET)
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class GestionSalle implements MessageListener {
    
    static final Logger logger = Logger.getLogger("GestionSalle");
    
    public GestionSalle() {
    }
    
    @Override
    public void onMessage(Message message) {
        logger.log(Level.INFO, "Message reçu Gestion Salle : " + message, "Message");
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;


                    // Traitement
                    SalleSingleton s = new SalleSingleton();
                    Salle salle;
                    // On essaye de récuperer une salle dispo
                    try {
                        salle = s.recupererSalleDispo(projet.getDate(), projet.getParticipants(), projet.getType_manif());
                        salle.setOccupation(projet.getDate());
                        logger.log(Level.INFO, "Salle dispo : " + salle, "Message");
                        projet.setSalle(salle);
                    } catch (SalleException ex) {
                        logger.log(Level.INFO, ex.toString());
                    }
                    
                    // 
                    
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionSalle.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}
