package HongZe.springReview.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("jdbc.properties")
@ComponentScan
@EnableTransactionManagement
public class AppConfig {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserServiceForDAO userService = context.getBean(UserServiceForDAO.class);
		// 插入Bob:
		if (userService.fetchUserByEmail("bob@example.com") == null) {
			userService.register("bob@example.com", "Bob", "password1");
		}
		// 插入Alice:
		if (userService.fetchUserByEmail("alice@example.com") == null) {
			userService.register("alice@example.com", "Alice", "password2");
		}
		// 插入Tom:
		if (userService.fetchUserByEmail("tom@example.com") == null) {
			userService.register("tom@example.com", "Tom", "password2");
		}
		// 插入Root:
		try {
			userService.register("root@example.com", "root", "password3");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		for (User u : userService.getUsers(1)) {
			System.out.println(u);
		}
	}

	@Value("${jdbc.url}")
	String jdbcUrl;
	@Value("${jdbc.username}")
	String userName;
	@Value("${jdbc.password}")
	String password;

	@Bean
	DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(userName);
		config.setPassword(password);
		config.addDataSourceProperty("autoCommit", "true");
		config.addDataSourceProperty("connectionTimeout", "5");
		config.addDataSourceProperty("idleTimeout", "60");
		return new HikariDataSource(config);
	}

	@Bean
	JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	PlatformTransactionManager createTxManager(@Autowired DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
