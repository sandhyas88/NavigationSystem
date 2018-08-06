package com.microsoft.navigation.common;

import java.util.HashMap;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import com.microsoft.navigation.builder.IGraphBuilder;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public class GraphEngineer {
	
	private IGraphBuilder graphBuilder;
	
	public GraphEngineer(IGraphBuilder graphBuilder)
	{
		this.graphBuilder = graphBuilder;
	}
	
	public DefaultDirectedWeightedGraph<INode, IEdge> makeGraph(String json)
	{
		HashMap<String,HashMap<String,Double>> nodeMap = JsonUtil.getMapFromJson(json);
		for(String v1 : nodeMap.keySet())
		{
			HashMap<String,Double> map = nodeMap.get(v1);
			INode source = graphBuilder.addVertex(v1);
			for(String v2 : map.keySet())
			{
				INode destination = graphBuilder.addVertex(v2);
				double distance = map.get(v2);
				graphBuilder.addEdge(source, destination, distance);
				
			}
		}
		return graphBuilder.getGraph();
	}

}