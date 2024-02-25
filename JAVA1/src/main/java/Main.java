import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double atomPrice = getTickerPrice("ATOMUSDT");
		System.out.println(atomPrice);
	}

	private static double getTickerPrice(String symbol) {
		try {
			URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + symbol);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// 解析JSON响应
				JSONObject jsonObject = new JSONObject(response.toString());
				return Double.parseDouble(jsonObject.getString("price"));
			} else {
				System.out.println("API请求失败，响应代码：" + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0; // 默认返回0
	}
}
