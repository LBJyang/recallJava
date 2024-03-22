package HongZe.springReview.DAO;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import jakarta.annotation.PostConstruct;

public abstract class AbstractDAO<T> extends JdbcDaoSupport {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String table;
	private Class<T> entityClass;
	private RowMapper<T> rowMapper;

	public AbstractDAO() {
		// TODO Auto-generated constructor stub
		this.entityClass = getParameteriedType();
	}

	private Class<T> getParameteriedType() {
		// TODO Auto-generated method stub
		Type type = getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			throw new IllegalArgumentException("Class " + getClass().getName() + "do not have parameterized type.");
		}
		return null;
	}

	@PostConstruct
	public void init() {
		super.setJdbcTemplate(jdbcTemplate);
	}
}
