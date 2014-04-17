package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import javax.ejb.Stateless;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeMatch;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeTableau extends AbstractFacade<Tableau> {


	/**
	 * Fonction utilitaire permettan de creer un tableau
	 * @param nomTableau
	 * @param typeTableau
	 * @param tournoi
	 */
	public Tableau create(String nomTableau, TypeMatch typeTableau){
		Tableau newInstance = newInstance();
		newInstance.setNom(nomTableau);
		//newInstance.setTypeMatch(typeTableau);
		create(newInstance);
		return newInstance;
	}
	
	public void ajouterEquipe(Tableau tableau, Joueur joueur){
		tableau.getInscrits().add(joueur);
		em.persist(tableau);
	}

	public void supprimerJoueur(Tableau tableau, Joueur joueur) {
		tableau.getInscrits().remove(joueur);
		update(tableau);
	}
}
