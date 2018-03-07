package com.railroad.model.dto;


import java.util.ArrayList;
import java.util.List;

public class TrainDto {

    private Long trainId;
    private String trainNumber;
    private Boolean enabled;
    private List<WagonDto> wagons = new ArrayList<>();
    private List<ScheduleDto> schedules = new ArrayList<>();
    private List<TicketDto> tickets = new ArrayList<>();

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

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainDto train = (TrainDto) o;

        if (trainId != null ? !trainId.equals(train.trainId) : train.trainId != null) return false;
        if (trainNumber != null ? !trainNumber.equals(train.trainNumber) : train.trainNumber != null) return false;
        if (enabled != null ? !enabled.equals(train.enabled) : train.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (trainNumber != null ? trainNumber.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }

}
