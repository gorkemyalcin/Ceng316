package models;

public enum UserType {
	ADMIN("admin"),
	CM("cm");

	String type;

	UserType(String type) { 
		this.type = type; 
	}

	public String getValue() {
		return type; 
	}
}
