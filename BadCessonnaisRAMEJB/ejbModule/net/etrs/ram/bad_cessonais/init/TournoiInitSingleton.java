package net.etrs.ram.bad_cessonais.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.Tournoi;
import net.etrs.ram.bad_cessonais.sessions.FacadeTournoi;

@Singleton
@Startup       //se lance au demarrage
public class TournoiInitSingleton {
	
	@EJB
	private FacadeTournoi facadeTournoi;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@PostConstruct
	public void init() throws ParseException{
		
		
		if(facadeTournoi.countTournoi() == 0){
			facadeTournoi.ajouterTournoi(createTournoi("Tournoi de l'ascension",sdf.parse("29/05/2014")));
			facadeTournoi.ajouterTournoi(createTournoi("Tournoi fou de bad",sdf.parse("29/05/2014")));
			facadeTournoi.ajouterTournoi(createTournoi("Tournoi de noël",sdf.parse("20/12/2013")));
			facadeTournoi.ajouterTournoi(createTournoi("Tournoi de Pâques",sdf.parse("19/04/2013")));
		}
		

		
		
		
	}

	
	private Tournoi createTournoi(String nom, Date d){
		Tournoi t = new Tournoi();
		t.setNom(nom);
		t.setDateTournoi(d);
		return t;
	}
	
	
	
}
