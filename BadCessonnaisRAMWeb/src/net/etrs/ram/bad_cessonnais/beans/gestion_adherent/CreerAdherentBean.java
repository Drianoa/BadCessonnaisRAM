package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

@ManagedBean

public class CreerAdherentBean {

	
	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter @Setter
	Adherent nouveauAdherent;
	
	@Getter @Setter
	Boolean activerFfba = false;
	
	@PostConstruct
	public void init()
	{
		nouveauAdherent = facadeAdherent.newInstance();
	}
	
	
	/**
	 * On enregistre un nouvel adherent en base
	 */
	public void enregistrer(){
		//log.info(nouveauAdherent);  
		facadeAdherent.create(nouveauAdherent);
		nouveauAdherent = facadeAdherent.newInstance();
		JsfUtils.sendMessage(null, FacesMessage.SEVERITY_INFO, "Information", "L'adhérent à bien été ajouté");
	}

	
	
	
	public void buttonSearchFFBaIsEnabled(AjaxBehaviorEvent event){
		
		InputText inputLicenceFFBa = (InputText) event.getSource();
		
		
		
		 UIViewRoot viewRoot =  FacesContext.getCurrentInstance().getViewRoot();
		  UIComponent component1 = viewRoot.findComponent("formAdherent:buttonSearchFFBa");  //form id
		  HtmlCommandButton bu = (HtmlCommandButton)component1;

		    //List<UIComponent> componentList = form.getChildren();

		  System.out.println(bu.getTitle());
		
		
		
		if("".equals(inputLicenceFFBa.getSubmittedValue())){
			System.out.println("c'est vide");
			bu.setDisabled(true);
			activerFfba = false;
		}else{
			System.out.println("c'est pas vide");
			bu.setDisabled(false);
			activerFfba = true;

		}

		
	}
	
	
	
	

}
