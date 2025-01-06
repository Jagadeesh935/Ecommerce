package com.jk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jk.model.Review;

import java.util.List;

import com.jk.model.AvgRatings;


@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{
    
    List<Review> findByProductId(Long productId);

    @Query("select new com.jk.model.AvgRatings(r.productId, avg(r.stars)) from Review r group by r.productId")
    List<AvgRatings> getAllProductsAvgRatings();
}
