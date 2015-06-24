package fr.imie.ITransverse;

import java.sql.SQLException;
import java.util.List;

import fr.imie.dto.SiteDTO;


public interface ISiteDAO {

	SiteDTO ajouter(SiteDTO siteDTO) throws SQLException;

	List<SiteDTO> readAll()throws SQLException;
	
	List<SiteDTO> readByDTO(SiteDTO siteDTO)throws SQLException;
	
	SiteDTO update (SiteDTO siteDTO)throws SQLException;
	
	Integer delete(SiteDTO siteDTO)throws SQLException;

}