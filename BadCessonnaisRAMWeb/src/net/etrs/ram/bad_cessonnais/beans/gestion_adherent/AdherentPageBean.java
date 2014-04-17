package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Justificatif;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Origine;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;
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
		JsfUtils.sendMessage("growl", FacesMessage.SEVERITY_INFO, "Information","Suppresion ok");
		log.info("Tentative de desactivation de l'adherent : "+ a.toString());
	//return facadeAdherent.read(id);
	}	
	public void consulterAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);

	}
	
	/**
	 * On récupère la listes des justificatifs
	 * @return
	 */
	public Justificatif[] getListeJustificatif(){
		return facadeAdherent.getListeJustificatif();
	}
	public Origine[] getListeOrigine(){
		return facadeAdherent.getListeOrigine();
	}
	public Sexe[] getListeSexe(){
		return facadeAdherent.getListeSexe();
	}
	

}
