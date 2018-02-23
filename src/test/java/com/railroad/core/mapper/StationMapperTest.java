package com.railroad.core.mapper;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Station;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StationMapperTest {

    private StationMapper stationMapper = new StationMapper();

    @Test
    public void stationListToStationDtoList() throws Exception {
        Station firstStation = new Station(1L, "Paris", 48.867928, 2.342780);
        Station secondStation = new Station(2L, "Versailles", 48.785071, 2.120433);
        List<Station> stations = new ArrayList<>();
        stations.add(firstStation);
        stations.add(secondStation);
        List<StationDto> stationDtos = stationMapper.stationListToStationDtoList(stations);
        for (int i = 0; i < stations.size(); i++) {
            assertEquals(stationDtos.get(i).getStationId(), stations.get(i).getStationId());
            assertEquals(stationDtos.get(i).getStationTitle(), stations.get(i).getStationTitle());
            assertEquals(stationDtos.get(i).getLat(), stations.get(i).getLat());
            assertEquals(stationDtos.get(i).getLng(), stations.get(i).getLng());
        }
    }

    @Test
    public void stationToStationDto() throws Exception {
        Station station = new Station(1L, "Paris", 48.867928, 2.342780);
        StationDto stationDto = stationMapper.stationToStationDto(station);
        assertEquals(station.getStationId(), stationDto.getStationId());
        assertEquals(station.getStationTitle(), stationDto.getStationTitle());
        assertEquals(station.getLat(), stationDto.getLat());
        assertEquals(station.getLng(), stationDto.getLng());
    }

    @Test
    public void stationDtoToStation() throws Exception {
        StationDto stationDto = new StationDto(1L, "Paris", 48.867928, 2.342780);
        Station station = stationMapper.stationDtoToStation(stationDto);
        assertEquals(station.getStationId(), stationDto.getStationId());
        assertEquals(station.getStationTitle(), stationDto.getStationTitle());
        assertEquals(station.getLat(), stationDto.getLat());
        assertEquals(station.getLng(), stationDto.getLng());
    }

}