package com.railroad.model.dto;

import com.railroad.model.entity.Station;

public class NeighbouringStationDto {

    private Station departureStation;
    private Station arriveStation;

    public NeighbouringStationDto() {
    }

    public NeighbouringStationDto(Station departureStation, Station arriveStation) {
        this.departureStation = departureStation;
        this.arriveStation = arriveStation;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(Station arriveStation) {
        this.arriveStation = arriveStation;
    }
}
