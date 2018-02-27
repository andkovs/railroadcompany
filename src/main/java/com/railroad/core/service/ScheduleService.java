package com.railroad.core.service;

import com.railroad.model.dao.DirectionDao;
import com.railroad.model.dao.ScheduleDao;
import com.railroad.model.dto.ScheduleAddDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final
    ScheduleDao scheduleDao;

    private final
    DirectionService directionService;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao, DirectionService directionService) {
        this.scheduleDao = scheduleDao;
        this.directionService = directionService;
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
}
