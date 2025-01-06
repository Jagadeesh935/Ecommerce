package com.jk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String productName;
	
	private int price;
	@NotBlank
	private String category;

	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "sellerId", nullable = false)
	private User user;

	public Product() {
	}

	public Product(long id, String productName, int price, String category, int quantity, User user) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				", quantity=" + quantity +
				", user=" + user +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public @NotBlank String getProductName() {
		return productName;
	}

	public void setProductName(@NotBlank String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public @NotBlank String getCategory() {
		return category;
	}

	public void setCategory(@NotBlank String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
