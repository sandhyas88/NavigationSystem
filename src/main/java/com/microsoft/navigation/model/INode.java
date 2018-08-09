package com.microsoft.navigation.model;

import java.util.Set;

public interface INode {
	
	public String getId();
	public void setAdjacencySet(Set<IEdge> adjacencySet);
	public Set<IEdge> getAdjacencySet();
}
