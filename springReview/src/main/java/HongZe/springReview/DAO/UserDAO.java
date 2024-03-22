package HongZe.springReview.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HongZe.springReview.service.User;

@Component
@Transactional
public class UserDAO extends AbstractDAO<User> {
	public User fetchByEmail(String email) {
		List<User> users = getJdbcTemplate().query("select * from users where email = ?",
				(ResultSet rs, int rowNum) -> {
					return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}, new Object[] { email });
		return users.isEmpty() ? null : users.get(0);
	}

	public User getUserByEmail(String email) {
		return getJdbcTemplate().queryForObject("select * from users where email = ?", getRowMapper(), email);
	}

	public User createUser(String email, String name, String password) {
		KeyHolder holder = new GeneratedKeyHolder();
		if (1 != getJdbcTemplate().update((conn) -> {
			PreparedStatement ps = conn.prepareStatement("insert into users(email,password,name) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, email);
			ps.setObject(2, name);
			ps.setObject(3, password);
			return ps;
		}, holder)) {
			throw new RuntimeException("Insert failed!");
		}
		if ("root".equalsIgnoreCase(name)) {
			throw new RuntimeException("Invalid name, will rollback...");
		}
		return new User(holder.getKey().longValue(), email, name, password);
	}

	public void updateUser(User user) {
		if (1 != getJdbcTemplate().update("update users set name = ? where id = ?", user.getName(), user.getId())) {
			throw new RuntimeException("User not found by Id!");
		}
	}

	public User login(String email, String password) {
		User user = fetchByEmail(email);
		if (user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("login failed.");
	}
}
