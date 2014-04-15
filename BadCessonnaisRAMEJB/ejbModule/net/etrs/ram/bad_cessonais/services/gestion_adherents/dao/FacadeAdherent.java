package net.etrs.ram.bad_cessonais.services.gestion_adherents.dao;



import javax.ejb.Stateless;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeAdherent extends AbstractFacade<Adherent>{

	

	public void desactiverAdherent(Adherent a ){
		//em.persist(m);
	}
	
	
}
