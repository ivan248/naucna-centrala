package com.udd.naucnacentrala.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Pattern(regexp = "^[A-Za-z]+$")
	private String firstName;
	
	@Pattern(regexp = "^[A-Za-z]+$")
	private String lastName;

	@Email
	@Column(unique = true)
	@Size(min = 1, max = 60)
	@NotEmpty
	private String email;
	
    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
	private String password;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "id")})
    private List<Authority> authorities;;
	
	private String city;
	
	private String state;
	
	// titula
	private String title;
	
	@ManyToMany
	private Set<ScientificArea> scientificAreas;
	
	public User() {
		
	}

	public User(@Pattern(regexp = "^[A-Za-z]+$") String firstName, @Pattern(regexp = "^[A-Za-z]+$") String lastName,
			@Email @Size(min = 1, max = 60) @NotEmpty String email, @NotEmpty String password,
			List<com.udd.naucnacentrala.domain.Authority> authorities, String city, String state, String title,
			Set<ScientificArea> scientificAreas) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.city = city;
		this.state = state;
		this.title = title;
		this.scientificAreas = scientificAreas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<ScientificArea> getScientificAreas() {
		return scientificAreas;
	}

	public void setScientificAreas(Set<ScientificArea> scientificAreas) {
		this.scientificAreas = scientificAreas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", authorities=" + authorities + ", city=" + city + ", state=" + state
				+ ", title=" + title + ", scientificAreas=" + scientificAreas + "]";
	}

}
