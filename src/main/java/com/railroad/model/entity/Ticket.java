package com.railroad.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Ticket {
    private Long ticketId;
    private Long userId;
    private Long trainId;
    private Long wagonId;
    private Long seatNamber;
    private Date ticketDate;
    private Long departureStationId;
    private Long arriveStationId;
    private Train trainByTrainId;
    private Wagon wagonByWagonId;
    private User userByUserId;
    private Station stationByDepartureStationId;
    private Station stationByArriveStationId;

    public Ticket() {
    }

    public Ticket(Long userId, Long trainId, Long wagonId, Long seatNamber, Date ticketDate, Long departureStationId, Long arriveStationId) {
        this.userId = userId;
        this.trainId = trainId;
        this.wagonId = wagonId;
        this.seatNamber = seatNamber;
        this.ticketDate = ticketDate;
        this.departureStationId = departureStationId;
        this.arriveStationId = arriveStationId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", unique = true, nullable = false)
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "seat_namber")
    public Long getSeatNamber() {
        return seatNamber;
    }

    public void setSeatNamber(Long seatNamber) {
        this.seatNamber = seatNamber;
    }

    @Basic
    @Column(name = "ticket_date")
    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    @Basic
    @Column(name = "departure_station_id")
    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    @Basic
    @Column(name = "arrive_station_id")
    public Long getArriveStationId() {
        return arriveStationId;
    }

    public void setArriveStationId(Long arriveStationId) {
        this.arriveStationId = arriveStationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (ticketId != null ? !ticketId.equals(ticket.ticketId) : ticket.ticketId != null) return false;
        if (seatNamber != null ? !seatNamber.equals(ticket.seatNamber) : ticket.seatNamber != null) return false;
        if (ticketDate != null ? !ticketDate.equals(ticket.ticketDate) : ticket.ticketDate != null) return false;
        if (departureStationId != null ? !departureStationId.equals(ticket.departureStationId) : ticket.departureStationId != null)
            return false;
        if (arriveStationId != null ? !arriveStationId.equals(ticket.arriveStationId) : ticket.arriveStationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (seatNamber != null ? seatNamber.hashCode() : 0);
        result = 31 * result + (ticketDate != null ? ticketDate.hashCode() : 0);
        result = 31 * result + (departureStationId != null ? departureStationId.hashCode() : 0);
        result = 31 * result + (arriveStationId != null ? arriveStationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "train_id", nullable = false, insertable = false, updatable = false)
    public Train getTrainByTrainId() {
        return trainByTrainId;
    }

    public void setTrainByTrainId(Train trainByTrainId) {
        this.trainByTrainId = trainByTrainId;
    }

    @ManyToOne
    @JoinColumn(name = "wagon_id", referencedColumnName = "wagon_id", nullable = false, insertable = false, updatable = false)
    public Wagon getWagonByWagonId() {
        return wagonByWagonId;
    }

    public void setWagonByWagonId(Wagon wagonByWagonId) {
        this.wagonByWagonId = wagonByWagonId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "departure_station_id", referencedColumnName = "station_id", nullable = false, insertable = false, updatable = false)
    public Station getStationByDepartureStationId() {
        return stationByDepartureStationId;
    }

    public void setStationByDepartureStationId(Station stationByDepartureStationId) {
        this.stationByDepartureStationId = stationByDepartureStationId;
    }

    @ManyToOne
    @JoinColumn(name = "arrive_station_id", referencedColumnName = "station_id", nullable = false, insertable = false, updatable = false)
    public Station getStationByArriveStationId() {
        return stationByArriveStationId;
    }

    public void setStationByArriveStationId(Station stationByArriveStationId) {
        this.stationByArriveStationId = stationByArriveStationId;
    }

    @Basic
    @Column(name = "train_id")
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "wagon_id")
    public Long getWagonId() {
        return wagonId;
    }

    public void setWagonId(Long wagonId) {
        this.wagonId = wagonId;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
