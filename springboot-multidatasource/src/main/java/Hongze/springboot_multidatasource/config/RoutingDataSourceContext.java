package Hongze.springboot_multidatasource.config;

public class RoutingDataSourceContext implements AutoCloseable {
	public static final String MASTER_DATASOURCE = "masterDataSource";
	public static final String SLAVE_DATASOURCE = "slaveDataSource";

	static final ThreadLocal<String> threadLocalDataSourceKey = new ThreadLocal<String>();

	public static String getDataSourceRoutingKey() {
		String key = threadLocalDataSourceKey.get();
		return key == null ? MASTER_DATASOURCE : key;
	}

	public RoutingDataSourceContext(String key) {
		threadLocalDataSourceKey.set(key);
	}

	@Override
	public void close() throws Exception {
		threadLocalDataSourceKey.remove();
	}

}
