package HongZe.HibernateRecall.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HongZe.HibernateRecall.entity.User;

@Component
@Transactional
public class UserService {
	@Autowired
	SessionFactory sessionFactory;

	public User fetchUserById(long id) {
		return sessionFactory.getCurrentSession().byId(User.class).load(id);
	}

	public User getUserById(long id) {
		User user = fetchUserById(id);
		if (user == null) {
			throw new RuntimeException("user not found by id " + id);
		}
		return user;
	}

	public User fetchUserByEmail(String email) {
		User user = new User();
		user.setEmail(email);
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User u where u.email = ?1", User.class)
				.setParameter(1, email).list();
		return list.isEmpty() ? null : list.get(0);
	}

	public User getUserByEmail(String email) {
		User user = fetchUserByEmail(email);
		if (user == null) {
			throw new RuntimeException("user not found by email: " + email);
		}
		return user;
	}

	public List<User> getUsers(int pageIndex) {
		int pageSize = 100;
		return sessionFactory.getCurrentSession().createQuery("from User u", User.class)
				.setFirstResult(pageSize * (pageIndex - 1)).setMaxResults(pageSize * pageIndex).list();
	}

	public User signin(String email, String password) {
		List<User> user = sessionFactory.getCurrentSession()
				.createQuery("from User u where email = ?1 and password =?2", User.class).setParameter(1, email)
				.setParameter(2, password).list();
		return user.isEmpty() ? null : user.get(0);
	}

	public User login(String email, String password) {
		List<User> list = sessionFactory.getCurrentSession().createNamedQuery("login", User.class) // named query
				.setParameter("e", email).setParameter("pwd", password).list();
		return list.isEmpty() ? null : list.get(0);
	}

	public User register(String email, String password, String name) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		sessionFactory.getCurrentSession().persist(user);
		System.out.println(user.getId());
		return user;
	}

	public boolean delete(Long id) {
		User user = sessionFactory.getCurrentSession().byId(User.class).load(id);
		if (user != null) {
			sessionFactory.getCurrentSession().remove(user);
			return true;
		}
		return false;
	}

	public void update(Long id, String name) {
		User user = sessionFactory.getCurrentSession().byId(User.class).load(id);
		user.setName(name);
		sessionFactory.getCurrentSession().merge(user);
	}
}
