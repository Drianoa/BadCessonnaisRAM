package net.etrs.ram.bad_cessonais.init.gestion_tournoi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.administration.Droit;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;
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
			
				
				facadeJoueur.create(createJoueur("001122","QUIROUL","Pierre",1982,"Cesson"));
				facadeJoueur.create(createJoueur("125478","BAMBELL","Larry",1981,"Cesson"));
				facadeJoueur.create(createJoueur("258147","AUCHART","Alexandre",1982,"Cesson"));
				facadeJoueur.create(createJoueur("321987","MERLY","Adrien",1980,"Cesson"));
				facadeJoueur.create(createJoueur("456987","ROBERT","Manu",1975,"Cesson" ));			
				facadeJoueur.create(createJoueur("123978","STRAP","Jacques",1979,"Cesson"));
				facadeJoueur.create(createJoueur("456321","DUVAL","Paul",1978,"Cesson"));
				facadeJoueur.create(createJoueur("147852","SAROUL","Raul",1979,"Cesson"));
				facadeJoueur.create(createJoueur("369852","MOLIDA","Peter",1980,"Cesson"));
			
		}
	}

	private Joueur createJoueur(String string, String string2,
			String string3, Integer annee, String string5) {
		
		Joueur joueur = facadeJoueur.newInstance();
		joueur.setNom(string2);
		joueur.setPrenom(string3);
		joueur.setAnneeNaissance(annee);
		joueur.setClub(string5);
		joueur.setLicenceFcd(string);
		facadeJoueur.create(joueur);
		return joueur;
	}

}
