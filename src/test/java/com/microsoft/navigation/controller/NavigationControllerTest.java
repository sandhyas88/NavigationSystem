package com.microsoft.navigation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.microsoft.navigation.config.ApplicationConfig;
import com.microsoft.navigation.constants.TestConstants;
import com.microsoft.navigation.model.MapRequest;
import com.microsoft.navigation.repo.IMapRepository;
import com.microsoft.navigation.service.IMapService;
import com.microsoft.navigation.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class NavigationControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	NavigationController controller;

	@Spy
	private IMapService mapService = new MapService();

	@Mock
	private IMapRepository mapRepositoryMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void saveMap_MapNotFound_ShouldBeAvailableOnRetrieval() throws Exception {

		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(false);
		Mockito.when(mapRepositoryMock.save(Mockito.any())).thenReturn(null);

		mockMvc.perform(post("/maps/").contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
				.andExpect(status().isCreated());

	}

	@Test
	public void saveMap_MapFound_ShouldConflict() throws Exception {

		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);

		mockMvc.perform(post("/maps/").contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
				.andExpect(status().isConflict());

	}

	@Test
	public void testGetPath_mapNotFound() throws Exception {
		MapRequest mr = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
		Mockito.when(mapRepositoryMock.findById(Mockito.anyString())).thenReturn(Optional.of(mr));
		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(false);
		this.mockMvc.perform(get("/maps/testMapA/path?start=b&end=a")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetPath_badRequest() throws Exception {
		MapRequest mr = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);
		Mockito.when(mapRepositoryMock.findById(Mockito.anyString())).thenReturn(Optional.of(mr));
		this.mockMvc.perform(get("/maps/testMapA/path?start=b&end=k")).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetPath_nullPath() throws Exception {
		MapRequest mr = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);
		Mockito.when(mapRepositoryMock.findById(Mockito.anyString())).thenReturn(Optional.of(mr));
		this.mockMvc.perform(get("/maps/testMapA/path?start=b&end=p")).andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void testGetPath_oK() throws Exception {
		MapRequest mr = new MapRequest(TestConstants.MAPID, TestConstants.MAPNODES);
		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);
		Mockito.when(mapRepositoryMock.findById(Mockito.anyString())).thenReturn(Optional.of(mr));
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("b");
		arr.add("c");
		arr.add("a");
		this.mockMvc.perform(get("/maps/testMapA/path?start=b&end=a")).andExpect(status().isOk())
				.andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.totalDistance").value(10)).andExpect(jsonPath("$.path").value(arr));
	}

	@Test
	public void testCreateMap_ok() throws Exception {

		this.mockMvc
				.perform(post("/maps/").contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
				.andExpect(status().isCreated());

	}

	@Test
	public void testCreateMap_Conflict() throws Exception {
		Mockito.when(mapRepositoryMock.existsById(Mockito.anyString())).thenReturn(true);
		this.mockMvc
				.perform(post("/maps/").contentType(TestConstants.APPLICATION_JSON_UTF8).content(TestConstants.MAPJSON))
				.andExpect(status().isConflict());

	}

}
