package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BasicConnectionPool;
import models.Grade;
import models.ScheduleNode;

public class ScheduleRepository {

	public ScheduleNode selectScheduleNode(String scheduleId) {
		ScheduleNode foundSchedule = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.schedule_nodes WHERE schedule_node_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, scheduleId);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("Reading schedule records...");
			while (resultSet.next()) {
				String scheduleNodeId = resultSet.getString("schedule_node_id");				
				String scheduleNodeHour = resultSet.getString("schedule_node_hour");			
				String scheduleNodeDay = resultSet.getString("schedule_node_day"); 				
				Grade scheduleNodeGrade = Grade.valueOf(resultSet.getString("schedule_node_grade"));			
				String courseCodes = resultSet.getString("course_codes");
				
				foundSchedule = new ScheduleNode(scheduleNodeId, scheduleNodeDay, scheduleNodeHour, scheduleNodeGrade, courseCodes);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundSchedule;

	}
	
	public List<ScheduleNode> selectAllScheduleNodes() {
		List<ScheduleNode> scheduleList = new ArrayList<ScheduleNode>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.schedule_nodes";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String scheduleNodeId = resultSet.getString("schedule_node_id");
				String scheduleNodeDay = resultSet.getString("schedule_node_day");
				String scheduleNodeHour = resultSet.getString("schedule_node_hour");
				Grade scheduleNodeGrade = Grade.valueOf(resultSet.getString("schedule_node_grade"));	
				String courseCodes = resultSet.getString("course_codes");
				
				ScheduleNode newSchedule = new ScheduleNode(scheduleNodeId, scheduleNodeDay, scheduleNodeHour, scheduleNodeGrade, courseCodes);

				scheduleList.add(newSchedule);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return scheduleList;
	}
	
	public boolean deleteSchedule(Integer scheduleIdParam) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String deleteStatement = "DELETE FROM public.schedule_nodes WHERE schedule_node_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, scheduleIdParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}
	
	public boolean insertSchedule(ScheduleNode schedule) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String insertStatement = "INSERT INTO public.schedule_nodes VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,schedule.getScheduleId());
			preparedStatement.setString(2,schedule.getTime());
			preparedStatement.setString(3,schedule.getDay());
			preparedStatement.setString(4,schedule.getGrade().toString());
			preparedStatement.setString(5,schedule.getCourseCodes());
			
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertSchedules(List<ScheduleNode> scheduleList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String insertStatement = "INSERT INTO public.schedule_nodes VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			for(ScheduleNode schedule:scheduleList) {
				preparedStatement.setString(1,schedule.getScheduleId());
				preparedStatement.setString(2,schedule.getTime());
				preparedStatement.setString(3,schedule.getDay());
				preparedStatement.setString(4,schedule.getGrade().toString());
				preparedStatement.setString(5,schedule.getCourseCodes());
				
				preparedStatement.executeUpdate();
				System.out.println("Insertion for " + schedule.getCourseCodes() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateSchedule(ScheduleNode schedule) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String updateStatement = "UPDATE public.schedule_nodes SET schedule_node_id = ?, schedule_node_hour = ?, schedule_node_day = ?, schedule_node_grade = ? WHERE schedule_node_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1,schedule.getScheduleId());
			preparedStatement.setString(2,schedule.getTime());
			preparedStatement.setString(3,schedule.getDay());
			preparedStatement.setString(4,schedule.getGrade().toString());
			preparedStatement.setString(5,schedule.getScheduleId());

			preparedStatement.executeUpdate();	
			System.out.println("Update for " + schedule.getCourseCodes() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
}
