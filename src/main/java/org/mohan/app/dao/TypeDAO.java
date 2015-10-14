package org.mohan.app.dao;

import java.io.IOException;
import java.util.List;

import org.mohan.app.model.Customer;
import org.mohan.app.model.Type;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TypeDAO extends IGenericsDAO<Type> {
	
	ObjectMapper mapper = new ObjectMapper();
	public TypeDAO() {
		base_url += "type/";
	}

	@Override
	public List<Type> getAll() {
		String json = super.callGET(base_url);
		List<Type> list = null;
		try {
			list =  mapper.readValue(json, new TypeReference<List<Type>>(){});
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
	public Type getByPK(Type obj) {
		String json = super.callGET(base_url + obj.getType());
		Type type = null;
		try {
			type = mapper.readValue(json, Type.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public int add(Type obj) {
		String data = "";
		try {
			data = mapper.writeValueAsString(obj);
			System.out.println(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String json = super.callPOST(base_url, data);
		return 0;
	}

	@Override
	public int update(Type obj) {
		String data = "";
		try {
			data = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String json = super.callPOST(base_url + obj.getType(), data);
		return 0;
	}

	@Override
	public int delete(Type obj) {
		super.callDELETE(base_url + obj.getType());
		return 0;
	}
}
