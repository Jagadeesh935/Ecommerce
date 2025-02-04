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

	private String roles;

	public User() {
	}

	public User(long id, String username, String password, String mobile, String door, String street, String city, String country, String state, String district, String roles) {
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
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public @NotBlank(message = "Username is required") String getUsername() {
		return username;
	}

	public void setUsername(@NotBlank(message = "Username is required") String username) {
		this.username = username;
	}

	public @NotBlank(message = "Password must be at least 6 characters") @Size(min = 6) String getPassword() {
		return password;
	}

	public void setPassword(@NotBlank(message = "Password must be at least 6 characters") @Size(min = 6) String password) {
		this.password = password;
	}

	public @NotBlank(message = "Mobile must be 10 digits") @Size(min = 10, max = 10) String getMobile() {
		return mobile;
	}

	public void setMobile(@NotBlank(message = "Mobile must be 10 digits") @Size(min = 10, max = 10) String mobile) {
		this.mobile = mobile;
	}

	public @NotBlank(message = "Door is required") String getDoor() {
		return door;
	}

	public void setDoor(@NotBlank(message = "Door is required") String door) {
		this.door = door;
	}

	public @NotBlank(message = "Street is required") String getStreet() {
		return street;
	}

	public void setStreet(@NotBlank(message = "Street is required") String street) {
		this.street = street;
	}

	public @NotBlank(message = "City is required") String getCity() {
		return city;
	}

	public void setCity(@NotBlank(message = "City is required") String city) {
		this.city = city;
	}

	public @NotBlank(message = "Country is required") String getCountry() {
		return country;
	}

	public void setCountry(@NotBlank(message = "Country is required") String country) {
		this.country = country;
	}

	public @NotBlank(message = "State is required") String getState() {
		return state;
	}

	public void setState(@NotBlank(message = "State is required") String state) {
		this.state = state;
	}

	public @NotBlank(message = "District is required") String getDistrict() {
		return district;
	}

	public void setDistrict(@NotBlank(message = "District is required") String district) {
		this.district = district;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", mobile='" + mobile + '\'' +
				", door='" + door + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", district='" + district + '\'' +
				", roles='" + roles + '\'' +
				'}';
	}
}
