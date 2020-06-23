package models;

import java.time.LocalTime;

public class ScheduleNode {

	private String scheduleId;
	private String day;
	private Grade grade;
	private String courseCodes;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public ScheduleNode(String scheduleId, String day, String time, Grade grade, String courseCodes) {
		this.scheduleId = scheduleId;
		this.day = day;

		String startTimeString = time.substring(0,5);
		String endTimeString = time.substring(6,11);


		LocalTime startTime = LocalTime.parse(startTimeString);
		LocalTime endTime = LocalTime.parse(endTimeString);

		this.startTime = startTime;
		this.endTime = endTime;
		
		this.grade = grade;
		this.courseCodes = courseCodes;
	}
	
	public static void main(String[] args) {
		ScheduleNode sn = new ScheduleNode("1","123","12:30-13:30", Grade.FIRST, "5");
		System.out.println(sn.toString());
	}
	
	public String toString() {
		return scheduleId + "- Date: " + day.toString() + ",  Time: " + startTime.toString() + "-" + endTime.toString() + ", Grade: " + grade.toString() + ", CourseCodes: " + courseCodes;
	}
	
	public String getTime() {
		return startTime.toString() + "-" + endTime.toString();
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public String getCourseCodes() {
		return courseCodes;
	}

	public void setCourseCodes(String courseCodes) {
		this.courseCodes = courseCodes;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}
