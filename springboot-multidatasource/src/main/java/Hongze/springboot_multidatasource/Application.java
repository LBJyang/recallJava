package Hongze.springboot_multidatasource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import Hongze.springboot_multidatasource.config.MasterDataSourceConfiguration;
import Hongze.springboot_multidatasource.config.SlaveDataSourceConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Import({ MasterDataSourceConfiguration.class, SlaveDataSourceConfiguration.class })
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
