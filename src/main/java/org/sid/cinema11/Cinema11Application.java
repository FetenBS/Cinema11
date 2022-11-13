package org.sid.cinema11;

import org.sid.cinema11.dao.FilmRepository;

import org.sid.cinema11.entities.Film;
import org.sid.cinema11.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.web.bind.annotation.CrossOrigin;
@Configuration
@SpringBootApplication
//@CrossOrigin(*)
public class Cinema11Application implements CommandLineRunner {
	
	@Autowired
private ICinemaInitService cinemaInitService;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration  ;
	public static void main(String[] args) {
		SpringApplication.run(Cinema11Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		/*repositoryRestConfiguration.exposeIdsFor(Film.class);
		repositoryRestConfiguration.getCorsRegistry()
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowedMethods("OPTIONS","HEAD","GET","PUT","POST","DELETE","PATCH");
	*/

		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
		
	}

}
