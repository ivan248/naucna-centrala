package com.udd.naucnacentrala.elasticsearch;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "scientificpaper", type = "paper", shards = 1)
public class ScientificPaperDTO {

	@Id
	private Long id;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String title;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String author;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String keywords;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String pdfText;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String magazine;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String abstractDescrption;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String scientificArea;

	@Field(type = FieldType.Nested)
	@JsonProperty
	private List<UserElasticSearchDTO> coAuthors;

	@Field(type = FieldType.Nested)
	@JsonProperty
	private List<UserElasticSearchDTO> reviewers;

	public ScientificPaperDTO() {

	}

	public ScientificPaperDTO(Long id, String title, String author, String keywords, String pdfText, String magazine,
			String abstractDescrption, String scientificArea, List<UserElasticSearchDTO> coAuthors,
			List<UserElasticSearchDTO> reviewers) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.pdfText = pdfText;
		this.magazine = magazine;
		this.abstractDescrption = abstractDescrption;
		this.scientificArea = scientificArea;
		this.coAuthors = coAuthors;
		this.reviewers = reviewers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String getMagazine() {
		return magazine;
	}

	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}

	public String getAbstractDescrption() {
		return abstractDescrption;
	}

	public void setAbstractDescrption(String abstractDescrption) {
		this.abstractDescrption = abstractDescrption;
	}

	public String getScientificArea() {
		return scientificArea;
	}

	public void setScientificArea(String scientificArea) {
		this.scientificArea = scientificArea;
	}

	public List<UserElasticSearchDTO> getCoAuthors() {
		return coAuthors;
	}

	public void setCoAuthors(List<UserElasticSearchDTO> coAuthors) {
		this.coAuthors = coAuthors;
	}

	public List<UserElasticSearchDTO> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<UserElasticSearchDTO> reviewers) {
		this.reviewers = reviewers;
	}

	@Override
	public String toString() {
		return "MagazineDTO [id=" + id + ", title=" + title + ", author=" + author + ", keywords=" + keywords
				+ ", pdfText=" + pdfText + ", magazine=" + magazine + ", abstractDescrption=" + abstractDescrption
				+ ", scientificArea=" + scientificArea + ", coAuthors=" + coAuthors + ", reviewers=" + reviewers + "]";
	}

}