package HongZe.review.OOP;

public class StringBuilderReview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fields = { "name", "position", "salary" };
		String table = "employee";
		String insert = buildInsertSql(table, fields);
		System.out.println(insert);
		String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
		System.out.println(s.equals(insert) ? "测试成功" : "测试失败");
	}

	private static String buildInsertSql(String table, String[] fields) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(100);
		sb.append("INSERT INTO ").append(table).append(" (");
		for (String string : fields) {
			sb.append(string).append(", ");
		}
//		sb.delete(sb.length() - 2, sb.length()).append(") VALUES (?, ?, ?)");

		sb.replace(sb.length() - 2, sb.length(), ") VALUES (?, ?, ?)");
		return sb.toString();
	}

}
