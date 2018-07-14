/**
 * 
 */
package fr.epita.iam.datamodel;

/**
 * Getters and setters are present in the identity definition 
 * @author Vijay Krishnan
 */
public class Identity {
	
	private String uid;
	private String displayName;
	private String email;
	private String joinday;
	
	/**
	 * @param uid
	 * @param displayName
	 * @param email
	 * @param joindate 
	 */
	public Identity(String uid, String displayName, String email, String joindate) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		this.joinday = joindate;
	}
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the user_id to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the joinday
	 */
	public String joinday() {
		return joinday;
	}
	/**
	 * @param joinday
	 */
	public void setbirthday(String joinday) {
		this.joinday = joinday;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + ",joinday=" + joinday + "]";
	}
}
