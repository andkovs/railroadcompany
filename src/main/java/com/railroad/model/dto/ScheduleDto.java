package com.railroad.model.dto;

import com.railroad.model.entity.Direction;

public class ScheduleDto {

    private Long scheduleId;
    private Long directionId;
    private Long trainId;
    private String departureTime;
    private String arriveTime;
    private Direction directionByDirectionId;

    public ScheduleDto() {
    }

    public ScheduleDto(Long scheduleId, Long directionId, Long trainId, String departureTime, String arriveTime, Direction directionByDirectionId) {
        this.scheduleId = scheduleId;
        this.directionId = directionId;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.directionByDirectionId = directionByDirectionId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Direction getDirectionByDirectionId() {
        return directionByDirectionId;
    }

    public void setDirectionByDirectionId(Direction directionByDirectionId) {
        this.directionByDirectionId = directionByDirectionId;
    }
}