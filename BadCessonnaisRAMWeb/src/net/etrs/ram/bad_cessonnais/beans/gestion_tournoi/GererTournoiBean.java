package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;


@ManagedBean
@ViewScoped
public class GererTournoiBean {
		
	@EJB
	private FacadeTournoi facadeTournoi;
	
	/**
	 * Liste tous les tournois.
	 * @return
	 */
	public List<Tournoi> listerTournois(){		
		return facadeTournoi.readAll();
	}
	
	/**
	 * passage du tournoi séléctionné à la page suivante.
	 * @param tournoi
	 */
	public void putInFlash(Tournoi tournoi){
		JsfUtils.putInFlashScope("tournoi", tournoi.getId());
	}
		
}
