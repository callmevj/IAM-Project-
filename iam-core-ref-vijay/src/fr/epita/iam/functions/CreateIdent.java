/**
 * 
 */
package fr.epita.iam.functions;

import static fr.epita.iam.util.Println.println;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
/**
 * Here we Create and Save the identity
 *@author Vijay Krishnan
 */
public class CreateIdent {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private CreateIdent(){
		
	}
	
	/**
	 * Identity writer method to persist new identity to the database
	 * @param scanner
	 * Takes the parameters from the user input
	 */
	
	public static void execute(Scanner scanner){
		println("Creation of the Identity");
		println("Kindly enter the name to be displayed");
		String displayName = scanner.nextLine();
		println("Kindly enter the email address");
		String email = scanner.nextLine();
		println("Kindly enter the Join Date in the format MM/dd/yyyy");
		
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		String joinday = identityWriter.identityjoindate(scanner.nextLine());
		
		Identity identity = new Identity("",displayName, email,joinday);
				
		try {
			identityWriter.write(identity);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,"An error is being displayed while saving Identity to database. Kindly try again", e);
			}
		println("Identity successfully created");
	}
}
