package com.microsoft.navigation.service;

import com.microsoft.navigation.model.INode;
import com.microsoft.navigation.model.Map;
import com.microsoft.navigation.model.Path;

public interface IMapService {
	
	public Path getShortestPath(Map map, INode source, INode destination);

}
