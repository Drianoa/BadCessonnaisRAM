package net.etrs.ram.bad_cessonais.services.gestion_actualite.dao;

import java.util.List;

import javax.ejb.Stateless;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_actualite.Actualite;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeActualite extends AbstractFacade<Actualite> {
	public List<Actualite> listerDernieresActus(){
		return em.createNamedQuery("Actulite.findLast").setMaxResults(10).getResultList();
	}
}