package com.hashbinary.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class ServiceRunnerCallable implements Callable<ServiceResult> {
	private Model model = null;

	public ServiceRunnerCallable(Model model) {
		this.model = model;
	}

	@Override
	public ServiceResult call() throws Exception {
		ServiceResult result = new ServiceResult();
		try {
			System.out.println("URL :"+ model.getServiceURL());
			System.out.println("Request Type :"+ model.getRequestType().toString());
			
			URL url = new URL(model.getServiceURL());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod(model.getRequestType().toString());
			//TODO: Request body for POST is pending.
			//conn.setRequestProperty("Accept", "application/json");
			
			//OutputStream os = conn.getOutputStream();
			
			System.out.println(conn.getResponseCode());
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			StringBuffer buffer = new StringBuffer();
			while ((output = br.readLine()) != null) {
				buffer.append(output);
			}
			
			result.setResult(buffer.toString());
			if (model.getServiceResult().equals(output))
				result.setStatus("SUCCESS");
			else
				result.setStatus("ERROR");
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			result.setStatus("ERROR");
			e.printStackTrace();

		} catch (IOException e) {
			result.setStatus("ERROR");
			e.printStackTrace();

		}

		return result;
	}

}
