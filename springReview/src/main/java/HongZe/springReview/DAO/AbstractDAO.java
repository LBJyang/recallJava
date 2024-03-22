package HongZe.springReview.DAO;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		this.table = this.entityClass.getSimpleName().toLowerCase() + "s";
		this.rowMapper = new BeanPropertyRowMapper<>(entityClass);
	}

	public T getById(long id) {
		return getJdbcTemplate().queryForObject("select * from " + table + "where id = ?", rowMapper, id);
	}

	public List<T> getAll(int pageIndex) {
		int limit = 100;
		int offset = limit * (pageIndex - 1);
		return getJdbcTemplate().query("select * from " + table + "limit ? offset ?", rowMapper,
				new Object[] { limit, offset });
	}

	public void deleteById(long id) {
		getJdbcTemplate().update("delete from " + table + "where id = ?", id);
	}

	public RowMapper<T> getRowMapper() {
		return rowMapper;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getParameteriedType() {
		// TODO Auto-generated method stub
		Type type = getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			throw new IllegalArgumentException("Class " + getClass().getName() + " do not have parameterized type.");
		}
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		if (types.length != 1) {
			throw new IllegalArgumentException(
					"Class " + getClass().getName() + " have more than 1 parameterized type.");
		}
		Type r = types[0];
		if (!(r instanceof Class<?>)) {
			throw new IllegalArgumentException(
					"Class " + getClass().getName() + " does not have parameterized type of class");
		}
		return (Class<T>) r;
	}

	@PostConstruct
	public void init() {
		super.setJdbcTemplate(jdbcTemplate);
	}
}
