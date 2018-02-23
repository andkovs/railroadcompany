package com.railroad.model.dto;

import java.util.ArrayList;

public class StationDto {

    private Long stationId;
    private String stationTitle;
    private Double lat;
    private Double lng;
    private ArrayList<Long> arriveStationIds = new ArrayList<>();

    public StationDto() {
    }

    public StationDto(Long stationId, String stationTitle, Double lat, Double lng) {
        this.stationId = stationId;
        this.stationTitle = stationTitle;
        this.lat = lat;
        this.lng = lng;
    }

    public ArrayList<Long> getArriveStationIds() {
        return arriveStationIds;
    }

    public void setArriveStationIds(ArrayList<Long> arriveStations) {
        this.arriveStationIds = arriveStations;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
