/**
 * 
 */
package fr.imie.factory;

import fr.imie.model.*;
import fr.imie.ITransverse.*;



/**
 * @author imie
 *
 */
public class Factory implements IFactory {

	private static Factory instance ;
	
	private Factory(){}
	
	public static synchronized Factory getInstance(){
		if(instance==null){
			instance = new Factory();
		}
		return instance;
	}
	
	@Override
	public Service getService() {
		return Service.getInstance(this);
	}

	@Override
	public UsagerDAO getUsagerDAO() {
		return UsagerDAO.getInstance();
	}

	@Override
	public SiteDAO getSiteDAO() {
		return SiteDAO.getInstance();
	}
}
