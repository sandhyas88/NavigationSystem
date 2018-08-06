package com.microsoft.navigation.model;

public class DefaultEdge implements IEdge {

	private INode source;
	private INode destination;
	private double distance;
	
	public DefaultEdge(INode source, INode destination, double distance)
	{
		this.distance = distance;
		this.source = source;
		this.destination = destination;
	}
	
	
	@Override
	public double getDistance() {
		return this.distance;
	}


	public INode getSource() {
		return source;
	}


	public INode getDestination() {
		return destination;
	}


	
	
}
