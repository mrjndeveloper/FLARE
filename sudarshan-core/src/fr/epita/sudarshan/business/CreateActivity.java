package fr.epita.sudarshan.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.sudarshan.datamodel.Identity;
/*import fr.epita.sudarshan.services.FileIdentityDAO;*/
import fr.epita.sudarshan.services.IdentityJDBCDAO;


/**
 * 
 * @author Sudarshan
 *
 */
public class CreateActivity {
	/**
	 * This method insert the new identity in database
	 * @param scanner allow to enter input in system if logged in
	 * @throws SQLException any SQL exception
	 *
	 */
	public static void insert(Scanner scanner) throws SQLException {
		System.out.println("Identity Creation");
		System.out.println("Please input the Display Name");
		String displayName = scanner.nextLine();
		System.out.println("Please input the Email Address");
		String email = scanner.nextLine();
		System.out.println("Please input the Birth date");
		String dob = scanner.nextLine();
		Identity identity = new Identity("", displayName, email, dob);

		// persist the identity somewhere
		System.out.println("This is the identity you created");
		
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		identityWriter.write(identity);

		System.out.println("creation Done");

	}
	
	/**
	 * This method list all the identity stored in database 
	 * @param scanner allow to enter input in system if logged in
	 * @throws SQLException any SQL exception
	 *
	 */

	public static void ListIdentity(Scanner scanner) throws SQLException {
		System.out.println("Identity Listing");
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();

		List<Identity> allIdentities = identityWriter.readAllIdentities();
		//System.out.println(allIdentities);
		for (Identity iden : allIdentities) {
		    System.out.println(iden);
		}

		System.out.println("Listing Done");

	}

	/**
	 * This method check if uid exist or not and update identity if found
	 * @param scanner allow to enter input in system if logged in
	 * @throws SQLException any SQL exception
	 */
	public static void update(Scanner scanner) throws SQLException {
		System.out.println("Identity Update");
		System.out.println("Please input the User Id");
		String uid = scanner.nextLine();
		Identity identity = new Identity(uid, "", "", "");

		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		ResultSet rs = identityWriter.check(identity);
		if (rs.next()) {
			System.out.println("User Id Found");
			System.out.println("Please input the Display Name");
			String displayName = scanner.nextLine();
			System.out.println("Please input the Email Address");
			String email = scanner.nextLine();
			System.out.println("Please input the Birth date");
			String dob = scanner.nextLine();
			Identity identitynew = new Identity(uid, displayName, email, dob);
			
			identityWriter.update(identitynew);
			System.out.println("Update Done");
		} else {
			System.out.println("User Id Not Found");
		}

	}

	/**
	 * This method check if id exist or not and delete identity if found
	 * @param scanner userid to scan
	 * @throws SQLException any SQL exception
	 */
	public static void delete(Scanner scanner) throws SQLException {
		System.out.println("Identity Delete");
		System.out.println("Please input the User Id");
		String uid = scanner.nextLine();
		Identity identity = new Identity(uid, "", "", "");

		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		ResultSet rs = identityWriter.check(identity);
		if (rs.next()) {
			identityWriter.delete(identity);
			System.out.println("Identity Deleted");
		} else {
			System.out.println("User Id Not Found");
		}

	}
}
