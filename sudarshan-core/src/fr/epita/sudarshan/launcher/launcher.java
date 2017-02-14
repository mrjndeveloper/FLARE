package fr.epita.sudarshan.launcher;

import java.sql.SQLException;

import java.util.Scanner;

import fr.epita.sudarshan.business.CreateActivity;

/**
 * This is simple application for Create, Modify and Delete function implementation in Java
 * @author Sudarshan
 * @version 1.0
 *
 */

public class launcher {

	/**
	 * The main method for this application
	 * @param args array of string arguments
	 * @throws SQLException is exception for sql if any
	 */
	public static void main(String[] args) throws SQLException {
		System.out.println("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);

		// authentication
		if (!authenticate(scanner)) {
			end(scanner);
			return;
		}

		// menu
		System.out.println("Please select an action :");
		System.out.println("a. Create an Identity");
		System.out.println("b. Modify an Identity");
		System.out.println("c. Delete an Identity");
		System.out.println("d. List all Identities");
		System.out.println("e. Quit");
		String choice = scanner.nextLine();
		/* IdentityJDBCDAO dao; */
		switch (choice) {
		case "a":
			// Create
			CreateActivity.insert(scanner);
			break;
		case "b":
			// Modify
			CreateActivity.update(scanner);
			break;

		case "c":
			// Delete
			CreateActivity.delete(scanner);
			break;

		case "d":
			// List
			CreateActivity.ListIdentity(scanner);
			break;
		case "e":
			// Quit
			break;

		default:
			System.out.println("Your choice is not recognized");
			break;
		}

		end(scanner);
	}

	/**
	 * Ends the scanner
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		System.out.println("Thanks for using this application, good bye!");
		scanner.close();
	}

	/**
	 * Check the Authentication
	 * Default User = admin and password = admin
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		System.out.println("Please type your login : ");
		String login = scanner.nextLine();

		System.out.println("Please type your password : ");
		String password = scanner.nextLine();

		if (login.equals("admin") && password.equals("admin")) {
			System.out.println("Athentication was successful");
			return true;
		} else {
			System.out.println("Athentication failed");
			return false;
		}
	}

}
