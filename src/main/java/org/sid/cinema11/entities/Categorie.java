package org.sid.cinema11.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Categorie {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Column(length=75)
private String name;
	
	
	public Categorie(Long id, String name, Collection<Film> films) {
	super();
	this.id = id;
	this.name = name;
	this.films = films;
}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Collection<Film> getFilms() {
		return films;
	}


	public void setFilms(Collection<Film> films) {
		this.films = films;
	}


	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}


	@OneToMany(mappedBy="categorie")
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Film> films;
}
