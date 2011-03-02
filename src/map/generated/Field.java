
package map.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice>
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
    "compositefield",
    "customfield",
    "div",
    "input",
    "select",
    "sometype"
})
@XmlRootElement(name = "field")
public class Field {

    protected Compositefield compositefield;
    protected Customfield customfield;
    protected Div div;
    protected Input input;
    protected Select select;
    protected Sometype sometype;

    /**
     * Gets the value of the compositefield property.
     * 
     * @return
     *     possible object is
     *     {@link Compositefield }
     *     
     */
    public Compositefield getCompositefield() {
        return compositefield;
    }

    /**
     * Sets the value of the compositefield property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compositefield }
     *     
     */
    public void setCompositefield(Compositefield value) {
        this.compositefield = value;
    }

    /**
     * Gets the value of the customfield property.
     * 
     * @return
     *     possible object is
     *     {@link Customfield }
     *     
     */
    public Customfield getCustomfield() {
        return customfield;
    }

    /**
     * Sets the value of the customfield property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customfield }
     *     
     */
    public void setCustomfield(Customfield value) {
        this.customfield = value;
    }

    /**
     * Gets the value of the div property.
     * 
     * @return
     *     possible object is
     *     {@link Div }
     *     
     */
    public Div getDiv() {
        return div;
    }

    /**
     * Sets the value of the div property.
     * 
     * @param value
     *     allowed object is
     *     {@link Div }
     *     
     */
    public void setDiv(Div value) {
        this.div = value;
    }

    /**
     * Gets the value of the input property.
     * 
     * @return
     *     possible object is
     *     {@link Input }
     *     
     */
    public Input getInput() {
        return input;
    }

    /**
     * Sets the value of the input property.
     * 
     * @param value
     *     allowed object is
     *     {@link Input }
     *     
     */
    public void setInput(Input value) {
        this.input = value;
    }

    /**
     * Gets the value of the select property.
     * 
     * @return
     *     possible object is
     *     {@link Select }
     *     
     */
    public Select getSelect() {
        return select;
    }

    /**
     * Sets the value of the select property.
     * 
     * @param value
     *     allowed object is
     *     {@link Select }
     *     
     */
    public void setSelect(Select value) {
        this.select = value;
    }

    /**
     * Gets the value of the sometype property.
     * 
     * @return
     *     possible object is
     *     {@link Sometype }
     *     
     */
    public Sometype getSometype() {
        return sometype;
    }

    /**
     * Sets the value of the sometype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sometype }
     *     
     */
    public void setSometype(Sometype value) {
        this.sometype = value;
    }

}
