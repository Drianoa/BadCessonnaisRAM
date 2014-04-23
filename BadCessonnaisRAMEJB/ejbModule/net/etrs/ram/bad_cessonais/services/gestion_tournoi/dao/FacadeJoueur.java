package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeJoueur extends AbstractFacade<Joueur> {
	
	/**
	 * AutoCompletion des joueurs
	 * @param licence
	 * @return
	 */
	public List<Joueur> autocompletion(String licence){
		TypedQuery<Joueur> query = em.createNamedQuery("Joueur.findByLicenceAuto",Joueur.class);
		return query.setParameter("licenceFcd", licence).setMaxResults(5).getResultList();
	}
	
	/**
	 * méthode permettant de trouver un joueur via la licence
	 * @param licence
	 * @return
	 */
	public Joueur findJoueurByLicence(String licence){
		TypedQuery<Joueur> createNamedQuery = em.createNamedQuery("Joueur.findByLicence", Joueur.class);
		return createNamedQuery.setParameter("licenceFcd",licence).getSingleResult();
	}

		/**
		 * 
		 * @param tableauActif
		 * @param licence
		 * @return
		 */
	public List<Joueur> autocompletionSansTableau(Tableau tableauActif, String licence) {
		TypedQuery<Joueur> query = em.createNamedQuery("Joueur.findByLicenceAuto",Joueur.class);
		return query.
				setParameter("licenceFcd", licence).
				setParameter("tableauActif", tableauActif).
				setMaxResults(5).
				getResultList();
	}
}
