package com.microsoft.navigation.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.microsoft.navigation.builder.BuildingNodeBuilder;
import com.microsoft.navigation.builder.DefaultEdgeBuilder;
import com.microsoft.navigation.builder.GraphBuilder;
import com.microsoft.navigation.builder.IEdgeBuilder;
import com.microsoft.navigation.builder.IGraphBuilder;
import com.microsoft.navigation.builder.INodeBuilder;
import com.microsoft.navigation.common.GraphEngineer;
import com.microsoft.navigation.exceptions.MapAlreadyExistsException;
import com.microsoft.navigation.exceptions.MapNotFoundException;
import com.microsoft.navigation.model.MapRequest;
import com.microsoft.navigation.model.Path;
import com.microsoft.navigation.repo.IMapRepository;
import com.microsoft.navigation.service.IMapService;
import com.microsoft.navigation.common.JsonUtil;


@RestController
@RequestMapping(value = "/maps")
public class NavigationController {

	private static Logger logger = LoggerFactory.getLogger(NavigationController.class);
	
	@Autowired
	private IMapService mapService;

	@Autowired
	private IMapRepository mapRepository;

	@RequestMapping(value = "/{mapId}/path", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public String getShortestPath(@PathVariable("mapId") final String id, @RequestParam("start") final String startId,
			@RequestParam("end") final String endId, HttpServletResponse response) {

		if (mapRepository.existsById(id)) {
			MapRequest mapRequest = mapRepository.findById(id).orElse(null);
			Preconditions.checkNotNull(mapRequest);
			HashMap<String, HashMap<String, Double>> nodeMap = JsonUtil.getMapFromJson(mapRequest.getNodes());
			INodeBuilder nodeBuilder = new BuildingNodeBuilder();
			IEdgeBuilder edgeBuilder = new DefaultEdgeBuilder();
			IGraphBuilder graphBuilder = new GraphBuilder(edgeBuilder, nodeBuilder);
			GraphEngineer graphEngineer = new GraphEngineer(graphBuilder);
			Path path = mapService.getShortestPath(graphEngineer.makeGraph(nodeMap), startId, endId);
			return JsonUtil.objectToString(path);

		} else {
			logger.error("Map does not exist");
			throw new MapNotFoundException("Map does not exist");
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }) 
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createMap(@RequestBody final String mapDetails, HttpServletResponse response) {

		String mapId = JsonUtil.getId(mapDetails, "id");
		String nodes = JsonUtil.getNestedJson(mapDetails, "nodes");
		MapRequest mapRequest = new MapRequest(mapId, nodes);
		if (!mapRepository.existsById(mapId)) {
			mapRepository.save(mapRequest);
		} else {
			logger.error("Map with the same id exists");
			throw new MapAlreadyExistsException("Map with the same id exists");
		}

	}

}
