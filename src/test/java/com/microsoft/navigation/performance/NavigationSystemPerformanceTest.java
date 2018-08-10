package com.microsoft.navigation.performance;

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
public class NavigationSystemPerformanceTest {
	
    
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
	public void testGetPath_memoryoK() throws Exception {
		
		MapRequest map = new MapRequest(TestConstants.MAPID, JsonUtil.createPerfJsonRequest());
		mapRepository.save(map);
		this.mockMvc.perform(get("/maps/testMapA/path?start=1&end=3")).andExpect(status().isOk())
				.andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.totalDistance").value(1));
		mapRepository.deleteAll();
	}
    
}
