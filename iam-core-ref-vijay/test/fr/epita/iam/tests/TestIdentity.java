/**
 * 
 */
package fr.epita.iam.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;
import static fr.epita.iam.util.Println.*;

/**
 * @author Vijay Krishnan
 *
 */
public class TestIdentity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Identity identity = new Identity("","Thomas Print Broussard", "tbr@tbr.com","12/11/1990");
			//System.out.println(identity);
			
			try {
				IdentityJDBCDAO idWriter = new IdentityJDBCDAO();
				idWriter.write(identity);
				println("The print Works");
				
				//create_Identity();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		/*	try {
				update();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */

	}
	
	/*
    public static void update () throws SQLException {
    	Connection currentConnection;
    	
			// TODO read those information from a file
			String user = "viajy";
			String password = "test";
			String connectionString = "jdbc:derby://localhost:1527/IAM;create=true";
			currentConnection = DriverManager.getConnection(connectionString, user, password);
			
			
			
			String sqlInstruction = "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ? , IDENTITY_BIRTHDATE = ? where IDENTITY_ID = ?";
			
			// String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
			PreparedStatement pstmt = currentConnection.prepareStatement(sqlInstruction);
			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			// TODO implement date for identity
			pstmt.setString(3, identity.getUid());
			pstmt.setString(3, identity.getbirthday());
			pstmt.execute();
    }
    
    */
    
    public static void create_Identity() throws SQLException{
    	Connection currentConnection;
    	
    	//Connection conn = null;
    	// TODO read those information from a file
    				//currentConnection = ConnectionFactory.getInstance().makeConnection();
    				//currentConnection.setAutoCommit(false);
    				
    				String user = "vijay";
    				String password = "test";
    				String connectionString = "jdbc:derby://localhost:1527/IAM;create=true";
    				currentConnection = DriverManager.getConnection(connectionString, user, password);
    				
    				
    				String birthdate = "11/24/2007";
    				String newDateString= "";
    			    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
    			    Date birthday;
    			    try {
    			        birthday = df.parse(birthdate);
    			        newDateString = df.format(birthday);
    			        //System.out.println(newDateString);
    			        //return newDateString;
    			    } catch (ParseException e) {
    			        e.printStackTrace();
    			    }
    			    
    			    Identity identity = new Identity("","Mark", "mark@gmail.com", birthdate);

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
		PreparedStatement pstmt = currentConnection.prepareStatement(sqlInstruction);
		
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		// TODO implement date for identity
		pstmt.setString(3, identity.joinday());
		pstmt.execute();
		println("Success");
    }

}
