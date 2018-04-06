package com.railroad.core.service;

import com.railroad.model.dto.BoardDto;
import com.railroad.model.dto.BoardTrainDto;
import com.railroad.model.dto.ScheduleDto;
import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Direction;
import com.railroad.model.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final
    StationService stationService;

    private final
    ScheduleService scheduleService;

    private final
    DirectionService directionService;

    @Autowired
    public BoardService(StationService stationService, ScheduleService scheduleService, DirectionService directionService) {
        this.stationService = stationService;
        this.scheduleService = scheduleService;
        this.directionService = directionService;
    }

    public List<BoardDto> getBoards() {
        List<BoardDto> boardDtos = new ArrayList<>();
        List<StationDto> stationDtos = stationService.getAllStations();
        for (StationDto s :
                stationDtos) {
            boardDtos.add(getBoardByStationId(s.getStationId()));
        }
        return boardDtos;
    }

    public BoardDto getBoardByStationId(Long stationId) {
        StationDto stationDto = stationService.getStationById(stationId);
        BoardDto boardDto = new BoardDto(stationDto.getStationId(), stationDto.getStationTitle());
        List<ScheduleDto> scheduleForDepStationDtos = scheduleService.getScheduleListByDepStationId(stationDto.getStationId());
        List<ScheduleDto> scheduleForArrStationDtos = scheduleService.getScheduleListByArrStationId(stationDto.getStationId());
        //List<ScheduleDto> mergeScheduleDtos = mergeSchedules(scheduleForDepStationDtos, scheduleForArrStationDtos);
        List<Direction> directions = directionService.getDirectionListByArrStationId(stationDto.getStationId());
        for (ScheduleDto sch :
                scheduleForDepStationDtos) {
            BoardTrainDto boardTrainDto = new BoardTrainDto();
            boardTrainDto.setTrainId(sch.getTrainId());
            boardTrainDto.setTrainNumber(sch.getTrainByTrainId().getTrainNumber());
            boardTrainDto.setEnabled(sch.getTrainByTrainId().getEnabled());
            boardTrainDto.setShift(sch.getTrainByTrainId().getShift());
            boardTrainDto.setDepartureTime(sch.getDepartureTime());
            boardTrainDto.setNextStationTitle(sch.getDirectionByDirectionId().getStationByArrStationId().getStationTitle());
            for (Direction d :
                    directions) {
                Schedule schedule = scheduleService.getScheduleByTrainIdAndDirectionId(sch.getTrainId(), d.getDirectionId());
                if (schedule != null) {
                    boardTrainDto.setArrivalTime(schedule.getArriveTime());
                }
            }
            boardDto.getBoardTrains().add(boardTrainDto);
        }
        for (ScheduleDto sch :
                scheduleForArrStationDtos) {
            BoardTrainDto boardTrainDto = new BoardTrainDto();
            boardTrainDto.setTrainId(sch.getTrainId());
            boardTrainDto.setTrainNumber(sch.getTrainByTrainId().getTrainNumber());
            boardTrainDto.setEnabled(sch.getTrainByTrainId().getEnabled());
            boardTrainDto.setShift(sch.getTrainByTrainId().getShift());
            boardTrainDto.setDepartureTime(null);
            boardTrainDto.setNextStationTitle(null);
            boardTrainDto.setArrivalTime(sch.getArriveTime());
            boardDto.getBoardTrains().add(boardTrainDto);
        }
        return boardDto;
    }

//    private List<ScheduleDto> mergeSchedules(List<ScheduleDto> scheduleForDepStationDtos, List<ScheduleDto> scheduleForArrStationDtos) {
//        for (ScheduleDto depSch :
//                scheduleForDepStationDtos) {
//            for (ScheduleDto arrSch:
//                    scheduleForArrStationDtos) {
//                if(depSch.getTrainId().equals(arrSch.getTrainId())){
//                    scheduleForArrStationDtos.remove(arrSch);
//                }
//            }
//        }
//        scheduleForDepStationDtos.addAll(scheduleForArrStationDtos);
//        return scheduleForDepStationDtos;
//    }
}
