
package map.generated;

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
 *         &lt;element ref="{}rule"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element ref="{}validation"/>
 *           &lt;element ref="{}query"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="column" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dbcolsize" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="dbdatatype" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="forid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hidden" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="mask" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="primarykey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="replace" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="validationattr" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rule",
    "validation",
    "query"
})
@XmlRootElement(name = "input")
public class Input {

    @XmlElement(required = true)
    protected String rule;
    protected Validation validation;
    protected Query query;
    @XmlAttribute(name = "class", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String clazz;
    @XmlAttribute(required = true)
    protected String column;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dbcolsize;
    @XmlAttribute
    protected String dbdatatype;
    @XmlAttribute(required = true)
    protected String forid;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String hidden;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String label;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String mandatory;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String mask;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute
    protected String primarykey;
    @XmlAttribute(required = true)
    protected String replace;
    @XmlAttribute(required = true)
    protected String type;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String validationattr;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String value;

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
     * Gets the value of the dbdatatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbdatatype() {
        return dbdatatype;
    }

    /**
     * Sets the value of the dbdatatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbdatatype(String value) {
        this.dbdatatype = value;
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
     * Gets the value of the hidden property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHidden() {
        return hidden;
    }

    /**
     * Sets the value of the hidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHidden(String value) {
        this.hidden = value;
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

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandatory(String value) {
        this.mandatory = value;
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

    /**
     * Gets the value of the primarykey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimarykey() {
        return primarykey;
    }

    /**
     * Sets the value of the primarykey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimarykey(String value) {
        this.primarykey = value;
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

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
