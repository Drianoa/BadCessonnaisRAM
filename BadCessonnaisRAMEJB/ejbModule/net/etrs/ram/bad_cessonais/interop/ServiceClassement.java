
package net.etrs.ram.bad_cessonais.interop;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServiceClassement", targetNamespace = "http://interop.mockup/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceClassement {


    /**
     * 
     * @param arg0
     * @return
     *     returns net.etrs.ram.bad_cessonais.interop.Licencie
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "classementLicencie", targetNamespace = "http://interop.mockup/", className = "net.etrs.ram.bad_cessonais.interop.ClassementLicencie")
    @ResponseWrapper(localName = "classementLicencieResponse", targetNamespace = "http://interop.mockup/", className = "net.etrs.ram.bad_cessonais.interop.ClassementLicencieResponse")
    @Action(input = "http://interop.mockup/ServiceClassement/classementLicencieRequest", output = "http://interop.mockup/ServiceClassement/classementLicencieResponse")
    public Licencie classementLicencie(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}