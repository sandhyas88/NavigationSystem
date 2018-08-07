package com.microsoft.navigation.service;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;

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
		DijkstraShortestPath<INode,IEdge> dsp = new DijkstraShortestPath<>(graph);
		GraphPath<INode,IEdge> path = dsp.getPath(source, destination);
		List<INode> nodes = path.getVertexList();
		Double distance = path.getWeight();
		Path p = new Path(source, destination, distance, nodes);
		return p;
	}
	

}
