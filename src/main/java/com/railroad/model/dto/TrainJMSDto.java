package com.railroad.model.dto;

import java.io.Serializable;

public class TrainJMSDto implements Serializable {

    private Long trainId;
    private Integer shift;
    private Boolean enabled;

    public TrainJMSDto() {
    }

    public TrainJMSDto(Long trainId, Integer shift, Boolean enabled) {
        this.trainId = trainId;
        this.shift = shift;
        this.enabled = enabled;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
