package com.railroad.model.dto;

public class WagonAddDto {

    private Long trainId;
    private Long wagonTypeId;
    private String wagonTitle;

    public String getWagonTitle() {
        return wagonTitle;
    }

    public void setWagonTitle(String wagonTitle) {
        this.wagonTitle = wagonTitle;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getWagonTypeId() {
        return wagonTypeId;
    }

    public void setWagonTypeId(Long wagonTypeId) {
        this.wagonTypeId = wagonTypeId;
    }
}
