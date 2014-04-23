package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;


@ManagedBean
@ViewScoped
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ModifierAdherentBean {
		

	@Getter	@Setter
	Adherent adherent;
	
	@EJB
	FacadeAdherent facadeAdherent;
	
	
	@PostConstruct
	public void init()
	{
		adherent = (Adherent) JsfUtils.getFromFlashScope("ADHERENT");
	}
	
	/**
	 * Modification d'un adhérent.
	 */
	public void validerModification()
	{
		facadeAdherent.update(adherent);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Modification effectuée"));
	}
	
		
		
	
	
}
