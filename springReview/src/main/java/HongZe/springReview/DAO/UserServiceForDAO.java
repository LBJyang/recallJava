package HongZe.springReview.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserServiceForDAO {
	@Autowired
	UserDAO userDAO;

	public User getUserById(long id) {
		return userDAO.getById(id);
	}

	public User fetchUserByEmail(String email) {
		return userDAO.fetchByEmail(email);
	}

	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	public List<User> getUsers(int pageIndex) {
		return userDAO.getAll(pageIndex);
	}

	public User login(String email, String password) {
		User user = fetchUserByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed.");
	}

	public User register(String email, String password, String name) {
		if (userDAO.fetchByEmail(email) != null) {
			throw new RuntimeException("Email is alreay exist.");
		}
		return userDAO.createUser(email, password, name);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
}
