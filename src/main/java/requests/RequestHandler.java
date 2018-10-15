package requests;

import java.io.IOException;

import JSON_Objects.VIPPS_AccessTokenJSON;
import JSON_Objects.VIPPS_CaTransactionJSON;

public class RequestHandler {
	
	// Read data from external json file in initiation
	
	public RequestHandler() throws IOException {
	VIPPS_APIKeys.readKeys();	
	}
	
	// Get access token
	
	public VIPPS_AccessTokenJSON getAccessTokenFromVipps() throws IOException {
		return new VIPPS_GetAccessToken().fetchAccessToken();
	}
	
	// Initiate Payment
	
	public String intiatePayment(int amount, String orderId, String transactionText, int mobileNumber, String accessToken) throws IOException {
		
		String URL;
		URL = new VIPPS_InitiatePaymentRequest(amount, mobileNumber, orderId, transactionText).initiatePayment(accessToken);
		if(URL != null) return URL; else return "";
	}
	
	// Get order status
	
	public String getOrderStatus(String orderId, String accessToken) throws IOException {
		
		VIPPS_GetOrderStatus getOrderStatus = new VIPPS_GetOrderStatus();
		
		return getOrderStatus.getStatus(orderId, accessToken);
	}

	public boolean captureOrder(String orderId, String accessToken) throws IOException {
		
		return new VIPPS_CaptureTransaction().captureTransaction(orderId, accessToken).equals("Captured");

		
	}

	public boolean cancelOrder(String vippsId, String accesstoken) throws IOException {

		return new VIPPS_CancelTransaction().cancelTransaction(vippsId, accesstoken).equals("Cancelled");
		
	}

}
