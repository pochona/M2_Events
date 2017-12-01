/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import exception.TraiteurExterneException;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import messages.Nommage;
import messages.Projet;
import singleton.BoissonsSingleton;

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
    
    @EJB
    private BoissonsSingleton boissonsSingleton;
    
    @Inject
    private JMSContext context;
    
    @Resource(mappedName = Nommage.TOPIC_CONFIRMATION)
    private Topic topicReponse;
    
    public GestionRestauration() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
             try {
                 ObjectMessage om = (ObjectMessage) message;
                 Object obj = om.getObject();
                 if (obj instanceof Projet) {
                    // Récupération d'un objet projet
                    Projet projet = (Projet) obj;
                    if(projet.hasSalle()){
                        logger.log(Level.INFO, "G. Restauration "  + projet.getReference(), "Message");

                        try {
                            // Traitement
                            this.traiterRestauration(projet);
                        } catch (TraiteurExterneException ex) {
                            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        // Retour dans Confirmation et propagation JMSType
                        //Appel des méthodes SOAP de Traiteur
                        if (message.getJMSType().equals(Nommage.MSG_PROJET))
                        {
                            try {
                                reserverTraiteur(projet.getReference(), projet.getDate(), projet.getParticipants());
                            } catch (DatatypeConfigurationException ex) {
                                Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Message m = context.createObjectMessage(projet);
                            m.setJMSType(Nommage.MSG_PROJET);
                            context.createProducer().send(topicReponse, m);
                        }
                        else {
                            try {
                                annulerTraiteur(projet.getReference(), projet.getDate());
                            } catch (DatatypeConfigurationException ex) {
                                Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Message m = context.createObjectMessage(projet);
                            m.setJMSType(Nommage.MSG_ANNULATION);
                            context.createProducer().send(topicReponse, m);
                        } 
                    }
                 }
             } catch (JMSException ex) {
                 Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
            /* } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);*/
            }
        }
    }
    
       private boolean reserverTraiteur(String refProjet, Date dateEvent, int participants) throws DatatypeConfigurationException {
        app.Traiteur_Service service = new app.Traiteur_Service();
        app.Traiteur port = service.getTraiteurPort();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateEvent);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return port.reserverTraiteur(refProjet, date2, participants);
    }
    
     private boolean annulerTraiteur(String refProjet, Date dateEvent) throws DatatypeConfigurationException {
        app.Traiteur_Service service = new app.Traiteur_Service();
        app.Traiteur port = service.getTraiteurPort();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dateEvent);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return port.annulerTraiteur(refProjet, date2);
    }
    
    public void traiterRestauration(Projet p) throws TraiteurExterneException{
        // On vérifie le type de manifestation : si lunch ou cocktail, on traite en interne
        if(p.getType_presta().equals(Projet.PRESTA_COCKTAIL) || p.getType_presta().equals(Projet.PRESTA_LUNCH)){
            boissonsSingleton.reserverBoissons(p);
        } else {
            try {
                // on est de type repas Assis, on va donc demander une réservation au traiteur externe via SOAP
                if (reserverTraiteur(p.getManif(), p.getDate(), p.getParticipants())){
                    logger.log(Level.INFO, "Traiteur  reservé ");
                } else {
                    throw new TraiteurExterneException(p);
                }
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
