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
	
	public List<Joueur> autocompletion(String licence){
		TypedQuery<Joueur> query = em.createNamedQuery("Joueur.findByLicenceAuto",Joueur.class);
		return query.setParameter("licenceFcd", licence).setMaxResults(5).getResultList();
	}
	
	public Joueur findJoueurByLicence(String licence){
		TypedQuery<Joueur> createNamedQuery = em.createNamedQuery("Joueur.findByLicence", Joueur.class);
		return createNamedQuery.setParameter("licenceFcd",licence).getSingleResult();
	}

	public List<Joueur> autocompletionSansTableau(Tableau tableauActif, String licence) {
		TypedQuery<Joueur> query = em.createNamedQuery("Joueur.findByLicenceAuto",Joueur.class);
		return query.
				setParameter("licenceFcd", licence).
				setParameter("tableauActif", tableauActif).
				setMaxResults(5).
				getResultList();
	}
}
