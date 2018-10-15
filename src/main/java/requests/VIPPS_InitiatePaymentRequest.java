package requests;

import java.io.IOException;
import com.google.gson.Gson;
import JSON_Objects.*;

public class VIPPS_InitiatePaymentRequest {
	

	VIPPS_InitiatePaymentJSON ipObject = new VIPPS_InitiatePaymentJSON();
	VIPPS_MerchantInfoJSON miObject = new VIPPS_MerchantInfoJSON();
	VIPPS_TransactionJSON trObject = new VIPPS_TransactionJSON();
	VIPPS_CustomerInfoJSON ciObject = new VIPPS_CustomerInfoJSON();
	String orderId, deeplink;
	
	HttpResponse response;
	
	public VIPPS_InitiatePaymentRequest(int amount, int mobileNumber, String orderId, String transactionText)
	{
		miObject.setMerchantSerialNumber(VIPPS_APIKeys.merchantSerialNumber);
		miObject.setFallBack("http://www.vipps.no");
		miObject.setIsApp(false);
		miObject.setCallbackPrefix("http://www.vipps.no");
		
		trObject.setAmount("" + amount);
		trObject.setOrderId(orderId);
		trObject.setTransactionText(transactionText);
		
		ciObject.setMobileNumber("" + mobileNumber);
		
		ipObject.setCustomerInfo(ciObject);
		ipObject.setMerchantInfo(miObject);
		ipObject.setTransaction(trObject);
		
		
		
	}
	
	public String initiatePayment(String accessToken) throws IOException {
		HttpRequest request;
		Gson gson = new Gson();
		String body = gson.toJson(ipObject);
		
		
		String [] headers = new String [] 
				{ 
					("POST /ecomm/v2/payments HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("Authorization: Bearer " + accessToken),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.productSubscriptionKey),
					("Content-Type: " + "application/json"),
					("Content-Length: " + body.length())
				};	

			request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, body);
			response = request.executeSSL();
			
			VIPPS_InitiatePaymentResponseJSON initiatePaymentResponse = gson.fromJson(response.getBody(), VIPPS_InitiatePaymentResponseJSON.class);
			try {
			return initiatePaymentResponse.getUrl();
			}
			catch(NullPointerException e) {
				System.out.println("Didnt get URL or Order from Initiate Payment!");
				return null;
			}
	}
	
}