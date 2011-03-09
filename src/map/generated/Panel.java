
package map.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{}button"/>
 *         &lt;element ref="{}fields"/>
 *         &lt;element name="testscriptlet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "buttonOrFieldsOrTestscriptlet"
})
@XmlRootElement(name = "panel")
public class Panel {

    @XmlElements({
        @XmlElement(name = "button", type = Button.class),
        @XmlElement(name = "testscriptlet", type = String.class),
        @XmlElement(name = "fields", type = Fields.class)
    })
    protected List<Object> buttonOrFieldsOrTestscriptlet;
    @XmlAttribute
    protected String id;

    /**
     * Gets the value of the buttonOrFieldsOrTestscriptlet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buttonOrFieldsOrTestscriptlet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getButtonOrFieldsOrTestscriptlet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Button }
     * {@link String }
     * {@link Fields }
     * 
     * 
     */
    public List<Object> getButtonOrFieldsOrTestscriptlet() {
        if (buttonOrFieldsOrTestscriptlet == null) {
            buttonOrFieldsOrTestscriptlet = new ArrayList<Object>();
        }
        return this.buttonOrFieldsOrTestscriptlet;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
