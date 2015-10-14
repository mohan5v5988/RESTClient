package org.mohan.app.dao;

import java.io.IOException;
import java.util.List;

import org.mohan.app.model.Customer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDAO extends IGenericsDAO<Customer> {

	ObjectMapper mapper = new ObjectMapper();
	public CustomerDAO() {
		base_url += "customer/"; 
	}
	
	@Override
	public List<Customer> getAll() {
		String json = super.callGET(base_url);
		List<Customer> list = null;
		try {
			list =  mapper.readValue(json, new TypeReference<List<Customer>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Customer getByPK(Customer obj) {
		String json = super.callGET(base_url+"get?nid="+obj.getNid());
		Customer cus = null;
		try {
			cus = mapper.readValue(json, Customer.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cus;
	}

	@Override
	public int add(Customer obj) {
		String data = "";
		try {
			data = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String json = super.callPOST(base_url, data);
		return 0;
	}

	@Override
	public int update(Customer obj) {
		String data = "";
		try {
			data = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String json = super.callPOST(base_url + obj.getNid(), data);
		return 0;
	}

	@Override
	public int delete(Customer obj) {
		super.callDELETE(base_url + obj.getNid());
		return 0;
	}
}
