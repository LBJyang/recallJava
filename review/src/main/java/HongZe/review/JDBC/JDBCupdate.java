package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCupdate {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(JDBCquery.url, JDBCquery.nameString,
				JDBCquery.passwordString)) {
			try (PreparedStatement ps = connection
					.prepareStatement("insert into students (id,class_id,name,gender,score) values (?,?,?,?,?)")) {
				ps.setObject(1, 5);
				ps.setObject(2, 1);
				ps.setObject(3, "杨帆");
				ps.setObject(4, "M");
				ps.setObject(5, 100);
				ps.executeUpdate();
				System.out.println("insert job done!");
			}
		}
	}

}
