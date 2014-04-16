
package net.etrs.ram.bad_cessonais.interop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for licencie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="licencie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="classementDouble" type="{http://interop.mockup/}classement" minOccurs="0"/>
 *         &lt;element name="classementDoubleMixte" type="{http://interop.mockup/}classement" minOccurs="0"/>
 *         &lt;element name="classementSimple" type="{http://interop.mockup/}classement" minOccurs="0"/>
 *         &lt;element name="licence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licencie", propOrder = {
    "classementDouble",
    "classementDoubleMixte",
    "classementSimple",
    "licence"
})
public class Licencie {

    protected Classement classementDouble;
    protected Classement classementDoubleMixte;
    protected Classement classementSimple;
    protected String licence;

    /**
     * Gets the value of the classementDouble property.
     * 
     * @return
     *     possible object is
     *     {@link Classement }
     *     
     */
    public Classement getClassementDouble() {
        return classementDouble;
    }

    /**
     * Sets the value of the classementDouble property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classement }
     *     
     */
    public void setClassementDouble(Classement value) {
        this.classementDouble = value;
    }

    /**
     * Gets the value of the classementDoubleMixte property.
     * 
     * @return
     *     possible object is
     *     {@link Classement }
     *     
     */
    public Classement getClassementDoubleMixte() {
        return classementDoubleMixte;
    }

    /**
     * Sets the value of the classementDoubleMixte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classement }
     *     
     */
    public void setClassementDoubleMixte(Classement value) {
        this.classementDoubleMixte = value;
    }

    /**
     * Gets the value of the classementSimple property.
     * 
     * @return
     *     possible object is
     *     {@link Classement }
     *     
     */
    public Classement getClassementSimple() {
        return classementSimple;
    }

    /**
     * Sets the value of the classementSimple property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classement }
     *     
     */
    public void setClassementSimple(Classement value) {
        this.classementSimple = value;
    }

    /**
     * Gets the value of the licence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicence() {
        return licence;
    }

    /**
     * Sets the value of the licence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicence(String value) {
        this.licence = value;
    }

}
