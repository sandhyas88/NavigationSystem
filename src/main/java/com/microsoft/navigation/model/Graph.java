package com.microsoft.navigation.model;

import java.util.HashSet;
import java.util.Set;

import com.microsoft.navigation.exceptions.NavigationSystemException;

public class Graph {
	
	private Set<INode> nodes;
	
	public Graph()
	{
		this.nodes = new HashSet<>();
	}
	
	
	public Set<INode> getNodes() {
		return nodes;
	}


	public INode getNodeById(String id)
	{
		for(INode node : nodes)
		{
			if(node.getId().equals(id)) 
			{
				return node;
			}
		}
		throw new NavigationSystemException("Vertex not found");
	}

	public void add(INode vertex) {
		this.nodes.add(vertex);
		
	}

}
