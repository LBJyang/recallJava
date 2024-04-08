package HongZe.springMVC;

import java.io.File;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
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
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import HongZe.springMVC.orm.MyTemplate;
import HongZe.springMVC.service.UserService;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.Servlet5Loader;
import io.pebbletemplates.spring.servlet.PebbleViewResolver;
import jakarta.servlet.ServletContext;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:/jdbc.properties")
public class AppConfig {
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
}
