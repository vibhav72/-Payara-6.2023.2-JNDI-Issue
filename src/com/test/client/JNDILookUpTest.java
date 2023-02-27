package com.test.client;
import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Properties;

// java --add-opens=java.base/java.lang=ALL-UNNAMED -cp .;c:\jeeServer\glassfish\lib\gf-client.jar; com.test.client.JNDILookUpTest
public class JNDILookUpTest {

    public static void main (String[] args) {


        try {
            System.out.println ("Getting context");

            // Get the JNDI host and port.
            final String jndiHost = "localhost";
            final String jndiPort = "7001";

            // Set the org.omg.CORBA.ORBInitialHost and org.omg.CORBA.ORBInitialPort as
            // system properties, otherwise Glassfish will attempt to use the default host
            // and port of values of localhost and 3700.
            System.setProperty ("org.omg.CORBA.ORBInitialHost", jndiHost);
            System.setProperty ("org.omg.CORBA.ORBInitialPort", jndiPort);

            // Create the initial context using the org.omg.CORBA.ORBInitialHost and
            // org.omg.CORBA.ORBInitialPort property values.
            final Properties props = new Properties ();

            props.setProperty (Context.INITIAL_CONTEXT_FACTORY,
                        "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty ("org.omg.CORBA.ORBInitialHost", jndiHost);
            props.setProperty ("org.omg.CORBA.ORBInitialPort", jndiPort);

            //final Context context = new InitialContext ();
            final Context context = new InitialContext (props);

            System.out.println ("Performing look up");

			System.out.println (context.toString());

            Object o = context.lookup ("java:global/app-ejb/TestSessionBean!com.test.ejb.TestSessionBeanRemote");

            System.out.println ("\nGot back " + o + "\n");
            

        } catch (final Exception e) {
            e.printStackTrace ();
        }

        System.exit(0);
    }
}
