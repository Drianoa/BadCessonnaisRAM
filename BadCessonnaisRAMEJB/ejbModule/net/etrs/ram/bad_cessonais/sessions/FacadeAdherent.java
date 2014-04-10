package net.etrs.ram.bad_cessonais.sessions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import net.etrs.ram.bad_cessonais.entities.Adherent;

@Stateless
public class FacadeAdherent {

	@PersistenceContext
	private EntityManager em;
	
	
	public Adherent newAdherent(){
		return new Adherent();
	}
	
	public void ajouterAdherent(Adherent a ){
		em.persist(a);
	}
	
	public void supprimerAdherent(Adherent a ){
		em.remove(a);
	}

	public void desactiverAdherent(Adherent a ){
		//em.persist(m);
	}
	
	public  List<Adherent> getListAdherent(){
		TypedQuery<Adherent> query = em.createNamedQuery("findAllAdherent", Adherent.class);
		return query.getResultList();
	}
	
	public Adherent findAdherent(Long id){
		return em.find(Adherent.class, id);
	}
	
}
