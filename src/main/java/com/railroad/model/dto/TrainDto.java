package com.railroad.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TrainDto {

    private Long trainId;
    private String trainNumber;
    private Boolean enabled;
    private List<WagonDto> wagons = new ArrayList<>();private
    List<ScheduleDto> schedules = new ArrayList<>();

    public TrainDto(Long trainId, String trainNumber, Boolean enabled) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.enabled = enabled;
    }

    public TrainDto() {
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<WagonDto> getWagons() {
        return wagons;
    }

    public void setWagons(List<WagonDto> wagons) {
        this.wagons = wagons;
    }

    public List<ScheduleDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDto> schedules) {
        this.schedules = schedules;
    }
}
