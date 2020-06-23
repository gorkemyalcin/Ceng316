package models;

public class Subscriber{

	private Category category;
	private String name;
	private String email;

	public Subscriber(String name, String email,  Category category) {
		this.name = name;
		this.email = email;
		this.category = category;
	}
	
	public String toString() {
		return "Name: " + name + ", Email: " + email + ", Category: " + category.toString() + "||";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
