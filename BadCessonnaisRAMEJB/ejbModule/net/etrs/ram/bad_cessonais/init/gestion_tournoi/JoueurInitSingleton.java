package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;

@Singleton
@Startup
public class JoueurInitSingleton {

	@EJB
	private FacadeJoueur facadeJoueur;

	@PostConstruct
	public void init() {
		if (facadeJoueur.countAll() == 0) {
			for (int i = 0; i < 10; i++) {
				Joueur joueur = facadeJoueur.newInstance();
				joueur.setNom("JOUEUR_" + i);
				joueur.setPrenom("Joueur" + i);
				joueur.setAnneeNaissance(1980 + i);
				joueur.setClub("CLUB_" + i);
				joueur.setLicenceFcd("12345" + i);
				facadeJoueur.create(joueur);
			}
		}
	}

}
