/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
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
        logger.log(Level.INFO, "Message reçu Gestion Restauration : "  + message, "Message");
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;


                    // Traitement

                    if (reserverTraiteur(projet.getManif(), projet.getDate(), projet.getParticipants())){
                        logger.log(Level.INFO, "Traiteur  reservé ");
                    }
                    else {
                        logger.log(Level.INFO, "Traiteur  occupé ");
                    }
                    // 
                    
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
             } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
       private static boolean reserverTraiteur(String refProjet, Date dateEvent, int participants) throws DatatypeConfigurationException {
        app.Traiteur_Service service = new app.Traiteur_Service();
        app.Traiteur port = service.getTraiteurPort();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateEvent);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return port.reserverTraiteur(refProjet, date2, participants);
    }
    
     private static boolean annulerTraiteur(String refProjet, Date dateEvent) throws DatatypeConfigurationException {
        app.Traiteur_Service service = new app.Traiteur_Service();
        app.Traiteur port = service.getTraiteurPort();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateEvent);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return port.annulerTraiteur(refProjet, date2);
    }
    
}
