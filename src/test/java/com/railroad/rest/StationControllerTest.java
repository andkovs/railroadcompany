package com.railroad.rest;

import com.railroad.config.AppConfig;
import com.railroad.core.service.StationService;
import com.railroad.model.dto.NeighbouringStationDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Station;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class StationControllerTest {

    @Mock
    private StationService mockStationService;

    @InjectMocks
    private StationController stationController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stationController).build();
    }

    @Test
    public void getAllStations() throws Exception {
        StationDto firstStation = new StationDto(1L, "Paris", 48.867928, 2.342780);
        StationDto secondStation = new StationDto(2L, "Versailles", 48.785071, 2.120433);
        NeighbouringStationDto firstNeighbouringStation = new NeighbouringStationDto(
                new Station(1L, "Paris", 48.867928, 2.342780),
                new Station(2L, "Versailles", 48.785071, 2.120433));
        NeighbouringStationDto secondNeighbouringStation = new NeighbouringStationDto(
                new Station(2L, "Versailles", 48.785071, 2.120433),
                new Station(1L, "Paris", 48.867928, 2.342780));
        when(mockStationService.getAllStations()).thenReturn(Arrays.asList(firstStation, secondStation));
        when(mockStationService.getAllNeighbouringStation()).thenReturn(Arrays.asList(firstNeighbouringStation, secondNeighbouringStation));
        mockMvc.perform(get("/station"))
                .andExpect(status().isOk())
                .andExpect(view().name("master"))
                .andExpect(model().attribute("stations", hasSize(2)))
                .andExpect(model().attribute("neighbouringStations", hasSize(2)))
                .andExpect(model().attribute("title", equalTo("Stations")))
                .andExpect(model().attribute("userClickStations", equalTo(true)))
                .andExpect(model().attribute("stations", hasItem(
                        allOf(
                                Matchers.hasProperty("stationId", is(1L)),
                                Matchers.hasProperty("stationTitle", is("Paris")),
                                Matchers.hasProperty("lat", is(48.867928)),
                                Matchers.hasProperty("lng", is(2.342780))
                        )
                )))
                .andExpect(model().attribute("stations", hasItem(
                        allOf(
                                Matchers.hasProperty("stationId", is(2L)),
                                Matchers.hasProperty("stationTitle", is("Versailles")),
                                Matchers.hasProperty("lat", is(48.785071)),
                                Matchers.hasProperty("lng", is(2.120433))
                        )
                )));
        verify(mockStationService, times(1)).getAllStations();
        verify(mockStationService, times(1)).getAllNeighbouringStation();
        verifyNoMoreInteractions(mockStationService);
    }

    @Test
    public void getStationById() throws Exception {
        StationDto firstStation = new StationDto(1L, "Paris", 48.867928, 2.342780);
        StationDto secondStation = new StationDto(2L, "Versailles", 48.785071, 2.120433);
        StationDto thirdStation = new StationDto(3L, "Chartres", 48.443925, 1.491150);
        NeighbouringStationDto firstNeighbouringStation = new NeighbouringStationDto(
                new Station(3L, "Chartres", 48.443925, 1.491150),
                new Station(2L, "Versailles", 48.785071, 2.120433));
        NeighbouringStationDto secondNeighbouringStation = new NeighbouringStationDto(
                new Station(2L, "Versailles", 48.785071, 2.120433),
                new Station(3L, "Chartres", 48.443925, 1.491150));
        when(mockStationService.getStationById(1L)).thenReturn(firstStation);
        when(mockStationService.getAllStationsWithoutCurrent(1L)).thenReturn(Arrays.asList(secondStation, thirdStation));
        when(mockStationService.getAllNeighbouringStationWithoutCurrent(1L)).thenReturn(Arrays.asList(firstNeighbouringStation, secondNeighbouringStation));
        mockMvc.perform(get("/station/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("master"))
                .andExpect(model().attribute("station", equalTo(firstStation)))
                .andExpect(model().attribute("stations", hasSize(2)))
                .andExpect(model().attribute("neighbouringStations", hasSize(2)))
                .andExpect(model().attribute("title", equalTo("Station " + firstStation.getStationTitle())))
                .andExpect(model().attribute("userClickStation", equalTo(true)))
                .andExpect(model().attribute("stations", hasItem(
                        allOf(
                                Matchers.hasProperty("stationId", is(2L)),
                                Matchers.hasProperty("stationTitle", is("Versailles")),
                                Matchers.hasProperty("lat", is(48.785071)),
                                Matchers.hasProperty("lng", is(2.120433))
                        )
                )))
                .andExpect(model().attribute("stations", hasItem(
                        allOf(
                                Matchers.hasProperty("stationId", is(3L)),
                                Matchers.hasProperty("stationTitle", is("Chartres")),
                                Matchers.hasProperty("lat", is(48.443925)),
                                Matchers.hasProperty("lng", is(1.491150))
                        )
                )));
        verify(mockStationService, times(1)).getStationById(1L);
        verify(mockStationService, times(1)).getAllStationsWithoutCurrent(1L);
        verify(mockStationService, times(1)).getAllNeighbouringStationWithoutCurrent(1L);
        verifyNoMoreInteractions(mockStationService);
    }

    @Test
    public void saveOrUpdateStation() throws Exception {
        StationDto station = new StationDto(null, "Paris", 48.867928, 2.342780);
        when(mockStationService.saveOrUpdateStation(station)).thenReturn(1L);
        mockMvc.perform(post("/station/add"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/station/" + 0));
    }

    @Test
    public void removeStation() throws Exception {
        mockMvc.perform(get("/station/1/delete"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/station/"));
    }

}