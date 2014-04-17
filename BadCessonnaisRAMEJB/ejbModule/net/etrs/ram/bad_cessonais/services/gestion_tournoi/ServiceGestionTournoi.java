package net.etrs.ram.bad_cessonais.services.gestion_tournoi;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeJoueur;
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
	
	@PersistenceContext
	EntityManager em;
	
	
	
	public Tournoi creerTournoi(Tournoi tournoi , List<Tableau> tableaux){
		facadeTournoi.create(tournoi);
		for (Tableau tableau : tableaux) {
			facadeTableau.create(tableau);
		}
		tournoi.getLstTableaux().addAll(tableaux);
		facadeTournoi.update(tournoi);
		return tournoi;
	}



	public void supprimerJoueur(Tableau tableau, Joueur joueur) {
		facadeTableau.supprimerJoueur(tableau,joueur);
	}



	public void inscrireJoueur(Tableau tableauActif, Joueur nouveauJoueur) {
		//MAJ Joueur
		nouveauJoueur = facadeJoueur.update(nouveauJoueur);
		
		if( !tableauActif.getInscrits().contains(nouveauJoueur)){
			tableauActif.getInscrits().add(nouveauJoueur);
			facadeTableau.update(tableauActif);
		}
		//MAJ Tableau
	}
}
