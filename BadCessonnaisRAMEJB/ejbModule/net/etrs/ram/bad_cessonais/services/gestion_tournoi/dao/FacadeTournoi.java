package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeTournoi extends AbstractFacade<Tournoi>{

	
	public  List<Tournoi> getListTournoi(){
		TypedQuery<Tournoi> query = em.createNamedQuery("findAllTournoi", Tournoi.class);
		return query.getResultList();
	}
	
	public Long countTournoi(){
		return em.createNamedQuery("countAllTournoi", Long.class).getSingleResult();
	}
	
	
	
}
