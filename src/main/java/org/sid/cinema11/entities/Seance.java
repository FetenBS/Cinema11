package org.sid.cinema11.entities;

import java.sql.Time;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Seance {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@javax.persistence.Temporal(TemporalType.TIME)
private Date heureDebut;
public Seance(Long id, Date heureDebut) {
	super();
	this.id = id;
	this.heureDebut = heureDebut;
}
public Seance() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Date getHeureDebut() {
	return heureDebut;
}
public void setHeureDebut(Date heureDebut) {
	this.heureDebut = heureDebut;
}

}
