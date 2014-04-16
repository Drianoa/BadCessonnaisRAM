package mockup.interop;

import javax.jws.WebMethod;
import javax.jws.WebService;

import mockup.model.Licencie;

@WebService()
public class ServiceClassement {

	@WebMethod()
	public  Licencie classementLicencie(String licence){
		return Licencie.getRandomLicencie(licence);
	}
}
