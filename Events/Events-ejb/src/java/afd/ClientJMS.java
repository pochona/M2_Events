package afd;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import messages.Nommage;

/**
 *
 * @author elavinal
 */
public abstract class ClientJMS {

    protected Context namingContext;
    private Connection connexion;
    protected Session session;

    protected void initJMS() {

        try {
            // Provide the details of remote JMS Provider
            Properties props = new Properties();
            props.put(Context.PROVIDER_URL, "mq://localhost:7676");
            /*System.setProperty("java.naming.factory.initial",
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");*/


            // creation du contexte JNDI.
            namingContext = new InitialContext();
            System.out.println("Initial Context created.");

            // recuperation de la ConnectionFactory
            ConnectionFactory cf = (ConnectionFactory) namingContext.lookup(Nommage.FABRIQUE_CONNEXIONS);
            System.out.println("Factory Name lookup done.");

            // creation de la connexion vers JMS provider
            connexion = cf.createConnection();
            System.out.println("Connection created.");

            // creation de la session
            session = connexion.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("Session created.");

            // Autre possibilite : JMSContext (connexion + session)
            // ==================================================
            // JMSContext jmsContext = cf.createContext();
            // ==================================================
        } catch (JMSException | NamingException ex) {
            Logger.getLogger(ClientJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void startJMS() throws JMSException {
        // demarre la connexion
        connexion.start();
    }

    protected void closeJMS() throws JMSException, NamingException {
        // liberation des ressources
        namingContext.close();
        connexion.close();
    }

}
