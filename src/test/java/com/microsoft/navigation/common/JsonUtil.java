package com.microsoft.navigation.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

	static final ObjectMapper mapper = new ObjectMapper();

	public static String createPerfJsonRequest() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();

		for (Integer i = 0; i < 100000; i++) {
			JsonNode childNode1 = mapper.createObjectNode();
			for (Integer j = 0; j < 100000; j++) {
				if (i == j)
					continue;

				((ObjectNode) childNode1).put(j.toString(), 1);
			}

			((ObjectNode) rootNode).set(i.toString(), childNode1);
		}

		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		return jsonString;
	}

}
