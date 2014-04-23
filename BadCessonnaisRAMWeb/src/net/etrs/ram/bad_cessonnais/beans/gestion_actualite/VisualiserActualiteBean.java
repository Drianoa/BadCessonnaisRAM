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

@SuppressWarnings("serial")
@Log4j
@ManagedBean
@ViewScoped
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VisualiserActualiteBean implements Converter, Serializable {

	@Getter
	@Setter
	Actualite actualiteCourante;

	@EJB
	FacadeActualite facadeActualite;

	@EJB
	ServiceActualite serviceActualite;

	@PostConstruct
	void init() {
		// actualites = serviceActualite.dernieresActus();
	}

	/**
	 * convertit l'id en une véritable instance d' {@link Actualite}.
	 */
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cmp, String value) {
		List<Actualite> liste = facadeActualite.search("id", value);
		
		if (liste.size() == 1) {
			return liste.get(0);
		} else {
			throw new ConverterException(new FacesMessage(
					"L'actualité est introuvable"));
		}
	}

	/**
	 * convertir une {@link Actualite} en une représentation String : son id.
	 */
	@Override
	public String getAsString(FacesContext ctx, UIComponent cmp, Object object) {
		if (object != null && object instanceof Actualite) {
			return ((Actualite) object).getId();
		} else {
			return "";
		}
	}

}
