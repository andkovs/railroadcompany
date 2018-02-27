package com.railroad.model.dto;

public class WagonDto {
    private Long wagonId;
    private Long trainId;
    private String wagonType;
    private String wagonTitle;

    public WagonDto() {
    }

    public WagonDto(Long wagonId, Long trainId, String wagonType, String wagonTitle) {
        this.wagonId = wagonId;
        this.trainId = trainId;
        this.wagonType = wagonType;
        this.wagonTitle = wagonTitle;
    }

    public Long getWagonId() {
        return wagonId;
    }

    public void setWagonId(Long wagonId) {
        this.wagonId = wagonId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }

    public String getWagonTitle() {
        return wagonTitle;
    }

    public void setWagonTitle(String wagonTitle) {
        this.wagonTitle = wagonTitle;
    }
}
