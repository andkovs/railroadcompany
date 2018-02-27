package com.railroad.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Train {
    private Long trainId;
    private String trainNumber;
    private Boolean enabled;
    private Collection<Wagon> wagonsByTrainId;
    private Collection<Schedule> schedulesByTrainId;

    public Train() {
    }

    public Train(Long trainId, String trainNumber, Boolean enabled) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", unique = true, nullable = false)
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "train_number")
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Basic
    @Column(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

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

    @OneToMany(mappedBy = "trainByTrainId")
    public Collection<Wagon> getWagonsByTrainId() {
        return wagonsByTrainId;
    }

    public void setWagonsByTrainId(Collection<Wagon> wagonsByTrainId) {
        this.wagonsByTrainId = wagonsByTrainId;
    }

    @OneToMany(mappedBy = "trainByTrainId")
    public Collection<Schedule> getSchedulesByTrainId() {
        return schedulesByTrainId;
    }

    public void setSchedulesByTrainId(Collection<Schedule> schedulesByTrainId) {
        this.schedulesByTrainId = schedulesByTrainId;
    }


}
