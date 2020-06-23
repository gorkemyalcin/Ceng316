package models;

public enum Category {
	FIRST_GRADE("FIRST_GRADE"),
	SECOND_GRADE("SECOND_GRADE"),
	THIRD_GRADE("THIRD_GRADE"),
	FORTH_GRADE("FORTH_GRADE"),
	PREPARATORY("PREPARATORY"),
	INSTRUCTOR("INSTRUCTOR"),
	ASSISTANT("ASSISTANT"),
	OTHER("OTHER"),
	ALL("ALL"),
	STUDENTS("STUDENTS");

	private String category;

	Category(String category) { this.category = category; }

	public String getCategory() { return category; }

}
