package requests;

import java.io.IOException;
import com.google.gson.Gson;
import JSON_Objects.*;

public class VIPPS_CaptureTransaction {

	VIPPS_CaTransactionJSON capture = new VIPPS_CaTransactionJSON();
	VIPPS_CaTransactionMerchantInfoJSON merchant = new VIPPS_CaTransactionMerchantInfoJSON();
	VIPPS_CaTransactionTransactionInfoJSON transaction = new VIPPS_CaTransactionTransactionInfoJSON();
	String orderId;
	
	public String captureTransaction(String orderId, String accessToken) throws IOException {
		
		capture.setMerchantInfo(merchant);
		capture.setTransaction(transaction);
		merchant.setMerchantSerialNumber(VIPPS_APIKeys.merchantSerialNumber);
		transaction.setTransactionText("Captured");
		
		this.orderId = orderId;
		HttpRequest request;
		Gson gson = new Gson();
		String body = gson.toJson(capture);
		
		String [] headers = new String [] 
				{ 
					("POST /ecomm/v2/payments/" + orderId + "/capture HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("Authorization: Bearer " + accessToken),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.productSubscriptionKey),
					("Content-Type: " + "application/json"),
					("Content-Length: " + body.length())
				};	

		request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, body);
		HttpResponse response = request.executeSSL();

		VIPPS_CaTransactionResponseJSON captureResponse = gson.fromJson(response.getBody(), VIPPS_CaTransactionResponseJSON.class);
		return captureResponse.getTransactionInfo().getStatus();
	}

}
