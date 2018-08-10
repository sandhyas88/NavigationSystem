package com.microsoft.navigation.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.microsoft.navigation.config.ApplicationConfig;
import com.microsoft.navigation.config.RedisConfig;
import com.microsoft.navigation.constants.TestConstants;
import com.microsoft.navigation.model.MapRequest;
import com.microsoft.navigation.repo.IMapRepository;
import com.microsoft.navigation.test.common.JsonUtil;

import redis.embedded.RedisServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, RedisConfig.class})
@WebAppConfiguration
public class NavigationSystemIntegrationTest {
	
    
    private static RedisServer redisServer;
    @Autowired
    private IMapRepository mapRepository;
    
    
    @Autowired
    private WebApplicationContext wac;
    
    RestTemplate restTemplate = new RestTemplate();
    
    private MockMvc mockMvc;
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mapRepository.deleteAll();
    }
    
    @BeforeClass
    public static void startRedisServer() throws IOException {
        redisServer = new RedisServer();
        redisServer.start();
    }
    
    @AfterClass
    public static void stopRedisServer() throws IOException {
        redisServer.stop();
    }
    
    @Test
    public void testGetPath_mapNotFound() throws Exception
    {
    	
    	this.mockMvc
        .perform(get("/maps/testMapA/path?start=b&end=a"))
        .andExpect(status().isNotFound());
	}
    
    @Test
    public void testGetPath_badRequest() throws Exception
    {
    	
    	MapRequest map = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
    	mapRepository.save(map);
    	this.mockMvc
        .perform(get("/maps/testMapA/path?start=b&end=k"))
        .andExpect(status().isBadRequest());
	}
    
    @Test
    public void testGetPath_nullPath() throws Exception
    {
    	
    	MapRequest map = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
    	mapRepository.save(map);
    	this.mockMvc
        .perform(get("/maps/testMapA/path?start=b&end=p"))
        .andExpect(status().isOk()).andExpect(content().string(""));
	}
    
    @Test
    public void testGetPath_oK() throws Exception
    {
    	
    	MapRequest map = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
    	ArrayList<String> arr = new ArrayList<String>();
    	arr.add("b");arr.add("c");arr.add("a");
    	mapRepository.save(map);
    	this.mockMvc
        .perform(get("/maps/testMapA/path?start=b&end=a"))
        .andExpect(status().isOk()).andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.totalDistance").value(10))
        .andExpect(jsonPath("$.path").value(arr));
	}
    
    @Test
    public void testCreateMap_ok() throws Exception
    {
    	
    	this.mockMvc.perform(post("/maps/")
    	.contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
        .andExpect(status().isCreated());
        
	}
    
    @Test
    public void testCreateMap_Conflict() throws Exception
    {
    	
    	MapRequest map = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
    	mapRepository.save(map);
    	this.mockMvc.perform(post("/maps/")
    	.contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
        .andExpect(status().isConflict());
        
	}
    
    
}
