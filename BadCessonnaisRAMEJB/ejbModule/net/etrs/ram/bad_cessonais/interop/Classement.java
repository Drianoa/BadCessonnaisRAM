
package net.etrs.ram.bad_cessonais.interop;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for classement.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="classement">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NC"/>
 *     &lt;enumeration value="T5"/>
 *     &lt;enumeration value="T10"/>
 *     &lt;enumeration value="T20"/>
 *     &lt;enumeration value="T50"/>
 *     &lt;enumeration value="A1"/>
 *     &lt;enumeration value="A2"/>
 *     &lt;enumeration value="A3"/>
 *     &lt;enumeration value="A4"/>
 *     &lt;enumeration value="B1"/>
 *     &lt;enumeration value="B2"/>
 *     &lt;enumeration value="B3"/>
 *     &lt;enumeration value="B4"/>
 *     &lt;enumeration value="C1"/>
 *     &lt;enumeration value="C2"/>
 *     &lt;enumeration value="C3"/>
 *     &lt;enumeration value="C4"/>
 *     &lt;enumeration value="D1"/>
 *     &lt;enumeration value="D2"/>
 *     &lt;enumeration value="D3"/>
 *     &lt;enumeration value="D4"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "classement")
@XmlEnum
public enum Classement {

    NC("NC"),
    @XmlEnumValue("T5")
    T_5("T5"),
    @XmlEnumValue("T10")
    T_10("T10"),
    @XmlEnumValue("T20")
    T_20("T20"),
    @XmlEnumValue("T50")
    T_50("T50"),
    @XmlEnumValue("A1")
    A_1("A1"),
    @XmlEnumValue("A2")
    A_2("A2"),
    @XmlEnumValue("A3")
    A_3("A3"),
    @XmlEnumValue("A4")
    A_4("A4"),
    @XmlEnumValue("B1")
    B_1("B1"),
    @XmlEnumValue("B2")
    B_2("B2"),
    @XmlEnumValue("B3")
    B_3("B3"),
    @XmlEnumValue("B4")
    B_4("B4"),
    @XmlEnumValue("C1")
    C_1("C1"),
    @XmlEnumValue("C2")
    C_2("C2"),
    @XmlEnumValue("C3")
    C_3("C3"),
    @XmlEnumValue("C4")
    C_4("C4"),
    @XmlEnumValue("D1")
    D_1("D1"),
    @XmlEnumValue("D2")
    D_2("D2"),
    @XmlEnumValue("D3")
    D_3("D3"),
    @XmlEnumValue("D4")
    D_4("D4");
    private final String value;

    Classement(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Classement fromValue(String v) {
        for (Classement c: Classement.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
