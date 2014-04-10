package sessions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Tournoi;

@Stateless
public class FacadeTournoi {

	@PersistenceContext
	private EntityManager em;
	
	
	public void ajouterTournoi(Tournoi t){
		em.persist(t);
	}
	
	public  List<Tournoi> getListTournoi(){
		
		TypedQuery<Tournoi> query = em.createNamedQuery("findAlltournoi", Tournoi.class);
		
		return query.getResultList();
	}
	
	
	
}
