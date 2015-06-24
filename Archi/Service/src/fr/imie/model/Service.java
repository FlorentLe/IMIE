/**
 * 
 */
package fr.imie.model;

import java.sql.SQLException;
import java.util.List;

import fr.imie.ITransverse.*;
import fr.imie.dto.*;

/**
 * @author imie
 *
 */
public class Service implements IService {
	
	private static Service instance;
	private IFactory factory;

	private Service(IFactory factory){
		this.factory = factory;
	}

	public static  synchronized  Service getInstance(IFactory factory){
		if (instance == null)
		{
			instance = new Service(factory);
		}
		return instance;
	}

	@Override
	public UsagerDTO ajouter(UsagerDTO usagerDTO) throws SQLException {
		return factory.getUsagerDAO().ajouter(usagerDTO);
	}

	@Override
	public List<UsagerDTO> readAllUsager() throws SQLException {
		return factory.getUsagerDAO().readAll();
	}

	@Override
	public List<UsagerDTO> readByDTO(UsagerDTO usagerDTO) throws SQLException {
		return factory.getUsagerDAO().readByDTO(usagerDTO);
	}

	@Override
	public UsagerDTO update(UsagerDTO usagerDTO) throws SQLException {
		return factory.getUsagerDAO().update(usagerDTO);
	}

	@Override
	public Integer delete(UsagerDTO usagerDTO) throws SQLException {
		return factory.getUsagerDAO().delete(usagerDTO);
	}


	@Override
	public UsagerDTO getAuthentifiedUsager(UsagerDTO usagerDTO) throws SQLException {
		return factory.getUsagerDAO().getAuthentifiedUsager(usagerDTO);
	}

	@Override
	public SiteDTO ajouter(SiteDTO siteDTO) throws SQLException {
		return factory.getSiteDAO().ajouter(siteDTO);
	}

	@Override
	public List<SiteDTO> readAllSite() throws SQLException {
		return factory.getSiteDAO().readAll();
	}

	@Override
	public List<SiteDTO> readByDTO(SiteDTO siteDTO) throws SQLException {
		return factory.getSiteDAO().readByDTO(siteDTO);
	}

	@Override
	public SiteDTO update(SiteDTO siteDTO) throws SQLException {
		return factory.getSiteDAO().update(siteDTO);
	}

	@Override
	public Integer delete(SiteDTO siteDTO) throws SQLException {
		return factory.getSiteDAO().delete(siteDTO);
	}
	

}
