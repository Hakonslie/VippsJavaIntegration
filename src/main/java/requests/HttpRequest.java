package requests;

import java.io.IOException;
import java.io.InputStream;
import javax.net.ssl.*;

public class HttpRequest {
	String host, URI, body;
	String [] requestHeaders;
	
	// Constructor for specifying headers in the String[] headers parameter
	
	public HttpRequest(String[] headers, String host, String body) {
		requestHeaders = headers;
		this.host = host;
		this.body = body;
		
	}
	
	// This executes a secure SSL request with headers specified in the requestHeaders array.
	
	public HttpResponse executeSSL() throws IOException {
		
		// initiate SSL Socket and connect:
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try(SSLSocket socket = (SSLSocket) sslsocketfactory.createSocket(host, 443)) 
		{
			
			// Write headers 
			
			for(String header : requestHeaders) {
				socket.getOutputStream().write((header + "\r\n").getBytes());		
			}
			socket.getOutputStream().write(("Connection: close\r\n").getBytes());
			socket.getOutputStream().write(("\r\n").getBytes());
			
			//If there is a body, add it after headers
			
			if(body != "") {
				socket.getOutputStream().write(body.getBytes());
			}
			
			//Get result and build string, return HttpResponse
			
			InputStream input = socket.getInputStream();
            StringBuilder responseBuilder = new StringBuilder();
            
            int c;
            System.out.println("Response: ");
            while ((c = input.read()) != -1) {
            	responseBuilder.append((char)c);
            	System.out.print((char)c);
            }
                      
            return new HttpResponse(responseBuilder.toString());
        }
			
	}
	
}
