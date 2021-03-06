package net.etrs.ram.bad_cessonais.services.gestion_tournoi;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Poule;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadePoule;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTableau;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;

@SuppressWarnings("serial")
@Stateless
public class ServiceGestionTournoi implements Serializable{
	
	@EJB
	private FacadeTournoi facadeTournoi;
	
	@EJB
	private FacadeTableau facadeTableau;
	
	@EJB
	private FacadeJoueur facadeJoueur;
	
	@EJB
	private FacadePoule facadePoule;
	
	@PersistenceContext
	EntityManager em;
	
	
	/**
	 * méthode créant un tournoi
	 * @param tournoi
	 * @param tableaux
	 * @return
	 */
	public Tournoi creerTournoi(Tournoi tournoi , List<Tableau> tableaux){
		facadeTournoi.create(tournoi);
		tournoi.getLstTableaux().addAll(tableaux);
		facadeTournoi.update(tournoi);
		return tournoi;
	}


	/**
	 * méthode pour supprimer un joueur d'un tableau
	 * @param tableau
	 * @param joueur
	 */
	public void supprimerJoueur(Tableau tableau, Joueur joueur) {
		facadeTableau.supprimerJoueur(tableau,joueur);
	}


	
	/**
	 * Mets à jour et/ou ajoute le joueur au tableau.
	 * @param tableauActif
	 * @param nouveauJoueur
	 */
	public void inscrireJoueur(Tableau tableauActif, Joueur nouveauJoueur) {
		nouveauJoueur = facadeJoueur.update(nouveauJoueur);
		
		if( !tableauActif.getInscrits().contains(nouveauJoueur)){
			tableauActif.getInscrits().add(nouveauJoueur);
			facadeTableau.update(tableauActif);
		}
	}


	/**
	 * méthode générant les poules
	 * @param tournoi
	 */
	public void genererLesPoules(Tournoi tournoi) {
		
		tournoi = facadeTournoi.read(tournoi.getId());
		
		
		for(Tableau tableau : tournoi.getLstTableaux()){
			//tableau = facadeTableau.read(tableau.getId());
			tableau.getPoules().clear();
			

			Poule poule = null;
			for(int i= 0 ; i< tableau.nbInscrits() ; i++){
				if(i % tableau.getJoueursPoule().getJoueurs() == 0){
					poule = facadePoule.newInstance();
					tableau.ajouterPoule(poule);
				}
				
				//on ajoute un joueur à la poule
				Joueur joueur = tableau.getInscrits().get(i);
				poule.ajouterJoueur(joueur);
			}
		}
		facadeTournoi.update(tournoi);
	}

	/**
	 * Enrigistre la listes des tableaux
	 * @param tournoi
	 */
	public void enregistrerTableaux(Tournoi tournoi) {
		for(Tableau tableau : tournoi.getLstTableaux()){
			facadeTableau.update(tableau);
		}
		facadeTournoi.refresh(tournoi);
	}


	/**
	 * méthode permettant de déplacer un joueur d'une pouel à une autre poule
	 * @param idPouleSource
	 * @param idPouleDest
	 * @param idJoueur
	 */
	public void deplacerJoueur(String idPouleSource, String idPouleDest,
			String idJoueur) {
			Poule source = facadePoule.read(idPouleSource);
			Poule dest = facadePoule.read(idPouleDest);
			Joueur joueur = facadeJoueur.read(idJoueur);
			
			
			source.supprimerJoueur(joueur);
			dest.ajouterJoueur(joueur);
			facadePoule.update(dest);
			facadePoule.update(source);
		
	}


	/**
	 * Generer un échéancier
	 * @param tournoi
	 */
	public void genererEcheancier(Tournoi tournoi) {
//		for(Tableau tableau : tournoi.getLstTableaux()){
//			for(Poule poule : tableau.getPoules()){
//				poule.getJoueurs()
//			}			
//		}
	}
}
