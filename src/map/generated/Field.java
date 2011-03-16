
package map.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice maxOccurs="2" minOccurs="0">
 *         &lt;element ref="{}compositefield"/>
 *         &lt;element ref="{}customfield"/>
 *         &lt;element ref="{}div"/>
 *         &lt;element ref="{}input"/>
 *         &lt;element ref="{}select"/>
 *         &lt;element ref="{}sometype"/>
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
    "compositefieldOrCustomfieldOrDiv"
})
@XmlRootElement(name = "field")
public class Field {

    @XmlElements({
        @XmlElement(name = "sometype", type = Sometype.class),
        @XmlElement(name = "customfield", type = Customfield.class),
        @XmlElement(name = "input", type = Input.class),
        @XmlElement(name = "compositefield", type = Compositefield.class),
        @XmlElement(name = "select", type = Select.class),
        @XmlElement(name = "div", type = Div.class)
    })
    protected List<Object> compositefieldOrCustomfieldOrDiv;

    /**
     * Gets the value of the compositefieldOrCustomfieldOrDiv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compositefieldOrCustomfieldOrDiv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompositefieldOrCustomfieldOrDiv().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sometype }
     * {@link Customfield }
     * {@link Input }
     * {@link Compositefield }
     * {@link Select }
     * {@link Div }
     * 
     * 
     */
    public List<Object> getCompositefieldOrCustomfieldOrDiv() {
        if (compositefieldOrCustomfieldOrDiv == null) {
            compositefieldOrCustomfieldOrDiv = new ArrayList<Object>();
        }
        return this.compositefieldOrCustomfieldOrDiv;
    }

}
