package org.sid.cinema11.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Salle {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String name;
private double nombrePlace;

public Salle() {
	super();
	// TODO Auto-generated constructor stub
}


public Salle(Long id, String name, double nombrePlace, Cinema cinema, Collection<Projection> projections,
		Collection<Place> places) {
	super();
	this.id = id;
	this.name = name;
	this.nombrePlace = nombrePlace;
	this.cinema = cinema;
	this.projections = projections;
	this.places = places;
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


public double getNombrePlace() {
	return nombrePlace;
}


public void setNombrePlace(double nombrePlace) {
	this.nombrePlace = nombrePlace;
}


public Cinema getCinema() {
	return cinema;
}


public void setCinema(Cinema cinema) {
	this.cinema = cinema;
}


public Collection<Projection> getProjections() {
	return projections;
}


public void setProjections(Collection<Projection> projections) {
	this.projections = projections;
}


public Collection<Place> getPlaces() {
	return places;
}


public void setPlaces(Collection<Place> places) {
	this.places = places;
}


@ManyToOne
@JsonProperty(access=Access.WRITE_ONLY)
private Cinema cinema;

@OneToMany(mappedBy="salle")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection<Projection> projections;

@OneToMany(mappedBy="salle")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection<Place> places;

}
