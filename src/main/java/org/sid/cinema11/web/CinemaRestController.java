package org.sid.cinema11.web;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.cinema11.dao.FilmRepository;
import org.sid.cinema11.dao.TicketRepository;
import org.sid.cinema11.entities.Film;
import org.sid.cinema11.entities.Ticket;
import org.sid.cinema11.service.CinemaInitServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//@RepositoryRestResource
//@RepositoryRestController

//import lombok.AllArgsConstructor;
import lombok.Data;
@RestController
@Data
@CrossOrigin("*")
public class CinemaRestController {
@Autowired
private FilmRepository filmRepository;

@Autowired
private TicketRepository  ticketRepository;
CinemaInitServiceImpl cinemaInitServiceImpl;
@GetMapping("/ListFilms")
public List<Film> getAllFilms(){
	return filmRepository.findAll();
}

@GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_PNG_VALUE)
public byte[] image(@PathVariable(name="id")Long id)throws Exception{
	Film f=filmRepository.findById(id).get();
	String photoName=f.getPhoto();
	File file=new File(System.getProperty("user.home")+"/ecom/images/"+photoName);
	//return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/images/"+f.getPhoto()));
	Path path=Paths.get(file.toURI());
	return Files.readAllBytes(path);
}
@PostMapping("/payerTickets")
@Transactional
public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
	List<Ticket> listTickets=new ArrayList<>();
	ticketForm.getTickets().forEach(idTicket->{
		//System.out.println(idTicket);
	Ticket ticket=ticketRepository.findById(idTicket).get();
	ticket.setNomClient(ticketForm.getNomClient());
	ticket.setReserve(true);
	ticket.setCodePayement(ticketForm.getCodePayement());
	//ticket.setId(ticketForm.getId());
	ticketRepository.save(ticket);
	listTickets.add(ticket);
});
return listTickets;
	}}
@Data 
class TicketForm{
	//private Long id;
	private Integer codePayement;
	private String nomClient;
	private List<Long> Tickets=new ArrayList<>();
}

