package com.railroad.model.dto;

import com.railroad.model.entity.Station;
import com.railroad.model.entity.Train;
import com.railroad.model.entity.User;
import com.railroad.model.entity.Wagon;

import java.sql.Date;

public class TicketDto {

    private Long ticketId;
    private Long userId;
    private Long trainId;
    private Long wagonId;
    private Long seatNumber;
    private Date ticketDate;
    private Long departureStationId;
    private Long arriveStationId;
    private Train train;
    private Wagon wagon;
    private User user;
    private Station departureStation;
    private Station arriveStation;

    public TicketDto() {
    }

    public TicketDto(Long ticketId, Long userId, Long trainId, Long wagonId, Long seatNumber, Date ticketDate, Long departureStationId, Long arriveStationId, Train train, Wagon wagon, User user, Station departureStation, Station arriveStation) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.trainId = trainId;
        this.wagonId = wagonId;
        this.seatNumber = seatNumber;
        this.ticketDate = ticketDate;
        this.departureStationId = departureStationId;
        this.arriveStationId = arriveStationId;
        this.train = train;
        this.wagon = wagon;
        this.user = user;
        this.departureStation = departureStation;
        this.arriveStation = arriveStation;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getWagonId() {
        return wagonId;
    }

    public void setWagonId(Long wagonId) {
        this.wagonId = wagonId;
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

    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Long getArriveStationId() {
        return arriveStationId;
    }

    public void setArriveStationId(Long arriveStationId) {
        this.arriveStationId = arriveStationId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public void setWagon(Wagon wagon) {
        this.wagon = wagon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
