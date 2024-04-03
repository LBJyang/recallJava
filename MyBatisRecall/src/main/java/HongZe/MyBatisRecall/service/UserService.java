package HongZe.MyBatisRecall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HongZe.MyBatisRecall.entity.User;
import HongZe.MyBatisRecall.mapper.UserMapper;

@Component
@Transactional
public class UserService {
	@Autowired
	UserMapper userMapper;

	public User getUserById(long id) {
		User user = userMapper.getById(id);
		if (user == null) {
			throw new RuntimeException("no user found by " + id);
		}
		return user;
	}

	public User fetchUserByEmail(String email) {
		return userMapper.getByEmail(email);
	}

	public User getUserByEmail(String email) {
		User user = fetchUserByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found by email.");
		}
		return user;
	}

	public List<User> getUsers(int pageIndex) {
		int pageSize = 100;
		return userMapper.getAll(pageSize * (pageIndex - 1), pageSize * pageIndex);
	}

	public User login(String email, String password) {
		User user = userMapper.getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed!");
	}

	public User register(String email, String password, String name) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		userMapper.insert(user);
		return user;
	}

	public void updateUser(Long id, String name) {
		User user = getUserById(id);
		user.setName(name);
		user.setCreatedAt(System.currentTimeMillis());
		userMapper.update(user);
	}

	public void deleteUser(Long id) {
		userMapper.deleteById(id);
	}
}
