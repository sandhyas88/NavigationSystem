package com.microsoft.navigation.model;

import java.util.ArrayList;

public class Path {

	private double totalDistance;
	private ArrayList<INode> nodes;
	
	public Path(double distance,ArrayList<INode> nodes)
	{
		this.totalDistance = distance;
		this.nodes = nodes;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}
	
	public ArrayList<INode> getNodes() {
		return nodes;
	}
	
	
	
}
