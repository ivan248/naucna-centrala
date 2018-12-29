package com.udd.naucnacentrala.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class UserElasticSearchDTO {

	@Field(type = FieldType.Text)
	private String firstName;

	@Field(type = FieldType.Text)
	private String lastName;

	@Field(type = FieldType.Keyword)
	private String email;

	public UserElasticSearchDTO() {

	}

	public UserElasticSearchDTO(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserElasticSearchDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
