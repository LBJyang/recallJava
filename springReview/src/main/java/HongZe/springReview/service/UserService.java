package HongZe.springReview.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HongZe.springReview.metrics.MetricTime;

@Component
public class UserService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private MailService mailService;

	public UserService(@Autowired MailService mailService) {
		// TODO Auto-generated constructor stub
		this.mailService = mailService;
	}

	private List<User> users = new ArrayList<>(List.of( // users:
			new User(1, "bob@example.com", "Bob", "password"), // bob
			new User(2, "alice@example.com", "Alice", "password"), // alice
			new User(3, "tom@example.com", "Tom", "password")));

	@MetricTime("login")
	@Transactional(rollbackFor = { IOException.class })
	public User login(String email, String password) {
		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				mailService.sendLoginMail(user);
				return user;
			}
		}
		throw new RuntimeException("login failed.");
	}

	public User getUser(long id) {
		return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
	}

	public User getUserById(long id) {
		return jdbcTemplate.execute((Connection conn) -> {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
				ps.setObject(1, id);
				try (var rs = ps.executeQuery()) {
					if (rs.next()) {
						return new User( // new User object:
								rs.getLong("id"), // id
								rs.getString("email"), // email
								rs.getString("password"), // password
								rs.getString("name")); // name
					}
					throw new RuntimeException("user not found by id.");
				}
			}

		});
	}

	public User getUserByName(String name) {
		return jdbcTemplate.execute("select * from users where name = ?", (PreparedStatement ps) -> {
			ps.setObject(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}
				throw new RuntimeException("can not find user by NAME");
			}

		});
	}

	public User getUserByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from users where email = ?", (ResultSet rs, int rowNum) -> {
			return new User( // new User object:
					rs.getLong("id"), // id
					rs.getString("email"), // email
					rs.getString("password"), // password
					rs.getString("name"));
		}, email);
	}

	public long getUsers() {
		return jdbcTemplate.queryForObject("select count(*) from users", (ResultSet rs, int rowNum) -> {
			return rs.getLong(1);
		});
	}

	public List<User> getUsers(int pageIndex) {
		int limit = 100;
		int offset = limit * (pageIndex - 1);
		return jdbcTemplate.query("select * from users limit ? offset ?", new BeanPropertyRowMapper<>(User.class),
				limit, offset);
	}

	@MetricTime("register")
	public User register(String email, String password, String name) {
		users.forEach((user) -> {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new RuntimeException("email exist.");
			}
		});
		User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong(), email, name, password);
		users.add(user);
		mailService.sendRegistrationMail(user);
		return user;
	}
}
