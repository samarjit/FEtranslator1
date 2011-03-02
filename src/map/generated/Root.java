
package map.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{}screen"/>
 *         &lt;element ref="{}panels"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "screen",
    "panels"
})
@XmlRootElement(name = "root")
public class Root {

    @XmlElement(required = true)
    protected Screen screen;
    @XmlElement(required = true)
    protected Panels panels;

    /**
     * Gets the value of the screen property.
     * 
     * @return
     *     possible object is
     *     {@link Screen }
     *     
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Sets the value of the screen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Screen }
     *     
     */
    public void setScreen(Screen value) {
        this.screen = value;
    }

    /**
     * Gets the value of the panels property.
     * 
     * @return
     *     possible object is
     *     {@link Panels }
     *     
     */
    public Panels getPanels() {
        return panels;
    }

    /**
     * Sets the value of the panels property.
     * 
     * @param value
     *     allowed object is
     *     {@link Panels }
     *     
     */
    public void setPanels(Panels value) {
        this.panels = value;
    }

}
