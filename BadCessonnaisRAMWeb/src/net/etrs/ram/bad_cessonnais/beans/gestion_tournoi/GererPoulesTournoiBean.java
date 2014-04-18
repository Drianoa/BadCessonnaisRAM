package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.JoueursPoule;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.ServiceGestionTournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
public class GererPoulesTournoiBean {
	
	@EJB
	FacadeTournoi facadeTournoi;
	
	@EJB
	FacadeJoueur facadeJoueur;
	
	@EJB
	ServiceGestionTournoi serviceGestionTournoi;
	
	@Getter
	@Setter
	Tournoi tournoi;	
	
	@PostConstruct
	public void init(){
		refreshTournoi();
	}
	
	public JoueursPoule[] listeJoueursPoule(){
		return JoueursPoule.values();
	}
	

	private void refreshTournoi() {
		tournoi =  facadeTournoi.search("nom", "Tournoi de l'ascension", "nom").get(0);
	}
	
	 public void onCarDrop(DragDropEvent ddEvent) {  
	     System.out.println(ddEvent.getData());  
	}  
}
