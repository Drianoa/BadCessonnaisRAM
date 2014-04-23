package net.etrs.ram.bad_cessonais.services.gestion_actualite;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.etrs.ram.bad_cessonais.entities.gestion_actualite.Actualite;
import net.etrs.ram.bad_cessonais.services.gestion_actualite.dao.FacadeActualite;

@SuppressWarnings("serial")
@Stateless
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceActualite implements Serializable{
	
	@EJB
	FacadeActualite facadeActualite;
	
	public void creerActualite(Actualite actualite){
		facadeActualite.create(actualite);
	}
	
	public List<Actualite> dernieresActus(){
		return facadeActualite.listerDernieresActus();
	}
}
