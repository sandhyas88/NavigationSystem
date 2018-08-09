package com.microsoft.navigation.builder;

import com.microsoft.navigation.model.Graph;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public interface IGraphBuilder {
	
	public INode addVertex(String id);
	public IEdge addEdge(INode source, INode destination, double distance);
	public Graph getGraph();

}
