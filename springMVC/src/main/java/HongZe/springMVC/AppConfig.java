package HongZe.springMVC;

import java.io.File;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import HongZe.springMVC.orm.MyTemplate;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.Servlet5Loader;
import io.pebbletemplates.spring.servlet.PebbleViewResolver;
import jakarta.jms.ConnectionFactory;
import jakarta.servlet.ServletContext;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJms
@EnableWebMvc
@PropertySource({ "classpath:/jdbc.properties", "classpath:/smtp.properties", "classpath:/jms.properties" })
public class AppConfig {
//	---------start the tomcat-----------------------------------------
	public static void main(String args[]) throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(Integer.getInteger("port", 8080));
		tomcat.getConnector();

		Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
		ctx.setResources(resources);
		tomcat.start();
		tomcat.getServer().await();
	}

//-----database configuration---------------------------------------------
	@Bean
	DataSource dataSource(@Value("${jdbc.url}") String jdbcUrl, @Value("${jdbc.username}") String jdbcUsername,
			@Value("${jdbc.password}") String jdbcPassword) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(jdbcUsername);
		config.setPassword(jdbcPassword);
		config.addDataSourceProperty("autoCommit", false);
		config.addDataSourceProperty("connectionTimeout", "5");
		config.addDataSourceProperty("idleTimeout", "60");
		return new HikariDataSource(config);
	}

	@Bean
	JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	MyTemplate myTemplate(@Autowired JdbcTemplate jdbcTemplate) {
		return new MyTemplate(jdbcTemplate, "HongZe.springMVC.entity");
	}

	@Bean
	PlatformTransactionManager createTransactionManager(@Autowired DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

//---------------------webmvc configuration--------------------------------
	@Bean
	WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// TODO Auto-generated method stub
				for (HandlerInterceptor handlerInterceptor : interceptors) {
					registry.addInterceptor(handlerInterceptor);
				}
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				// TODO Auto-generated method stub
				WebMvcConfigurer.super.addResourceHandlers(registry);
				registry.addResourceHandler("/static/**").addResourceLocations("/static/");
			}

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/api/**").allowedOrigins("http://local.liaoxuefeng.com:8080")
						.allowedHeaders("GET", "POST").maxAge(3600);
			}
		};
	}

	@Bean
	ViewResolver createViewResolver(@Autowired ServletContext servletContext) {
		var engine = new PebbleEngine.Builder().autoEscaping(true).cacheActive(false)
				.loader(new Servlet5Loader(servletContext)).build();
		var viewResolver = new PebbleViewResolver(engine);
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix("");
		return viewResolver;
	}

	@Bean
	ObjectMapper createObjectMapper() {
		var om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return om;
	}

	// -----------javamail configuration----------------------------
	@Bean
	JavaMailSender createJavaMailSender(@Value("${smtp.host}") String host, @Value("${smtp.port}") int port,
			@Value("${smtp.auth}") String auth, @Value("${smtp.username}") String username,
			@Value("${smtp.password}") String password, @Value("${smtp.debug:true}") String debug) {
		var mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocal", "smtp");
		props.put("mail.smtp.auth", auth);
		props.put("mail.debug", debug);
		if (port == 587) {
			props.put("mail.smtp.starttls.enable", "true");
		}
		if (port == 465) {
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		return mailSender;
	}

//	-----------JMS configuration----------------------------------------
	@Bean
	ConnectionFactory createConnectionFactory(@Value("${jms.properties:tcp://localhost:61616}") String url,
			@Value("${jms.properties:admin}") String username, @Value("${jms.properties:password}") String password) {
		return new ActiveMQJMSConnectionFactory(url, username, password);
	}

	@Bean
	JmsTemplate creaJmsTemplate(@Autowired ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}

	@Bean("jmsListenerContainerFactory")
	DefaultJmsListenerContainerFactory createDefaultJmsListenerContainerFactory(
			@Autowired ConnectionFactory connectionFactory) {
		var factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}
