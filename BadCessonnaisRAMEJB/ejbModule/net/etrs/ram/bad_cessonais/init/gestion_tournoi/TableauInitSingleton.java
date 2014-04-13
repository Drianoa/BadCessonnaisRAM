package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@Singleton
@Startup
@DependsOn(value={"TournoiInitSingleton" })
public class TableauInitSingleton {

	@EJB
	private FacadeTableau facadeTableau;
	
	@EJB
	private FacadeTournoi facadeTournoi;
	
	@PostConstruct
	public void init(){
		if(facadeTableau.countAll() < 0){
			Tournoi tournoi = facadeTournoi.getListTournoi().get(0);
			tournoi.getLstTableaux().add(facadeTableau.create("Veteran Homme Simple NC", TypeTableau.SIMPLE));
			tournoi.getLstTableaux().add(facadeTableau.create("Senior Femme Simple NC", TypeTableau.SIMPLE));
			tournoi.getLstTableaux().add(facadeTableau.create("Senior Double Mixte NC", TypeTableau.SIMPLE));
			facadeTournoi.update(tournoi);
			
		}
		
	}
	
	
}
