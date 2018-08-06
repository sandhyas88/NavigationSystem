package com.microsoft.navigation.builder;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public interface IGraphBuilder {
	
	public INode addVertex(String id);
	public IEdge addEdge(INode source, INode destination, double distance);
	public SimpleDirectedWeightedGraph<INode, IEdge> getGraph();

}
