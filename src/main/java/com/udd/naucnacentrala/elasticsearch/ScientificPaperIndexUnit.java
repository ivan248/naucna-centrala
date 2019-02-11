package com.udd.naucnacentrala.elasticsearch;

import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "scientificpaper", type = "paper", shards = 1)
public class ScientificPaperIndexUnit {

	@Id
	private Long id;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String title;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String author;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String keywords;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String pdfText;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String magazine;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String abstractDescription;

	@Field(type = FieldType.Text, analyzer = "serbian-analyzer")
	@JsonProperty
	private String scientificArea;

	@Field(type = FieldType.Nested)
	@JsonProperty
	private List<UserElasticSearchDTO> coAuthors;

	@Field(type = FieldType.Nested)
	@JsonProperty
	private List<UserElasticSearchDTO> reviewers;
	
	@GeoPointField
	@JsonProperty
	private GeoPoint geo_point;
	
	private Double price;
	
	private boolean openAccess;
	
	public ScientificPaperIndexUnit() {

	}

	public ScientificPaperIndexUnit(Long id, String title, String author, String keywords, String pdfText,
			String magazine, String abstractDescription, String scientificArea, List<UserElasticSearchDTO> coAuthors,
			List<UserElasticSearchDTO> reviewers, GeoPoint geo_point, Double price, boolean openAccess) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.pdfText = pdfText;
		this.magazine = magazine;
		this.abstractDescription = abstractDescription;
		this.scientificArea = scientificArea;
		this.coAuthors = coAuthors;
		this.reviewers = reviewers;
		this.geo_point = geo_point;
		this.price = price;
		this.openAccess = openAccess;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAbstractDescription() {
		return abstractDescription;
	}

	public void setAbstractDescription(String abstractDescription) {
		this.abstractDescription = abstractDescription;
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

	public GeoPoint getLocation() {
		return geo_point;
	}

	public void setLocation(GeoPoint location) {
		this.geo_point = location;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ScientificPaperIndexUnit [id=" + id + ", title=" + title + ", author=" + author + ", keywords="
				+ keywords + ", pdfText=" + pdfText + ", magazine=" + magazine + ", abstractDescription="
				+ abstractDescription + ", scientificArea=" + scientificArea + ", coAuthors=" + coAuthors
				+ ", reviewers=" + reviewers + ", geo_point=" + geo_point + ", price=" + price + "]";
	}

	public boolean isOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
	}

}