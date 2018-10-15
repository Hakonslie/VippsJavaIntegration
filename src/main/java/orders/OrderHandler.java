package orders;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;

import com.google.gson.Gson;

import JSON_Objects.VIPPS_GetOrderStatusJSON;
import requests.HttpRequest;
import requests.HttpResponse;
import requests.RequestHandler;
import requests.VIPPS_APIKeys;


public class OrderHandler {
	
	private int orderId = new Random().nextInt(995599);
	private DataSource dataSource;
	private RequestHandler requestHandler;
	
	public OrderHandler(DataSource dataSource) throws IOException {
		
		this.dataSource = dataSource;
		requestHandler = new RequestHandler();
		
	}
	
	/*
	 * Generate new Order
	 */
	
	public Order generate(int amount, String transactionText, String accesstoken, int mobileNumber) throws SQLException, IOException {
		
		// Increment orderId
		orderId+=1;
		
		// Create order
		
		Order order = new Order();
		order.setAmount(amount);
		order.setVippsId("Walker" + orderId);
		order.setTransactionText(transactionText);
		order.setMobileNumber(mobileNumber);
		
		order.setDeepLink(requestHandler.intiatePayment(order.getAmount(), order.getVippsId(), order.getTransactionText(), order.getMobileNumber(), accesstoken));
		
		// Create order in Database
		
		try(Connection connection = dataSource.getConnection()) {
			String sql ="insert into orders (vippsid, amount, transactiontext, mobilenumber) values (?, ?, ?, ?)";
			try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, order.getVippsId());
				statement.setInt(2, order.getAmount());
				statement.setString(3, order.getTransactionText());
				statement.setInt(4, order.getMobileNumber());
				statement.execute();
				
				try(ResultSet rs = statement.getGeneratedKeys()) {
						rs.next();
						order.setDbId(rs.getInt(1));
				}
			}
		}
	
		// return created order with id from internal database
		
		return order;		
		
	}
	
	public String updateOrderStatus(String orderId, String accesstoken) throws IOException, SQLException {
		String status;
		status = requestHandler.getOrderStatus(orderId, accesstoken);
		
		//check if status is different from what is stored in database
		
		if(status != null && !status.equals(retrieve(orderId).getStatus())) {
			//Update database
			
			System.out.println("Updating database");
			
			try(Connection connection = dataSource.getConnection()) {
				String sql = "update orders set status = ? where vippsid = ?";
				try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
					statement.setString(1, status);
					statement.setString(2, orderId);
					statement.execute();
				}
			}
			
		}
		
		
		return status;
	}
	


	/*
	 * Get Order from Database
	 */

	public Order retrieve(String vippsId) throws SQLException {
		Order order = new Order();
		
		// get order from Database	
		order.setVippsId(vippsId);
			try(Connection connection = dataSource.getConnection()) {		
				try(PreparedStatement statement = connection.prepareStatement("select * from orders where vippsid = ?")) {
	    			statement.setString(1, vippsId);
	    			try(ResultSet rs = statement.executeQuery()) {
	    				if(rs.next()) {
	    				order.setAmount(rs.getInt("amount"));
	    				order.setCapturedAmount(rs.getInt("capturedamount"));
	    				order.setRefundedAmount(rs.getInt("refundedamount"));
	    				order.setMobileNumber(rs.getInt("mobilenumber"));
	    				order.setDbId(rs.getInt("dbid"));
	    				order.setTransactionText(rs.getString("transactiontext"));
	    				order.setStatus(rs.getString("status"));
	    				}
	    				
	    			}	
	    		}
	    	}

		// return order. 
		
		return order;
	}

	public boolean captureOrder(String vippsId, String accesstoken) throws IOException, SQLException {
		
		boolean successful = requestHandler.captureOrder(vippsId, accesstoken);
		
		// if Order is captured - updated Database 
		// This is done manually because getOrderStatus does not return CAPTURE status
		
		if(successful) {
			try(Connection connection = dataSource.getConnection()) {
				String sql = "update orders set status = ? where vippsid = ?";
				try(PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
					statement.setString(1, "CAPTURE");
					statement.setString(2, vippsId);
					statement.execute();
				}
			}
		}
		
		return successful;
		
	}
	
	public boolean cancelOrder(String vippsId, String accesstoken) throws IOException, SQLException {

		boolean successful = requestHandler.cancelOrder(vippsId, accesstoken);
		
		// If request is successful call updateOrderStatus to update database
		
		if(successful)updateOrderStatus(vippsId, accesstoken);
		return successful;
		
	}
}
