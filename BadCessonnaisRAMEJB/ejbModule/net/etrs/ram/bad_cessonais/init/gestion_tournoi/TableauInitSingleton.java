package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeMatch;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@Singleton
@Startup
@DependsOn(value={"TournoiInitSingleton","JoueurInitSingleton"})
public class TableauInitSingleton {
	
	@EJB
	private FacadeTableau facadeTableau;
	
	@EJB
	private FacadeJoueur facadeJoueur;
	
	@EJB
	private FacadeTournoi facadeTournoi;
	
	
	
	@PostConstruct
	public void init(){
		if(facadeTableau.countAll() == 0){
			Tableau ts = facadeTableau.create("Veteran Homme Simple NC", TypeMatch.SIMPLE);
			//Tableau td = facadeTableau.create("Senior Double Mixte NC", TypeMatch.DOUBLE);
			associerTableauSimple(ts);
			//associerTableauDouble(td);
			
			
			Tournoi tournoi = getTournoiPrincipal();
			
			tournoi.getLstTableaux().add(ts);
			//tournoi.getLstTableaux().add(td);
			facadeTournoi.update(tournoi);
		}
	}


	/**
	 * retourne le tournoi principal
	 * @return
	 */
	private Tournoi getTournoiPrincipal() {
		return facadeTournoi.search("nom", "Tournoi de l'ascension", "nom").get(0);
	}

	/**
	 * Ajouter un joueur à un tableau
	 * @param ts
	 */
	private void associerTableauSimple(Tableau ts) {
		List<Joueur> listerJoueurs = facadeJoueur.readAll();
		for (Joueur joueur : listerJoueurs) {
			facadeTableau.ajouterEquipe(ts, joueur);
		}
		
	}
	
	
}
