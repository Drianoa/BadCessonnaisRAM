package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
public class CreerTournoiBean {
	
	Tournoi nouveauTournoi;
	
	@EJB
	FacadeTournoi facadeTournoi;
	
	@PostConstruct
	public void init(){
		nouveauTournoi = facadeTournoi.newInstance();
	}
	
	
}
