/**
 * 
 */
package fr.epita.iam.launcher;

import java.util.Scanner;

import fr.epita.iam.functions.CreateIdent;
import fr.epita.iam.functions.DeleteIdent;
import fr.epita.iam.functions.UpdateIdent;

import static fr.epita.iam.util.Println.*;

/**
 * Class which has the main method to Launch the application
 * 
 * @author Vijay Krishnan
 */
public class Launchercl {

	private Launchercl() {
	}

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		println("Bienvennue/Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		// authentication handled here to control access to the application
		if (!authenticate(scanner)) {
			end(scanner);
			return;
		}

		// menu from which user select item to use after authentication
		println("Please select an action :");
		println("a. to create an Identity");
		println("b. to update an Identity");
		println("c. to delete an Identity");
		println("d. quit");
		String choice = scanner.nextLine();
		switch (choice) {
		case "a":
			// Method call to Create identity
			CreateIdent.execute(scanner);
			break;
		case "b":
			// method call to Modify Identity
			UpdateIdent.execute(scanner);
			break;

		case "c":
			// method call to Delete identity
			DeleteIdent.execute(scanner);
			break;

		case "d":
			// Quit the application
			end(scanner);
			return;

		default:
			println("Your choice is not recognized");
			break;
		}
		end(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		println("Thanks for using!");
		scanner.close();
	}

	/**
	 * Method to authenticate the user. Reads the user name and password from a file
	 * - dbconfig.properties
	 * 
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		println("Kindly enter your login : ");
		String login = scanner.nextLine();

		println("Kindly enter your password : ");
		String password = scanner.nextLine();

		if ("vijay".equals(login) && "test".equals(password)) {
			println("Athentication was successful");
			return true;
		} else {
			println("Athentication failed");
			return false;
		}
	}

}
