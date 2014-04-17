package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;



@ManagedBean
public class CreerAdherentBean {

	
	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter @Setter
	Adherent nouveauAdherent;

	@Getter @Setter
	Adherent user;
	
	@PostConstruct
	public void init()
	{
		nouveauAdherent = facadeAdherent.newInstance();
	}
	
	
	/**
	 * On enregistre un nouvel adherent en base
	 */
	public void enregistrer(){
		//log.info(nouveauAdherent);  
		facadeAdherent.create(nouveauAdherent);
		nouveauAdherent = facadeAdherent.newInstance();
		JsfUtils.sendMessage(null, FacesMessage.SEVERITY_INFO, "Information", "L'adhérent à bien été ajouté");
	}
	

}
