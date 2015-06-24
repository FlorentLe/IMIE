package fr.imie.controller;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class connectedUserListener
 *
 */
@WebListener
public class ConnectedUserListener implements HttpSessionAttributeListener {

	private Integer countConnectedUser ;
    /**
     * Default constructor. 
     */
    public ConnectedUserListener() {
    	countConnectedUser = 0;
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	
         if(arg0.getName().compareTo("connectedUser")==0){
        	 ServletContext context = arg0.getSession().getServletContext();
        	 context.setAttribute("countConnectedUser", ++countConnectedUser);
         }
    }
	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	
    	if(arg0.getName().compareTo("connectedUser")==0){
       	 ServletContext context = arg0.getSession().getServletContext();
       	 context.setAttribute("countConnectedUser", --countConnectedUser);
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
