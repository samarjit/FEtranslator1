
package map.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{}datafield"/>
 *         &lt;element ref="{}displayfield" maxOccurs="unbounded"/>
 *         &lt;element ref="{}text"/>
 *         &lt;element ref="{}rule"/>
 *         &lt;element ref="{}validation"/>
 *         &lt;element ref="{}query"/>
 *       &lt;/sequence>
 *       &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="column" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="dbcolsize" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="forid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mask" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="replace" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="validationattr" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datafield",
    "displayfield",
    "text",
    "rule",
    "validation",
    "query"
})
@XmlRootElement(name = "compositefield")
public class Compositefield {

    @XmlElement(required = true)
    protected Datafield datafield;
    @XmlElement(required = true)
    protected List<Displayfield> displayfield;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected String rule;
    @XmlElement(required = true)
    protected Validation validation;
    @XmlElement(required = true)
    protected Query query;
    @XmlAttribute(name = "class", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String clazz;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String column;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dbcolsize;
    @XmlAttribute(required = true)
    protected String forid;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String mask;
    @XmlAttribute(required = true)
    protected String replace;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String type;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String validationattr;

    /**
     * Gets the value of the datafield property.
     * 
     * @return
     *     possible object is
     *     {@link Datafield }
     *     
     */
    public Datafield getDatafield() {
        return datafield;
    }

    /**
     * Sets the value of the datafield property.
     * 
     * @param value
     *     allowed object is
     *     {@link Datafield }
     *     
     */
    public void setDatafield(Datafield value) {
        this.datafield = value;
    }

    /**
     * Gets the value of the displayfield property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayfield property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayfield().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Displayfield }
     * 
     * 
     */
    public List<Displayfield> getDisplayfield() {
        if (displayfield == null) {
            displayfield = new ArrayList<Displayfield>();
        }
        return this.displayfield;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the rule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRule() {
        return rule;
    }

    /**
     * Sets the value of the rule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRule(String value) {
        this.rule = value;
    }

    /**
     * Gets the value of the validation property.
     * 
     * @return
     *     possible object is
     *     {@link Validation }
     *     
     */
    public Validation getValidation() {
        return validation;
    }

    /**
     * Sets the value of the validation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Validation }
     *     
     */
    public void setValidation(Validation value) {
        this.validation = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link Query }
     *     
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query }
     *     
     */
    public void setQuery(Query value) {
        this.query = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the column property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumn(String value) {
        this.column = value;
    }

    /**
     * Gets the value of the dbcolsize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbcolsize() {
        return dbcolsize;
    }

    /**
     * Sets the value of the dbcolsize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbcolsize(String value) {
        this.dbcolsize = value;
    }

    /**
     * Gets the value of the forid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForid() {
        return forid;
    }

    /**
     * Sets the value of the forid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForid(String value) {
        this.forid = value;
    }

    /**
     * Gets the value of the mask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMask() {
        return mask;
    }

    /**
     * Sets the value of the mask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMask(String value) {
        this.mask = value;
    }

    /**
     * Gets the value of the replace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplace() {
        return replace;
    }

    /**
     * Sets the value of the replace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplace(String value) {
        this.replace = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the validationattr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationattr() {
        return validationattr;
    }

    /**
     * Sets the value of the validationattr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationattr(String value) {
        this.validationattr = value;
    }

}
