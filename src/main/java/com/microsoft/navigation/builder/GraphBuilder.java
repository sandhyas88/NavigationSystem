package com.microsoft.navigation.builder;

import com.google.common.base.Preconditions;
import com.microsoft.navigation.model.Graph;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public class GraphBuilder implements IGraphBuilder {

	private Graph graph;
	private IEdgeBuilder edgeBuilder;
	private INodeBuilder nodeBuilder;

	public GraphBuilder(IEdgeBuilder edgeBuilder, INodeBuilder nodeBuilder) {
		this.graph = new Graph();
		this.edgeBuilder = edgeBuilder;
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public INode addVertex(String id) {
		Preconditions.checkArgument(!id.isEmpty(), "Id cannot be empty");
		INode vertex = nodeBuilder.createNode(id);
		INode addedVertex = graph.add(vertex);
		return addedVertex;
	}

	public IEdge addEdge(INode source, INode destination, double distance) {
		Preconditions.checkNotNull(source, "Source cannot be null");
		Preconditions.checkNotNull(destination, "Destination cannot be null");
		IEdge edge = edgeBuilder.createEdge(source, destination, distance);
		return edge;
	}

	public Graph getGraph() {
		return this.graph;
	}

}
