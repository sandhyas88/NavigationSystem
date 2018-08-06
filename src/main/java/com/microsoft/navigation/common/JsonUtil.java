package com.microsoft.navigation.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	public static HashMap<String,HashMap<String,Double>> getMapFromJson(String json)
	{
		HashMap<String,HashMap<String,Double>> nodeMap = null;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			nodeMap = mapper.readValue(json, new TypeReference<HashMap<String,HashMap<String,Double>>>(){});
			/*f*/
		} catch (Exception e) {
			logger.error("Error in parsing json",e);
		}
		return nodeMap;
		
	}
}
