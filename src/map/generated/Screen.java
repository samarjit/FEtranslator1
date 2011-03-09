
package map.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}htmltemplate"/>
 *         &lt;element ref="{}includedjsp"/>
 *         &lt;element ref="{}callbackclass"/>
 *         &lt;element ref="{}scripts"/>
 *         &lt;element ref="{}stylesheets"/>
 *         &lt;element ref="{}crud"/>
 *         &lt;element ref="{}dm"/>
 *         &lt;element ref="{}bl"/>
 *         &lt;element ref="{}commands"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "stylesheets",
    "crud",
    "dm",
    "bl",
    "commands"
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
    @XmlElement(required = true)
    protected Crud crud;
    @XmlElement(required = true)
    protected Dm dm;
    @XmlElement(required = true)
    protected Bl bl;
    @XmlElement(required = true)
    protected Commands commands;
    @XmlAttribute(required = true)
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
     * Gets the value of the crud property.
     * 
     * @return
     *     possible object is
     *     {@link Crud }
     *     
     */
    public Crud getCrud() {
        return crud;
    }

    /**
     * Sets the value of the crud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Crud }
     *     
     */
    public void setCrud(Crud value) {
        this.crud = value;
    }

    /**
     * Gets the value of the dm property.
     * 
     * @return
     *     possible object is
     *     {@link Dm }
     *     
     */
    public Dm getDm() {
        return dm;
    }

    /**
     * Sets the value of the dm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dm }
     *     
     */
    public void setDm(Dm value) {
        this.dm = value;
    }

    /**
     * Gets the value of the bl property.
     * 
     * @return
     *     possible object is
     *     {@link Bl }
     *     
     */
    public Bl getBl() {
        return bl;
    }

    /**
     * Sets the value of the bl property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bl }
     *     
     */
    public void setBl(Bl value) {
        this.bl = value;
    }

    /**
     * Gets the value of the commands property.
     * 
     * @return
     *     possible object is
     *     {@link Commands }
     *     
     */
    public Commands getCommands() {
        return commands;
    }

    /**
     * Sets the value of the commands property.
     * 
     * @param value
     *     allowed object is
     *     {@link Commands }
     *     
     */
    public void setCommands(Commands value) {
        this.commands = value;
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
