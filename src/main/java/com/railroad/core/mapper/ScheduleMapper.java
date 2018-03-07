package com.railroad.core.mapper;

import com.railroad.model.dto.ScheduleDto;
import com.railroad.model.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleMapper {

    public List<ScheduleDto> scheduleListToScheduleDtoList(List<Schedule> schedules){
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule sch:
                schedules) {
            scheduleDtos.add(new ScheduleDto(
                    sch.getScheduleId(),
                    sch.getDirectionId(),
                    sch.getTrainId(),
                    sch.getDepartureTime(),
                    sch.getArriveTime(),
                    sch.getTrainByTrainId(),
                    sch.getDirectionByDirectionId()
            ));
        }
        return scheduleDtos;
    }
}
