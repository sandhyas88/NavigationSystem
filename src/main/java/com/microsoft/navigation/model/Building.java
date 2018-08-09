package com.microsoft.navigation.model;

import java.util.Set;

public class Building implements INode {
	
	private String id;
	Set<IEdge> adjacencySet;
	
	public Building(String id)
	{
		this.id = id;
	}

	@Override
	public String getId() {
		
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	public Set<IEdge> getAdjacencySet() {
		return adjacencySet;
	}

	@Override
	public void setAdjacencySet(Set<IEdge> adjacencySet) {
		this.adjacencySet = adjacencySet;
	}
	
	
}
