package com.ycs.workflow.actions.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class WorkflowClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
	    System.out.println("Url:");
		String inp  = new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		HttpGet httpget = new HttpGet(inp);
		System.out.println("URI is "+httpget.getURI());
//	      HttpResponse response = client.execute(httpget);
//	      HttpEntity entity = response.getEntity();String str = EntityUtils.toString(entity)
	      
	      ResponseHandler<String>  responseHandler = new BasicResponseHandler();
          String responseBody = client.execute(httpget, responseHandler);
          JSONObject json = null;
 
//          JSONSerializer.toJSON(responseBody);
		  json = JSONObject.fromObject(responseBody);
         
          
          System.out.println(responseBody + "\n\nJSON="+json.toString(5));
	}

}
