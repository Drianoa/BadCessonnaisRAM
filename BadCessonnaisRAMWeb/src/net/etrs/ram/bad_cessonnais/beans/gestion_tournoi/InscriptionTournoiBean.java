package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.JoueursPoule;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
public class InscriptionTournoiBean {
	
	@EJB
	FacadeTournoi facadeTournoi;
	
	@Getter
	@Setter
	Tournoi tournoi;
	
	@PostConstruct
	public void init(){
		//tournoi = (Tournoi) JsfUtils.getFromFlashScope("tournoi");
		tournoi =  facadeTournoi.search("nom", "Tournoi de l'ascension", "nom").get(0);
	}
	
	public JoueursPoule[] listeJoueursPoule(){
		return JoueursPoule.values();
	}
	
}
