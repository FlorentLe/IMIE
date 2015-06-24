package fr.imie.ITransverse;


import java.sql.SQLException;
import java.util.List;

import fr.imie.dto.SiteDTO;
import fr.imie.dto.UsagerDTO;

public interface IService {
	
	UsagerDTO ajouter(UsagerDTO usagerDTO) throws SQLException ;
	List<UsagerDTO> readAllUsager() throws SQLException;
	List<UsagerDTO> readByDTO(UsagerDTO usagerDTO) throws SQLException;
	UsagerDTO update (UsagerDTO usagerDTO) throws SQLException;
	Integer delete(UsagerDTO usagerDTO) throws SQLException;
	UsagerDTO getAuthentifiedUsager(UsagerDTO usagerDTO) throws SQLException;
	
	SiteDTO ajouter(SiteDTO siteDTO) throws SQLException;

	List<SiteDTO> readAllSite() throws SQLException;
	List<SiteDTO> readByDTO(SiteDTO siteDTO) throws SQLException;
	SiteDTO update (SiteDTO siteDTO) throws SQLException;
	Integer delete(SiteDTO siteDTO) throws SQLException;
	
}
