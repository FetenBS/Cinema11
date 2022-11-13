package org.sid.cinema11.service;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema11.dao.CategorieRepository;
import org.sid.cinema11.dao.CinemaRepository;
import org.sid.cinema11.dao.FilmRepository;
import org.sid.cinema11.dao.PlaceRepository;
import org.sid.cinema11.dao.ProjectionRepository;
import org.sid.cinema11.dao.SalleRepository;
import org.sid.cinema11.dao.SeanceRepository;
import org.sid.cinema11.dao.TicketRepository;
import org.sid.cinema11.dao.VilleRepository;
import org.sid.cinema11.entities.Categorie;
import org.sid.cinema11.entities.Cinema;
import org.sid.cinema11.entities.Film;
import org.sid.cinema11.entities.Place;
import org.sid.cinema11.entities.Projection;
import org.sid.cinema11.entities.Salle;
import org.sid.cinema11.entities.Seance;
import org.sid.cinema11.entities.Ticket;
import org.sid.cinema11.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor @AllArgsConstructor
@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
	@Autowired private VilleRepository villeRepository;
	@Autowired private CinemaRepository cinemaRepository;
	@Autowired private SalleRepository salleRepository;
	@Autowired private PlaceRepository placeRepository;
	@Autowired private SeanceRepository seanceRepository;
	@Autowired private TicketRepository ticketRepository;
	@Autowired private CategorieRepository categorieRepository;
	@Autowired private ProjectionRepository projectionRepository;
	@Autowired private FilmRepository filmRepository;
	@Override
	public void initVilles() {
	Stream.of("Casa","Marrakech","Rabat","Tanger").forEach(nameVille->{
	Ville ville=new Ville();
	ville.setName(nameVille);
	villeRepository.save(ville);
	});

	}
	@Override
	public void initCinemas() {
	villeRepository.findAll().forEach(v->{
		Stream.of("MegaRama","Founoun","Daouliz","Hamra","Chahrazed")
		.forEach(nameCinema->{
			Cinema cinema=new Cinema();
			cinema.setName(nameCinema);
			cinema.setNombreSalles(3+(int)(Math.random()*7));
			cinema.setVille(v);
			cinemaRepository.save(cinema);
		
		});});}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++)    {
			
				Salle salle=new Salle();
				salle.setName("Salle"+(i+1));
				salle.setCinema(cinema);
			    salle.setNombrePlace(20+(int)(Math.random()*10));
			    salleRepository.save(salle);
			}});}

	@Override
	public void initPlaces() {
	salleRepository.findAll().forEach(salle->{
		for(int i=0;i<salle.getNombrePlace();i++) {
			Place place=new Place();
			place.setNumero(i+1);
			place.setSalle(salle);
			placeRepository.save(place);
		}
	});
		
	}

	@Override
	public void initSeances() {
		DateFormat df= new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();
			try {
			
			seance.setHeureDebut(df.parse(s));
			seanceRepository.save(seance);	
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		}
			);
		
	}

	@Override
	public void initCategories() {
		Stream.of("Drama","Romantique","Fiction","Action").forEach(nameCategorie->{
			Categorie categorie=new Categorie();
			categorie.setName(nameCategorie);
			categorieRepository.save(categorie);
			});
		
	}

	@Override
	public void initFilms() {
		double duree[]=new double[] {1,1.5,2,2.5,3};
		List<Categorie> categories=categorieRepository.findAll();
	    Stream.of("H","pride&prejudice","titanic","zoro").forEach(titreFilm->{
			Film film=new Film();
			film.setTitre(titreFilm);
			film.setDuree(duree[new Random().nextInt(duree.length)]);
		    film.setPhoto(titreFilm.replaceAll(" ","")+".png");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		})	;
		//});
		
}

	@Override
	public void initProjections() {
		double prices[]=new double[] {30,50,60,70,100};
		villeRepository.findAll().forEach(ville->{
	ville.getCinemas().forEach(cinema->{
		cinema.getSalles().forEach(salle->{
			filmRepository.findAll().forEach(film->{
				seanceRepository.findAll().forEach(seance->{
		
			Date d=new Date();
			Projection projection=new Projection();
			projection.setFilm(film);
			projection.setSalle(salle);
			projection.setSeance(seance);
			projection.setPrix(prices[new Random().nextInt(prices.length)]);
			projection.setDateprojection(d);
			projectionRepository.save(projection);
			});	});
		});});});
	}
	

	@Override
	public void initTickets() {
		double prices[]=new double[] {30,50,60,70,100};
		//String nomClient={"Ali","SAM","doda"};
		projectionRepository.findAll().forEach(p ->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setProjection(p);
				//ticket.setNomClient(nomClient);
				ticket.setPrix(p.getPrix());
				ticket.setReserve(false);
				ticketRepository.save(ticket);
				
			});
		});
		
	}

}
