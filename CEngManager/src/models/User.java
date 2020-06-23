package models;

public class User {

	private Integer id;
	private String username;
	private String password;
	private UserType userType;

	public User(Integer id, String username, String password, UserType userType) {
		this.setId(id);
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	public String toString() {
		return "Id: " + id + ", Username: " + username + ", Password: " + password + ", UserType: " + userType + "||";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return userType;
	}

	public void setType(UserType userType) {
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsLoggedIn(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
