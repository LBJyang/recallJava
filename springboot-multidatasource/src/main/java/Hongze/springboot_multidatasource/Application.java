package Hongze.springboot_multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Hongze.springboot_multidatasource.config.MasterDataSourceConfiguration;
import Hongze.springboot_multidatasource.config.RoutingDataSourceConfiguration;
import Hongze.springboot_multidatasource.config.SlaveDataSourceConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Import({ MasterDataSourceConfiguration.class, SlaveDataSourceConfiguration.class,
		RoutingDataSourceConfiguration.class })
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	// -- Mvc configuration ---------------------------------------------------

	@Bean
	WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
			}
		};
	}
}
