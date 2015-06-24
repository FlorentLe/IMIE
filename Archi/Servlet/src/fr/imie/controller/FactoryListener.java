package fr.imie.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fr.imie.ITransverse.IFactory;
import fr.imie.factory.Factory;


/**
 * Application Lifecycle Listener implementation class FactoryListener
 *
 */
@WebListener
public class FactoryListener implements HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	IFactory factory = Factory.getInstance();
    	arg0.getSession().getServletContext().setAttribute("factory", factory);
    }
	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	arg0.getSession().getServletContext().removeAttribute("factory");
    }
    
}
