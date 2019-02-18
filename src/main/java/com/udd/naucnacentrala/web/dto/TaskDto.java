package com.udd.naucnacentrala.web.dto;

public class TaskDto {

	private String id;
	private String name;
	private String magazineId;
	private String magazineName;

	public TaskDto() {}
	public TaskDto(String id, String name, String magazineId, String magazineName) {
		super();
		this.id = id;
		this.name = name;
		this.magazineId = magazineId;
		this.magazineName = magazineName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getmagazineId() {
		return magazineId;
	}
	public void setmagazineId(String magazineId) {
		this.magazineId = magazineId;
	}
	public String getmagazineName() {
		return magazineName;
	}
	public void setmagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

}
