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
	
	@RequestMapping(value = "/{mapId}/path", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public Path getShortestPath(@PathVariable("mapId") final String id, @RequestParam("start") final String startId, @RequestParam("end") final String endId, HttpServletResponse response) {

		if(mapRepository.existsById(id))
		{
			MapRequest mapRequest = mapRepository.findById(id).orElse(null);
			HashMap<String,HashMap<String,Double>> nodeMap = JsonUtil.getMapFromJson(mapRequest.getNodes());
			//final MapRequest mapRequest = mapRepository.findById(id).orElse(null);
			
			System.out.println(nodeMap.get("a"));
			INodeBuilder nodeBuilder = new BuildingNodeBuilder();
			IEdgeBuilder edgeBuilder = new DefaultEdgeBuilder();
			IGraphBuilder graphBuilder = new GraphBuilder(edgeBuilder, nodeBuilder);
			GraphEngineer graphEngineer = new GraphEngineer(graphBuilder);
			Map map = new Map(id,graphEngineer.makeGraph(nodeMap));
			Path path = mapService.getShortestPath(map, startId, endId);
			System.out.println("Path:"+path.getNodes());
			return path;
		}
		else
		{
			return null;
		}
    }
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public String getSomething(HttpServletResponse response) {

		return "Hello hello hello";
		
    }
	
	@RequestMapping(value = "/test2", method = RequestMethod.POST, consumes = { "application/json" })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createMap(@RequestBody final String mapDetails, HttpServletResponse response) {
		
		String mapId =  JsonUtil.getId(mapDetails,"id");
		String nodes =  JsonUtil.getNestedJson(mapDetails,"nodes");
		HashMap<String,HashMap<String,Double>> nodeMap = JsonUtil.getMapFromJson(nodes);
		MapRequest mapRequest = new MapRequest(mapId,nodes);
		//String mapId = mapRequest.getId();
		//need to add preconditions
		//if(!mapRepository.existsById(mapId))
		{
			mapRepository.save(mapRequest);
			System.out.println(nodeMap.get("a"));
			MapRequest mapRequest1 = mapRepository.findById(mapId).orElse(null);
			nodeMap = JsonUtil.getMapFromJson(mapRequest1.getNodes());
			System.out.println(nodeMap.values());
		}
		return "ok";
	
    }

}
