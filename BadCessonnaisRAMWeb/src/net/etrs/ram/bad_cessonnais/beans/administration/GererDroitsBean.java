package net.etrs.ram.bad_cessonnais.beans.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;


@ManagedBean
@RequestScoped
public class GererDroitsBean {
		
	@EJB
	private FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Adherent adherent;
	
	
	
	public List<Adherent> listerAdherents(){		
		return facadeAdherent.readAll();
	}

	
	public String compterAdherent()
	{
		return facadeAdherent.countAll().toString();
	}
		
	public void modifierDroitsAdherent(Adherent a){
		JsfUtils.putInFlashScope("ADHERENT", a);

	}	
	
	
}
