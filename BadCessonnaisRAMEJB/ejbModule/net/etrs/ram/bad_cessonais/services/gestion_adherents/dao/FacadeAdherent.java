package net.etrs.ram.bad_cessonais.services.gestion_adherents.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import net.etrs.ram.bad_cessonais.common.AbstractFacade;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Justificatif;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Origine;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;


@SuppressWarnings("unchecked")
@Stateless
public class FacadeAdherent extends AbstractFacade<Adherent>{

	
	
	/**
	 * 
	 * @param a
	 */
	public void desactiverAdherent(Adherent a ){
		//TODO desactiver un adherent
	}


	/**
	 * 
	 * @param motCle
	 * @return
	 */
	public List<Adherent> recherche(String motCle) {
		TypedQuery<Adherent> query = em.createNamedQuery("findAdherentLike", Adherent.class);
		query.setParameter("motcle", motCle); 
		return query.getResultList();
		
	}

	
	/**
	 * Teste si l'adherent existe en base
	 * @param a
	 * @return un boolean
	 */
	public boolean isExistAdherent(String nom,String prenom,Date d){
		TypedQuery<Adherent> query = em.createNamedQuery("isExist", Adherent.class);
		query.setParameter("nom", nom); 
		query.setParameter("prenom", prenom);
		query.setParameter("dateN", d);
		return query.getResultList().size()!=0 ;
	}
	
	
	/**
	 * Listes des Justificatifs
	 * @return Tableau de Justificatif
	 */
	public Justificatif[] getListeJustificatif(){
		return Justificatif.values();
	}
	/**
	 * Liste des origines
	 * @return Tableau d'origine
	 */
	public Origine[] getListeOrigine(){
		return Origine.values();
	}
	/**
	 * Liste des sexes
	 * @return Tableau de sexe
	 */
	public Sexe[] getListeSexe(){
		return Sexe.values();
	}
}
