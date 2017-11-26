/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import messages.Nommage;
import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
public class SimulationWebService  extends ClientJMS {
    
    public void sendProjet(){
        try {
            Context context = null;
            ConnectionFactory factory = null;
            Connection connection = null;
            String factoryName = "ConnectionFactoryEvents";

            Destination dest = null;

            Session session = null;
            MessageProducer sender = null;
            String text = "Message ";

            Projet p = new Projet( "monprojet", "amaury", 22);

            /*
            System.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            System.setProperty("org.omg.CORBA.ORBInitialHost",	"127.0.0.1");
            System.setProperty("org.omg.CORBA.ORBInitialPort",	"3700");*/
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(Nommage.TOPIC_DEMANDE);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connection.start();

            ObjectMessage m = session.createObjectMessage();
            
            m.setObject(p);
            sender.send(m);
            System.out.println("message envoyé ");
        } catch (NamingException ex) {
            Logger.getLogger(SimulationWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(SimulationWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        try {
            SimulationWebService webServ = new SimulationWebService();
            webServ.initJMS();
            

            webServ.startJMS();
            webServ.sendProjet();
            System.out.println("*** Service de Simulation web service démarré. ***");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                System.out.println("Appuyez sur 'Q' pour quitter.");
            } while (!br.readLine().equalsIgnoreCase("Q"));
            webServ.closeJMS();
        } catch (JMSException ex) {
            Logger.getLogger(SimulationWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimulationWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SimulationWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
