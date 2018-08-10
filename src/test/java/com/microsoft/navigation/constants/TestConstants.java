package com.microsoft.navigation.constants;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class TestConstants {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	public static final String MAPJSON = "{\"id\":\"testMapA\",\"nodes\":{\"a\":{\"b\":2,\"c\":5},\"b\":{\"c\":2},\"c\":{\"a\":8}}}";
	public static final String MAPID = "testMapA";
	public static final String MAPNODES = "{\"a\":{\"b\":2,\"c\":5},\"b\":{\"c\":2},\"c\":{\"a\":8},\"p\":{\"r\":9}}}";
}
