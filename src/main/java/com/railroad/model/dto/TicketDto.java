package com.railroad.model.dto;

import java.sql.Date;

public class TicketDto {

    private Long ticketId;
    private Long trainId;
    private String trainNumber;
    private Long seatNumber;
    private Date ticketDate;
    private UserDto user;
    private WagonDto wagon;
    private StationDto departureStation;
    private StationDto arriveStation;

    public TicketDto() {
    }

    public TicketDto(Long ticketId, Long trainId, String trainNumber, Long seatNumber, Date ticketDate, UserDto user, WagonDto wagon, StationDto departureStation, StationDto arriveStation) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.ticketDate = ticketDate;
        this.user = user;
        this.wagon = wagon;
        this.departureStation = departureStation;
        this.arriveStation = arriveStation;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

    public WagonDto getWagon() {
        return wagon;
    }

    public void setWagon(WagonDto wagon) {
        this.wagon = wagon;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public StationDto getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(StationDto departureStation) {
        this.departureStation = departureStation;
    }

    public StationDto getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(StationDto arriveStation) {
        this.arriveStation = arriveStation;
    }
}
