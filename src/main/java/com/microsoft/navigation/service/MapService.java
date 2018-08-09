package com.microsoft.navigation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.stereotype.Service;

import com.microsoft.navigation.exceptions.NavigationSystemException;
import com.microsoft.navigation.model.Graph;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;
import com.microsoft.navigation.model.Path;
import com.microsoft.navigation.model.PathNode;

@Service
public class MapService implements IMapService {

	@Override
	public Path getShortestPath(Graph graph, String sourceId, String destinationId) {

		INode source = graph.getNodeById(sourceId);
		INode destination = graph.getNodeById(destinationId);
		Map<INode, PathNode> result = new HashMap<>();
		PriorityQueue<PathNode> queue = new PriorityQueue<>(Comparator.comparingDouble(PathNode::getMinDistance));

		PathNode sourcePathNode = new PathNode(source);
		sourcePathNode.setPrevious(null);
		sourcePathNode.setMinDistance(0);
		queue.add(sourcePathNode);
		result.put(source, sourcePathNode);

		Set<INode> visited = new HashSet<>();
		while (!queue.isEmpty()) {
			PathNode pathNode = queue.poll();
			INode originalNode = pathNode.getNode();
			if (visited.contains(originalNode)) {
				continue;
			}
			visited.add(originalNode);
			Set<IEdge> edges = originalNode.getAdjacencySet();
			for (IEdge edge : edges) {
				INode v = edge.getDestination();
				double w = edge.getDistance();
				if (visited.contains(v)) {
					continue;
				}
				PathNode existingNode = result.get(v);
				if (existingNode == null || existingNode.getMinDistance() > pathNode.getMinDistance() + w) {
					PathNode newNode = new PathNode(v);
					newNode.setMinDistance(pathNode.getMinDistance() + w);
					newNode.setPrevious(pathNode);
					queue.add(newNode);
					result.put(v, existingNode);
				}
			}
		}
		PathNode destinationPathNode = result.get(destination);
		if (destinationPathNode == null) {
			throw new RuntimeException("no path found");
		}
		List<PathNode> pathNodes = new ArrayList<PathNode>();
		for (PathNode vertex = destinationPathNode; vertex != null; vertex = vertex.getPrevious()) {
			pathNodes.add(vertex);
		}

		Collections.reverse(pathNodes);
		String[] nodeArr = pathNodes.stream().map(node -> node.toString()).toArray(String[]::new);
		Path path = new Path(destinationPathNode.getMinDistance(), nodeArr);

		return path;

	}

}
