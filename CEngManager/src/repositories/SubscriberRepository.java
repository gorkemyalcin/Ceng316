package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BasicConnectionPool;
import models.Category;
import models.Subscriber;

public class SubscriberRepository {

	public Subscriber selectSubscriber(String subscriberEmailParam) {
		Subscriber foundSubscriber = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.subscribers WHERE subscriber_email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,subscriberEmailParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading subscriber records...");

			while (resultSet.next()) {
				String subscriberName = resultSet.getString("subscriber_name");
				String subscriberEmail = resultSet.getString("subscriber_email");
				Category subscriberCategory = Category.valueOf(resultSet.getString("subscriber_category"));
				foundSubscriber = new Subscriber(subscriberName, subscriberEmail, subscriberCategory);
			}
			
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundSubscriber;
	}
	
	public  List<Subscriber> selectSpecificCategorySubscribers(Category categoryParam) {
		List<Subscriber> subscriberList = new ArrayList<Subscriber>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.subscribers WHERE subscriber_category = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, categoryParam.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading subscriber records...");

			while (resultSet.next()) {
				String subscriberName = resultSet.getString("subscriber_name");
				String subscriberEmail = resultSet.getString("subscriber_email");
				Category subscriberCategory = Category.valueOf(resultSet.getString("subscriber_category"));

				Subscriber newSubscriber = new Subscriber( subscriberName, subscriberEmail, subscriberCategory);
				subscriberList.add(newSubscriber);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return subscriberList;
	}
	
	public  List<Subscriber> selectAllSubscribers() {
		List<Subscriber> subscriberList = new ArrayList<Subscriber>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.subscribers";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading subscriber records...");

			while (resultSet.next()) {
				String subscriberName = resultSet.getString("subscriber_name");
				String subscriberEmail = resultSet.getString("subscriber_email");
				Category subscriberCategory = Category.valueOf(resultSet.getString("subscriber_category"));

				Subscriber newSubscriber = new Subscriber( subscriberName, subscriberEmail, subscriberCategory);
				subscriberList.add(newSubscriber);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return subscriberList;
	}	
	
	public boolean deleteSubscriber(String subscriberEmail) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String deleteStatement = "DELETE FROM public.subscribers WHERE subscriber_email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, subscriberEmail);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}
	
	public boolean insertSubscriber(Subscriber subscriber) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String insertStatement = "INSERT INTO public.subscribers VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,subscriber.getName());
			preparedStatement.setString(2,subscriber.getEmail());
			preparedStatement.setString(3,subscriber.getCategory().toString());

			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertSubscribers(List<Subscriber> subscriberList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String insertStatement = "INSERT INTO public.subscribers VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			
			for(Subscriber subscriber:subscriberList) {
				preparedStatement.setString(1,subscriber.getName());
				preparedStatement.setString(2,subscriber.getEmail());
				preparedStatement.setString(3,subscriber.getCategory().toString());

				System.out.println("Insertion for " + subscriber.getName() + " is completed!");
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateSubscriber(Subscriber subscriber) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			
			String updateStatement = "UPDATE public.subscribers SET subscriber_name = ?, subscriber_email = ?, subscriber_category = ? WHERE subscriber_email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1,subscriber.getName());
			preparedStatement.setString(2,subscriber.getEmail());
			preparedStatement.setString(3,subscriber.getCategory().toString());
			preparedStatement.setString(4,subscriber.getEmail());


			preparedStatement.executeUpdate();
			
			System.out.println("Update for " + subscriber.getName() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
}

