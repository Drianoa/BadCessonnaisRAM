package mockup.model;

import lombok.Data;

@Data
public class Licencie {
	private String licence;
	
	private Classement classementSimple;
	
	private Classement classementDouble;
	
	private Classement classementDoubleMixte;
	
	public static Licencie getRandomLicencie(String licence){
		Licencie licencie = new Licencie();
		licencie.setLicence(licence);
		licencie.setClassementDouble(Classement.getRandomClassement());
		licencie.setClassementDoubleMixte(Classement.getRandomClassement());
		licencie.setClassementSimple(Classement.getRandomClassement());
		return licencie;
	}
}
