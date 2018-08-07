package com.microsoft.navigation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Map")
public class MapRequest {
	
	
	@Id
	private String id;
	String nodes;
	
	public MapRequest(String id, String nodes)
	{
		this.id = id;
		this.nodes = nodes;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNodes() {
		return nodes;
	}
	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "MapRequest [id=" + id + ", nodes=" + nodes + "]";
	}
	
	

}
