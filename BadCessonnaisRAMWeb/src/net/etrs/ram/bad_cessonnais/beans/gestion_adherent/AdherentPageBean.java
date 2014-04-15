package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;



@ManagedBean
public class AdherentPageBean {

	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Adherent adherent;
	
	
	@PostConstruct		
	public void init(){
		adherent = facadeAdherent.newInstance();
	}
	

	public void enregistrerAdherent(){
		//facadeAdherent.ajouterAdherent(adherent);
	}
	
	
	public List<Adherent> getAdherents(){
		return facadeAdherent.readAll();
	
	}
	public void modifierAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);
		
		//return facadeAdherent.read(id);
	}
	public void desactiverAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);
		//return facadeAdherent.read(id);
	}	
	
	
}
