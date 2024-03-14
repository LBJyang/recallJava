package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCqueryPreparedStatement {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		String nameString = "root";
		String passwordString = "0508";
		try (Connection connection = DriverManager.getConnection(url, nameString, passwordString)) {
			try (PreparedStatement ps = connection.prepareStatement(
					"select id,name,class_id,gender from students where gender = ? and class_id = ?")) {
				ps.setObject(1, "M");
				ps.setObject(2, 1);
				try (ResultSet rs = ps.executeQuery()) {
					System.out.println("Boys in Class One:");
					while (rs.next()) {
						System.out.println("id:" + rs.getLong("id") + "  name:" + rs.getString("name"));
					}
				}
			}
		}
	}

}
