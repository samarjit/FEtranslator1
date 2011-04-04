
package com.ycs.fe.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ycs.fe.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CallSP_QNAME = new QName("http://ws.plsqlcall/", "callSP");
    private final static QName _CallSPResponse_QNAME = new QName("http://ws.plsqlcall/", "callSPResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ycs.fe.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CallSPResponse }
     * 
     */
    public CallSPResponse createCallSPResponse() {
        return new CallSPResponse();
    }

    /**
     * Create an instance of {@link CallSP }
     * 
     */
    public CallSP createCallSP() {
        return new CallSP();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallSP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.plsqlcall/", name = "callSP")
    public JAXBElement<CallSP> createCallSP(CallSP value) {
        return new JAXBElement<CallSP>(_CallSP_QNAME, CallSP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallSPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.plsqlcall/", name = "callSPResponse")
    public JAXBElement<CallSPResponse> createCallSPResponse(CallSPResponse value) {
        return new JAXBElement<CallSPResponse>(_CallSPResponse_QNAME, CallSPResponse.class, null, value);
    }

}
