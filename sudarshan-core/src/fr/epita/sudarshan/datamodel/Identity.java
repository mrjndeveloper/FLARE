package fr.epita.sudarshan.datamodel;

/**
 * This is simple identity class
 * 
 * @author Sudarshan
 *
 */
public class Identity {

	private String uid;
	private String displayName;
	private String email;
	private String dob;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * the uid to set
	 * 
	 * @param uid userid stored in database
	 * 
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
	 * the displayName to set
	 * 
	 * @param displayName user full name of the user
	 * 
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
	 * the email to set
	 * 
	 * @param email email address of the user
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dob
	 */

	public String getDOB() {
		return dob;
	}

	/**
	 * the date of birth(dob) to set
	 * 
	 * @param dob date of birth of the user
	 * 
	 */
	public void setdob(String dob) {
		this.dob = dob;
	}

	/**
	 * @param uid is User id stored 
	 * @param displayName is User Name
	 * @param email is User email address
	 * @param dob is User Date of Birth
	 */
	public Identity(String uid, String displayName, String email, String dob) {
		this.uid = uid;
		this.displayName = displayName;
		this.email = email;
		this.dob = dob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayName=" + displayName + ", email=" + email + ", dob=" + dob + "]";
	}
}
