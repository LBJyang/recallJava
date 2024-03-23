package recallJava.spring_DAO.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import recallJava.spring_DAO.DAO.UserDAO;

@Component
@Transactional
public class UserService {
	@Autowired
	UserDAO userDao;

	public User getUserById(long id) {
		return userDao.getById(id);
	}

	public User fetchUserByEmail(String email) {
		return userDao.fetchUserByEmail(email);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public List<User> getUsers(int pageIndex) {
		return userDao.getAll(pageIndex);
	}

	public User login(String email, String password) {
		User user = fetchUserByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed.");
	}

	public User register(String email, String password, String name) {
		if (userDao.fetchUserByEmail(email) != null) {
			throw new RuntimeException("Email is alreay exist.");
		}
		return userDao.createUser(email, password, name);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}
