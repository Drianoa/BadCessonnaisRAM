package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeEquipe;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;


@Startup
@Singleton
//@DependsOn(value={"JoueurInitSingleton"})
public class EquipeInitSingleton {
	
	@EJB
	private FacadeEquipe facadeEquipe;
	
	@EJB
	private FacadeJoueur facadeJoueur;
	
	@PostConstruct
	public void init(){
		if(facadeEquipe.countAll() == 0){
			List<Joueur> joueurs = facadeJoueur.readAll();
			creerEquipeDe1(joueurs);
			creerEquipeDe2(joueurs);
		}
	}

	private void creerEquipeDe2(List<Joueur> joueurs) {
		for(int i =0; i<10 ; i+=2){
			facadeEquipe.creerEquipe(joueurs.get(i), joueurs.get(i+1));
		}
	}

	private void creerEquipeDe1(List<Joueur> joueurs) {
		for(Joueur joueur : joueurs){
			facadeEquipe.creerEquipe(joueur);
		}
	}
	
}
