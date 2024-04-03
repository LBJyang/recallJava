package HongZe.JPARecall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Book extends AbstractEntity {
	private String title;

	public Book(String title) {
		super();
		this.title = title;
	}

	@Column(nullable = false, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Book:[id=%d,title=%s]", getId(), title);
	}

}
