package HongZe.JPARecall;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import HongZe.JPARecall.entity.AbstractEntity;
import HongZe.JPARecall.entity.User;
import HongZe.JPARecall.service.UserService;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("jdbc.properties")
public class AppConfig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		if (userService.fetchUserByEmail("bob@example.com") == null) {
			User bob = userService.register("bob@example.com", "bob123", "Bob");
			System.out.println("Registered ok: " + bob);
		}
		if (userService.fetchUserByEmail("alice@example.com") == null) {
			try {
				User alice = userService.register("alice@example.com", "helloalice", "Alice");
				System.out.println("Registered ok: " + alice);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 查询所有用户:
		for (User u : userService.getUsers(1)) {
			System.out.println(u);
		}
		User bob = userService.login("bob@example.com", "bob123");
		System.out.println(bob);
		((ConfigurableApplicationContext) context).close();
	}

	@Value("${jdbc.url}")
	String jdbcUrl;
	@Value("${jdbc.username}")
	String jdbcUsername;
	@Value("${jdbc.password}")
	String jdbcPassword;

	@Bean
	DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(jdbcUsername);
		config.setPassword(jdbcPassword);
		config.addDataSourceProperty("autoCommit", false);
		config.addDataSourceProperty("connectionTimeout", 5);
		config.addDataSourceProperty("idleTimeout", 60);
		return new HikariDataSource(config);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean createEntityManageFactory(@Autowired DataSource dataSource) {
		var emFactory = new LocalContainerEntityManagerFactoryBean();
		emFactory.setDataSource(dataSource);
		emFactory.setPackagesToScan(AbstractEntity.class.getPackageName());
		emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		var props = new Properties();
		props.setProperty("hibernate.hbm2ddl.auto", "update"); // 生产环境不要使用
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		emFactory.setJpaProperties(props);
		return emFactory;
	}

	@Bean
	PlatformTransactionManager createTxManager(@Autowired EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
