package Hongze.springboot_multidatasource.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return RoutingDataSourceContext.getDataSourceRoutingKey();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		// TODO Auto-generated method stub
		DataSource ds = super.determineTargetDataSource();
		logger.info("determine target datasource:{}", ds);
		return ds;
	}

}
