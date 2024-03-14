package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCquery {
	public static String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
	public static String nameString = "root";
	public static String passwordString = "0508";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		try (Connection connection = DriverManager.getConnection(url, nameString, passwordString)) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet rs = statement.executeQuery("SELECT id, name FROM students WHERE gender='M'")) {
					while (rs.next()) {
						System.out.println("we have boys,such as " + rs.getString("name"));
					}
				}
			}
		}
	}
}
