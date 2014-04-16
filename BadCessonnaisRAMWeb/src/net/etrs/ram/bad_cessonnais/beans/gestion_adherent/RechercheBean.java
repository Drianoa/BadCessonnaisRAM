package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.inputtext.InputText;

import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@ManagedBean
public class RechercheBean {

	
	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Adherent adherent;
	
	
	@Getter	@Setter
	String motcle;
	
	@Getter	@Setter
	private List<Adherent> adherentFiltres;  
	
	
	public void tiptapClavier(AjaxBehaviorEvent event){
		
		InputText adherentRecherche = (InputText) event.getSource();
		String motCle = (String) adherentRecherche.getSubmittedValue();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO,"Info",""+motCle));
	

		System.out.println(adherentRecherche.getSubmittedValue());

			
		 facadeAdherent.recherche(motCle);
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
