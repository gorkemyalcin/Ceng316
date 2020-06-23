package models;

import java.util.List;

public class Course {

	private boolean courseStatus;
	private String courseCode;
	private String courseName;
	private List<String> instructorIdList;
	private List<String> assistantIdList;

	public Course(String courseCode, String courseName, boolean courseStatus, List<String> instructorIdList, List<String> assistantIdList) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseStatus = courseStatus;
		this.instructorIdList = instructorIdList;
		this.assistantIdList = assistantIdList;
	}

	public String toString() {
		return  "";
		}

	public String getAssistantIdListToString() {
		return assistantIdList.toString().substring(1,assistantIdList.toString().length()-1);
	}

	public String getInstructorIdListToString() {
		return instructorIdList.toString().substring(1,instructorIdList.toString().length()-1);
	}

	public boolean getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(boolean courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getInstructorIdList() {
		return instructorIdList;
	}

	public void setInstructorIdList(List<String> instructorIdList) {
		this.instructorIdList = instructorIdList;
	}

	public List<String> getAssistantIdList() {
		return assistantIdList;
	}

	public void setAssistantIdList(List<String> assistantIdList) {
		this.assistantIdList = assistantIdList;
	}
}
