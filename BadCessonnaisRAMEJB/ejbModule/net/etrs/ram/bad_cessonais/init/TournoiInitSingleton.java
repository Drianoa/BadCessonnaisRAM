package net.etrs.ram.bad_cessonais.init;

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
	
	@PostConstruct
	public void init(){
		
		
		if(facadeTournoi.getListTournoi().isEmpty()){
			facadeTournoi.ajouterTournoi(createTournoi("BELLEMAR"));
			facadeTournoi.ajouterTournoi(create("EMI"));
			facadeTournoi.ajouterTournoi(createMajor("SONY"));
			facadeTournoi.ajouterTournoi(createMajor("MyMajorCompany"));
		}
		

		
		
		
	}

	
	private Tournoi createTournoi(String nom, Date d){
		Tournoi t = new Tournoi();
		t.setNom(nom);
		t.setDateTournoi(d);
		return t;
	}
	
	
	
}
