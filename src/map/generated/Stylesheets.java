
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
 *         &lt;element ref="{}styleinclude" maxOccurs="unbounded" minOccurs="0"/>
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
    "styleincludeOrText"
})
@XmlRootElement(name = "stylesheets")
public class Stylesheets {

    @XmlElementRefs({
        @XmlElementRef(name = "styleinclude", type = JAXBElement.class),
        @XmlElementRef(name = "text", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> styleincludeOrText;

    /**
     * Gets the value of the styleincludeOrText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the styleincludeOrText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStyleincludeOrText().add(newItem);
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
    public List<JAXBElement<String>> getStyleincludeOrText() {
        if (styleincludeOrText == null) {
            styleincludeOrText = new ArrayList<JAXBElement<String>>();
        }
        return this.styleincludeOrText;
    }

}
