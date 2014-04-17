package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Joueur;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeJoueur extends AbstractFacade<Joueur> {
	
	public List<Joueur> autocompletion(String licence){
		TypedQuery<Joueur> query = em.createNamedQuery("Joueur.findByLicenceAuto",Joueur.class);
		return query.setParameter("licenceFcd", licence).setMaxResults(5).getResultList();
		
	}
}
