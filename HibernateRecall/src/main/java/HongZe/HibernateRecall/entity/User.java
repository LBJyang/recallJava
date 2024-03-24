package HongZe.HibernateRecall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries(@NamedQuery(name = "login", query = "SELECT u FROM User u WHERE u.email = :e AND u.password = :pwd"))
public class User extends AbstractEntity {
	private String email;
	private String password;
	private String name;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String password, String name, String email) {
		super();
		setId(id);
		this.password = password;
		this.name = name;
		this.email = email;
	}

	@Column(nullable = false, unique = true, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("User:[id=%d,email=%s,password=%s,name=%s]", getId(), email, password, name);
	}

}
