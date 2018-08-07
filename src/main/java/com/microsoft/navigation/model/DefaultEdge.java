package com.microsoft.navigation.model;

import java.io.Serializable;

import org.jgrapht.graph.DefaultWeightedEdge;

public class DefaultEdge extends DefaultWeightedEdge implements IEdge, Serializable {

	private static final long serialVersionUID = -407262409533004719L;
	private INode source;
	private INode destination;
	private double distance;
	private String label;
	
	public DefaultEdge(INode source, INode destination, double distance) 
	{
		this.distance = distance;
		this.source = source;
		this.destination = destination;
		this.label = source.getId() + "-" + destination.getId(); 
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

	public String getLabel() {
		return label;
	}

	public int hashCode()
	{
		return label.hashCode();
	}

	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DefaultEdge)) {
			return false;
		}

		DefaultEdge edge = (DefaultEdge) obj;
		return label.equals(edge.label);
	}


	@Override
	public String toString() {
		return "DefaultEdge [source=" + source + ", destination=" + destination + ", distance=" + distance + ", label="
				+ label + "]";
	}
	
	
}
