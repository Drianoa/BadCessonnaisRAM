package net.etrs.ram.bad_cessonnais.beans.gestion_tournoi;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.ServiceGestionTournoi;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;


@FieldDefaults(level= AccessLevel.PRIVATE)
@ManagedBean
@ViewScoped
@Log4j
public class InfosEcheancierTournoiBean {
	
	@Getter @Setter
	int terrains = 4;
	
	@Getter @Setter
	Date time = roundAssist();
	
	@Getter @Setter
	int tempsInterMatchs = 5;
	
	Tournoi tournoi;
	
	@EJB
	ServiceGestionTournoi serviceGestionTournoi;
	
	public void genererEcheancier(){
		serviceGestionTournoi.genererEcheancier(tournoi);
	}
	
	private Date roundAssist(){
		Calendar c = Calendar.getInstance();
		int offset = 0;
		offset = 5 - (c.get(Calendar.MINUTE) % 5) + 10;
		c.add(Calendar.MINUTE, offset);
		return c.getTime();
	}
}
