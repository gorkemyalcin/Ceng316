package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool{

	private static String url = "jdbc:postgresql://localhost:5432/cengmanagerdb";
	private static String user = "postgres";
	private static String password = "postgres";
	private static int INITIAL_POOL_SIZE = 5;
	private static List<Connection> connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
	private static List<Connection> usedConnections = new ArrayList<>();

	public static void create() throws SQLException {
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			connectionPool.add(createConnection(url, user, password));
		}
	}

	public static Connection getConnection() throws SQLException {
		create();
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public static boolean releaseConnection(Connection connection) throws SQLException {
		connection.close();
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	private static Connection createConnection(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

}
