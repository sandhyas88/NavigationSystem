package com.microsoft.navigation.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Path {

	private double totalDistance;
	private String[] nodes;
	
	public Path(double distance,String[] nodes)
	{
		this.totalDistance = distance;
		this.nodes = nodes;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}
	
	@JsonProperty("path")
	public String[] getNodes() {
		return nodes;
	}
	
}
