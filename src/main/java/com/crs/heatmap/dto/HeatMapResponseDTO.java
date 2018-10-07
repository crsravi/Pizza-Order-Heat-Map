package com.crs.heatmap.dto;

public class HeatMapResponseDTO {

    private Long numberOfOrders;
    private String regionName;
    private String latitutude;
    private String longitude;
    private Long radius;
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Long numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getLatitutude() {
        return latitutude;
    }

    public void setLatitutude(String latitutude) {
        this.latitutude = latitutude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }
}
