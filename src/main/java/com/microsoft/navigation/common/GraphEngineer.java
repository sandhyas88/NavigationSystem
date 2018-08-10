package com.microsoft.navigation.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import com.microsoft.navigation.builder.IGraphBuilder;
import com.microsoft.navigation.model.Graph;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public class GraphEngineer {
	
	private IGraphBuilder graphBuilder;
	
	public GraphEngineer(IGraphBuilder graphBuilder)
	{
		this.graphBuilder = graphBuilder;
	}
	
	// Directly parsing json for efficiency
	public Graph makeGraph(HashMap<String,HashMap<String,Double>> nodeMap)
	{
		for(Entry<String, HashMap<String,Double>> entry1 : nodeMap.entrySet())
		{
			INode node = graphBuilder.addVertex(entry1.getKey());
			Set<IEdge> adjacencySet = new HashSet<IEdge>();
			for (Entry<String, Double> entry2 : entry1.getValue().entrySet())
			{
				INode destination = graphBuilder.addVertex(entry2.getKey());
				IEdge edge = graphBuilder.addEdge(node, destination, entry2.getValue());
				adjacencySet.add(edge);
			}
			node.setAdjacencySet(adjacencySet);
		}
		return graphBuilder.getGraph();
	}

}
