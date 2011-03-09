
package map.generated;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence>
 *         &lt;element ref="{}jsonrpc" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}selectonload" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}sqlselect" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}savefieldids" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}sqlinsert" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}sqldelete" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}sqlselectcount" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}sqlupdate" maxOccurs="unbounded" minOccurs="0"/>
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
    "jsonrpc",
    "selectonload",
    "sqlselect",
    "savefieldids",
    "sqlinsert",
    "sqldelete",
    "sqlselectcount",
    "sqlupdate"
})
@XmlRootElement(name = "crud")
public class Crud {

    protected List<Jsonrpc> jsonrpc;
    protected List<Selectonload> selectonload;
    protected List<Sqlselect> sqlselect;
    protected List<Savefieldids> savefieldids;
    protected List<Sqlinsert> sqlinsert;
    protected List<Sqldelete> sqldelete;
    protected List<Sqlselectcount> sqlselectcount;
    protected List<Sqlupdate> sqlupdate;

    /**
     * Gets the value of the jsonrpc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jsonrpc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJsonrpc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Jsonrpc }
     * 
     * 
     */
    public List<Jsonrpc> getJsonrpc() {
        if (jsonrpc == null) {
            jsonrpc = new ArrayList<Jsonrpc>();
        }
        return this.jsonrpc;
    }

    /**
     * Gets the value of the selectonload property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectonload property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectonload().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Selectonload }
     * 
     * 
     */
    public List<Selectonload> getSelectonload() {
        if (selectonload == null) {
            selectonload = new ArrayList<Selectonload>();
        }
        return this.selectonload;
    }

    /**
     * Gets the value of the sqlselect property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sqlselect property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSqlselect().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqlselect }
     * 
     * 
     */
    public List<Sqlselect> getSqlselect() {
        if (sqlselect == null) {
            sqlselect = new ArrayList<Sqlselect>();
        }
        return this.sqlselect;
    }

    /**
     * Gets the value of the savefieldids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the savefieldids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSavefieldids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Savefieldids }
     * 
     * 
     */
    public List<Savefieldids> getSavefieldids() {
        if (savefieldids == null) {
            savefieldids = new ArrayList<Savefieldids>();
        }
        return this.savefieldids;
    }

    /**
     * Gets the value of the sqlinsert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sqlinsert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSqlinsert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqlinsert }
     * 
     * 
     */
    public List<Sqlinsert> getSqlinsert() {
        if (sqlinsert == null) {
            sqlinsert = new ArrayList<Sqlinsert>();
        }
        return this.sqlinsert;
    }

    /**
     * Gets the value of the sqldelete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sqldelete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSqldelete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqldelete }
     * 
     * 
     */
    public List<Sqldelete> getSqldelete() {
        if (sqldelete == null) {
            sqldelete = new ArrayList<Sqldelete>();
        }
        return this.sqldelete;
    }

    /**
     * Gets the value of the sqlselectcount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sqlselectcount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSqlselectcount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqlselectcount }
     * 
     * 
     */
    public List<Sqlselectcount> getSqlselectcount() {
        if (sqlselectcount == null) {
            sqlselectcount = new ArrayList<Sqlselectcount>();
        }
        return this.sqlselectcount;
    }

    /**
     * Gets the value of the sqlupdate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sqlupdate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSqlupdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sqlupdate }
     * 
     * 
     */
    public List<Sqlupdate> getSqlupdate() {
        if (sqlupdate == null) {
            sqlupdate = new ArrayList<Sqlupdate>();
        }
        return this.sqlupdate;
    }

}
