package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.JoueursPoule;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.ServiceGestionTournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
@Log4j
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

	//	@Getter
	//	@Setter
	//	@ManagedProperty("#{param}")
	private int x;

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

	public void deplacerJoueur() {  
		try{
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String idPouleSource = params.get("pouleSource");
			String idPouleDest= params.get("pouleDest");
			String idJoueur= params.get("joueur");

			serviceGestionTournoi.deplacerJoueur(idPouleSource, idPouleDest, idJoueur);
		}catch (Exception e) {
			log.error("Erreur lors de la mise à jour des poules", e);
			JsfUtils.sendMessage("growl", "Erreur lors de la mise à jour des poules");
		}
	}  
	
}
