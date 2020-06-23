package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import database.BasicConnectionPool;
import models.Course;
import models.ScheduleNode;


public class CourseRepository {
	
	private ScheduleRepository scheduleRepository;
	
	public CourseRepository() {
		this.scheduleRepository = new ScheduleRepository();
	}
	
	public Course selectCourse(String courseCodeParam) {
		Course foundCourse = null;
		
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.courses WHERE course_code = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,courseCodeParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading course records...");

			while (resultSet.next()) {
				String courseName = resultSet.getString("course_name");
				String courseCode = resultSet.getString("course_code");
		
				String instructorIds = resultSet.getString("instructor_id");
				List<String> instructorIdList = new ArrayList<String>();
				
				StringTokenizer instructorIdTokenizer = new StringTokenizer(instructorIds);
				while(instructorIdTokenizer.hasMoreTokens()) {
					instructorIdList.add(instructorIdTokenizer.nextToken());
				}
				
				String assistantIds = resultSet.getString("assistant_id");
				List<String> assistantIdList = new ArrayList<String>();
				
				StringTokenizer assistantIdTokenizer = new StringTokenizer(assistantIds, ",");
				while(assistantIdTokenizer.hasMoreTokens()) {
					assistantIdList.add(assistantIdTokenizer.nextToken());
				}
				
				String scheduleNodeIds = resultSet.getString("schedule_node_ids");
				List<ScheduleNode> scheduleNodeList = new ArrayList<ScheduleNode>();
				StringTokenizer scheduleNodeTokenizer = new StringTokenizer(scheduleNodeIds, ",");
				while(scheduleNodeTokenizer.hasMoreTokens()) {
					scheduleNodeList.add(scheduleRepository.selectScheduleNode(scheduleNodeTokenizer.nextToken()));
				}
				
				boolean courseStatus = resultSet.getBoolean("course_status");

				foundCourse = new Course(courseCode, courseName, courseStatus, instructorIdList, assistantIdList);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundCourse;
	}
	
	public List<Course> selectAllCourses() {//TODO list values inside the db
		List<Course> courseList = new ArrayList<Course>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.courses";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading course records...");

			while (resultSet.next()) {
				String courseName = resultSet.getString("course_name");
				String courseCode = resultSet.getString("course_code");

				String instructorIds = resultSet.getString("instructor_id");
				List<String> instructorIdList = new ArrayList<String>();
				
				StringTokenizer instructorIdTokenizer = new StringTokenizer(instructorIds);
				while(instructorIdTokenizer.hasMoreTokens()) {
					instructorIdList.add(instructorIdTokenizer.nextToken());
				}
				
				String assistantIds = resultSet.getString("assistant_id");
				List<String> assistantIdList = new ArrayList<String>();
				

				StringTokenizer assistantIdTokenizer = new StringTokenizer(assistantIds);
				while(assistantIdTokenizer.hasMoreTokens()) {
					assistantIdList.add(assistantIdTokenizer.nextToken());
				}
				
				String scheduleNodeIds = resultSet.getString("schedule_node_ids");
				List<ScheduleNode> scheduleNodeList = new ArrayList<ScheduleNode>();
				StringTokenizer scheduleNodeTokenizer = new StringTokenizer(scheduleNodeIds, ",");
				while(scheduleNodeTokenizer.hasMoreTokens()) {
					scheduleNodeList.add(scheduleRepository.selectScheduleNode(scheduleNodeTokenizer.nextToken()));
				}
				
				boolean courseStatus = resultSet.getBoolean("course_status");

				Course newCourse = new Course(courseCode, courseName, courseStatus, instructorIdList, assistantIdList);
				
				courseList.add(newCourse);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return courseList;
	}
	
	public boolean deleteCourse(String courseCodeParam) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String deleteStatement = "DELETE FROM public.courses WHERE course_code = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, courseCodeParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertCourse(Course course) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.courses VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, course.getCourseName());
			preparedStatement.setString(2, course.getCourseCode());
			preparedStatement.setString(3, course.getInstructorIdListToString());
			preparedStatement.setString(4, course.getAssistantIdListToString());
			preparedStatement.setBoolean(5, course.getCourseStatus());
			
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertCourses(List<Course> courseList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.courses VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			for(Course course:courseList) {
				preparedStatement.setString(1, course.getCourseName());
				preparedStatement.setString(2, course.getCourseCode());
				preparedStatement.setString(3, course.getInstructorIdListToString());
				preparedStatement.setString(4, course.getAssistantIdListToString());
				preparedStatement.setBoolean(5, course.getCourseStatus());
				preparedStatement.executeUpdate();
				System.out.println("Insertion for " + course.getCourseCode() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateCourse(Course course) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String updateStatement = "UPDATE public.courses SET course_name = ?, course_code = ?, instructor_id = ?,"
					+ " assistant_id = ?, course_status = ? WHERE course_code = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			
			preparedStatement.setString(1, course.getCourseName());
			preparedStatement.setString(2, course.getCourseCode());
			preparedStatement.setString(3, course.getInstructorIdListToString());
			preparedStatement.setString(4, course.getAssistantIdListToString());
			preparedStatement.setBoolean(5, course.getCourseStatus());
			preparedStatement.setString(6, course.getCourseCode());
			preparedStatement.executeUpdate();	
			System.out.println("Update for " + course.getCourseCode() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
}