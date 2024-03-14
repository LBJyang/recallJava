package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCdelete {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(JDBCquery.url, JDBCquery.nameString,
				JDBCquery.passwordString)) {
			try (PreparedStatement ps = connection
					.prepareStatement("delete from students where id not in (1,2,3,4,5)")) {
				int n = ps.executeUpdate();
				System.out.println(n);
			}
		}
	}

}
