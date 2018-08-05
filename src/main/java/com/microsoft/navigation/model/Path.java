package com.microsoft.navigation.model;

import java.util.List;

public class Path {

	private INode source;
	private INode destination;
	private double totalDistance;
	private List<INode> nodes;
	
	public Path(INode source, INode destination, double distance,List<INode> nodes2)
	{
		this.totalDistance = distance;
		this.nodes = nodes2;
		this.source = source;
		this.destination = destination;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}
	
	public List<INode> getNodes() {
		return nodes;
	}

	public INode getSource() {
		return source;
	}

	public INode getDestination() {
		return destination;
	}
	
	
	
}
