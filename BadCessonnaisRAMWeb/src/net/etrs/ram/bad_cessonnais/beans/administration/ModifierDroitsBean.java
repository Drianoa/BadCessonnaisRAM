package net.etrs.ram.bad_cessonnais.beans.administration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.administration.Droit;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;


@ManagedBean
@ViewScoped
public class ModifierDroitsBean {
		

	@Getter	@Setter
	Adherent adherent;
	
	@EJB
	FacadeAdherent facadeAdherent;
	
	/**
	 * Retourne la liste des droits possibles
	 * @return
	 */
	public List<SelectItem> getListeDroits()
	{
		List<SelectItem> liste = new ArrayList<>();
		for (Droit droit : Droit.values()) {
			liste.add(new SelectItem(droit, droit.getLibelle()));
		}
		return liste;
	}
	

	@PostConstruct
	public void init()
	{
		adherent = (Adherent) JsfUtils.getFromFlashScope("ADHERENT");
	}
	
	
	/**
	 * Modification des droits d'un adhérent
	 */
	public void validerModification()
	{
		facadeAdherent.update(adherent);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Modification effectuée"));
	}
	
		
		
	
	
}
