package com.udd.naucnacentrala.web.dto;

import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "magazines", type = "magazine", shards = 1)
public class MagazineDTO {

	@Id
	private Long id;
	private String title;
	private String author;
	private String keywords;
	private int publicationYear;
	private String filename;
	private String mime;
	private String category;
	private String language;
	private String content;
	private String highlight;
	
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
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getMime() {
		return mime;
	}
	
	public void setMime(String mime) {
		this.mime = mime;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getHighlight() {
		return highlight;
	}
	
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
	@Override
	public String toString() {
		return "MagazineDTO [id=" + id + ", title=" + title + ", author=" + author + ", keywords=" + keywords
				+ ", publicationYear=" + publicationYear + ", filename=" + filename + ", mime=" + mime + ", category="
				+ category + ", language=" + language + ", content=" + content + ", highlight=" + highlight + "]";
	}
}