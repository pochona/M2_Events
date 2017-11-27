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
    
    @Resource(mappedName = Nommage.TOPIC_PROJET)
    private Topic topicReponse;
    
    @EJB
    private GestionProjetLocal gestionProjet;
    
    static final Logger logger = Logger.getLogger("GestionProjet");
    
    public GPListener_Salle() {
    }
    
    @Override
    public void onMessage(Message message) {
        logger.log(Level.INFO, "GP Salle Message recu"+message, "Message");
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;
                    logger.log(Level.INFO, "GP Salle "  + projet.getReference(), "Message");
                    // Traitement
                    gestionProjet.traiterSalleAttribue(projet);

                    // Transmission au Topic Projet
                    context.createProducer().send(topicReponse, projet);
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GPListener_Salle.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
}
