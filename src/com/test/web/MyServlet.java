package com.test.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;


// javac -cp .;c:\jeeServer\glassfish/modules/jakarta.servlet-api.jar;c:\jeeServer\glassfish/modules/jakarta.servlet.jsp-api.jar MyServlet.java
@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
    res.setContentType("text/html; charset=utf-8");
    try (PrintWriter out = res.getWriter()) {
      out.println("<html><body>");
      out.println("Servlet: Hello Servlet World!<br>");
      out.println(getServletContext().getServerInfo());
	  out.println(lookUpTest ());
	  out.println("</body></html>");	  
    }
  }
	private  Object lookUpTest () { 
	
		try {
												
			// Get the JNDI host and port.
            final String jndiHost = "localhost";
            final String jndiPort = "7001";
			 // Create the initial context using the org.omg.CORBA.ORBInitialHost and
            // org.omg.CORBA.ORBInitialPort property values.
            final Properties props = new Properties ();

            props.setProperty (Context.INITIAL_CONTEXT_FACTORY,
                        "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty ("org.omg.CORBA.ORBInitialHost", jndiHost);
            props.setProperty ("org.omg.CORBA.ORBInitialPort", jndiPort);

            final Context context = new InitialContext (props);
			//final Context context = new InitialContext ();
			
			System.out.println (context.toString());			
            
			Object o1 = context.lookup ("java:global/app-ejb/TestSessionBean!com.test.ejb.TestSessionBeanRemote");
			
			return o1;
			
		} catch (final Exception e) {
            e.printStackTrace ();
        }
		return null;
	}
  
}