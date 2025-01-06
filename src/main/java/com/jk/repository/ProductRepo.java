package com.jk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jk.model.Product;
import com.jk.model.ProductDTO;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	@Modifying
	@Query(value = "insert into highlights (highlight, product_id) values (:h, :productId)", nativeQuery = true)
	void addHighlight(String h, Long productId);

	@Query("select new com.jk.model.ProductDTO(p.id, p.productName, p.price, p.user.id, p.productName, p.category) from Product p")
	List<ProductDTO> getRandomProducts();

	@Query(value = "select highlight from highlights where product_id=:id", nativeQuery=true)
	List<String> getHighlightsById(Long id);
	
}
