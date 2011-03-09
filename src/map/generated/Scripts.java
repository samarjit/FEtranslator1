
package map.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{}scriptinclude" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}text" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "scriptincludeOrText"
})
@XmlRootElement(name = "scripts")
public class Scripts {

    @XmlElementRefs({
        @XmlElementRef(name = "scriptinclude", type = JAXBElement.class),
        @XmlElementRef(name = "text", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> scriptincludeOrText;

    /**
     * Gets the value of the scriptincludeOrText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scriptincludeOrText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScriptincludeOrText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getScriptincludeOrText() {
        if (scriptincludeOrText == null) {
            scriptincludeOrText = new ArrayList<JAXBElement<String>>();
        }
        return this.scriptincludeOrText;
    }

}
