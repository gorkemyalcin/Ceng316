package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import database.BasicConnectionPool;
import models.Instructor;

public class InstructorRepository {

	public Instructor selectInstructor(String instructorIdParam) {
		Instructor foundInstructor = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String selectStatement = "SELECT * FROM public.instructors WHERE instructor_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,instructorIdParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Reading instructor records...");
			while (resultSet.next()) {
				String instructorId = resultSet.getString("instructor_id");
				String instructorName = resultSet.getString("instructor_name");
				String instructorEmail =resultSet.getString("instructor_email");
				
				String courseCode = resultSet.getString("course_code");
				List<String> courseCodeList = new ArrayList<String>();				
				StringTokenizer courseCodeTokenizer = new StringTokenizer(courseCode, ",");
				while(courseCodeTokenizer.hasMoreTokens()) {
					courseCodeList.add(courseCodeTokenizer.nextToken());
				}
				foundInstructor = new Instructor(instructorId, instructorName, instructorEmail, courseCodeList);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundInstructor;
	}
	
	public List<Instructor> selectAllInstructors() {
		List<Instructor> instructorList = new ArrayList<Instructor>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String selectStatement = "SELECT * FROM public.instructors";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading instructor records...");
			while (resultSet.next()) {
				String instructorId = resultSet.getString("instructor_id");
				String instructorName = resultSet.getString("instructor_name");
				String instructorEmail = resultSet.getString("instructor_email");
				
				String courseCode = resultSet.getString("course_code");
				List<String> courseCodeList = new ArrayList<String>();
				
				StringTokenizer courseCodeTokenizer = new StringTokenizer(courseCode, ",");
				while(courseCodeTokenizer.hasMoreTokens()) {
					courseCodeList.add(courseCodeTokenizer.nextToken());
				}		
				Instructor newInstructor = new Instructor(instructorId, instructorName, instructorEmail, courseCodeList);
				instructorList.add(newInstructor);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return instructorList;
	}
	
	public boolean deleteInstructor(String instructorIdParam) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String deleteStatement = "DELETE FROM public.instructors WHERE instructor_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, instructorIdParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}
	
	public boolean insertInstructors(List<Instructor> instructorList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String insertStatement = "INSERT INTO public.instructors VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			for(Instructor instructor:instructorList) {
				preparedStatement.setString(1, instructor.getName());
				preparedStatement.setString(2, instructor.getId());
				preparedStatement.setString(3, instructor.getCourseCodeListToString());
				preparedStatement.setString(4, instructor.getEmail());
				
				preparedStatement.executeUpdate();
				System.out.println("Insertion for " + instructor.getName() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public boolean insertInstructor(Instructor instructor) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.instructors VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, instructor.getName());
			preparedStatement.setString(2, instructor.getId());
			preparedStatement.setString(3, instructor.getCourseCodeListToString());
			preparedStatement.setString(4, instructor.getEmail());
			
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateInstructor(Instructor instructor) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String updateStatement = "UPDATE public.instructors SET instructor_name = ?, instructor_id = ?, course_code = ?, instructor_email = ? WHERE instructor_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1,instructor.getName());
			preparedStatement.setString(2,instructor.getId());
			preparedStatement.setString(3,instructor.getCourseCodeListToString());
			preparedStatement.setString(4, instructor.getEmail());
			preparedStatement.setString(5,instructor.getId());

			preparedStatement.executeUpdate();	
			System.out.println("Update for " + instructor.getName() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
}
