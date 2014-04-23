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
	
	/**
	 * Initialisation
	 */
	@PostConstruct
	public void init(){
		
		if(facadeAdherent.readAll().isEmpty()){
			facadeAdherent.create(createAdherent("001122","QUIROUL","Pierre","21/05/1982",Sexe.HOMME,Droit.ADHERENT));
			facadeAdherent.create(createAdherent("125478","BAMBELL","Larry","22/09/1981",Sexe.HOMME,Droit.ADHERENT));
			facadeAdherent.create(createAdherent("258147","AUCHART","Alexandre","29/10/1982",Sexe.HOMME,Droit.ANIMATEUR));
			facadeAdherent.create(createAdherent("321987","MERLY","Adrien","01/01/1980",Sexe.HOMME,Droit.PRINCIPAL));
			facadeAdherent.create(createAdherent("456987","ROBERT","Manu","15/07/1975",Sexe.HOMME,Droit.PRESIDENT));			
			facadeAdherent.create(createAdherent("123978","STRAP","Jacques","12/12/1979",Sexe.HOMME,Droit.ADHERENT));
			facadeAdherent.create(createAdherent("456321","DUVAL","Paul","10/03/1978",Sexe.HOMME,Droit.ADHERENT));
			facadeAdherent.create(createAdherent("147852","SAROUL","Raul","25/04/1979",Sexe.HOMME,Droit.ADHERENT));
			facadeAdherent.create(createAdherent("369852","MOLIDA","Peter","05/11/1980",Sexe.HOMME,Droit.ADHERENT));
		}
		
	}

	/**
	 * Classe permettant de peupler des nouveaux adherents
	 * @param licenceFCD
	 * @param nom
	 * @param prenom
	 * @param dNaiss
	 * @param sexe
	 * @param droit
	 * @return
	 */
	private Adherent createAdherent(String licenceFCD,String nom,String prenom,String dNaiss,Sexe sexe,Droit droit){
		Adherent a = facadeAdherent.newInstance();
		a.setLicenceFcd(licenceFCD);
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
