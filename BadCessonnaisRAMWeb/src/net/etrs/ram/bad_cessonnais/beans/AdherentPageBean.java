package net.etrs.ram.bad_cessonnais.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.Adherent;
import net.etrs.ram.bad_cessonais.sessions.FacadeAdherent;



@ManagedBean
public class AdherentPageBean {

	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter @Setter
	Adherent adherent;
	
	
	@PostConstruct		
	public void init(){
		adherent = facadeAdherent.newAdherent();
	}
	

	public void enregistrerAdherent(){
		
	}
	
	
	public List<Adherent> getAdherent(){
		return facadeAdherent.getListAdherent();
	
	}
	
	
}