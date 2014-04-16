
package net.etrs.ram.bad_cessonais.interop;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.etrs.ram.bad_cessonais.interop package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClassementLicencieResponse_QNAME = new QName("http://interop.mockup/", "classementLicencieResponse");
    private final static QName _ClassementLicencie_QNAME = new QName("http://interop.mockup/", "classementLicencie");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.etrs.ram.bad_cessonais.interop
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClassementLicencieResponse }
     * 
     */
    public ClassementLicencieResponse createClassementLicencieResponse() {
        return new ClassementLicencieResponse();
    }

    /**
     * Create an instance of {@link ClassementLicencie }
     * 
     */
    public ClassementLicencie createClassementLicencie() {
        return new ClassementLicencie();
    }

    /**
     * Create an instance of {@link Licencie }
     * 
     */
    public Licencie createLicencie() {
        return new Licencie();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassementLicencieResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interop.mockup/", name = "classementLicencieResponse")
    public JAXBElement<ClassementLicencieResponse> createClassementLicencieResponse(ClassementLicencieResponse value) {
        return new JAXBElement<ClassementLicencieResponse>(_ClassementLicencieResponse_QNAME, ClassementLicencieResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassementLicencie }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interop.mockup/", name = "classementLicencie")
    public JAXBElement<ClassementLicencie> createClassementLicencie(ClassementLicencie value) {
        return new JAXBElement<ClassementLicencie>(_ClassementLicencie_QNAME, ClassementLicencie.class, null, value);
    }

}
