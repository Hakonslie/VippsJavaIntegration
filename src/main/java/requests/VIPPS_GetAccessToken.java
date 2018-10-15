package requests;

import java.io.IOException;
import com.google.gson.*;

import JSON_Objects.VIPPS_AccessTokenJSON;

public class VIPPS_GetAccessToken {

	private HttpRequest request;
	public VIPPS_GetAccessToken() {
		
		String [] headers = new String [] 
				{ 
					("POST " + VIPPS_APIKeys.accesstokenuri + " HTTP/1.1"), 
					("Host: " + VIPPS_APIKeys.urlRoot), 
					("client_id: " + VIPPS_APIKeys.clientId),
					("client_secret: " + VIPPS_APIKeys.clientSecret),
					("Ocp-Apim-Subscription-Key: " + VIPPS_APIKeys.accessTokenSubscriptionKey),
					("Content-Length: 0")
				};	

			request = new HttpRequest(headers, VIPPS_APIKeys.urlRoot, "");
		}
		
	// Execute request and return filled accesstoken json object
	
	protected VIPPS_AccessTokenJSON fetchAccessToken() throws IOException{
		Gson gson = new Gson();
		return gson.fromJson(request.executeSSL().getBody(), VIPPS_AccessTokenJSON.class);
	}

}
