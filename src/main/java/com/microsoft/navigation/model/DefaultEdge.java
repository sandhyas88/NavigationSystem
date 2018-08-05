package com.microsoft.navigation.model;

public class DefaultEdge implements IEdge {

	private double distance;
	
	public DefaultEdge(double distance)
	{
		this.distance = distance;
	}
	
	
	@Override
	public double getDistance() {
		return this.distance;
	}
	
}
