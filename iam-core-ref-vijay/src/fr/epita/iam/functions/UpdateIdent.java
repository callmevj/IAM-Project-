/**
 * 
 */
package fr.epita.iam.functions;

import static fr.epita.iam.util.Println.println;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
/**
 * Here we update and Save the identity
 * User will have to enter all the details for the identity they wish to update except the uid.
 * 
 */
/**
 * @author Vijay Krishnan
 *
 */
public class UpdateIdent {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private UpdateIdent(){
		}
	
	public static void execute(Scanner scanner){
		println("Updating the Identity");
				
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		List<Identity> identityList = new ArrayList<>();
		try {
			identityList = identityJDBCDAO.readAllIdentities();
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE,"An erros is being displayed  while connecting to Database. Kndly Try again", e1);
			}
			for (Identity current : identityList) {
			String listToPrint = current.toString();
			println(listToPrint);
			
			println("Kindly enter the ID of the user you wish to update");
			}
		
		String uid = scanner.nextLine();
		println("Enter the Display Name to Update");
		String displayName = scanner.nextLine();
		println("Enter the email address To Update");
		String email = scanner.nextLine();
		println("Enter the Join Date in the format MM/dd/yyyy");
		IdentityJDBCDAO identityUpdate = new IdentityJDBCDAO();
		String joinday = identityUpdate.identityjoindate(scanner.nextLine());
		
		Identity identity = new Identity(uid,displayName, email,joinday);
				
			try {
				if (identityUpdate.updateiden(identity)){
				println("Identity Successuflly Updated");
				}
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE,"An error is being displayed while Updating identity. Kindly Try again", e);
			}
		
	}
}
