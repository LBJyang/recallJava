package HongZe.review.reflection;

public class ClassInfo {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		printClassInfo("this is a string".getClass());
		printClassInfo(Runnable.class);
		printClassInfo(String[].class);
		printClassInfo(int.class);
		printClassInfo(Class.forName("java.time.Month"));
	}

	private static void printClassInfo(Class cls) {
		// TODO Auto-generated method stub
		System.out.println("Class Name:" + cls.getName());
		System.out.println("Simple Name:" + cls.getSimpleName());
		if (cls.getPackage() != null) {
			System.out.println("Package Name:" + cls.getPackage().getName());
		}
		System.out.println("is interface:" + cls.isInterface());
		System.out.println("is enum:" + cls.isEnum());
		System.out.println("is array:" + cls.isArray());
		System.out.println("is primitive:" + cls.isPrimitive());
		System.out.println("--------------------------------");
	}
}
