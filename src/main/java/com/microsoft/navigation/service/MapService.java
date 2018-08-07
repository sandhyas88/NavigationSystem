package com.microsoft.navigation.service;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import com.microsoft.navigation.exceptions.NavigationSystemException;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;
import com.microsoft.navigation.model.Map;
import com.microsoft.navigation.model.Path;

@Service
public class MapService implements IMapService {

	@Override
	public Path getShortestPath(Map map, String sourceId, String destinationId) {
		SimpleDirectedWeightedGraph<INode,IEdge> graph = map.getGraph();
		INode source = map.getNodeById(sourceId);
		INode destination = map.getNodeById(destinationId);
		Path p = null;
		if(graph.containsVertex(source) && graph.containsVertex(destination))
		{
			DijkstraShortestPath<INode,IEdge> dsp = new DijkstraShortestPath<>(graph);
			GraphPath<INode,IEdge> path = dsp.getPath(source, destination);
			if(path != null)
			{
				List<INode> nodes = path.getVertexList();
				String[] nodeArr = nodes.stream().map(node -> node.toString()).toArray(String[]::new);
				Double distance = path.getWeight();
				p = new Path(distance, nodeArr);
			}
			return p;
			
		}
		else
		{
			throw new NavigationSystemException("Source/Destination not present in the map");
		}
	}
	

}
