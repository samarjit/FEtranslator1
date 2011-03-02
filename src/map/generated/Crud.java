
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{}jsonrpc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}selectonload"/>
 *         &lt;element ref="{}sqlselect"/>
 *         &lt;element ref="{}savefieldids"/>
 *         &lt;element ref="{}sqlinsert"/>
 *         &lt;element ref="{}sqldelete"/>
 *         &lt;element ref="{}sqlselectcount"/>
 *         &lt;element ref="{}sqlupdate"/>
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
    "jsonrpcOrSelectonloadOrSqlselect"
})
@XmlRootElement(name = "crud")
public class Crud {

    @XmlElementRefs({
        @XmlElementRef(name = "sqlselect", type = Sqlselect.class),
        @XmlElementRef(name = "savefieldids", type = Savefieldids.class),
        @XmlElementRef(name = "selectonload", type = Selectonload.class),
        @XmlElementRef(name = "jsonrpc", type = Jsonrpc.class),
        @XmlElementRef(name = "sqlinsert", type = JAXBElement.class),
        @XmlElementRef(name = "sqlselectcount", type = Sqlselectcount.class),
        @XmlElementRef(name = "sqlupdate", type = JAXBElement.class),
        @XmlElementRef(name = "sqldelete", type = JAXBElement.class)
    })
    protected List<Object> jsonrpcOrSelectonloadOrSqlselect;

    /**
     * Gets the value of the jsonrpcOrSelectonloadOrSqlselect property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jsonrpcOrSelectonloadOrSqlselect property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJsonrpcOrSelectonloadOrSqlselect().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqlselect }
     * {@link Savefieldids }
     * {@link Selectonload }
     * {@link Jsonrpc }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Sqlselectcount }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<Object> getJsonrpcOrSelectonloadOrSqlselect() {
        if (jsonrpcOrSelectonloadOrSqlselect == null) {
            jsonrpcOrSelectonloadOrSqlselect = new ArrayList<Object>();
        }
        return this.jsonrpcOrSelectonloadOrSqlselect;
    }

}
