package com.railroad.core.service;

import com.railroad.config.AppConfig;
import com.railroad.core.mapper.DirectionMapper;
import com.railroad.model.dao.DirectionDao;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class DirectionServiceTest {

    @Mock
    private DirectionDao mockDirectionDao;

    @Mock
    private DirectionMapper mockDirectionMapper;

    @InjectMocks
    private DirectionService directionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDirectionListByDepStationId() throws Exception {
        Direction direction = new Direction();
        direction.setDirectionId(1L);
        direction.setDepStationId(1L);
        direction.setArrStationId(2L);
        when(mockDirectionDao.getDirectionListByDepStationId(1L)).thenReturn(Collections.singletonList(direction));
        List<Direction> directions = directionService.getDirectionListByDepStationId(1L);
        assertEquals(directions.get(0).getDirectionId(), direction.getDirectionId());
        assertEquals(directions.get(0).getDepStationId(), direction.getDepStationId());
        assertEquals(directions.get(0).getArrStationId(), direction.getArrStationId());
    }

    @Test
    public void saveDirections() throws Exception {
        StationDto stationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        stationDto.getArriveStationIds().add(2L);
        Direction direction = new Direction();
        direction.setDepStationId(1L);
        direction.setArrStationId(2L);
        when(mockDirectionMapper.stationDtoToDirectionList(stationDto)).thenReturn(Collections.singletonList(direction));
        directionService.saveDirections(stationDto);
        verify(mockDirectionDao, times(1)).saveDirection(direction);
        verify(mockDirectionMapper, times(1)).stationDtoToDirectionList(stationDto);
    }

    @Test
    public void mergeDirections() throws Exception {
        StationDto stationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        stationDto.getArriveStationIds().add(2L);

        Direction direction = new Direction();
        direction.setDepStationId(1L);
        direction.setArrStationId(2L);

        when(mockDirectionDao.getDirectionListByDepStationId(1L)).thenReturn(Collections.singletonList(direction));
        when(mockDirectionMapper.stationDtoToDirectionList(stationDto)).thenReturn(Collections.singletonList(direction));

        directionService.mergeDirections(stationDto);

        verify(mockDirectionDao, times(1)).getDirectionListByDepStationId(1L);
        verify(mockDirectionMapper, times(1)).stationDtoToDirectionList(stationDto);
    }

    @Test
    public void removeDirectionsByStationId() throws Exception {
        directionService.removeDirectionsByStationId(1L);
        verify(mockDirectionDao, times(1)).removeDirectionsByStationId(1L);
    }

    @Test
    public void getAllDirections() throws Exception {
        Direction firstDirection = new Direction();
        firstDirection.setDepStationId(1L);
        firstDirection.setArrStationId(2L);

        Direction secondDirection = new Direction();
        secondDirection.setDepStationId(2L);
        secondDirection.setArrStationId(1L);

        when(mockDirectionDao.getAllDirections()).thenReturn(Arrays.asList(firstDirection, secondDirection));
        directionService.getAllDirections();
        verify(mockDirectionDao, times(1)).getAllDirections();
    }

}