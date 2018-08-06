package com.microsoft.navigation.builder;

import com.microsoft.navigation.model.Building;
import com.microsoft.navigation.model.INode;

public class BuildingNodeBuilder implements INodeBuilder {

	@Override
	public INode createNode(String id) {
		INode node = new Building(id);
		return node;
	}

}
