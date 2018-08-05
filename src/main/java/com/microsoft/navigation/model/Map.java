package com.microsoft.navigation.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

public class Map {
	
	private String id;
	private Graph<INode, IEdge> graph;
	
	public Map(String id)
	{
		this.id = id;
		this.graph = new DefaultDirectedGraph<>(IEdge.class);
	}

	public String getId() {
		return id;
	}


	public Graph<INode, IEdge> getGraph() {
		return graph;
	}
	
		

}
