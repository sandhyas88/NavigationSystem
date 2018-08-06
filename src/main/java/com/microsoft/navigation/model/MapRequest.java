package com.microsoft.navigation.model;

import java.util.HashMap;

public class MapRequest {
	
	private String id;
	private HashMap<String,HashMap<String,Double>> nodes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, HashMap<String, Double>> getNodes() {
		return nodes;
	}
	public void setNodes(HashMap<String, HashMap<String, Double>> nodes) {
		this.nodes = nodes;
	}
	
	

}
