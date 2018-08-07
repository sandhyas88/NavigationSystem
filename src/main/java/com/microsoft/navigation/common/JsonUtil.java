package com.microsoft.navigation.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.navigation.exceptions.NavigationSystemException;

public class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	static final ObjectMapper mapper = new ObjectMapper();
	
	public static HashMap<String,HashMap<String,Double>> getMapFromJson(String json)
	{
		HashMap<String,HashMap<String,Double>> nodeMap = null;
		try {
			
			nodeMap = mapper.readValue(json, new TypeReference<HashMap<String,HashMap<String,Double>>>(){});
		} catch (Exception e) {
			logger.error("Error in parsing json" + json,e);
			throw new NavigationSystemException("Error in parsing json" + json,e);
		}
		return nodeMap;
		
	}
	
	public static String getId(String json, String name)
	{
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			String id = rootNode.get(name).asText();
			return id;
		} catch (Exception e) {
			logger.error("Error in parsing json" + json,e);
			throw new NavigationSystemException("Error in parsing json" + json,e);
		}
		
	}
	
	public static String getNestedJson(String json, String name)
	{
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(json);
			JsonNode node = rootNode.get(name);
			return mapper.writeValueAsString(node);
		} catch (Exception e) {
			logger.error("Error in parsing json" + json,e);
			throw new NavigationSystemException("Error in parsing json" + json,e);
		}
		
	}
	
}
