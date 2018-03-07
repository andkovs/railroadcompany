package com.railroad.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Station {
    private Long stationId;
    private String stationTitle;
    private Double lat;
    private Double lng;
    private Collection<Direction> directionsByStationId;
    private Collection<Direction> directionsByStationId_0;
    private Collection<Ticket> ticketsByStationId;
    private Collection<Ticket> ticketsByStationId_0;

    public Station() {
    }

    public Station(Long stationId, String stationTitle, Double lat, Double lng) {
        this.stationId = stationId;
        this.stationTitle = stationTitle;
        this.lat = lat;
        this.lng = lng;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", unique = true, nullable = false)
    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "station_title")
    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

    @Basic
    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (stationId != null ? !stationId.equals(station.stationId) : station.stationId != null) return false;
        if (stationTitle != null ? !stationTitle.equals(station.stationTitle) : station.stationTitle != null)
            return false;
        if (lat != null ? !lat.equals(station.lat) : station.lat != null) return false;
        if (lng != null ? !lng.equals(station.lng) : station.lng != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId != null ? stationId.hashCode() : 0;
        result = 31 * result + (stationTitle != null ? stationTitle.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stationByDepStationId")
    public Collection<Direction> getDirectionsByStationId() {
        return directionsByStationId;
    }

    public void setDirectionsByStationId(Collection<Direction> directionsByStationId) {
        this.directionsByStationId = directionsByStationId;
    }

    @OneToMany(mappedBy = "stationByArrStationId")
    public Collection<Direction> getDirectionsByStationId_0() {
        return directionsByStationId_0;
    }

    public void setDirectionsByStationId_0(Collection<Direction> directionsByStationId_0) {
        this.directionsByStationId_0 = directionsByStationId_0;
    }

    @OneToMany(mappedBy = "stationByDepartureStationId")
    public Collection<Ticket> getTicketsByStationId() {
        return ticketsByStationId;
    }

    public void setTicketsByStationId(Collection<Ticket> ticketsByStationId) {
        this.ticketsByStationId = ticketsByStationId;
    }

    @OneToMany(mappedBy = "stationByArriveStationId")
    public Collection<Ticket> getTicketsByStationId_0() {
        return ticketsByStationId_0;
    }

    public void setTicketsByStationId_0(Collection<Ticket> ticketsByStationId_0) {
        this.ticketsByStationId_0 = ticketsByStationId_0;
    }
}
