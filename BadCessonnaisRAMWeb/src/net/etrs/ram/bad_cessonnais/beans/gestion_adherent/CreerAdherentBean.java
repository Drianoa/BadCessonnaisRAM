package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;



import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonnais.utils.ToolBox;

@ManagedBean

public class CreerAdherentBean {

	
	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter @Setter
	Adherent nouveauAdherent;
	
	@PostConstruct
	public void init()
	{
		nouveauAdherent = facadeAdherent.newInstance();
	}
	
	
	/**
	 * On enregistre un nouvel adherent en base
	 */
	public void enregistrer(){
		facadeAdherent.create(nouveauAdherent);
		nouveauAdherent = facadeAdherent.newInstance();
		JsfUtils.sendMessage(null, FacesMessage.SEVERITY_INFO, "Information", "L'adhérent a bien été ajouté");
	}

	
	
	/**
	 * Activation/Désactivation du bouton si on met à jour le champ N° licence FFBa 
	 * @param event
	 */
	public void buttonSearchFFBaIsEnabled(AjaxBehaviorEvent event){
		InputText inputLicenceFFBa = (InputText) event.getSource();
		
		//on recherche le button de recherche du licencié
		UIViewRoot viewRoot =  FacesContext.getCurrentInstance().getViewRoot();
		UIComponent component1 = viewRoot.findComponent("formAdherent:buttonSearchFFBa");  //Button id
		HtmlCommandButton button = (HtmlCommandButton)component1;

		if( !ToolBox.isIntegerException(inputLicenceFFBa.getSubmittedValue().toString()) || inputLicenceFFBa.getSubmittedValue().toString().length()!=6){
			button.setDisabled(true);
		}else{
			button.setDisabled(false);
		}

		
	}
	

	
	

}
