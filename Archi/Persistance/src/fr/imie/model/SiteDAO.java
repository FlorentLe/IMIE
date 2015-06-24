/**
 * 
 */
package fr.imie.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.ITransverse.ISiteDAO;
import fr.imie.dto.SiteDTO;
import fr.imie.dto.UsagerDTO;



/**
 * @author imie
 *
 */
public class SiteDAO implements ISiteDAO {

	private static SiteDAO instance;

	private SiteDAO(){}

	public static  synchronized  SiteDAO getInstance(){
		if (instance == null)
		{
			instance = new SiteDAO();
		}
		return instance;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.ISiteDAO#ajouter(fr.imie.DTO.SiteDTO)
	 */
	@Override
	public SiteDTO ajouter(SiteDTO siteDTO) throws SQLException{
		SiteDTO siteDTOInserted = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "INSERT INTO site(nom)"
					+ " VALUES (?) returning id, nom";
			try (PreparedStatement statement = connection
					.prepareStatement(query)) {
				statement.setString(1, siteDTO.getNom());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						siteDTOInserted = buildDTO(resultSet);
					}
				}
				catch (SQLException e) {
					throw new SQLException("L'ajout n'est pas possible, erreur dans la requête : "+e.getMessage());
				}
			}catch (SQLException e) {
				throw new SQLException("L'ajout n'est pas possible, erreur dans la construction de la requête : "+e.getMessage());
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erreur de connexion à la base de données : "+e.getMessage());
		}

		return siteDTOInserted;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.ISiteDAO#readAll()
	 */
	@Override
	public List<SiteDTO> readAll() throws SQLException{
		List<SiteDTO> siteDTOs = new ArrayList<SiteDTO>();
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			try (Statement statement = connection.createStatement()) {
				String query = "select * from site";
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while (resultSet.next()) {
						buildDTO(resultSet);
						siteDTOs.add(buildDTO(resultSet));
					}
				}
				catch (SQLException e) {
					throw new SQLException("Le listing n'est pas possible, erreur dans la requête : "+e.getMessage());
				}
			}catch (SQLException e) {
				throw new SQLException("Le listing n'est pas possible, erreur dans la construction de la requête : "+e.getMessage());
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erreur de connexion à la base de données : "+e.getMessage());
		}
		return siteDTOs;
	}

	private SiteDTO buildDTO(ResultSet resultSet) throws SQLException {
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setId(resultSet.getInt("id"));
		siteDTO.setNom(resultSet.getString("nom"));
		return siteDTO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.DAO.ISiteDAO#readByDTO(fr.imie.DTO.SiteDTO)
	 */
	@Override
	public List<SiteDTO> readByDTO(SiteDTO siteDTO) throws SQLException{
		List<SiteDTO> retour = new ArrayList<SiteDTO>();
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "select id, nom from site ";
			Boolean firstConstraint = true;
			if (siteDTO.getNom() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" nom like ?");
				firstConstraint = false;
			}
			if (siteDTO.getId() != null) {
				query = query.concat(firstConstraint ? "where" : "and").concat(
						" id=?");
				firstConstraint = false;
			}

			try (PreparedStatement preparedStatement = connection
					.prepareStatement(query)) {

				Integer numParam = 1;
				if (siteDTO.getNom() != null) {
					preparedStatement.setString(numParam++,
							"%" + siteDTO.getNom() + "%");
				}
				if (siteDTO.getId() != null) {
					preparedStatement.setInt(numParam++, siteDTO.getId());
				}

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						retour.add(buildDTO(resultSet));
					}
				}
				catch (SQLException e) {
					throw new SQLException("La recherche n'est pas possible, erreur dans la requête : "+e.getMessage());
				}
			}catch (SQLException e) {
				throw new SQLException("La recherche n'est pas possible, erreur dans la construction de la requête : "+e.getMessage());
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erreur de connexion à la base de données : "+e.getMessage());
		}
		return retour;

	}

	@Override
	public SiteDTO update(SiteDTO siteDTO) throws SQLException{
		if (siteDTO.getId() == null) {
			throw new IllegalArgumentException("id obligatoire pour update");
		}
		SiteDTO siteDTOUpdated = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {
			String query = "UPDATE site " + "SET nom=? "
					+ "WHERE id=? returning id, nom";
			try (PreparedStatement statement = connection
					.prepareStatement(query)) {
				statement.setString(1, siteDTO.getNom());
				statement.setInt(2, siteDTO.getId());
				System.out.println(statement.toString());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						siteDTOUpdated = buildDTO(resultSet);
					}
				}
				catch (SQLException e) {
					throw new SQLException("La mise à jour n'est pas possible, erreur dans la requête : "+e.getMessage());
				}
			}catch (SQLException e) {
				throw new SQLException("La mise à jour n'est pas possible, erreur dans la construction de la requête : "+e.getMessage());
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erreur de connexion à la base de données : "+e.getMessage());
		}

		return siteDTOUpdated;

	}

	@Override
	public Integer delete(SiteDTO siteDTO) throws SQLException {
		// TODO singleton sur la classe DAO
		UsagerDAO usagerDAO = UsagerDAO.getInstance();
		Integer retour = null;
		try (Connection connection = ConnectionProvider.getInstance()
				.provideConnection()) {

			try {
				connection.setAutoCommit(false);
				UsagerDTO usagerDTO = new UsagerDTO();
				usagerDTO.setSiteDTO(siteDTO);
				List<UsagerDTO> usagerDTOs = usagerDAO.readByDTO(usagerDTO);
				for (UsagerDTO usagerDTO2 : usagerDTOs) {
					usagerDAO.delete(usagerDTO2, connection);
				}

				String query = "DELETE FROM site WHERE id=?";
				try (PreparedStatement statement = connection
						.prepareStatement(query)) {
					statement.setInt(1, siteDTO.getId());

					retour = statement.executeUpdate();
				}
				catch (SQLException e) {
					throw new SQLException("La suppression n'est pas possible, erreur dans la requête : "+e.getMessage());
				}
				connection.commit();

			}catch (SQLException e) {
				throw new SQLException("La suppression n'est pas possible, transaction non respectée : "+e.getMessage());
			}
			
		} catch (SQLException e) {
			throw new SQLException("Erreur de connexion à la base de données : "+e.getMessage());
		}
		return retour;
	}
}
