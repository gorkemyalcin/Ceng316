package models;

import java.util.List;

public class Assistant extends Person{

	private List<String> courseCodeList; //TODO courseCode class?
	
	public Assistant(String id, String name, String email, List<String> courseCodeList) {
		super(id, name, email);
		this.courseCodeList = courseCodeList;
	}
	
	public String toString() {
		return "Id: "  + super.getId() + ", Name: " + super.getName() + ", Email: " + super.getEmail() + ", Courses: " + getCourseCodeListToString() + "||";
	}

	public String getCourseCodeListToString() {
		return courseCodeList.toString().substring(1,courseCodeList.toString().length()-1);
	}
	
	public List<String> getCourseCodeList() {
		return courseCodeList;
	}

}
