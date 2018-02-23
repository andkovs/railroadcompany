package com.railroad.core.mapper;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Station;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StationMapper {

    /**
     * Converts list of stations to list of stations DTO
     *
     * @param stations list od stations
     * @return list of stationsDto
     */
    public List stationListToStationDtoList(List<Station> stations) {
        return stations.stream().map(station -> new StationDto(
                station.getStationId(),
                station.getStationTitle(),
                station.getLat(),
                station.getLng())).collect(Collectors.toList());
    }

    /**
     * Converts station to stationDto
     *
     * @param station Convertible station
     * @return stationDto
     */
    public StationDto stationToStationDto(Station station) {
        return new StationDto(
                station.getStationId(),
                station.getStationTitle(),
                station.getLat(),
                station.getLng());
    }

    /**
     * Converts stationDto to station
     *
     * @param stationDto Convertible stationDto
     * @return station
     */
    public Station stationDtoToStation(StationDto stationDto) {
        return new Station(stationDto.getStationId(),
                stationDto.getStationTitle(),
                stationDto.getLat(),
                stationDto.getLng());
    }
}
