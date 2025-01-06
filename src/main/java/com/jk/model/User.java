package com.jk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password must be at least 6 characters")
    @Size(min = 6)
    private String password;

    @NotBlank(message = "Mobile must be 10 digits")
    @Size(min = 10, max = 10)
    private String mobile;

    @NotBlank(message = "Door is required")
    private String door;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;
	
	
	public User(long id, @NotBlank String username, @NotBlank @Size(min = 6) String password,
			@NotBlank @Size(min = 10, max = 10) String mobile, @NotBlank String door, @NotBlank String street,
			@NotBlank String city, @NotBlank String country, @NotBlank String state, @NotBlank String district) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.door = door;
		this.street = street;
		this.city = city;
		this.country = country;
		this.state = state;
		this.district = district;
	}
	public User() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mobile=" + mobile + ", door="
				+ door + ", street=" + street + ", city=" + city + ", country=" + country + ", state=" + state
				+ ", district=" + district + "]";
	}
	
	
}
