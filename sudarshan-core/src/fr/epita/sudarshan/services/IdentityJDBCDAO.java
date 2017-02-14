package fr.epita.sudarshan.services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.sudarshan.datamodel.Identity;
import fr.epita.sudarshan.exceptions.DaoInitializationException;

/**
 * 
 * @author Sudarshan
 *
 */
public class IdentityJDBCDAO {

	private Connection currentConnection;

	public IdentityJDBCDAO() throws DaoInitializationException {

		try {
			getConnection();
		} catch (SQLException e) {
			DaoInitializationException die = new DaoInitializationException();
			die.initCause(e);
			throw die;
		}
	}

	/**
	 * Connect to the Database
	 * 
	 * @throws SQLException any SQL Error
	 */
	private Connection getConnection() throws SQLException {
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			String user = "admin";
			String password = "admin";
			String connectionString = "jdbc:derby://localhost:1527/project;create=true";
			this.currentConnection = DriverManager.getConnection(connectionString, user, password);
		}
		return this.currentConnection;
	}

	/**
	 * Disconnect the database
	 */
	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception e) {
			// TODO trace Exception
		}
	}

	/**
	 * Read all the identities from the database
	 * 
	 * @return list of identity stored in database
	 * @throws SQLException any SQL Error
	 */
	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			String dob = rs.getString("IDENTITY_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), displayName, email, dob);
			identities.add(identity);
		}
		releaseResources();
		return identities;
	}

	/**
	 * Insert an identity in the database
	 * 
	 * @param identity details of user to insert
	 * @throws SQLException any SQL Error
	 */

	public void write(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getDOB());

		pstmt.execute();
		releaseResources();
	}

	/**
	 * 
	 * @param identity details of the user
	 * @return ResultSet of Identity if found else empty
	 * @throws SQLException any SQL Error
	 */
	public ResultSet check(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "SELECT * from IDENTITIES  WHERE IDENTITY_ID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getUid());
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	/**
	 * UPDATE an identity in the database
	 * 
	 * @param identity details of user
	 * @throws SQLException any SQL Error
	 */
	public void update(Identity identity) throws SQLException {
		Connection connection = getConnection();
		String sqlInstruction = "UPDATE IDENTITIES set IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ?, IDENTITY_BIRTHDATE = ?  WHERE IDENTITY_ID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getDOB());
		pstmt.setString(4, identity.getUid());
		pstmt.execute();
		releaseResources();

	}

	/**
	 * DELETE an identity in the database
	 * 
	 * @param identity details of user
	 * @throws SQLException any SQL Error
	 */
	public void delete(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "DELETE FROM IDENTITIES WHERE IDENTITY_ID = ?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getUid());
		pstmt.execute();
		releaseResources();
	}
}
