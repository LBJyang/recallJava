package HongZe.review.reflection;

import HongZe.review.beans.Student;

public class GetClassMethod {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		Class<Student> stdClass = Student.class;
		System.out.println(stdClass.getMethod("getScore", String.class));
		System.out.println(stdClass.getMethod("getName"));
		System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));
	}

}
