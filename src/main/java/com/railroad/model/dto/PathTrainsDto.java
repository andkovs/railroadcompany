package com.railroad.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PathTrainsDto {

    private List<StationDto> stations = new ArrayList<>();
    private List<TrainDto> trains;

    public PathTrainsDto() {
    }

    public PathTrainsDto(List<StationDto> stations, List<TrainDto> trains) {
        this.stations = stations;
        this.trains = trains;
    }

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }

    public List<TrainDto> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainDto> trains) {
        this.trains = trains;
    }
}
