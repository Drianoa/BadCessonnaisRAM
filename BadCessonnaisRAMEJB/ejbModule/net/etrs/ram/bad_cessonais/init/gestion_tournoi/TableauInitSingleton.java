package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Equipe;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeMatch;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeEquipe;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@Singleton
@Startup
@DependsOn(value={"TournoiInitSingleton","EquipeInitSingleton" })
public class TableauInitSingleton {
	
	@EJB
	private FacadeTableau facadeTableau;
	
	@EJB
	private FacadeEquipe facadeEquipe;
	
	@EJB
	private FacadeTournoi facadeTournoi;
	
	
	
	@PostConstruct
	public void init(){
		if(facadeTableau.countAll() == 0){
			Tableau ts = facadeTableau.create("Veteran Homme Simple NC", TypeMatch.SIMPLE);
			Tableau td = facadeTableau.create("Senior Double Mixte NC", TypeMatch.DOUBLE);
			associerTableauSimple(ts);
			associerTableauDouble(td);
			
			Tournoi tournoi = getTournoiPrincipal();
			
			tournoi.getLstTableaux().add(ts);
			tournoi.getLstTableaux().add(td);
			facadeTournoi.update(tournoi);
		}
	}



	private Tournoi getTournoiPrincipal() {
		
		return facadeTournoi.search("nom", "Tournoi de l'ascension", "nom").get(0);
	}



	private void associerTableauDouble(Tableau td) {
		List<Equipe> listerEquipes = facadeEquipe.listerEquipes(TypeMatch.SIMPLE);
		for (Equipe equipe : listerEquipes) {
			facadeTableau.ajouterEquipe(td, equipe);
		}
		
	}



	private void associerTableauSimple(Tableau ts) {
		List<Equipe> listerEquipes = facadeEquipe.listerEquipes(TypeMatch.DOUBLE);
		for (Equipe equipe : listerEquipes) {
			facadeTableau.ajouterEquipe(ts, equipe);
		}
		
	}
	
	
}
