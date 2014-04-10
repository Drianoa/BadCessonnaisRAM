package init;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import entities.Adherent;
import sessions.FacadeAdherent;

@Singleton
@Startup       //se lance au demarrage
public class AdherentInitSingleton {
	
	@EJB
	private FacadeAdherent facadeAdherent;
	
	@PostConstruct
	public void init(){
		
		
		if(facadeAdherent.getListAdherent().isEmpty()){
			facadeAdherent.ajouterAdherent(createMajor("Warner"));
			facadeAdherent.ajouterAdherent(createMajor("EMI"));
			facadeAdherent.ajouterAdherent(createMajor("SONY"));
			facadeAdherent.ajouterAdherent(createMajor("MyMajorCompany"));
		}
		

		
		
		
	}

	
	private Adherent createMajor(String nom){
		Adherent a = new Adherent();
		a.setNom(nom);
		return a;
	}
	
	
	
}
