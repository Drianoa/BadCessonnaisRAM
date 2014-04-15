package net.etrs.ram.bad_cessonnais.beans.administration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import lombok.Getter;
import lombok.Setter;
import net.etrs.ram.bad_cessonais.entities.administration.Droit;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;


@ManagedBean
@RequestScoped
public class ModifierDroitsBean {
		

	
	@Getter	@Setter
	Adherent adherent;
	
	public List<String> getListeDroits()
	{
		List<String> liste = new ArrayList<>();
		for (Droit droit : Droit.values()) {
			liste.add(droit.getLibelle());
			
		}
		return liste;
	}
	
	
	
	
		
		
	
	
}
