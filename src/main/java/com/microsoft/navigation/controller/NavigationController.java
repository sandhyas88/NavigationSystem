package com.microsoft.navigation.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.navigation.builder.BuildingNodeBuilder;
import com.microsoft.navigation.builder.DefaultEdgeBuilder;
import com.microsoft.navigation.builder.GraphBuilder;
import com.microsoft.navigation.builder.IEdgeBuilder;
import com.microsoft.navigation.builder.IGraphBuilder;
import com.microsoft.navigation.builder.INodeBuilder;
import com.microsoft.navigation.common.GraphEngineer;
import com.microsoft.navigation.common.JsonUtil;
import com.microsoft.navigation.exceptions.MapAlreadyExistsException;
import com.microsoft.navigation.exceptions.MapNotFoundException;
import com.microsoft.navigation.model.MapRequest;
import com.microsoft.navigation.model.Path;
import com.microsoft.navigation.repo.IMapRepository;
import com.microsoft.navigation.service.IMapService;

@RestController
@RequestMapping(value = "/maps")
public class NavigationController {

	@Autowired
	private IMapService mapService;

	@Autowired
	private IMapRepository mapRepository;

	@RequestMapping(value = "/{mapId}/path", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Path getShortestPath(@PathVariable("mapId") final String id, @RequestParam("start") final String startId,
			@RequestParam("end") final String endId, HttpServletResponse response) {

		if (mapRepository.existsById(id)) {
			MapRequest mapRequest = mapRepository.findById(id).orElse(null);
			HashMap<String, HashMap<String, Double>> nodeMap = JsonUtil.getMapFromJson(mapRequest.getNodes());
			INodeBuilder nodeBuilder = new BuildingNodeBuilder();
			IEdgeBuilder edgeBuilder = new DefaultEdgeBuilder();
			IGraphBuilder graphBuilder = new GraphBuilder(edgeBuilder, nodeBuilder);
			GraphEngineer graphEngineer = new GraphEngineer(graphBuilder);
			Path path = mapService.getShortestPath(graphEngineer.makeGraph(nodeMap), startId, endId);
			return path;

		} else {
			throw new MapNotFoundException("Map does not exist");
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createMap(@RequestBody final String mapDetails, HttpServletResponse response) {

		String mapId = JsonUtil.getId(mapDetails, "id");
		String nodes = JsonUtil.getNestedJson(mapDetails, "nodes");
		MapRequest mapRequest = new MapRequest(mapId, nodes);
		if (!mapRepository.existsById(mapId)) {
			mapRepository.save(mapRequest);
		} else {
			throw new MapAlreadyExistsException("Map with the same id exists");
		}

	}

}
