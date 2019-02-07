package com.udd.naucnacentrala.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Magazine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	private int ISSN;
	
	@ManyToMany
	private Set<ScientificArea> scientificAreas;
	
	private PaymentType paymentType;
	
	/*
	 * ID that the bank gives to the merchant when registering for online payment
	 */
	@Pattern(regexp = "\\w{1,30}")
	private String merchantId;
	
	// recenzenti
	@JoinTable(name ="magazine_reviewers")
	@ManyToMany
	private Set<User> reviewers;
	
	// recenzenti
	@JoinTable(name ="magazine_editors")
	@ManyToMany
	private Set<User> editorsOfSpecificAreas;
	
	@NotNull
	private double price;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="main_editor_id")
	@NotNull
	@JsonIgnore
	private User mainEditor;
	
	public Magazine() {
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getISSN() {
		return ISSN;
	}

	public void setISSN(int iSSN) {
		ISSN = iSSN;
	}

	public Set<ScientificArea> getScientificAreas() {
		return scientificAreas;
	}

	public void setScientificAreas(Set<ScientificArea> scientificAreas) {
		this.scientificAreas = scientificAreas;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Set<User> getReviewers() {
		return reviewers;
	}

	public void setReviewers(Set<User> reviewers) {
		this.reviewers = reviewers;
	}

	public Set<User> getEditorsOfSpecificAreas() {
		return editorsOfSpecificAreas;
	}

	public void setEditorsOfSpecificAreas(Set<User> editorsOfSpecificAreas) {
		this.editorsOfSpecificAreas = editorsOfSpecificAreas;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getMainEditor() {
		return mainEditor;
	}

	public void setMainEditor(User mainEditor) {
		this.mainEditor = mainEditor;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
}
