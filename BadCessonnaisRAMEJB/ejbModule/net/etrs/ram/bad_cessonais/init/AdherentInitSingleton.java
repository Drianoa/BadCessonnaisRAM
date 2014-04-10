package net.etrs.ram.bad_cessonais.init;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.Adherent;
import net.etrs.ram.bad_cessonais.sessions.FacadeAdherent;

@Singleton
@Startup       //se lance au demarrage
public class AdherentInitSingleton {
	
	@EJB
	private FacadeAdherent facadeAdherent;
	
	@PostConstruct
	public void init(){
		
		
		if(facadeAdherent.getListAdherent().isEmpty()){
			facadeAdherent.ajouterAdherent(createAdherent("AUCHART"));
			facadeAdherent.ajouterAdherent(createAdherent("ROBERT"));
			facadeAdherent.ajouterAdherent(createAdherent("MERLY"));
			facadeAdherent.ajouterAdherent(createAdherent("BELLEMAR"));
		}
		
	}

	
	private Adherent createAdherent(String nom){
		Adherent a = new Adherent();
		a.setNom(nom);
		return a;
	}
	
	
	
}