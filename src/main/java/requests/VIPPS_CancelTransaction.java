package requests;

import java.io.IOException;

import com.google.gson.Gson;

import JSON_Objects.VIPPS_CaTransactionJSON;
import JSON_Objects.VIPPS_CaTransactionMerchantInfoJSON;
import JSON_Objects.VIPPS_CaTransactionResponseJSON;
import JSON_Objects.VIPPS_CaTransactionTransactionInfoJSON;

public class VIPPS_CancelTransaction {

	VIPPS_CaTransactionJSON cancel = new VIPPS_CaTransactionJSON();
	VIPPS_CaTransactionMerchantInfoJSON merchant = new VIPPS_CaTransactionMerchantInfoJSON();
	VIPPS_CaTransactionTransactionInfoJSON transaction = new VIPPS_CaTransactionTransactionInfoJSON();
	String orderId;
	
	public String cancelTransaction(String orderId, String accessToken) throws IOException {
		
		cancel.setMerchantInfo(merchant);
		cancel.setTransaction(transaction);
		merchant.setMerchantSerialNumber(VIPPS_APIKeys.merchantSerialNumber);
		transaction.setTransactionText("Cancelled");
		
		this.orderId = orderId;
		HttpRequest request;
		Gson gson = new Gson();
		String body = gson.toJson(cancel);
		
		String [] headers = new String [] 
				{ 
					("PUT /ecomm/v2/payments/" + orderId + "/cancel HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("Authorization: Bearer " + accessToken),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.productSubscriptionKey),
					("Content-Type: " + "application/json"),
					("Content-Length: " + body.length())
				};	

		request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, body);
		HttpResponse response = request.executeSSL();

		VIPPS_CaTransactionResponseJSON cancelResponse = gson.fromJson(response.getBody(), VIPPS_CaTransactionResponseJSON.class);
		return cancelResponse.getTransactionInfo().getStatus();
	}

}
