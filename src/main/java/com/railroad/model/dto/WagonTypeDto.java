package com.railroad.model.dto;

public class WagonTypeDto {

    private Long wagonId;
    private String wagonType;
    private Integer capacity;

    public WagonTypeDto() {
    }

    public WagonTypeDto(Long wagonId, String wagonType, Integer capacity) {
        this.wagonId = wagonId;
        this.wagonType = wagonType;
        this.capacity = capacity;
    }

    public Long getWagonId() {
        return wagonId;
    }

    public void setWagonId(Long wagonId) {
        this.wagonId = wagonId;
    }

    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
