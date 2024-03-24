package HongZe.review.OOP;

import java.util.StringJoiner;

public class StringJoinerReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fields = { "name", "position", "salary" };
		String table = "employee";
		String select = buildSelectSql(table, fields);
		System.out.println(select);
		System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
	}

	private static String buildSelectSql(String table, String[] fields) {
		// TODO Auto-generated method stub
		StringJoiner sj = new StringJoiner(", ", "SELECT ", " FROM " + table);
		for (String string : fields) {
			sj.add(string);
		}
		return sj.toString();
	}

}
