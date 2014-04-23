package net.etrs.ram.bad_cessonnais.beans.gestion_actualite;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonais.entities.gestion_actualite.Actualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.ServiceActualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.dao.FacadeActualite;
import net.etrs.ram.bad_cessonnais.beans.gestion_adherent.AdherentPageBean;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

/**
 * Bean pour la page de création d'actualité d'actualité.
 * @author adrien.merly
 *
 */
@Log4j
@ManagedBean
@ViewScoped
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AjouterActualiteBean {

	@Getter @Setter
	Actualite actualite;
	
	@EJB
	FacadeActualite facadeActualite;
	
	@EJB
	ServiceActualite serviceActualite;
	
	/**
	 * initialisation de l'objet actualité avec une nouvelle actualité.
	 */
	@PostConstruct
	void init(){
		actualite = facadeActualite.newInstance();
	}
	
	/**
	 * Enregistrement de l'actualitée en base de données.
	 */
	public void creerActualite(){
		try{
			serviceActualite.creerActualite(actualite);
			actualite = facadeActualite.newInstance();
			JsfUtils.sendMessage("L'actualité à été crée");
		}catch(Exception exception){
			JsfUtils.sendMessage(exception);
			throw exception;
		}
	}
}
