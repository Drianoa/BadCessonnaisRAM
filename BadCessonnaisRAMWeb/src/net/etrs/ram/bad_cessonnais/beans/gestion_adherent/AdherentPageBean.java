package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;


@Log4j
@ManagedBean
public class AdherentPageBean {

	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Adherent adherent;
	
	//Partie pour le log sur la gestion des adherents
	//private Log log = LogFactory.getLog(this.getClass());
	
	@Getter	@Setter
	private List<Adherent> adherentFiltres;  
	
	
	
	
	
	@PostConstruct		
	public void init(){
		adherent = facadeAdherent.newInstance();
	}
	

	public void enregistrerAdherent(){
		//facadeAdherent.ajouterAdherent(adherent);
	}
	
	
	public List<Adherent> getAdherents(){
		return facadeAdherent.readAll();
	
	}
	public void modifierAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);
		
		//return facadeAdherent.read(id);
	}
	public void desactiverAdherent(Adherent a){
		//JsfUtils.putInFlashScope("ADHERENT", a);
		
		
		facadeAdherent.delete(a);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Desactivation ok"));
		
		log.info("Tentative de desactivation de l'adherent : "+ a.toString());
		
		JsfUtils.sendMessage("Colloque ajouté");

		//return facadeAdherent.read(id);
	}	
	public void consulterAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);

	}
	

	
	

}
