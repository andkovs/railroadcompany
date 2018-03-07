package com.railroad.model.dto;

public class TicketFormDto {

    private TrainDto train;
    private StationDto depStation;
    private StationDto arrStation;
    private String depTime;
    private String arrTime;
    private Integer numberOfSeats;
    private Integer numberOfAvailableSeats;

    public TicketFormDto() {
    }

    public TrainDto getTrain() {
        return train;
    }

    public void setTrain(TrainDto train) {
        this.train = train;
    }

    public StationDto getDepStation() {
        return depStation;
    }

    public void setDepStation(StationDto depStation) {
        this.depStation = depStation;
    }

    public StationDto getArrStation() {
        return arrStation;
    }

    public void setArrStation(StationDto arrStation) {
        this.arrStation = arrStation;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(Integer numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }
}
