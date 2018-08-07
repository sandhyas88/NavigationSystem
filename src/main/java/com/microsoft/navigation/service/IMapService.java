package com.microsoft.navigation.service;

import com.microsoft.navigation.exceptions.NavigationSystemException;
import com.microsoft.navigation.model.Map;
import com.microsoft.navigation.model.MapRequest;
import com.microsoft.navigation.model.Path;

public interface IMapService {
	
	public Path getShortestPath(Map map, String startId, String endId) throws NavigationSystemException;

}
