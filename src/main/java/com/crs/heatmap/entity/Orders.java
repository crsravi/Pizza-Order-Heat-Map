package com.crs.heatmap.entity;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Orders {
    @Id
    private Long id;
    private Long region;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }
}
