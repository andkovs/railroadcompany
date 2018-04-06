package com.railroad.model.dto;

public class BoardTrainDto {

    private Long trainId;
    private String trainNumber;
    private Boolean enabled;
    private Integer shift;
    private String arrivalTime;
    private String departureTime;
    private String nextStationTitle;

    public BoardTrainDto() {
    }

    public BoardTrainDto(Long trainId, String trainNumber, Boolean enabled, Integer shift, String arrivalTime, String departureTime, String nextStationTitle) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.enabled = enabled;
        this.shift = shift;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.nextStationTitle = nextStationTitle;
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

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getNextStationTitle() {
        return nextStationTitle;
    }

    public void setNextStationTitle(String nextStationTitle) {
        this.nextStationTitle = nextStationTitle;
    }
}

