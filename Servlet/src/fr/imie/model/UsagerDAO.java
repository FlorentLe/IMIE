/**
 * 
 */
package fr.imie.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 * @author imie
 *
 */
public class UsagerDAO implements IUsagerDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.IUsagerDAO#ajouter(fr.imie.DTO.UsagerDTO)
	 */
	@Override
	public UsagerDTO ajouter(UsagerDTO usagerDTO) {
		UsagerDTO usagerDTOInserted = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "INSERT INTO usager(nom, prenom, date_naissance, email, site_id,login,password)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?) returning id, nom, prenom, date_naissance, email, nb_connexion,site_id,login,password";
			try (PreparedStatement statement = connection
					.prepareStatement(query)) {
				statement.setString(1, usagerDTO.getNom());
				statement.setString(2, usagerDTO.getPrenom());
				Date dateNaiss = null;
				if (usagerDTO.getDateNaiss() != null) {
					dateNaiss = new Date(usagerDTO.getDateNaiss().getTime());
				}
				statement.setDate(3, dateNaiss);
				statement.setString(4, usagerDTO.getEmail());

				if (usagerDTO.getSiteDTO() != null) {
					statement.setInt(5, usagerDTO.getSiteDTO().getId());
				} else {
					statement.setNull(5, Types.INTEGER);
				}
				statement.setString(6, usagerDTO.getLogin());
				statement.setString(7, usagerDTO.getPassword());

				// System.out.format("nb de record inséré : %d\n", recordCount);

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						usagerDTOInserted = buildDTO(resultSet);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return usagerDTOInserted;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.IUsagerDAO#readAll()
	 */
	@Override
	public List<UsagerDTO> readAll() {
		List<UsagerDTO> usagerDTOs = new ArrayList<UsagerDTO>();
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			try (Statement statement = connection.createStatement()) {
				String query = "select * from usager";
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while (resultSet.next()) {
						buildDTO(resultSet);
						usagerDTOs.add(buildDTO(resultSet));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usagerDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.IUsagerDAO#readByDTO(fr.imie.DTO.UsagerDTO)
	 */
	@Override
	public List<UsagerDTO> readByDTO(UsagerDTO usagerDTO) {
		List<UsagerDTO> retour = new ArrayList<UsagerDTO>();
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "select id, nom, prenom, date_naissance, email, nb_connexion, site_id,login,password from usager ";
			Boolean firstConstraint = true;
			if (usagerDTO.getNom() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" nom like ?");
				firstConstraint = false;
			}
			if (usagerDTO.getPrenom() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" prenom like ?");
				firstConstraint = false;
			}
			if (usagerDTO.getDateNaiss() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" date_naissance=?");
				firstConstraint = false;
			}
			if (usagerDTO.getNbConnexion() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" nb_connexion=?");
				firstConstraint = false;
			}
			if (usagerDTO.getId() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" id=?");
				firstConstraint = false;
			}
			if (usagerDTO.getEmail() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" email like ?");
				firstConstraint = false;
			}
			if (usagerDTO.getSiteDTO() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" site_id=?");
				firstConstraint = false;
			}
			if (usagerDTO.getLogin() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" login like ?");
				firstConstraint = false;
			}
			if (usagerDTO.getPassword() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" password like ?");
				firstConstraint = false;
			}

			try (PreparedStatement preparedStatement = connection
					.prepareStatement(query)) {

				Integer numParam = 1;
				if (usagerDTO.getNom() != null) {
					preparedStatement.setString(numParam++,
							"%" + usagerDTO.getNom() + "%");
				}
				if (usagerDTO.getPrenom() != null) {
					preparedStatement.setString(numParam++,
							"%" + usagerDTO.getPrenom() + "%");
				}
				if (usagerDTO.getDateNaiss() != null) {
					preparedStatement.setDate(numParam++, new Date(usagerDTO
							.getDateNaiss().getTime()));
				}
				if (usagerDTO.getNbConnexion() != null) {
					preparedStatement.setInt(numParam++,
							usagerDTO.getNbConnexion());
				}
				if (usagerDTO.getId() != null) {
					preparedStatement.setInt(numParam++, usagerDTO.getId());
				}
				if (usagerDTO.getEmail() != null) {
					preparedStatement.setString(numParam++,
							"%" + usagerDTO.getEmail() + "%");
				}
				if (usagerDTO.getSiteDTO() != null) {
					preparedStatement.setInt(numParam++, usagerDTO
							.getSiteDTO().getId());
				}
				if (usagerDTO.getLogin() != null) {
					preparedStatement.setString(numParam++,
							"%" + usagerDTO.getLogin() + "%");
				}
				if (usagerDTO.getPassword() != null) {
					preparedStatement.setString(numParam++,
							"%" + usagerDTO.getPassword() + "%");
				}

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						retour.add(buildDTO(resultSet));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return retour;

	}
	
	@Override
	public UsagerDTO getAuthentifiedUsager(UsagerDTO usagerDTO) {
		UsagerDTO retour = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "select id, nom, prenom, date_naissance, email, nb_connexion, site_id,login,password from usager where login=? and password=?";
			
			
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(query)) {
				
				if (usagerDTO.getLogin() != null) {
					preparedStatement.setString(1,usagerDTO.getLogin());
				}
				if (usagerDTO.getPassword() != null) {
					preparedStatement.setString(2,usagerDTO.getPassword());
				}

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if(resultSet.next()) {
						retour = buildDTO(resultSet);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return retour;

	}

	@Override
	public UsagerDTO update(UsagerDTO usagerDTO) {
		if (usagerDTO.getId() == null) {
			throw new IllegalArgumentException("id obligatoire pour update");
		}
		UsagerDTO usagerDTOUpdated = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "UPDATE usager "
					+ "SET nom=?, prenom=?, date_naissance=?, email=?, nb_connexion=?, login=?, password=? "
					+ "WHERE id=? returning id, nom, prenom, date_naissance, email, nb_connexion,login,password";
			try (PreparedStatement statement = connection
					.prepareStatement(query)) {
				statement.setString(1, usagerDTO.getNom());
				statement.setString(2, usagerDTO.getPrenom());
				Date dateNaiss = usagerDTO.getDateNaiss() == null ? null
						: new Date(usagerDTO.getDateNaiss().getTime());
				statement.setDate(3, dateNaiss);
				statement.setString(4, usagerDTO.getEmail());
				if (usagerDTO.getNbConnexion() == null) {
					statement.setNull(5, Types.INTEGER);
				} else {
					statement.setInt(5, usagerDTO.getNbConnexion());
				}
				statement.setString(6, usagerDTO.getLogin());
				statement.setString(7, usagerDTO.getPassword());
				statement.setInt(8, usagerDTO.getId());

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						usagerDTOUpdated = buildDTO(resultSet);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return usagerDTOUpdated;

	}

	@Override
	public Integer delete(UsagerDTO usagerDTO) {
		Integer retour;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			retour = delete(usagerDTO, connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return retour;
	}

	@Override
	public Integer delete(UsagerDTO usagerDTO, Connection connection) {

		Integer retour = null;
		String query = "DELETE FROM usager WHERE id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, usagerDTO.getId());

			retour = statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return retour;
	}

	private UsagerDTO buildDTO(ResultSet resultSet) throws SQLException {
		UsagerDTO usagerDTO = new UsagerDTO();
		usagerDTO.setId(resultSet.getInt("id"));
		usagerDTO.setNom(resultSet.getString("nom"));
		usagerDTO.setPrenom(resultSet.getString("prenom"));
		usagerDTO.setDateNaiss(resultSet.getDate("date_naissance"));
		usagerDTO.setEmail(resultSet.getString("email"));
		usagerDTO.setNbConnexion(resultSet.getInt("nb_connexion"));
	/*	Integer fkSite = resultSet.getInt("site_id");*/
		usagerDTO.setLogin(resultSet.getString("login"));
		usagerDTO.setPassword(resultSet.getString("password"));
	/*	if (resultSet.wasNull()) {
			fkSite = null;
		}
		// TODO singleton sur la classe DAO
		ISiteDAO siteDAO = new SiteDAO();
		if (fkSite != null) {
			SiteDTO siteDTO = new SiteDTO();
			siteDTO.setId(fkSite);
			siteDTO = siteDAO.readByDTO(siteDTO).get(0);
			usagerDTO.setSiteDTO(siteDTO);
		}*/
		return usagerDTO;

	}
}
