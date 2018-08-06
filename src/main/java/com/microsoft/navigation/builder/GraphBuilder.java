package com.microsoft.navigation.builder;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public class GraphBuilder implements IGraphBuilder {
	
	private SimpleDirectedWeightedGraph<INode, IEdge> graph;
	private IEdgeBuilder edgeBuilder;
	private INodeBuilder nodeBuilder;
	
	public GraphBuilder(IEdgeBuilder edgeBuilder, INodeBuilder nodeBuilder)
	{
		this.graph = new SimpleDirectedWeightedGraph<INode,IEdge>(IEdge.class);
		this.edgeBuilder = edgeBuilder;
		this.nodeBuilder = nodeBuilder;
	}
	
	public INode addVertex(String id)
	{
		INode node = nodeBuilder.createNode(id);
		graph.addVertex(node);
		return node;
	}
	
	public IEdge addEdge(INode source, INode destination, double distance)
	{
		IEdge edge = edgeBuilder.createEdge(source, destination, distance);
		graph.addEdge(source, destination, edge);
		graph.setEdgeWeight(edge, distance);
		return edge;
	}
	
	public SimpleDirectedWeightedGraph<INode, IEdge> getGraph()
	{
		return this.graph;
	}

}
