package com.railroad.model.dto;

import com.railroad.model.entity.Station;

public class DirectionDto {

    private Long directionId;
    private Long depStationId;
    private Long arrStationId;
    private Station stationByDepStationId;
    private Station stationByArrStationId;

    public DirectionDto() {
    }

    public DirectionDto(Long directionId, Long depStationId, Long arrStationId, Station stationByDepStationId, Station stationByArrStationId) {
        this.directionId = directionId;
        this.depStationId = depStationId;
        this.arrStationId = arrStationId;
        this.stationByDepStationId = stationByDepStationId;
        this.stationByArrStationId = stationByArrStationId;
    }

    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
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

    public Station getStationByDepStationId() {
        return stationByDepStationId;
    }

    public void setStationByDepStationId(Station stationByDepStationId) {
        this.stationByDepStationId = stationByDepStationId;
    }

    public Station getStationByArrStationId() {
        return stationByArrStationId;
    }

    public void setStationByArrStationId(Station stationByArrStationId) {
        this.stationByArrStationId = stationByArrStationId;
    }
}
