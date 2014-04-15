package net.etrs.ram.bad_cessonais.init.gestion_adherents;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.etrs.ram.bad_cessonais.entities.administration.Droit;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;

@Singleton
@Startup       //se lance au demarrage
public class AdherentInitSingleton {
	
	@EJB
	private FacadeAdherent facadeAdherent;
	
	@PostConstruct
	public void init(){
		
		if(facadeAdherent.readAll().isEmpty()){
			facadeAdherent.create(createAdherent("AUCHART","Alexandre","29/09/1982",Sexe.HOMME,Droit.ANIMATEUR));
			facadeAdherent.create(createAdherent("MERLY","Adrien","01/01/1980",Sexe.HOMME,Droit.PRINCIPAL));
			facadeAdherent.create(createAdherent("ROBERT","Manu","05/11/1979",Sexe.HOMME,Droit.PRESIDENT));			

		}
		
	}

	
	private Adherent createAdherent(String nom,String prenom,String dNaiss,Sexe sexe,Droit droit){
		Adherent a = new Adherent();
		a.setNom(nom);
		a.setPrenom(prenom);
		a.setSexe(sexe);
		a.setDroit(droit);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			a.setDateNaissance(sdf.parse(dNaiss));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return a;
	}
	
	
	
}
