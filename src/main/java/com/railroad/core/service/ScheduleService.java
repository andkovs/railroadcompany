package com.railroad.core.service;

import com.railroad.core.mapper.ScheduleMapper;
import com.railroad.model.dao.ScheduleDao;
import com.railroad.model.dto.ScheduleAddDto;
import com.railroad.model.dto.ScheduleDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final
    ScheduleDao scheduleDao;

    private final
    DirectionService directionService;

    private final
    ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao, DirectionService directionService, ScheduleMapper scheduleMapper) {
        this.scheduleDao = scheduleDao;
        this.directionService = directionService;
        this.scheduleMapper = scheduleMapper;
    }


    public void saveSchedule(ScheduleAddDto scheduleAddDto) {
        Long directionId = directionService.getDirectionIdByDepStationIdAndArriveStationId(scheduleAddDto.getDepStationId(), scheduleAddDto.getArrStationId());
        scheduleDao.saveSchedule(new Schedule(
                directionId, scheduleAddDto.getTrainId(),
                scheduleAddDto.getDepartureTime(),
                scheduleAddDto.getArriveTime()));
    }

    public Long getTrainIdByScheduleId(Long id) {
        return scheduleDao.getTrainIdByScheduleId(id);
    }

    public void removeScheduleById(Long id) {
        scheduleDao.removeScheduleById(id);
    }

    public List<ScheduleDto> getScheduleListByDepStationId(Long id) {
        List<Direction> directions = directionService.getDirectionListByDepStationId(id);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Direction d :
                directions) {
            List<ScheduleDto> scheduleListByDirectionId = scheduleMapper.scheduleListToScheduleDtoList(scheduleDao.getScheduleListByDirectionId(d.getDirectionId()));
            scheduleDtos.addAll(scheduleListByDirectionId);
        }
        return scheduleDtos;
    }

    public List<ScheduleDto> getScheduleListByArrStationId(Long id) {
        List<Direction> directions = directionService.getDirectionListByArrStationId(id);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Direction d :
                directions) {
            List<ScheduleDto> scheduleListByDirectionId = scheduleMapper.scheduleListToScheduleDtoList(scheduleDao.getScheduleListByDirectionId(d.getDirectionId()));
            scheduleDtos.addAll(scheduleListByDirectionId);
        }
        return scheduleDtos;
    }

    public Schedule getScheduleByTrainIdAndDirectionId(Long trainId, Long directionId) {
        return scheduleDao.getScheduleByTrainIdAndDirectionId(trainId, directionId);
    }
}
