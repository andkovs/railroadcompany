package com.railroad.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wagon_type", schema = "railroad")
public class WagonType {
    private Long wagonTypeId;
    private String wagonType;
    private Integer capacity;
    private Collection<Wagon> wagonsByWagonTypeId;

    public WagonType() {
    }

    public WagonType(Long wagonTypeId, String wagonType, Integer capacity, Collection<Wagon> wagonsByWagonTypeId) {
        this.wagonTypeId = wagonTypeId;
        this.wagonType = wagonType;
        this.capacity = capacity;
        this.wagonsByWagonTypeId = wagonsByWagonTypeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wagon_type_id", unique = true, nullable = false)
    public Long getWagonTypeId() {
        return wagonTypeId;
    }

    public void setWagonTypeId(Long wagonTypeId) {
        this.wagonTypeId = wagonTypeId;
    }

    @Basic
    @Column(name = "wagon_type")
    public String getWagonType() {
        return wagonType;
    }

    public void setWagonType(String wagonType) {
        this.wagonType = wagonType;
    }

    @Basic
    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WagonType wagonType1 = (WagonType) o;

        if (wagonTypeId != null ? !wagonTypeId.equals(wagonType1.wagonTypeId) : wagonType1.wagonTypeId != null)
            return false;
        if (wagonType != null ? !wagonType.equals(wagonType1.wagonType) : wagonType1.wagonType != null) return false;
        if (capacity != null ? !capacity.equals(wagonType1.capacity) : wagonType1.capacity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wagonTypeId != null ? wagonTypeId.hashCode() : 0;
        result = 31 * result + (wagonType != null ? wagonType.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "wagonTypeByWagonTypeId")
    public Collection<Wagon> getWagonsByWagonTypeId() {
        return wagonsByWagonTypeId;
    }

    public void setWagonsByWagonTypeId(Collection<Wagon> wagonsByWagonTypeId) {
        this.wagonsByWagonTypeId = wagonsByWagonTypeId;
    }
}
