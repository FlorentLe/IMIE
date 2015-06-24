package fr.imie.model;

import java.sql.Connection;
import java.util.List;

public interface IUsagerDAO {

	UsagerDTO ajouter(UsagerDTO usagerDTO);

	List<UsagerDTO> readAll();
	
	List<UsagerDTO> readByDTO(UsagerDTO usagerDTO);
	
	UsagerDTO update (UsagerDTO usagerDTO);
	
	Integer delete(UsagerDTO usagerDTO);

	Integer delete(UsagerDTO usagerDTO, Connection connection);

	UsagerDTO getAuthentifiedUsager(UsagerDTO usagerDTO);

}