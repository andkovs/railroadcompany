package com.railroad.model.entity;

import javax.persistence.*;

@Entity
public class Schedule {
    private Long scheduleId;
    private Long directionId;
    private Long trainId;
    private String departureTime;
    private String arriveTime;
    private Train trainByTrainId;
    private Direction directionByDirectionId;

    public Schedule() {
    }

    public Schedule(Long directionId, Long trainId, String departureTime, String arriveTime) {
        this.directionId = directionId;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", unique = true, nullable = false)
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "direction_id")
    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
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
    @Column(name = "departure_time")
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "arrive_time")
    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
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
    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id", nullable = false, insertable = false, updatable = false)
    public Direction getDirectionByDirectionId() {
        return directionByDirectionId;
    }

    public void setDirectionByDirectionId(Direction directionByDirectionId) {
        this.directionByDirectionId = directionByDirectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (scheduleId != null ? !scheduleId.equals(schedule.scheduleId) : schedule.scheduleId != null) return false;
        if (directionId != null ? !directionId.equals(schedule.directionId) : schedule.directionId != null)
            return false;
        if (trainId != null ? !trainId.equals(schedule.trainId) : schedule.trainId != null) return false;
        if (departureTime != null ? !departureTime.equals(schedule.departureTime) : schedule.departureTime != null)
            return false;
        if (arriveTime != null ? !arriveTime.equals(schedule.arriveTime) : schedule.arriveTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scheduleId != null ? scheduleId.hashCode() : 0;
        result = 31 * result + (directionId != null ? directionId.hashCode() : 0);
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arriveTime != null ? arriveTime.hashCode() : 0);
        return result;
    }


}
