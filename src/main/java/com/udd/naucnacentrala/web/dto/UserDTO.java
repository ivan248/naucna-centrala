package com.udd.naucnacentrala.web.dto;

import java.util.Set;

import com.udd.naucnacentrala.domain.UserRole;

public class UserDTO {

	private String email;
	private String password;
	private UserRole userRole;
	private String title;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private Set<String> scientificAreas;
	
	public UserDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<String> getScientificAreas() {
		return scientificAreas;
	}

	public void setScientificAreas(Set<String> scientificAreas) {
		this.scientificAreas = scientificAreas;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + ", userRole=" + userRole + ", title=" + title
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", state=" + state
				+ ", scientificAreas=" + scientificAreas + "]";
	}
	
}
