package com.jk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.model.Review;
import com.jk.repository.ReviewRepo;
import com.jk.model.AvgRatings;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepo reviewrepo;

    public Review addReview(Review review){
        return reviewrepo.save(review);
    }

    public List<Review> getReviewByProductId(Long id){
        return reviewrepo.findByProductId(id);
    }

    public List<AvgRatings> getAllProductsAvgRatings(){
        return reviewrepo.getAllProductsAvgRatings();
    }

}
