package com.microsoft.navigation.controller;

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
import com.microsoft.navigation.model.Map;
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
	
	@RequestMapping(value = "/{mapId}/path", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Path getShortestPath(@PathVariable("mapId") final String id, @RequestParam("start") final String startId, @RequestParam("end") final String endId, HttpServletResponse response) {

		final Map map = mapRepository.findById(id).orElse(null);
		Path path = mapService.getShortestPath(map, startId, endId);
		return path;
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMap(@RequestBody final MapRequest mapRequest, HttpServletResponse response) {
		
		String mapId = mapRequest.getId();
		String nodeJson = mapRequest.getNodes();

		Map map = mapRepository.findById(mapId).orElse(null);
		//need to add preconditions
		if(map == null)
		{
			INodeBuilder nodeBuilder = new BuildingNodeBuilder();
			IEdgeBuilder edgeBuilder = new DefaultEdgeBuilder();
			IGraphBuilder graphBuilder = new GraphBuilder(edgeBuilder, nodeBuilder);
			GraphEngineer graphEngineer = new GraphEngineer(graphBuilder);
			map = new Map(mapId,graphEngineer.makeGraph(nodeJson));
			mapRepository.save(map);
		}
    }

}
