package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import database.BasicConnectionPool;
import models.Assistant;

public class AssistantRepository {

	public Assistant selectAssistant(String assistantIdParam) {
		Assistant foundAssistant = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.assistants WHERE assistant_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,assistantIdParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Reading assistant records...");

			while (resultSet.next()) {
				String assistantId = resultSet.getString("assistant_id");
				String assistantName = resultSet.getString("assistant_name");
				String assistantEmail = resultSet.getString("assistant_email");
				
				String courseCode = resultSet.getString("course_code");
				List<String> courseCodeList = new ArrayList<String>();
				
				StringTokenizer courseCodeTokenizer = new StringTokenizer(courseCode, ",");
				while(courseCodeTokenizer.hasMoreTokens()) {
					courseCodeList.add(courseCodeTokenizer.nextToken());
				}

				foundAssistant = new Assistant(assistantId, assistantName, assistantEmail, courseCodeList);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundAssistant;
	}
	
	public List<Assistant> selectAllAssistants() {
		List<Assistant> assistantList = new ArrayList<Assistant>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.assistants";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading assistant records...");
			
			while (resultSet.next()) {
				String assistantId = resultSet.getString("assistant_id");
				String assistantName = resultSet.getString("assistant_name");
				String assistantEmail = resultSet.getString("assistant_email");
				
				String courseCode = resultSet.getString("course_code");
				List<String> courseCodeList = new ArrayList<String>();
				
				StringTokenizer courseCodeTokenizer = new StringTokenizer(courseCode, ",");
				while(courseCodeTokenizer.hasMoreTokens()) {
					courseCodeList.add(courseCodeTokenizer.nextToken());
				}
				
				Assistant newAssistant = new Assistant(assistantId, assistantName, assistantEmail, courseCodeList);

				assistantList.add(newAssistant);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return assistantList;
	}
	
	public boolean deleteAssistant(String assistantIdParam) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String deleteStatement = "DELETE FROM public.assistants WHERE assistant_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, assistantIdParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}
	
	public boolean insertAssistant(Assistant assistant) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.assistants VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, assistant.getName());
			preparedStatement.setString(2, assistant.getId());
			preparedStatement.setString(3, assistant.getCourseCodeListToString() );
			preparedStatement.setString(4, assistant.getEmail());
			
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertAssistants(List<Assistant> assistantList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String insertStatement = "INSERT INTO public.assistants VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			for(Assistant assistant:assistantList) {
				preparedStatement.setString(1, assistant.getName());
				preparedStatement.setString(2, assistant.getId());
				preparedStatement.setString(3, assistant.getCourseCodeListToString());
				preparedStatement.setString(4, assistant.getEmail());
				
				preparedStatement.executeUpdate();
				System.out.println("Insertion for " + assistant.getName() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateAssistant(Assistant assistant) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String updateStatement = "UPDATE public.assistants SET assistant_name = ?, assistant_id = ?, course_code = ?, assistant_email = ? WHERE assistant_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1,assistant.getName());
			preparedStatement.setString(2,assistant.getId());
			preparedStatement.setString(3,assistant.getCourseCodeListToString());
			preparedStatement.setString(4, assistant.getEmail());
			preparedStatement.setString(5,assistant.getId());

			preparedStatement.executeUpdate();	
			System.out.println("Update for " + assistant.getName() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

}
