package com.microsoft.navigation.model;

import java.io.Serializable;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Map")
public class Map implements Serializable {
	
	private static final long serialVersionUID = 7279406294916559817L;
	private String id;
	private DefaultDirectedWeightedGraph<INode, IEdge> graph;
	
	public Map(String id, SimpleDirectedWeightedGraph<INode, IEdge> simpleDirectedWeightedGraph)
	{
		this.id = id;
		this.graph = new DefaultDirectedWeightedGraph<>(IEdge.class);
	}

	public String getId() {
		return id;
	}


	public DefaultDirectedWeightedGraph<INode, IEdge> getGraph() {
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
		

}
