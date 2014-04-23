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

/**
 * Bean permettant d'inscrire un joueur au tournoi.
 * @author adrien.merly
 *
 */
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
	
	/**
	 * On initialise le bean avec le tournoi sélectionné. et un nouveau joueur.
	 */
	@PostConstruct
	public void init(){
		tournoiId = (String) JsfUtils.getFromFlashScope("tournoi");
		refreshTournoi();
		nouveauJoueur = facadeJoueur.newInstance();
	}
	
	public JoueursPoule[] listeJoueursPoule(){
		return JoueursPoule.values();
	}
	
	/**
	 * Fonction de suppression d'un joueur d'unn  tableau.
	 * @param tableau
	 * @param joueur
	 */
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

	/**
	 * Rafraichit le  tournoi.
	 */
	private void refreshTournoi() {
		tournoi =  facadeTournoi.read(tournoiId);
	}
	
	/**
	 * defini le tableau actif lorsqu'on veux ajouter un joueur.
	 * @param tableau
	 */
	public void definirtableauActif(Tableau tableau){
		tableauActif = tableau;
	}
	
	/**
	 * Autocompletion sur le numéro de licence d'un joueur déjà inscrit au tournoi.  
	 * @param licence
	 * @return
	 */
	public List<String> autoComplete(String licence){
		ArrayList<String > list = new ArrayList<>();
		for(Joueur joueur : facadeJoueur.autocompletionSansTableau(tableauActif, licence)){
			list.add(joueur.getLicenceFcd());
		}
		
		return list;
	}
	
	/**
	 * Complete les champs pour le joueur lors de la sélection.
	 * @param event
	 */
	public void gererSelection(SelectEvent event){
		String licence = (String)event.getObject();
		nouveauJoueur = facadeJoueur.findJoueurByLicence(licence);
	}
	
	/**
	 * Inscrit le joueur sur le tableau actif.
	 */
	public void inscrireJoueur(){
		serviceGestionTournoi.inscrireJoueur(tableauActif,nouveauJoueur);
		nouveauJoueur = facadeJoueur.newInstance();
		refreshTournoi();
	}
	
	/**
	 * Permet de passer à la vue suivante.
	 */
	public void naviguerGererPoules(){
		facadeTournoi.update(tournoi);
		serviceGestionTournoi.genererLesPoules(tournoi);
		
		JsfUtils.putInFlashScope("tournoi", tournoi);
	}
	
}
