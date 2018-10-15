package clientCommunicator;

import java.io.IOException;

import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

import requests.VIPPS_APIKeys;

public class DatabaseConnection {

	private DataSource datasource;
	
	public DatabaseConnection() {
		
		try {
			VIPPS_APIKeys.readKeys();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			PGPoolingDataSource dataSource = new PGPoolingDataSource();
		    dataSource.setUrl(VIPPS_APIKeys.databaseAddress);
		    dataSource.setUser(VIPPS_APIKeys.databaseUsername);
		    dataSource.setPassword(VIPPS_APIKeys.databasePassword);
		    datasource = dataSource;
	}
		
	public DataSource getDataSource() {
		return this.datasource;
	}

}
