package HongZe.springReview;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import HongZe.springReview.service.UserService;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.register("test@example.com", "password", "test");
		userService.login("bob@example.com", "password");
		System.out.println(userService.getClass().getName());
	}

}
