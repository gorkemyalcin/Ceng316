package controllers;

import java.util.List;

import models.Course;
import repositories.CourseRepository;

public class CourseController {
	
	private CourseRepository courseRepository;
	
	public CourseController() {
		this.courseRepository = new CourseRepository();
	}
	
	
	public boolean addCourse(Course course) {
		return courseRepository.insertCourse(course);
	}
	
	public boolean deleteCourse(String courseCode) {
		return courseRepository.deleteCourse(courseCode);
	}
	
	public boolean editCourseName(String courseCode, String newName) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.setCourseName(courseCode);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	public boolean editCourseCode(String courseCode, String newCode) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.setCourseCode(courseCode);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	
	public boolean editCourseInstructors(String courseCode, List<String> newInstructorIdList) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.setInstructorIdList(newInstructorIdList);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	public boolean editCourseAssistants(String courseCode, List<String> newAssistantIdList) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.setAssistantIdList(newAssistantIdList);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}

	public boolean addCourseInstructor(String courseCode, String instructorId) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.getInstructorIdList().add(instructorId);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	public boolean addCourseAssistant(String courseCode, String assistantId) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.getAssistantIdList().add(assistantId);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	public boolean deleteCourseAssistant(String courseCode, String assistantId) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.getAssistantIdList().remove(assistantId);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
	public boolean deleteCourseInstructor(String courseCode, String instructorId) {
		boolean result = false;
		Course foundCourse = courseRepository.selectCourse(courseCode);
		if(foundCourse != null) {
			foundCourse.getInstructorIdList().remove(instructorId);
			result = courseRepository.updateCourse(foundCourse);
		}
		return result;
	}
	
}
