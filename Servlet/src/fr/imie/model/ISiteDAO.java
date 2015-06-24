package fr.imie.model;

import java.util.List;


public interface ISiteDAO {

	SiteDTO ajouter(SiteDTO siteDTO);

	List<SiteDTO> readAll();
	
	List<SiteDTO> readByDTO(SiteDTO siteDTO);
	
	SiteDTO update (SiteDTO siteDTO);
	
	Integer delete(SiteDTO siteDTO);

}