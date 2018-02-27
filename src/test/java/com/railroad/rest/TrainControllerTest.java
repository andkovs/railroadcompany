package com.railroad.rest;

import com.railroad.config.AppConfig;
import com.railroad.core.service.StationService;
import com.railroad.core.service.TrainService;
import com.railroad.model.dto.NeighbouringStationDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.dto.TrainDto;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class TrainControllerTest {

    @Mock
    private TrainService mockTrainService;

    @InjectMocks
    private TrainController trainController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainController).build();
    }

    @Test
    public void getAllStations() throws Exception {
        TrainDto firstTrainDto = new TrainDto(1L, "A100", true);
        TrainDto secondTrainDto = new TrainDto(2L, "A101", true);

        when(mockTrainService.getAllTrains()).thenReturn(Arrays.asList(firstTrainDto, secondTrainDto));
        mockMvc.perform(get("/train"))
                .andExpect(status().isOk())
                .andExpect(view().name("master"))
                .andExpect(model().attribute("trains", hasSize(2)))
                .andExpect(model().attribute("title", equalTo("Trains")))
                .andExpect(model().attribute("userClickTrains", equalTo(true)))
                .andExpect(model().attribute("trains", hasItem(
                        allOf(
                                Matchers.hasProperty("trainId", is(1L)),
                                Matchers.hasProperty("trainNumber", is("A100")),
                                Matchers.hasProperty("enabled", is(true))
                        )
                )))
                .andExpect(model().attribute("trains", hasItem(
                        allOf(
                                Matchers.hasProperty("trainId", is(2L)),
                                Matchers.hasProperty("trainNumber", is("A101")),
                                Matchers.hasProperty("enabled", is(true))
                        )
                )));
        verify(mockTrainService, times(1)).getAllTrains();
        verifyNoMoreInteractions(mockTrainService);

    }

}