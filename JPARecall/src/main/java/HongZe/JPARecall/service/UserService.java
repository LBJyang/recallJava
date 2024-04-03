package HongZe.JPARecall.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HongZe.JPARecall.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
@Transactional
public class UserService {
	@PersistenceContext
	EntityManager em;

	public User getUserById(long id) {
		User user = em.find(User.class, id);
		if (user == null) {
			throw new RuntimeException("User not found by " + id);
		}
		return user;
	}

	public User fetchUserByEmail(String email) {
		TypedQuery<User> query = em.createQuery("select u from User u where email = :e", User.class);
		query.setParameter("e", email);
		List<User> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
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
		TypedQuery<User> query = em.createQuery("select u from User u", User.class);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize * pageIndex);
		return query.getResultList();
	}

	public User login(String email, String password) {
		TypedQuery<User> query = em.createNamedQuery("login", User.class);
		query.setParameter("e", email);
		query.setParameter("pwd", password);
		return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
	}

	public User register(String email, String password, String name) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		em.persist(user);
		return user;
	}

	public void updateUser(Long id, String name) {
		User user = getUserById(id);
		user.setName(name);
		em.refresh(user);
	}

	public void deleteUser(Long id) {
		User user = getUserById(id);
		em.remove(user);
	}
}
