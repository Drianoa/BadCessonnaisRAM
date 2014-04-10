package sessions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Adherent;

@Stateless
public class FacadeAdherent {

	@PersistenceContext
	private EntityManager em;
	
	
	public void ajouterAdherent(Adherent m ){
		em.persist(m);
	}
	
	public  List<Adherent> getListAdherent(){
		
		TypedQuery<Adherent> query = em.createNamedQuery("findAllAdherent", Adherent.class);
		
		return query.getResultList();
	}
	
	
	
}
