package buisness;

public class User {

	private String firstName;
	private String lastName;
	private String email;
	private String passWord;

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassWord() {
		return this.passWord;
	}

	/**
	 * 
	 * @param oldPassWord
	 * @param newPassWord
	 */
	public void setPassWord(String oldPassWord, String newPassWord) {
		// TODO - implement buisness.User.setPassWord
		throw new UnsupportedOperationException();
	}

	public void resetPassWord() {
		// TODO - implement buisness.User.resetPassWord
		throw new UnsupportedOperationException();
	}

}