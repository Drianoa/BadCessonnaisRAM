package net.etrs.ram.bad_cessonais.services.gestion_adherents.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeAdherent extends AbstractFacade<Adherent>{

	

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
