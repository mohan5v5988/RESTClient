package org.mohan.app.dao;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

abstract class IGenericsJClientDAO<T> {
	String base_url = "http://localhost:8080/RestWithSpring/rest/";

	public abstract List<T> getAll();

	public abstract T getByPK(T obj);

	public abstract int add(T obj);

	public abstract int update(T obj);

	public abstract int delete(T obj);

	protected String callGET(String url) {
		String output = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			output = response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	protected String callPOST(String url, String json) {
		String output = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class, json);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			System.out.println("Output from Server .... \n");
			output = response.getEntity(String.class);
			System.out.println(output);
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return output;
	}

	protected String callDELETE(String url) {
		String output = "";
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			output = response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		 }
		return output;
	}

}
