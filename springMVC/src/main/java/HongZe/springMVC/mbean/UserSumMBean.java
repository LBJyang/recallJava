package HongZe.springMVC.mbean;

import org.jgroups.annotations.ManagedAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import HongZe.springMVC.service.UserService;

@Component
@ManagedResource(objectName = "users:name=getSum")
public class UserSumMBean {
	int sum;
	@Autowired
	UserService userService;

	@ManagedAttribute(name = "sum", description = "get User sum.")
	public int getSum() {
		return userService.getUsers().size();
	}
}
