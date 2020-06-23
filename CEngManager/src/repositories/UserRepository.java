package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BasicConnectionPool;
import models.User;
import models.UserType;

/**
 * @author Gorkem
 *
 */
public class UserRepository {

	public User selectUser(Integer userIdParam) {
		User foundUser = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.users WHERE user_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setInt(1,userIdParam);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading user records...");

			while (resultSet.next()) {
				Integer userId = resultSet.getInt("user_id");
				String userName = resultSet.getString("user_name");
				String userPassword = resultSet.getString("user_password");
				UserType userType = UserType.valueOf(resultSet.getString("user_type").toUpperCase());

				foundUser = new User(userId, userName, userPassword, userType);

			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundUser;
	}
	
	public User selectUser(String username) {
		User foundUser = null;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.users WHERE user_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1,username);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading user records...");

			while (resultSet.next()) {
				Integer userId = resultSet.getInt("user_id");
				String userName = resultSet.getString("user_name");
				String userPassword = resultSet.getString("user_password");
				UserType userType = UserType.valueOf(resultSet.getString("user_type").toUpperCase());

				foundUser = new User(userId, userName, userPassword, userType);

			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return foundUser;
	}
	
	public List<User> selectAllUsers() {
		List<User> userList = new ArrayList<User>(); 
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String selectStatement = "SELECT * FROM public.users";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Reading user records...");

			while (resultSet.next()) {
				Integer userId = resultSet.getInt("user_id");
				String userName = resultSet.getString("user_name");
				String userPassword = resultSet.getString("user_password");
				UserType userType = UserType.valueOf(resultSet.getString("user_type").toUpperCase());

				User newUser = new User(userId, userName, userPassword, userType);
				userList.add(newUser);
			}
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return userList;
	}
	
	public boolean deleteUser(Integer userIdParam) {//TODO no response?
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String deleteStatement = "DELETE FROM public.users WHERE user_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, userIdParam);
			preparedStatement.executeUpdate();

			System.out.println("Removal succesful...");
			BasicConnectionPool.releaseConnection(connection);
			result = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;

	}

	public boolean insertUser(User user) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String insertStatement = "INSERT INTO public.users VALUES(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setInt(1,user.getId());
			preparedStatement.setString(2,user.getUsername());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setString(4,user.getType().toString().toUpperCase());

			preparedStatement.executeUpdate();
			result = true;
			System.out.println("Insertion complete!");
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean insertUsers(List<User> userList) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");
			String insertStatement = "INSERT INTO public.users VALUES(?,?,?,?)";
			for(User user:userList) {
				PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
				preparedStatement.setInt(1,user.getId());
				preparedStatement.setString(2,user.getUsername());
				preparedStatement.setString(3,user.getPassword());
				preparedStatement.setString(4,user.getType().toString().toUpperCase());

				preparedStatement.executeUpdate();
			}
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public boolean updateUser(User user) {
		boolean result = false;
		try (Connection connection = BasicConnectionPool.getConnection();) {
			System.out.println("Connected to database!");

			String updateStatement = "UPDATE public.users SET user_name = ?, user_password = ?, user_type = ?, user_id = ? WHERE user_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getType().toString());
			preparedStatement.setInt(4,user.getId());
			preparedStatement.setInt(5,user.getId());
			
			preparedStatement.executeUpdate();
			System.out.println("Update for " + user.getUsername() + " is completed!");
			result = true;
			BasicConnectionPool.releaseConnection(connection);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
}
