package com.railroad.model.dto;


import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private Long stationId;
    private String stationTitle;

    List<BoardTrainDto> boardTrains = new ArrayList();

    public BoardDto() {
    }

    public BoardDto(Long stationId, String stationTitle) {
        this.stationId = stationId;
        this.stationTitle = stationTitle;
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

    public List<BoardTrainDto> getBoardTrains() {
        return boardTrains;
    }

    public void setBoardTrains(List<BoardTrainDto> boardTrains) {
        this.boardTrains = boardTrains;
    }
}
