package com.railroad.core.mapper;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectionMapperTest {

    private DirectionMapper directionMapper = new DirectionMapper();

    @Test
    public void stationDtoToDirectionList() throws Exception {

        StationDto stationDto = new StationDto(2L, "Versailles", 48.867928, 2.342780);
        stationDto.getArriveStationIds().add(1L);
        stationDto.getArriveStationIds().add(3L);
        stationDto.getArriveStationIds().add(4L);

        Direction firstDirection = new Direction();
        firstDirection.setDepStationId(2L);
        firstDirection.setArrStationId(1L);
        Direction secondDirection = new Direction();
        secondDirection.setDepStationId(2L);
        secondDirection.setArrStationId(3L);
        Direction thirdDirection = new Direction();
        thirdDirection.setDepStationId(2L);
        thirdDirection.setArrStationId(4L);

        List<Direction> inputDirections = new ArrayList<>();
        inputDirections.add(firstDirection);
        inputDirections.add(secondDirection);
        inputDirections.add(thirdDirection);

        List<Direction> outputDirections = directionMapper.stationDtoToDirectionList(stationDto);
        assertEquals(inputDirections.size(), outputDirections.size());
        for (int i = 0; i < outputDirections.size(); i++) {
            assertEquals(inputDirections.get(i).getDepStationId(), outputDirections.get(i).getDepStationId());
            assertEquals(inputDirections.get(i).getArrStationId(), outputDirections.get(i).getArrStationId());
        }
    }
}