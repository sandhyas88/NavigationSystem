package com.microsoft.navigation.builder;

import com.microsoft.navigation.model.DefaultEdge;
import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public class DefaultEdgeBuilder implements IEdgeBuilder {

	@Override
	public IEdge createEdge(INode source, INode destination, Double weight) {
		IEdge edge = new DefaultEdge(source, destination, weight);
		return edge;
	}

}
