package com.microsoft.navigation.builder;

import com.microsoft.navigation.model.IEdge;
import com.microsoft.navigation.model.INode;

public interface IEdgeBuilder {
	
	public IEdge createEdge(INode source, INode destination, Double weight);

}
