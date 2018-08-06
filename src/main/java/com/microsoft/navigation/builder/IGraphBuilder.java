package com.microsoft.navigation.builder;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public interface IGraphBuilder {
	
	public INode addVertex(String id);
	public IEdge addEdge(INode source, INode destination, double distance);
	public DefaultDirectedWeightedGraph<INode, IEdge> getGraph();

}
