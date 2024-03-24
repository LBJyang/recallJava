package HongZe.HibernateRecall;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import HongZe.HibernateRecall.entity.User;
import HongZe.HibernateRecall.service.UserService;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("jdbc.properties")
public class AppConfig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		if (userService.fetchUserByEmail("yangjiaze@dmail.com") == null) {
			User jiaze = userService.register("yangjiaze@dmail.com", "yangjiaze", "yangjiaze");
			System.out.println(jiaze);
		}
		if (userService.fetchUserByEmail("yangjiahong@dmail.com") == null) {
			User jiahong = userService.register("yangjiahong@dmail.com", "yangjiahong", "yangjiahong");
			System.out.println(jiahong);
		}
		for (User user : userService.getUsers(1)) {
			System.out.println(user);
		}
		User jiaze = userService.login("yangjiaze@dmail.com", "yangjiaze");
		System.out.println(jiaze);
		((ConfigurableApplicationContext) context).close();
	}

	@Value("${jdbc.url}")
	String jdbcUrl;
	@Value("${jdbc.username}")
	String jdbcUsername;
	@Value("${jdbc.password}")
	String jdbcPassword;

	@Bean
	DataSource getDataSource() {
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
	LocalSessionFactoryBean createSessionFactory(@Autowired DataSource dataSource) {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		prop.setProperty("hibernate.show_sql", "true");
		var sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("HongZe.HibernateRecall.entity");
		sessionFactoryBean.setHibernateProperties(prop);
		return sessionFactoryBean;
	}

	@Bean
	PlatformTransactionManager createTxManager(@Autowired SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
}
