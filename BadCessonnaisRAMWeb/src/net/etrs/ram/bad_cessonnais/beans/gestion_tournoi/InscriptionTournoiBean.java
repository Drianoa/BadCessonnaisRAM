package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.JoueursPoule;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.ServiceGestionTournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

import org.primefaces.event.SelectEvent;

@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
public class InscriptionTournoiBean {
	
	@EJB
	FacadeTournoi facadeTournoi;
	
	@EJB
	FacadeJoueur facadeJoueur;
	
	@EJB
	ServiceGestionTournoi serviceGestionTournoi;
	
	@Getter
	@Setter
	Tournoi tournoi;
	
	String tournoiId;
	
	@Getter @Setter
	Joueur nouveauJoueur;
	
	@Getter @Setter
	Tableau tableauActif;
	
	@PostConstruct
	public void init(){
		tournoiId = (String) JsfUtils.getFromFlashScope("tournoi");
		
		refreshTournoi();
		nouveauJoueur = facadeJoueur.newInstance();
	}
	
	public JoueursPoule[] listeJoueursPoule(){
		return JoueursPoule.values();
	}
	
	public void supprimerJoueur(Tableau tableau, Joueur joueur){
		try{
			serviceGestionTournoi.supprimerJoueur(tableau,joueur);
			//----------------------------
			refreshTournoi();
			//---------------------------------
			JsfUtils.sendMessage(String.format("Le joueur %s %s à été supprimé", joueur.getNom(), joueur.getPrenom()));
		}catch(Exception exception){
			JsfUtils.sendMessage(exception);
		}
	}

	private void refreshTournoi() {
		tournoi =  facadeTournoi.read(tournoiId);
	}
	
	public void definirtableauActif(Tableau tableau){
		tableauActif = tableau;
	}
	
	public List<String> autoComplete(String licence){
		ArrayList<String > list = new ArrayList<>();
		for(Joueur joueur : facadeJoueur.autocompletionSansTableau(tableauActif, licence)){
			list.add(joueur.getLicenceFcd());
		}
		
		return list;
	}
	
	public void gererSelection(SelectEvent event){
		String licence = (String)event.getObject();
		nouveauJoueur = facadeJoueur.findJoueurByLicence(licence);
	}
	
	public void inscrireJoueur(){
		serviceGestionTournoi.inscrireJoueur(tableauActif,nouveauJoueur);
		nouveauJoueur = facadeJoueur.newInstance();
		refreshTournoi();
	}
	
	public void naviguerGererPoules(){
		facadeTournoi.update(tournoi);
		//serviceGestionTournoi.enregistrerTableaux(tournoi);
		serviceGestionTournoi.genererLesPoules(tournoi);
		
		JsfUtils.putInFlashScope("tournoi", tournoi);
	}
	
}
