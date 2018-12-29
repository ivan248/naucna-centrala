package com.udd.naucnacentrala.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ScientificArea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotEmpty
	private String scientificAreaName;
	
	public ScientificArea() {
		
	}

	public Long getId() {
		return id;
	}

	public String getScientificAreaName() {
		return scientificAreaName;
	}

	public void setScientificAreaName(String scientificAreaName) {
		this.scientificAreaName = scientificAreaName;
	}
	
	
}
