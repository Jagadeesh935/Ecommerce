package com.jk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int stars;
    private String reviewTitle;
    private String description;
    private Long productId;


    public Review() {
    }

    public Review(Long id, int stars, String reviewTitle, String description, Long productId) {
        this.id = id;
        this.stars = stars;
        this.reviewTitle = reviewTitle;
        this.description = description;
        this.productId = productId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReviewTitle() {
        return this.reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Review id(Long id) {
        setId(id);
        return this;
    }

    public Review stars(int stars) {
        setStars(stars);
        return this;
    }

    public Review reviewTitle(String reviewTitle) {
        setReviewTitle(reviewTitle);
        return this;
    }

    public Review description(String description) {
        setDescription(description);
        return this;
    }

    public Review productId(Long productId) {
        setProductId(productId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(id, review.id) && stars == review.stars && Objects.equals(reviewTitle, review.reviewTitle) && Objects.equals(description, review.description) && Objects.equals(productId, review.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stars, reviewTitle, description, productId);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", stars='" + getStars() + "'" +
            ", reviewTitle='" + getReviewTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }

    
}
