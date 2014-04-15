package net.etrs.ram.bad_cessonais.init.gestion_adherents;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;

@Singleton
@Startup       //se lance au demarrage
public class AdherentInitSingleton {
	
	@EJB
	private FacadeAdherent facadeAdherent;
	
	@PostConstruct
	public void init(){
		
		if(facadeAdherent.readAll().isEmpty()){
			facadeAdherent.create(createAdherent("AUCHART","Alexandre","29/09/1982"));
			facadeAdherent.create(createAdherent("MERLY","Adrien","01/01/1980"));
			facadeAdherent.create(createAdherent("ROBERT","Manu","01/04/1970"));
			

		}
		
	}

	
	private Adherent createAdherent(String nom,String prenom,String dNaiss){
		Adherent a = new Adherent();
		a.setNom(nom);
		a.setPrenom(prenom);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			a.setDateNaissance(sdf.parse(dNaiss));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return a;
	}
	
	
	
}
