package com.railroad.core.service;

import com.railroad.config.AppConfig;
import com.railroad.core.mapper.StationMapper;
import com.railroad.model.dao.StationDao;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class StationServiceTest {

    @Mock
    private StationDao mockStationDao;

    @Mock
    private StationMapper mockStationMapper;

    @Mock
    private DirectionService mockDirectionService;

    @InjectMocks
    private StationService stationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStations() throws Exception {
        Station firstStation = new Station(1L, "Paris", 48.867928, 2.342780);
        Station secondStation = new Station(2L, "Versailles", 48.785071, 2.120433);

        StationDto firstStationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        StationDto secondStationDto = new StationDto(2L, "Versailles", 48.785071, 2.120433);

        when(mockStationDao.getAllStations()).thenReturn(Arrays.asList(firstStation, secondStation));
        when(mockStationMapper.stationListToStationDtoList(Arrays.asList(firstStation, secondStation)))
                .thenReturn(Arrays.asList(firstStationDto, secondStationDto));
        stationService.getAllStations();
        verify(mockStationDao, times(1)).getAllStations();
        verify(mockStationMapper, times(1)).stationListToStationDtoList(Arrays.asList(firstStation, secondStation));

    }

    @Test
    public void getStationById() throws Exception {
        Station station = new Station(1L, "Paris", 48.867928, 2.342780);
        StationDto stationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        Direction firstDirection = new Direction();
        firstDirection.setDirectionId(1L);
        firstDirection.setDepStationId(1L);
        firstDirection.setArrStationId(2L);
        Direction secondDirection = new Direction();
        secondDirection.setDirectionId(2L);
        secondDirection.setDepStationId(2L);
        secondDirection.setArrStationId(1L);

        when(mockStationDao.getStationById(1L)).thenReturn(station);
        when(mockStationMapper.stationToStationDto(station)).thenReturn(stationDto);
        when(mockDirectionService.getDirectionListByDepStationId(1L)).thenReturn(Arrays.asList(firstDirection, secondDirection));

        StationDto result = stationService.getStationById(1L);
        assertEquals(result.getStationId(), stationDto.getStationId());
        assertEquals(result.getStationTitle(), stationDto.getStationTitle());
        assertEquals(result.getLat(), stationDto.getLat());
        assertEquals(result.getLng(), stationDto.getLng());

        verify(mockStationDao, times(2)).getStationById(1L);
        verify(mockStationMapper, times(1)).stationToStationDto(station);
        verify(mockDirectionService, times(1)).getDirectionListByDepStationId(1L);
    }

    @Test
    public void getAllStationsWithoutCurrent() throws Exception {
        Station firstStation = new Station(1L, "Paris", 48.867928, 2.342780);
        Station secondStation = new Station(2L, "Versailles", 48.785071, 2.120433);
        List<Station> stations = new ArrayList<>();
        stations.add(firstStation);
        stations.add(secondStation);

        StationDto firstStationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        StationDto secondStationDto = new StationDto(2L, "Versailles", 48.785071, 2.120433);
        List<StationDto> stationDtos = new ArrayList<>();
        stationDtos.add(firstStationDto);
        stationDtos.add(secondStationDto);

        when(mockStationDao.getAllStations()).thenReturn(Arrays.asList(firstStation, secondStation));
        when(mockStationMapper.stationListToStationDtoList(stations)).thenReturn(stationDtos);

        List<StationDto> result = stationService.getAllStationsWithoutCurrent(1L);
        for (StationDto s :
                result) {
            assertNotEquals(s.getStationId(), Long.valueOf(1L));
        }

        verify(mockStationDao, times(1)).getAllStations();
        verify(mockStationMapper, times(1)).stationListToStationDtoList(Arrays.asList(firstStation, secondStation));
    }

    @Test
    public void saveOrUpdateStation() throws Exception {
// TODO: 23.02.2018 write test
    }

    @Test
    public void removeStationById() throws Exception {
// TODO: 23.02.2018 write test
    }

    @Test
    public void getAllNeighbouringStation() throws Exception {
// TODO: 23.02.2018 write test
    }

    @Test
    public void getAllNeighbouringStationWithoutCurrent() throws Exception {
// TODO: 23.02.2018 write test
    }

}