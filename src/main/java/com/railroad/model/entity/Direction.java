package com.railroad.model.entity;

import javax.persistence.*;

@Entity
public class Direction {
    private Long directionId;
    private Long depStationId;
    private Long arrStationId;
    private Station stationByDepStationId;
    private Station stationByArrStationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direction_id", unique = true, nullable = false)
    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    @Basic
    @Column(name = "dep_station_id")
    public Long getDepStationId() {
        return depStationId;
    }

    public void setDepStationId(Long depStationId) {
        this.depStationId = depStationId;
    }

    @Basic
    @Column(name = "arr_station_id")
    public Long getArrStationId() {
        return arrStationId;
    }

    public void setArrStationId(Long arrStationId) {
        this.arrStationId = arrStationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

//        if (directionId != null ? !directionId.equals(direction.directionId) : direction.directionId != null)
//            return false;
        if (depStationId != null ? !depStationId.equals(direction.depStationId) : direction.depStationId != null)
            return false;
        if (arrStationId != null ? !arrStationId.equals(direction.arrStationId) : direction.arrStationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = directionId != null ? directionId.hashCode() : 0;
        result = 31 * result + (depStationId != null ? depStationId.hashCode() : 0);
        result = 31 * result + (arrStationId != null ? arrStationId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dep_station_id", referencedColumnName = "station_id", nullable = false, insertable = false, updatable = false)
    public Station getStationByDepStationId() {
        return stationByDepStationId;
    }

    public void setStationByDepStationId(Station stationByDepStationId) {
        this.stationByDepStationId = stationByDepStationId;
    }

    @ManyToOne
    @JoinColumn(name = "arr_station_id", referencedColumnName = "station_id", nullable = false, insertable = false, updatable = false)
    public Station getStationByArrStationId() {
        return stationByArrStationId;
    }

    public void setStationByArrStationId(Station stationByArrStationId) {
        this.stationByArrStationId = stationByArrStationId;
    }
}
