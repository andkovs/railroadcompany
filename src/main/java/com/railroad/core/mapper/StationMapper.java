package com.railroad.core.mapper;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Station;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationMapper {

    /**
     * Converts list of stations to list of stations DTO's.
     *
     * @param stations list od stations.
     * @return list of station DTO's.
     */
    public List<StationDto> stationListToStationDtoList(List<Station> stations) {
        if (stations == null) {
            return new ArrayList();
        }
        return stations.stream().map(station -> new StationDto(
                station.getStationId(),
                station.getStationTitle(),
                station.getLat(),
                station.getLng())).collect(Collectors.toList());
    }

    /**
     * Converts station to stationDto
     *
     * @param station Convertible station.
     * @return station DTO.
     */
    public StationDto stationToStationDto(Station station) {
        if (station == null) {
            return null;
        }
        return new StationDto(
                station.getStationId(),
                station.getStationTitle(),
                station.getLat(),
                station.getLng());
    }

    /**
     * Converts station DTO to station.
     *
     * @param stationDto convertible station DTO.
     * @return station.
     */
    public Station stationDtoToStation(StationDto stationDto) {
        if (stationDto == null) {
            return null;
        }
        return new Station(stationDto.getStationId(),
                stationDto.getStationTitle(),
                stationDto.getLat(),
                stationDto.getLng());
    }
}
