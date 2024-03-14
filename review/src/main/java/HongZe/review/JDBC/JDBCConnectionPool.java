package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCConnectionPool {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(JDBCquery.url);
		config.setUsername(JDBCquery.nameString);
		config.setPassword(JDBCquery.passwordString);
		config.addDataSourceProperty("connectionTimeout", "1000");
		config.addDataSourceProperty("idleTimeout", "600000");
		config.addDataSourceProperty("maximumPoolSize", "10");
		DataSource ds = new HikariDataSource(config);
		try (Connection connection = ds.getConnection()) {
			try (PreparedStatement ps = connection.prepareStatement("delete from students where id = ?")) {
				ps.setObject(1, 4);
				int n = ps.executeUpdate();
				System.out.println(n + "line deleted!");
			}

		}

	}

}
