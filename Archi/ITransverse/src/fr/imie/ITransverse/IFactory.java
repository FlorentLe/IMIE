package fr.imie.ITransverse;

public interface IFactory {
	IService getService();	
	IUsagerDAO getUsagerDAO();
	ISiteDAO getSiteDAO();
}
