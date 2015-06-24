package fr.imie.ITransverse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.imie.dto.*;

public interface IUsagerDAO {

	UsagerDTO ajouter(UsagerDTO usagerDTO) throws SQLException;

	List<UsagerDTO> readAll() throws SQLException;
	
	List<UsagerDTO> readByDTO(UsagerDTO usagerDTO) throws SQLException;
	
	UsagerDTO update (UsagerDTO usagerDTO) throws SQLException;
	
	Integer delete(UsagerDTO usagerDTO) throws SQLException;

	Integer delete(UsagerDTO usagerDTO, Connection connection) throws SQLException;

	UsagerDTO getAuthentifiedUsager(UsagerDTO usagerDTO) throws SQLException;

}