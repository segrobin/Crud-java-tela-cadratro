package br.org.opa;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new javax.faces.webapp.FacesServlet()), "*.xhtml");

        server.setHandler(handler);

        server.start();
        server.join();
    }

}