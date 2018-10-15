package requests;

import java.io.IOException;
import com.google.gson.Gson;
import JSON_Objects.*;

public class VIPPS_GetOrderStatus {

	VIPPS_GetOrderStatusJSON gos= new VIPPS_GetOrderStatusJSON();
	VIPPS_TransactionInfoJSON miObject = new VIPPS_TransactionInfoJSON();
	
	public String getStatus(String orderId, String accessToken) throws IOException {
		

		HttpRequest request;
		Gson gson = new Gson();
		String body = "";

		
		String [] headers = new String [] 
				{ 
					("GET /ecomm/v2/payments/" + orderId + "/status HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("Authorization: Bearer " + accessToken),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.productSubscriptionKey),
					("Content-Type: " + "application/json")
				};	

		request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, body);
		HttpResponse response = request.executeSSL();
		boolean requestSucceed = false;
		String status = "";
		try {
		VIPPS_GetOrderStatusJSON getOrderStatusResponse = gson.fromJson(response.getBody(), VIPPS_GetOrderStatusJSON.class);
		status = getOrderStatusResponse.getTransactionInfo().getStatus();
		} catch (com.google.gson.JsonSyntaxException e) {
			e.printStackTrace(); 
			System.out.println("JSON object failed, sending new request  in 5 seconds");
			status = getStatus(orderId, accessToken);			
		}
			
		return status;
	}

}
