package com.railroad.core.service;

import com.railroad.core.mapper.StationMapper;
import com.railroad.model.dao.StationDao;
import com.railroad.model.dto.NeighbouringStationDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StationService {

    private final
    StationDao stationDao;

    private final
    StationMapper stationMapper;

    private final
    DirectionService directionService;

    @Autowired
    public StationService(StationDao stationDao, StationMapper stationMapper, DirectionService directionService) {
        this.stationDao = stationDao;
        this.stationMapper = stationMapper;
        this.directionService = directionService;
    }

    /**
     * Gets list of all stations.
     *
     * @return list of all direction DTO's.
     */
    public List getAllStations() {
        return stationMapper.stationListToStationDtoList(stationDao.getAllStations());
    }

    /**
     * Gets station DTO by station id.
     *
     * @param id station id.
     * @return station DTO.
     */
    public StationDto getStationById(Long id) {
        //if Station not exist, then return empty StationDto
        if (stationDao.getStationById(id) == null) {
            return new StationDto(0L, "", 48.867928, 2.34278);
        }
        //get stationDto by id
        StationDto stationDto = stationMapper.stationToStationDto(stationDao.getStationById(id));
        //get list of directions, which can we get from current station
        List<Direction> directions = directionService.getDirectionListByDepStationId(id);
        for (Direction direction :
                directions) {
            //gets id list by the arrive stationId from directions
            stationDto.getArriveStationIds().add(direction.getArrStationId());
        }
        return stationDto;
    }

    /**
     * Gets all stations without current.
     *
     * @param id current station id.
     * @return list of station DTO's.
     */
    public List<StationDto> getAllStationsWithoutCurrent(Long id) {
        List<StationDto> stations = stationMapper.stationListToStationDtoList(stationDao.getAllStations());
        //removes current station from stations list
        stations.removeIf(s -> s.getStationId().equals(id));
        return stations;
    }

    /**
     * Saves or updates station. If station doe's not exist,
     * then save, else update.
     *
     * @param stationDto saved/updated station DTO.
     * @return new or current station id.
     */
    public Long saveOrUpdateStation(StationDto stationDto) {
        if (stationDto.getStationId() == null || stationDao.getStationById(stationDto.getStationId()) == null) {
            //save station and get new id
            Long id = stationDao.saveStation(stationMapper.stationDtoToStation(stationDto));
            stationDto.setStationId(id);
            //save directions
            directionService.saveDirections(stationDto);
            return id;
        } else {
            //update station
            stationDao.updateStation(stationMapper.stationDtoToStation(stationDto));
            //
            directionService.mergeDirections(stationDto);
            return stationDto.getStationId();
        }
    }

    /**
     * Removes station by id.
     *
     * @param id station id.
     */
    public void removeStationById(Long id) {
        if (id == null || stationDao.getStationById(id) == null) {
            return;
        }
        stationDao.removeStation(id);
        directionService.removeDirectionsByStationId(id);
    }

    /**
     * Gets all neighbouring station DTO's.
     *
     * @return list of neighbouring station DTO's.
     */
    public List<NeighbouringStationDto> getAllNeighbouringStation() {
        List<Direction> directions = directionService.getAllDirections();
        return directions.stream().map(d -> new NeighbouringStationDto(
                stationDao.getStationById(d.getDepStationId()),
                stationDao.getStationById(d.getArrStationId())
        )).collect(Collectors.toList());
    }

    /**
     * Gets all neighbouring station DTO's without current.
     *
     * @param id current station id.
     * @return list of neighbouring station DTO's without current.
     */
    public List<NeighbouringStationDto> getAllNeighbouringStationWithoutCurrent(Long id) {
        List<NeighbouringStationDto> neighbouringStationDtos = getAllNeighbouringStation();
        neighbouringStationDtos.removeIf(s -> s.getDepartureStation().getStationId().equals(id) || s.getArriveStation().getStationId().equals(id));
        return neighbouringStationDtos;
    }
}
