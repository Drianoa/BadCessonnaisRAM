package net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao;

import javax.ejb.Stateless;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tableau;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.TypeTableau;

@SuppressWarnings("unchecked")
@Stateless
public class FacadeTableau extends AbstractFacade<Tableau> {


	/**
	 * Fonction utilitaire permettan de creer un tableau
	 * @param nomTableau
	 * @param typeTableau
	 * @param tournoi
	 */
	public Tableau create(String nomTableau, TypeTableau typeTableau){
		Tableau newInstance = newInstance();
		newInstance.setNom(nomTableau);
		newInstance.setTypeTableau(typeTableau);
		create(newInstance);
		return newInstance;
	}
	
	public Long countAll(){
		return em.createNamedQuery("Tableau.countAll", Long.class).getSingleResult();
	}
}
