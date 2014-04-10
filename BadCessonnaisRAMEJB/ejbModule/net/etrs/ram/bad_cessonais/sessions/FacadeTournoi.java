package net.etrs.ram.bad_cessonais.sessions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.entities.Tournoi;

@Stateless
public class FacadeTournoi {

	@PersistenceContext
	private EntityManager em;
	
	public void ajouterTournoi(Tournoi t){
		em.persist(t);
	}
	
	public  List<Tournoi> getListTournoi(){
		TypedQuery<Tournoi> query = em.createNamedQuery("findAllTournoi", Tournoi.class);
		return query.getResultList();
	}
	
	public Long countTournoi(){
		return em.createNamedQuery("countAllTournoi", Long.class).getSingleResult();
	}
	
	
	
}
