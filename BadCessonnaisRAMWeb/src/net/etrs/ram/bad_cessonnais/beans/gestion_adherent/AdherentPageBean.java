package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Justificatif;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Origine;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;
import net.etrs.ram.bad_cessonais.interop.Licencie;
import net.etrs.ram.bad_cessonais.interop.ServiceClassement;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;


@Log4j
@ManagedBean
public class AdherentPageBean {

	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Licencie licencie;
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
	
	/**
	 * Récupération de tous les adhérents
	 * @return
	 */
	public List<Adherent> getAdherents(){
		return facadeAdherent.readAll();
	
	}
	
	//TODO
	public void modifierAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);
		
		//return facadeAdherent.read(id);
	}
	
	//TODO dans un premier temps on supprime l'adherent
	public void desactiverAdherent(Adherent a){
		//JsfUtils.putInFlashScope("ADHERENT", a);
		facadeAdherent.delete(a);
		JsfUtils.sendMessage("growl", FacesMessage.SEVERITY_INFO, "Information","Suppresion effectuée");
		log.info("Desactivation de l'adherent : "+ a.toString());
	}	
	
	
	/**
	 * On recupere les informations de classement du licencié via le WS 
	 */
	public void getClassementLicencieFFBa(){
		String licenceFFBa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("formAdherent:inputFFBaAdherent");

		try {
			URL url = new URL("http://201214-22:9090/ServeurFFbadWeb/ServiceClassement?wsdl");
			QName qname = new QName("http://interop.mockup/","ServiceClassementService");
			
			
			Service service = Service.create(url, qname);
			ServiceClassement sc = service.getPort(ServiceClassement.class);
			licencie = sc.classementLicencie(licenceFFBa);
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();

		}
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
