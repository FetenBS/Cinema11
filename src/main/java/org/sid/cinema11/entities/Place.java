package org.sid.cinema11.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Place {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private int numero;
private double longitude,latitude,altitude;

public Place() {
	super();
	// TODO Auto-generated constructor stub
}

public Place(Long id, int numero, double longitude, double latitude, double altitude, Collection<Ticket> tickets,
		Salle salle) {
	super();
	this.id = id;
	this.numero = numero;
	this.longitude = longitude;
	this.latitude = latitude;
	this.altitude = altitude;
	this.tickets = tickets;
	this.salle = salle;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public int getNumero() {
	return numero;
}

public void setNumero(int numero) {
	this.numero = numero;
}

public double getLongitude() {
	return longitude;
}

public void setLongitude(double longitude) {
	this.longitude = longitude;
}

public double getLatitude() {
	return latitude;
}

public void setLatitude(double latitude) {
	this.latitude = latitude;
}

public double getAltitude() {
	return altitude;
}

public void setAltitude(double altitude) {
	this.altitude = altitude;
}

public Collection<Ticket> getTickets() {
	return tickets;
}

public void setTickets(Collection<Ticket> tickets) {
	this.tickets = tickets;
}

public Salle getSalle() {
	return salle;
}

public void setSalle(Salle salle) {
	this.salle = salle;
}

@OneToMany(mappedBy="place")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection <Ticket> tickets;
@ManyToOne
private Salle salle;

}
