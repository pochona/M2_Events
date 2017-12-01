/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import exception.SalleException;
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
import javax.jms.Queue;
import javax.jms.Topic;
import messages.Nommage;
import messages.Projet;
import messages.Salle;
import singleton.SalleSingleton;
import static ws.GPListener_Demande.logger;
import static ws.GestionRestauration.logger;

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

    @EJB
    private SalleSingleton salleSingleton;
    
    @Inject
    private JMSContext context;
    
    @Resource(mappedName = Nommage.QUEUE_SALLE)
    private Queue queueReponse;
    
    static final Logger logger = Logger.getLogger("GestionSalle");
    
    public GestionSalle() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            if(message.getJMSType().equals(Nommage.MSG_RESA_SALLE)){
                this.traiterDemande(message);
            } else if(message.getJMSType().equals(Nommage.MSG_ANNUL_SALLE)) {
                this.traiterAnnulation(message);
            }
        } catch (JMSException ex) {
            Logger.getLogger(GPListener_Demande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reserverSalle(Projet projet){
        Salle salle;
        // On essaye de récuperer une salle dispo
        try {
            salle = salleSingleton.recupererSalleDispo(projet.getDate(), projet.getParticipants(), projet.getType_presta());
            salle.setOccupation(projet.getDate());
            logger.log(Level.INFO, "Salle dispo : " + salle, "Message");
            projet.setSalle(salle);
        } catch (SalleException ex) {
            logger.log(Level.INFO, ex.toString());
        }
    }
    
    public void annulerSalle(Projet projet){
        //salleSingleton.annulerSalle(projet.getSalle(), projet);
    }
    
    public void traiterDemande(Message message) throws JMSException{
        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            Object obj = om.getObject();
            if (obj instanceof Projet) {
                // Récupération d'un objet projet
                Projet projet = (Projet) obj;
                logger.log(Level.INFO, "----Reservation de salle----", "Message");
                // Traitement
                reserverSalle(projet);

               // Transmission au Topic Projet
                Message m = context.createObjectMessage(projet);
                m.setJMSType(Nommage.MSG_PROJET);
                
                // Transmission au Topic Projet
                context.createProducer().send(queueReponse, m);
            }
       }
    }
    
    public void traiterAnnulation(Message message) throws JMSException{
        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            Object obj = om.getObject();
            if (obj instanceof Projet) {
                // Récupération d'un objet projet
                Projet projet = (Projet) obj;
                logger.log(Level.INFO, "----Annulation de salle----", "Message");
                // Traitement
                this.annulerSalle(projet);
                
                Message m = context.createObjectMessage(projet);
                m.setJMSType(Nommage.MSG_ANNULATION);

                // Transmission au Topic Projet
                context.createProducer().send(queueReponse, m);
            }
       }
    }
    
}
