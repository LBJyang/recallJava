package HongZe.review.beans;

public class Student extends Person {
	public int id;
	public int class_id;
	public String nameString;
	public String gender;
	public int score;

	public Student(int id, int class_id, String nameString, String gender, int score) {
		super();
		this.id = id;
		this.class_id = class_id;
		this.nameString = nameString;
		this.gender = gender;
		this.score = score;
	}

	public int getScore(String name) {
		return 99;
	}
}
