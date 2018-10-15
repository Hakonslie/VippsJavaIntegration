package clientCommunicator;

import java.io.IOException;
import java.sql.SQLException;

import org.flywaydb.core.Flyway;

import JSON_Objects.VIPPS_AccessTokenJSON;
import orders.Order;
import orders.OrderHandler;
import requests.RequestHandler;
import requests.VIPPS_APIKeys;
import requests.VIPPS_GetOrderStatus;

public class Main {
	
	OrderHandler orderHandler;
	DatabaseConnection databaseConnection;
	VIPPS_AccessTokenJSON accessTokenObject;
	RequestHandler requestHandler;
	
	// Initiate database, orderHandler and requestHandler
	
	public Main() throws IOException
	{
		VIPPS_APIKeys.readKeys();
		databaseConnection = new DatabaseConnection();
		Flyway flyway = Flyway.configure().dataSource(databaseConnection.getDataSource()).load();
		flyway.migrate();
		
		orderHandler = new OrderHandler(databaseConnection.getDataSource());
		requestHandler = new RequestHandler();
		
	}
	
	// Retrieve or update accessToken
	
	public String updateAccessToken() throws IOException {
		if(accessTokenObject != null) {
			if(Long.parseLong(accessTokenObject.getExpiresOn()) < System.currentTimeMillis()) {
				return accessTokenObject.getAccessToken();
			}
		}
		accessTokenObject = requestHandler.getAccessTokenFromVipps();
		return accessTokenObject.getAccessToken();
	}
	
	//Create order without checking getOrderStatus
	
	public Order createOrder() throws SQLException, IOException {
		Order order = orderHandler.generate(5000, "Test transaction", updateAccessToken(), Integer.parseInt(VIPPS_APIKeys.mobileNumber));
		
		return order;
	}
	
	//Create order and wait for orderStatus update
	
	public Order createSpesificOrder(int amount, String transactionText, int mobileNumber) throws SQLException, IOException {
	 
		//Open 
		
		String accesstoken = updateAccessToken();
		Order order = orderHandler.generate(amount, transactionText, accesstoken, mobileNumber);
		
		System.out.println(order.getDeepLink());
		
		// While order status = initiate wait 20 seconds and check again
		
		String status;
		while((status = orderHandler.updateOrderStatus(order.getVippsId(), accesstoken)).equals("INITIATE"))
		{
			for(int i = 20; i > 0; i--) {
				System.out.println(i);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		order.setStatus(status);
		return order;
	}
	
	// Check Order Status
	
	public String checkOrderStatus(String vippsId) throws SQLException, IOException {
		String status;
		status = orderHandler.updateOrderStatus(vippsId, updateAccessToken());
		return status;
	}
	
	// Retrieve order from Database
	
	public Order getOrderFromDatabaseByVippsId(String VippsId) throws SQLException {
		return orderHandler.retrieve(VippsId);
	}
	
	// Capture order 

	public boolean captureOrder(String vippsId) throws IOException, SQLException {
		return orderHandler.captureOrder(vippsId, updateAccessToken());
	}

	// Cancel order
	
	public boolean cancelOrder(String vippsId) throws IOException, SQLException {
		return orderHandler.cancelOrder(vippsId, updateAccessToken());
	}
	

}
