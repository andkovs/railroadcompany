package com.railroad.core.service;

import com.railroad.core.mapper.DirectionMapper;
import com.railroad.model.dao.DirectionDao;
import com.railroad.model.dto.DirectionDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionService {


    private final
    DirectionDao directionDao;

    private final
    DirectionMapper directionMapper;

    @Autowired
    public DirectionService(DirectionDao directionDao, DirectionMapper directionMapper) {
        this.directionDao = directionDao;
        this.directionMapper = directionMapper;
    }

    /**
     * Get list of directions by
     * departure station's id.
     *
     * @param id departure station's id.
     * @return list of direction.s
     */
    List getDirectionListByDepStationId(Long id) {
        return directionDao.getDirectionListByDepStationId(id);
    }

    /**
     * Saves directions.
     *
     * @param stationDto station DTO containing list of directions.
     */
    void saveDirections(StationDto stationDto) {
        createBidirectional(directionMapper.stationDtoToDirectionList(stationDto)).forEach(directionDao::saveDirection);
    }

    /**
     * merges directions. Removes old,
     * add new directions.
     *
     * @param stationDto station DTO containing list of directions.
     */
    void mergeDirections(StationDto stationDto) {
        List<Direction> oldDirections = directionDao.getDirectionListByDepStationId(stationDto.getStationId());
        List<Direction> newDirections = directionMapper.stationDtoToDirectionList(stationDto);
        List<Direction> intersect = newDirections.stream().filter(oldDirections::contains).collect(Collectors.toList());
        List<Direction> saveDirections = newDirections.stream().filter(c -> !intersect.contains(c)).collect(Collectors.toList());
        List<Direction> removeDirections = oldDirections.stream().filter(c -> !intersect.contains(c)).collect(Collectors.toList());
        createBidirectional(saveDirections).forEach(directionDao::saveDirection);
        createBidirectional(removeDirections).forEach(directionDao::removeDirection);

    }

    /**
     * Creates reverse directions.
     *
     * @param directions list of unidirectional path(directions).
     * @return list of bidirectional path's.
     */
    private List<Direction> createBidirectional(List<Direction> directions) {
        List<Direction> bidirectional = new ArrayList<>();
        for (Direction d :
                directions) {
            bidirectional.add(d);
            Direction direction = new Direction();
            direction.setDepStationId(d.getArrStationId());
            direction.setArrStationId(d.getDepStationId());
            bidirectional.add(direction);
        }
        return bidirectional;
    }

    /**
     * Removes directions by station id.
     *
     * @param id station's id.
     */
    void removeDirectionsByStationId(Long id) {
        directionDao.removeDirectionsByStationId(id);
    }

    /**
     * Gets list of all directions.
     *
     * @return list of all directions.
     */
    public List<DirectionDto> getAllDirections() {
        return directionMapper.directionListToDirectionDtoList(directionDao.getAllDirections());
    }

    /**
     * Gets direction id by departure station id
     * and arrive station id.
     * @param depStationId departure station id.
     * @param arrStationId arrive station id.
     * @return direction id.
     */
    Long getDirectionIdByDepStationIdAndArriveStationId(Long depStationId, Long arrStationId) {
        return directionDao.getDirectionIdByDepStationIdAndArriveStationIdFromDb(depStationId, arrStationId);
    }
}
