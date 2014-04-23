package net.etrs.ram.bad_cessonnais.beans.gestion_actualite;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonais.entities.gestion_actualite.Actualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.ServiceActualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.dao.FacadeActualite;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;

@SuppressWarnings("serial")
@Log4j
@ManagedBean
@ViewScoped
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VisualiserActualiteBean implements  Serializable {

	@Getter
	@Setter
	Actualite actualiteCourante;

	@EJB
	FacadeActualite facadeActualite;

	@EJB
	ServiceActualite serviceActualite;

	@PostConstruct
	void init() {
		actualiteCourante = (Actualite) JsfUtils.getFromFlashScope("actualite");
	}

	

}
