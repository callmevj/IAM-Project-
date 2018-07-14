package fr.epita.iam.functions;
import static fr.epita.iam.util.Println.println;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * Class to delete the identity
 * @author Vijay Krishnan
 * 
 */
public class DeleteIdent {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private DeleteIdent(){
		
	}
	/**
	 * Method to search for identity and the delete the specific identity
	 * @param scanner
	 */
	public static void execute(Scanner scanner){
		println("Deletion of the Identity");
				
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		List<Identity> identityList = new ArrayList<>();
		try {
			identityList = identityJDBCDAO.readAllIdentities();
			} catch (Exception e1) {
				LOGGER.log(Level.SEVERE,"An error is being displayed while connecting to database. Kindly try again", e1);
				}
	
			for (Identity current : identityList) {
			String listToPrint = current.toString();
			println(listToPrint);
			}
		println("Kindly enter the ID of the Identity you intend to delete");
		String uid = scanner.nextLine();
		IdentityJDBCDAO identityDelete = new IdentityJDBCDAO();		
			try {
				if(identityDelete.deleteiden(uid)){
				println("Identity Successuflly deleted");
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE,"An error is being displayed while deleting identity from Database.Kindly try again", e);
			}
		
	}
	

}
