
package map.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}htmltemplate"/>
 *         &lt;element ref="{}includedjsp"/>
 *         &lt;element ref="{}callbackclass"/>
 *         &lt;element ref="{}scripts"/>
 *         &lt;element ref="{}stylesheets"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "htmltemplate",
    "includedjsp",
    "callbackclass",
    "scripts",
    "stylesheets"
})
@XmlRootElement(name = "screen")
public class Screen {

    @XmlElement(required = true)
    protected String htmltemplate;
    @XmlElement(required = true)
    protected String includedjsp;
    @XmlElement(required = true)
    protected String callbackclass;
    @XmlElement(required = true)
    protected Scripts scripts;
    @XmlElement(required = true)
    protected Stylesheets stylesheets;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;

    /**
     * Gets the value of the htmltemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtmltemplate() {
        return htmltemplate;
    }

    /**
     * Sets the value of the htmltemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtmltemplate(String value) {
        this.htmltemplate = value;
    }

    /**
     * Gets the value of the includedjsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludedjsp() {
        return includedjsp;
    }

    /**
     * Sets the value of the includedjsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludedjsp(String value) {
        this.includedjsp = value;
    }

    /**
     * Gets the value of the callbackclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallbackclass() {
        return callbackclass;
    }

    /**
     * Sets the value of the callbackclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallbackclass(String value) {
        this.callbackclass = value;
    }

    /**
     * Gets the value of the scripts property.
     * 
     * @return
     *     possible object is
     *     {@link Scripts }
     *     
     */
    public Scripts getScripts() {
        return scripts;
    }

    /**
     * Sets the value of the scripts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scripts }
     *     
     */
    public void setScripts(Scripts value) {
        this.scripts = value;
    }

    /**
     * Gets the value of the stylesheets property.
     * 
     * @return
     *     possible object is
     *     {@link Stylesheets }
     *     
     */
    public Stylesheets getStylesheets() {
        return stylesheets;
    }

    /**
     * Sets the value of the stylesheets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stylesheets }
     *     
     */
    public void setStylesheets(Stylesheets value) {
        this.stylesheets = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
