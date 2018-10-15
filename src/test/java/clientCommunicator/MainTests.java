package clientCommunicator;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import clientCommunicator.Main;
import orders.Order;
import requests.VIPPS_GetOrderStatus;

public class MainTests {
	Main main;
	String token;
	
	@Before
	public void setMain() throws IOException {
	main = new Main();
	}
	
	@Test
	@Ignore
	public void shouldGenerateOrderInDatabase() throws SQLException, IOException {
		Order orderCreated = main.createOrder();
		Order orderCaught = main.getOrderFromDatabaseByVippsId(orderCreated.getVippsId());
		assertThat(orderCreated).isEqualTo(orderCaught);
	}
	@Test
	@Ignore
	public void shouldFetchAccessTokenFromVipps() throws IOException {
		String token = main.updateAccessToken();
		assertThat(token).isNotNull();
		System.out.println(token);
	}
	@Test
	@Ignore
	public void shouldInitiatePaymentAndReturnURL() throws IOException, SQLException {
		
		String URL = main.createOrder().getDeepLink();
		System.out.println(URL);
	}
	@Test
	@Ignore
	public void shouldGenerateOrderAndUpdateOrderStatus() throws IOException, SQLException {
		
		Order orderCreated = main.createOrder();
		String status = main.checkOrderStatus(orderCreated.getVippsId());
		assertThat(status).isEqualTo("RESERVE");	
		
	}

	@Test
	@Ignore
	public void shouldCaptureOrder() throws SQLException, IOException {
		boolean captured = false;
		Order orderCreated = main.createOrder();
		if(orderCreated.getStatus().equals("RESERVE")) {
			captured = main.captureOrder(orderCreated.getVippsId());
		}
		
		assertThat(captured).isEqualTo(true);
	}
	
	@Test
	@Ignore
	public void shouldCancelOrder() throws SQLException, IOException {
		boolean cancelled = false;
		Order orderCreated = main.createOrder();
		if(orderCreated.getStatus().equals("RESERVE")) {
			cancelled = main.cancelOrder(orderCreated.getVippsId());
		}
		
		assertThat(cancelled).isEqualTo(true);
	}

	@Test
	@Ignore
	public void shouldGenerateSpecificOrderAndCapture() throws IOException, SQLException {
		
		Order orderCreated = main.createSpesificOrder(50000, "Please buy my stuff", 48058625);
		if(orderCreated.getStatus().equals("RESERVE"));
		main.captureOrder(orderCreated.getVippsId());
		
	}
}