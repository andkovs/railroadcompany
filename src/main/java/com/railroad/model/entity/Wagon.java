package com.railroad.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Wagon {
    private Long wagonId;
    private Long trainId;
    private Long wagonTypeId;
    private String wagonTitle;
    private Train trainByTrainId;
    private WagonType wagonTypeByWagonTypeId;
    private Collection<Ticket> ticketsByWagonId;

    public Wagon() {
    }

    public Wagon(Long wagonId, Long trainId, Long wagonTypeId, String wagonTitle, Train trainByTrainId, WagonType wagonTypeByWagonTypeId) {
        this.wagonId = wagonId;
        this.trainId = trainId;
        this.wagonTypeId = wagonTypeId;
        this.wagonTitle = wagonTitle;
        this.trainByTrainId = trainByTrainId;
        this.wagonTypeByWagonTypeId = wagonTypeByWagonTypeId;
    }

    public Wagon(Long trainId, Long wagonTypeId, String wagonTitle) {
        this.trainId = trainId;
        this.wagonTypeId = wagonTypeId;
        this.wagonTitle = wagonTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wagon_id", unique = true, nullable = false)
    public Long getWagonId() {
        return wagonId;
    }

    public void setWagonId(Long wagonId) {
        this.wagonId = wagonId;
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
    @Column(name = "wagon_type_id")
    public Long getWagonTypeId() {
        return wagonTypeId;
    }

    public void setWagonTypeId(Long wagonTypeId) {
        this.wagonTypeId = wagonTypeId;
    }

    @Basic
    @Column(name = "wagon_title")
    public String getWagonTitle() {
        return wagonTitle;
    }

    public void setWagonTitle(String wagonTitle) {
        this.wagonTitle = wagonTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        if (wagonId != null ? !wagonId.equals(wagon.wagonId) : wagon.wagonId != null) return false;
        if (trainId != null ? !trainId.equals(wagon.trainId) : wagon.trainId != null) return false;
        if (wagonTypeId != null ? !wagonTypeId.equals(wagon.wagonTypeId) : wagon.wagonTypeId != null) return false;
        if (wagonTitle != null ? !wagonTitle.equals(wagon.wagonTitle) : wagon.wagonTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wagonId != null ? wagonId.hashCode() : 0;
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (wagonTypeId != null ? wagonTypeId.hashCode() : 0);
        result = 31 * result + (wagonTitle != null ? wagonTitle.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "wagon_type_id", referencedColumnName = "wagon_type_id", nullable = false, insertable = false, updatable = false)
    public WagonType getWagonTypeByWagonTypeId() {
        return wagonTypeByWagonTypeId;
    }

    public void setWagonTypeByWagonTypeId(WagonType wagonTypeByWagonTypeId) {
        this.wagonTypeByWagonTypeId = wagonTypeByWagonTypeId;
    }

    @OneToMany(mappedBy = "wagonByWagonId")
    public Collection<Ticket> getTicketsByWagonId() {
        return ticketsByWagonId;
    }

    public void setTicketsByWagonId(Collection<Ticket> ticketsByWagonId) {
        this.ticketsByWagonId = ticketsByWagonId;
    }
}
