package com.railroad.model.dto;

import java.util.List;

public class SearchResultDto {

    private Long depStationId;
    private Long arrStationId;
    private String fromTime;
    private String toTime;
    private List<TrainDto> trains;

    public SearchResultDto() {
    }

    public SearchResultDto(Long depStationId, Long arrStationId, String fromTime, String toTime, List<TrainDto> trains) {
        this.depStationId = depStationId;
        this.arrStationId = arrStationId;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.trains = trains;
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

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public List<TrainDto> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainDto> trains) {
        this.trains = trains;
    }
}
