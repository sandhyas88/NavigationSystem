package com.microsoft.navigation.service;

import com.microsoft.navigation.exceptions.NavigationSystemException;
import com.microsoft.navigation.model.Graph;
import com.microsoft.navigation.model.Path;

public interface IMapService {
	
	public Path getShortestPath(Graph graph, String sourceId, String destinationId) throws NavigationSystemException;

}
