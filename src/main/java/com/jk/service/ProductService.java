package com.jk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.model.Product;
import com.jk.model.ProductDTO;
import com.jk.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productrepo;
	
	public Product saveProduct(Product product) {
		return productrepo.save(product);
	}

	@Transactional
	public void addHighlights(Long productId, String[] highlight) {
		// TODO Auto-generated method stub
		for (String h : highlight) {
			productrepo.addHighlight(h, productId);
		}
	}

	public List<ProductDTO> getRandomProducts() {
		// TODO Auto-generated method stub
		return productrepo.getRandomProducts();
	}

	public Product getProductById(Long id){
		Optional<Product> op = productrepo.findById(id);
		if (op.isPresent()){
			return op.get();
		}
		return null;
	}

    public List<String> getHighlightsById(long id) {
		return productrepo.getHighlightsById(id);
    }


}
