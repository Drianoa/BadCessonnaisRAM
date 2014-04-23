package net.etrs.ram.bad_cessonnais.beans.gestion_actualite;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.etrs.ram.bad_cessonais.entities.gestion_actualite.Actualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.ServiceActualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.dao.FacadeActualite;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

@Log4j
@ManagedBean
@ViewScoped
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AccueilActualiteBean {
	

	@Getter @Setter
	List<Actualite> actualites;

	@EJB
	FacadeActualite facadeActualite;

	@EJB
	ServiceActualite serviceActualite;


	@PostConstruct
	void init(){
		actualites = serviceActualite.dernieresActus();
	}
	
	
	
	
}
