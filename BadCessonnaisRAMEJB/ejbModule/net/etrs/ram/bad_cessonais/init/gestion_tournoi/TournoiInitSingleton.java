package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import lombok.Getter;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;



/**
 * Classe d'intialisation des Tournois.
 * @author adrien.merly
 *
 */
@Singleton
@Startup       //se lance au demarrage
public class TournoiInitSingleton {
	
	@EJB
	private FacadeTournoi facadeTournoi;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

	
//	@Getter
//	private static Tournoi tournoiPrincipal;
	
	
	/**
	 * Méthode d'intitialisation des tournois.
	 * @throws ParseException
	 */
	@PostConstruct
	public void init() throws ParseException{
		
		if(facadeTournoi.countAll() == 0){
			Tournoi tournoiPrincipal =  createTournoi("Tournoi de l'ascension",sdf.parse("29/05/2014"));
			facadeTournoi.create(tournoiPrincipal);
			facadeTournoi.create(createTournoi("Tournoi fou de bad",sdf.parse("29/05/2014")));
			facadeTournoi.create(createTournoi("Tournoi de noël",sdf.parse("20/12/2013")));
			facadeTournoi.create(createTournoi("Tournoi de Pâques",sdf.parse("19/04/2013")));
		}
	}

	/**
	 * Fonction utilitaire de création de tournoi.
	 * @param nom
	 * @param date
	 * @return
	 */
	private Tournoi createTournoi(String nom, Date date){
		Tournoi tournoi = facadeTournoi.newInstance();
		tournoi.setNom(nom);
		tournoi.setDateTournoi(date);
		return tournoi;
	}
	
	
	
}
