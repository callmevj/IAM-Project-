package fr.epita.iam.services;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoException;

/**
	 * Class to create the JDBCAO for persisting the data from and to the database.
	 * @author Vijay Krishnan 
	 * 
 */
public class IdentityJDBCDAO {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Connection currentConnection;
	public IdentityJDBCDAO() {
			
		try {
			getConnection();
		} catch (SQLException e) {
			DaoException die = new DaoException();
			die.initCause(e);
			throw die;
		}
	}

	/**
	 * Create connection to the database and throws exception if instance connection fails
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	private Connection getConnection() throws SQLException {
		

		String connectionString="";
		String user="";
		String pass="";
		currentConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/IAM;create=true", "vijay", "test");
		
		System.out.println(currentConnection);
		//File file = new File("dbconfig.properties");
//		try (Scanner scanner = new Scanner(file)){
//		
//		while (scanner.hasNext()) {
//			connectionString = scanner.nextLine();
//			user = scanner.nextLine();
//			pass = scanner.nextLine();
//		 } 
//		} catch(Exception e){
//			LOGGER.log(Level.SEVERE,"File Not Found", e);
//		}
//		
//		try {
//			this.currentConnection.getSchema();
//		} catch (Exception e) {
//			LOGGER.log(Level.INFO,"New connection created", e);
//					//this.currentConnection = DriverManager.getConnection(connectionString, user, pass);
//		}
		return this.currentConnection;
	}

	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception eResources) {
			LOGGER.log(Level.SEVERE,"Error encountered while Releasing Resources", eResources);
		}
	}

	/**
	 * Read all the identities from the database
	 * @return
	 * @throws SQLException
	 */
	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from IAMTEST.IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			String joinday = rs.getString("IDENTITY_JOINDATE");
			Identity identity = new Identity(String.valueOf(uid), displayName, email,String.valueOf(joinday));
			identities.add(identity);
		}
		statement.close();
		return identities;
		
	}

	/**
	 * Method to translate and format the birthday string into date format for persisting to database.
	 * logger level severe
	 * @param joindate
	 * @throws Exception
	 * @return newDateString
	 */
	public String identityjoindate(String joindate){
		
		String newDateString= "";
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date joinday;
	    try {
	        joinday = df.parse(joindate);
	        newDateString = df.format(joinday);	 
	        System.out.println(newDateString);
	    } catch (ParseException edate) {
	        LOGGER.log(Level.SEVERE,"Error encountered with parsing Date", edate);
	    }
	    return newDateString;
	}
	
	/**
	 * write an identity in the database
	 * logger level severe
	 * @param identity
	 * @throws SQLException
	 * 
	 */
	public void write(Identity identity) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			Connection witeConnection = getConnection();
		String sqlInstruction = "INSERT INTO IAMTEST.IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_JOINDATE) VALUES(?,?,?)";
		pstmt = witeConnection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.joinday());
		pstmt.execute();
		pstmt.close();
	   } catch (Exception e) {
		   LOGGER.log(Level.SEVERE,"Error encountered while saving Identity", e);
			      }finally {
			if (pstmt !=null)
			pstmt.close();
		}
	
	}
	
	
	
	
	/**
	 * update an identity in the database
	 * logger level severe
	 * @param identity
	 * @throws SQLException
	 * @return Boolean
	 * 
	 * 
	 */
	public Boolean updateiden(Identity identity) throws SQLException{
		PreparedStatement pstmt = null;
		Boolean rs = false;
		try{	
			Connection updateConnection = getConnection();
		String sqlInstruction = "UPDATE IAMTEST.IDENTITIES SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ? , IDENTITY_JOINDATE = ? where IDENTITY_ID = ?";
		pstmt = updateConnection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.joinday());
		pstmt.setInt(4, Integer.parseInt(identity.getUid()));
		rs = 0 == pstmt.executeUpdate();
		
			} catch(Exception e2){
				 LOGGER.log(Level.SEVERE,"Error encountered while updating Identity", e2);
			} finally {
			if (pstmt !=null)
			pstmt.close();
		}
		return rs;
	}
	
	/**
	 * Delete  an identity from the database
	 * logger level severe
	 * @param uid
	 * @throws SQLException
	 * @return Boolean
	 * 
	 */
	public Boolean deleteiden(String uid) throws SQLException{
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Boolean delrs = false;
			try{	
			Connection deleteConnection = getConnection();
			pstmt2 = deleteConnection.prepareStatement("select * from IAMTEST.IDENTITIES where IDENTITY_ID =?");
			pstmt2.setInt(1,Integer.parseInt(uid));
			ResultSet rs = pstmt2.executeQuery();

			while (rs.next()) {
				String sqlInstruction = "DELETE FROM IAMTEST.IDENTITIES where IDENTITY_ID = ?";
				pstmt = deleteConnection.prepareStatement(sqlInstruction);
				pstmt.setInt(1,Integer.parseInt(uid));
				delrs = 0 == pstmt.executeUpdate();
			}
				} catch(Exception e3){
					LOGGER.log(Level.SEVERE,"Error encountered while deleting Identity", e3);
		} finally {

			if (pstmt !=null)
				pstmt.close();
			if (pstmt2!=null)
				pstmt2.close();
			releaseResources();
			
		}
			return delrs;
	}
}
