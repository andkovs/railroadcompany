package com.railroad.model.search;

import com.railroad.model.dto.StationDto;
import com.railroad.model.entity.Station;

import java.util.ArrayList;

public class Node {

    private StationDto station;
    private ArrayList<StationDto> edges = new ArrayList<>();
    private Boolean searched;
    private Node parent;

    public Node() {
    }

    public Node(StationDto station, ArrayList<StationDto> edges, Boolean searched, Node parent) {
        this.station = station;
        this.edges = edges;
        this.searched = searched;
        this.parent = parent;
    }

    public StationDto getStation() {
        return station;
    }

    public void setStation(StationDto station) {
        this.station = station;
    }

    public ArrayList<StationDto> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<StationDto> edges) {
        this.edges = edges;
    }

    public Boolean getSearched() {
        return searched;
    }

    public void setSearched(Boolean searched) {
        this.searched = searched;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
