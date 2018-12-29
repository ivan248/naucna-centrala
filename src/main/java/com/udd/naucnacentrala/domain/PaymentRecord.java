package com.udd.naucnacentrala.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class PaymentRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	private PaymentType paymentType;

	// type of paying item + it`s id => example : Magazine:012, Subscription:15...
	private String payedItemID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="user_id")
	@NotNull
	@JsonIgnore
	private User user;
	
	public PaymentRecord() {
		
	}

	public Long getId() {
		return id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getPayedItemID() {
		return payedItemID;
	}

	public void setPayedItemID(String payedItemID) {
		this.payedItemID = payedItemID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
