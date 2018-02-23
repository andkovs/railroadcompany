package com.railroad.core.mapper;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DirectionMapper {

    /**
     * Converts StationDto object to list of Directions
     *
     * @param stationDto the station with list of arrive stations
     * @return list of directions
     */
    public List<Direction> stationDtoToDirectionList(StationDto stationDto) {
        List<Direction> directions = new ArrayList<Direction>();
        for (Long id :
                stationDto.getArriveStationIds()) {
            //direction from A to B
            Direction direction = new Direction();
            direction.setDepStationId(stationDto.getStationId());
            direction.setArrStationId(id);
            directions.add(direction);
        }
        return directions;
    }
}
