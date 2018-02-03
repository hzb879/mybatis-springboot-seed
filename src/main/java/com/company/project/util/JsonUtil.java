package com.company.project.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static JsonNode parseJson(String json) {
		JsonNode node = null;
		try {
			node = mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	public static ObjectNode parseToObjectNode(String json) {
		ObjectNode node = null;
		try {
			node = (ObjectNode) mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	public static ArrayNode parseToArrayNode(String json) {
		ArrayNode node = null;
		try {
			node = (ArrayNode) mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	public static String createJson(Object obj){
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	public static <T> T parseAsSimpleObj(String jsonStr, Class<T> objClass){
		T t = null;
		try {
			t = mapper.readValue(jsonStr, objClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> T parseAsComplexObj(String jsonStr, TypeReference<T> typeReference){
		T t=null;
		try {
			t = mapper.readValue(jsonStr, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	
}
