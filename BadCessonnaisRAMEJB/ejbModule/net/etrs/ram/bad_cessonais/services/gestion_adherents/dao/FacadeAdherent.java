package net.etrs.ram.bad_cessonais.services.gestion_adherents.dao;



import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeAdherent extends AbstractFacade<Adherent>{

	
	public void desactiverAdherent(Adherent a ){
		//em.persist(m);
	}

	public List<Adherent> recherche(String motCle) {
		TypedQuery<Adherent> query = em.createNamedQuery("findAdherentLike", Adherent.class);
		query.setParameter("motcle", motCle); 
		return query.getResultList();
		
	}
	
	
}
