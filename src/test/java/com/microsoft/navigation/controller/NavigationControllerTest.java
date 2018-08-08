package com.microsoft.navigation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.microsoft.navigation.config.ApplicationConfig;
import com.microsoft.navigation.exceptions.MapAlreadyExistsException;
import com.microsoft.navigation.repo.IMapRepository;
import com.microsoft.navigation.service.IMapService;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class NavigationControllerTest {
	
	
	private MockMvc mockMvc;
	
	@InjectMocks
	NavigationController controller;
    
	@Mock
	private IMapService mapServiceMock;
	
    @Mock
	private IMapRepository mapRepositoryMock;
	
	 public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	 public static final String sampleMapPostJson = "{\"id\":\"testMapA\",\"nodes\":{\"a\":{\"b\":2,\"c\":5},\"b\":{\"c\":2},\"c\":{\"a\":8}}}";
	 
	 
	 @Before
	 public void setUp() {
		 
		 MockitoAnnotations.initMocks(this);
	     this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();//webAppContextSetup(webApplicationContext).build();
	     
	     
	 }
	 
	@Test
    public void saveMap_MapNotFound_ShouldBeAvailableOnRetrieval() throws Exception {
 
        Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(false);
        Mockito.when(mapRepositoryMock.save(Mockito.any())).thenReturn(null);
 
        mockMvc.perform(post("/maps/")
                .contentType(APPLICATION_JSON_UTF8).content(sampleMapPostJson))
                .andExpect(status().isCreated());
		
    }
	
	@Test//(expected = MapAlreadyExistsException.class)
    public void saveMap_MapFound_ShouldConflict() throws Exception {
 
        Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);
 
        mockMvc.perform(post("/maps/")
                .contentType(APPLICATION_JSON_UTF8).content(sampleMapPostJson))
                .andExpect(status().isConflict());
		
    }
	
	

}
