package com.railroad.core.service;

import com.railroad.core.mapper.StationMapper;
import com.railroad.model.dao.StationDao;
import com.railroad.model.dto.DirectionDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
     * @return list of all station DTO's.
     */
    public List<StationDto> getAllStations() {
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
        if (id == null || stationDao.getStationById(id) == null) {
            return getNewStationDto();
        }
        //get stationDto by id
        StationDto stationDto = stationMapper.stationToStationDto(stationDao.getStationById(id));
        if (stationDto == null) {
            return getNewStationDto();
        }
        //get list of directions, which can we get from current station
        List<Direction> directions = directionService.getDirectionListByDepStationId(id);
        if (directions == null) {
            directions = new ArrayList<>();
        }
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
        if (stationDto == null) {
            return null;
        }
        if (stationDto.getStationId() == null || stationDao.getStationById(stationDto.getStationId()) == null) {
            //save station and get new id
            if (stationDto.getStationTitle() == null || stationDao.getStationByStationTitle(stationDto.getStationTitle()) != null) {
                return null;
            }
            Station station = stationMapper.stationDtoToStation(stationDto);
            if (station == null) {
                return null;
            }
            Long id = stationDao.saveStation(station);
            if (id == null) {
                return null;
            }
            stationDto.setStationId(id);
            //save directions
            directionService.saveDirections(stationDto);
            return id;
        } else {
            Station checkStation = stationDao.getStationByStationTitle(stationDto.getStationTitle());
            if (checkStation != null) {
                if (!Objects.equals(checkStation.getStationId(), stationDto.getStationId())) {
                    return null;
                }
            }
            //update station
            Station updateStation = stationMapper.stationDtoToStation(stationDto);
            if (updateStation != null) {
                stationDao.updateStation(updateStation);
            }
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
    public List<DirectionDto> getAllNeighbouringStation() {
        List<DirectionDto> directionDtos = directionService.getAllDirections();
        if (directionDtos == null) {
            return new ArrayList<>();
        }
        return directionDtos;
    }

    /**
     * Gets all neighbouring station DTO's without current.
     *
     * @param id current station id.
     * @return list of neighbouring station DTO's without current.
     */
    public List<DirectionDto> getAllNeighbouringStationWithoutCurrent(Long id) {
        List<DirectionDto> neighbouringStationDtos = getAllNeighbouringStation();
        neighbouringStationDtos.removeIf(s -> s.getStationByDepStationId().getStationId().equals(id) || s.getStationByArrStationId().getStationId().equals(id));
        return neighbouringStationDtos;
    }

    private StationDto getNewStationDto() {
        return new StationDto(0L, "", 49.88, 3.35);
    }
}
