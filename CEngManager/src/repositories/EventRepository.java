package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BasicConnectionPool;
import models.Event;

public class EventRepository {

	public Event selectEvent(String eventTitleParam) {
		Event foundEvent = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.events WHERE event_title = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,eventTitleParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading event records...");

			while (resultSet.next()) {
				String eventTitle = resultSet.getString("event_title");
				String eventTime = resultSet.getString("event_time");
				String eventUrl = resultSet.getString("event_url");
				String eventDate = resultSet.getString("event_date");
				String eventContent = resultSet.getString("event_content");
				String eventLocation = resultSet.getString("event_location");
				
				foundEvent = new Event(eventTitle, eventTime, eventUrl, eventDate, eventContent, eventLocation);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundEvent;
	}
	
	public boolean deleteEvent(String eventTitleParam) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String deleteStatement = "DELETE FROM public.events WHERE event_title = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1,eventTitleParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public List<Event> selectAllEvents() {
		List<Event> eventList = new ArrayList<Event>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.events";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading event records...");
			
			while (resultSet.next()) {
				String eventTitle = resultSet.getString("event_title");
				String eventTime = resultSet.getString("event_time");
				String eventUrl = resultSet.getString("event_url");
				String eventDate = resultSet.getString("event_date");
				String eventContent = resultSet.getString("event_content");
				String eventLocation = resultSet.getString("event_location");
				
				Event newEvent = new Event(eventTitle, eventTime, eventUrl, eventDate, eventContent, eventLocation);
				eventList.add(newEvent);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return eventList;
	}
	
	public boolean insertEvent(Event event) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.events VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getTime());
			preparedStatement.setString(3, event.getUrl());
			preparedStatement.setString(4, event.getDate());
			preparedStatement.setString(5, event.getContent());
			preparedStatement.setString(6, event.getLocation());
			preparedStatement.executeUpdate();
			
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertEvents(List<Event> eventList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String insertStatement = "INSERT INTO public.events VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			for(Event event:eventList) {
				preparedStatement.setString(1, event.getTitle());
				preparedStatement.setString(2, event.getTime());
				preparedStatement.setString(3, event.getUrl());
				preparedStatement.setString(4, event.getDate());
				preparedStatement.setString(5, event.getContent());
				preparedStatement.setString(6, event.getLocation());
				preparedStatement.executeUpdate();
				
				System.out.println("Insertion for " + event.getTitle() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateEvent(Event event) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String updateStatement = "UPDATE public.events SET event_title = ?, event_time = ?, event_url = ?, event_date = ?, event_content = ?, event_location = ? WHERE event_title = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, event.getTitle());
			preparedStatement.setString(2, event.getTime());
			preparedStatement.setString(3, event.getUrl());
			preparedStatement.setString(4, event.getDate());
			preparedStatement.setString(5, event.getContent());
			preparedStatement.setString(6, event.getLocation());
			preparedStatement.setString(7, event.getTitle());
			preparedStatement.executeUpdate();	
			
			System.out.println("Update for " + event.getTitle() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		EventRepository er = new EventRepository();
		er.deleteEvent("second2event");
		Event event = new Event("second2event", "testemail","test", "123", "213", "1232");
		er.insertEvent(event);
		event.setTitle("updated eventname");
		er.updateEvent(event);
		System.out.println(er.selectEvent("second2event").toString());
		er.deleteEvent("second2event");
		System.out.println(er.selectAllEvents().toString());
	}

}
