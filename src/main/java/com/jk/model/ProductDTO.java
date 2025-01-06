package com.jk.model;

public class ProductDTO {

	private long id;
	private String productName;
	private int price;
	private long sellerId;
	private String imageurl;
	private String rating;



	public ProductDTO() {
	}

	public ProductDTO(long id, String productName, int price, long sellerId, String imageurl, String rating) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.sellerId = sellerId;
		this.imageurl = imageurl;
		this.rating = rating;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public ProductDTO id(long id) {
		setId(id);
		return this;
	}

	public ProductDTO productName(String productName) {
		setProductName(productName);
		return this;
	}

	public ProductDTO price(int price) {
		setPrice(price);
		return this;
	}

	public ProductDTO sellerId(long sellerId) {
		setSellerId(sellerId);
		return this;
	}

	public ProductDTO imageurl(String imageurl) {
		setImageurl(imageurl);
		return this;
	}

	public ProductDTO rating(String rating) {
		setRating(rating);
		return this;
	}

	

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", productName='" + getProductName() + "'" +
			", price='" + getPrice() + "'" +
			", sellerId='" + getSellerId() + "'" +
			", imageurl='" + getImageurl() + "'" +
			", rating='" + getRating() + "'" +
			"}";
	}
	
}
