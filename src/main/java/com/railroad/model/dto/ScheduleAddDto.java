package com.railroad.model.dto;

public class ScheduleAddDto {

    private Long trainId;
    private Long depStationId;
    private Long arrStationId;
    private String departureTime;
    private String arriveTime;

    public ScheduleAddDto() {
    }

    public ScheduleAddDto(Long trainId, Long depStationId, Long arrStationId, String departureTime, String arriveTime) {
        this.trainId = trainId;
        this.depStationId = depStationId;
        this.arrStationId = arrStationId;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getDepStationId() {
        return depStationId;
    }

    public void setDepStationId(Long depStationId) {
        this.depStationId = depStationId;
    }

    public Long getArrStationId() {
        return arrStationId;
    }

    public void setArrStationId(Long arrStationId) {
        this.arrStationId = arrStationId;
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
}
