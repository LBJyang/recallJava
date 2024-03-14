package HongZe.review.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import HongZe.review.beans.Student;

public class JDBCbatch {

	public static void main(String[] args) throws SQLException {
		Student[] students = new Student[] { new Student(6, 2, "zhuxi", "M", 100),
				new Student(7, 2, "xiaoxia", "M", 100) };

		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(JDBCquery.url, JDBCquery.nameString,
				JDBCquery.passwordString)) {
			try (PreparedStatement ps = connection
					.prepareStatement("insert into students (id,class_id,name,gender,score) values(?,?,?,?,?)")) {
				for (Student student : students) {
					ps.setObject(1, student.id);
					ps.setObject(2, student.class_id);
					ps.setObject(3, student.nameString);
					ps.setObject(4, student.gender);
					ps.setObject(5, student.score);
					ps.addBatch();
				}
				int[] ns = ps.executeBatch();
				for (int i : ns) {
					System.out.println(i + " line inserted");
				}
			}
		}
	}

}
