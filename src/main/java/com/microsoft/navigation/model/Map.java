package com.microsoft.navigation.model;

import java.io.Serializable;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;



public class Map implements Serializable {
	
	private static final long serialVersionUID = 7279406294916559817L;
	private String id;
	private SimpleDirectedWeightedGraph<INode, IEdge> graph;
	
	public Map(String id, SimpleDirectedWeightedGraph<INode, IEdge> simpleDirectedWeightedGraph)
	{
		this.id = id;
		this.graph = simpleDirectedWeightedGraph;
	}

	
	public String getId() {
		return id;
	}


	public SimpleDirectedWeightedGraph<INode, IEdge> getGraph() {
		return graph;
	}
	
	public INode getNodeById(String nodeId)
	{
		Set<INode> nodes = graph.vertexSet();
		for(INode node : nodes)
		{
			if(node.getId().equals(nodeId))
			{
				return node;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Map [id=" + id + ", graph=" + graph.toString() + "]";
	}
		

}
