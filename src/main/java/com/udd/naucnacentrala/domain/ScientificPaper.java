package com.udd.naucnacentrala.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ScientificPaper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NotEmpty
	private String title;
	
	private String keywords;
	
	private String abstractDescription;
	
	@Lob
	private String pdf;
	
	@JoinTable(name = "scientific_paper_co_authors")
	@ManyToMany
	private Set<User> coAuthors;
		
	@ManyToOne(optional = false)
	@JoinColumn(name="scientific_area_id")
	@JsonIgnore
	private ScientificArea scientificArea;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="author_id")
	@JsonIgnore
	private User author;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="magazine_id")
	@JsonIgnore
	private Magazine magazine;
	
	public ScientificPaper() {
		
	}
	
	public ScientificPaper(Long id, @NotEmpty String title, String keywords, String abstractDescription, String pdf,
			Set<User> coAuthors, ScientificArea scientificArea, User author,
			Magazine magazine) {
		super();
		this.id = id;
		this.title = title;
		this.keywords = keywords;
		this.abstractDescription = abstractDescription;
		this.pdf = pdf;
		this.coAuthors = coAuthors;
		this.scientificArea = scientificArea;
		this.author = author;
		this.magazine = magazine;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAbstractDescription() {
		return abstractDescription;
	}

	public void setAbstractDescription(String abstractDescription) {
		this.abstractDescription = abstractDescription;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Set<User> getCoAuthors() {
		return coAuthors;
	}

	public void setCoAuthors(Set<User> coAuthors) {
		this.coAuthors = coAuthors;
	}

	public ScientificArea getScientificArea() {
		return scientificArea;
	}

	public void setScientificArea(ScientificArea scientificArea) {
		this.scientificArea = scientificArea;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

}
