package com.jk.model;

public class AvgRatings {
    
    private Long productId;
    private double avgRatings;


    public AvgRatings() {
    }

    public AvgRatings(Long productId, double avgRatings) {
        this.productId = productId;
        this.avgRatings = Math.round(avgRatings * 10) / 10.0;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getAvgRatings() {
        return this.avgRatings;
    }

    public void setAvgRatings(double avgRatings) {
        this.avgRatings = avgRatings;
    }

    public AvgRatings productId(Long productId) {
        setProductId(productId);
        return this;
    }

    public AvgRatings avgRatings(double avgRatings) {
        setAvgRatings(avgRatings);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", avgRatings='" + getAvgRatings() + "'" +
            "}";
    }
    
}
