package com.ycs.fe.actions;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import map.generated.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


@Action(value="jaxbtest",results={@Result(name="success",location="/pages/screenmap.jsp")})
public class JaxbTestAC extends ActionSupport{
 private String data;
 
private static final long serialVersionUID = -3779974905508711588L;
private Root screenroot; 

	public String execute(){
		System.out.println("JaxB Test screenMap ");
		if(screenroot != null){
			JSONObject json1 = new JSONObject(screenroot);
			System.out.println("json1="+json1);
		}
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Root.class);
			final Root root = (Root) jaxbContext
					.createUnmarshaller()
					.unmarshal(JaxbTestAC.class.getResourceAsStream("map/ProgramSetup.xml")
					//		new File(									"C:/Eclipse/workspace1/FEtranslator1/src/map/ProgramSetup.xml")
					);
			screenroot = root;
			JSONObject json = new JSONObject(root);
			Gson gson = new Gson();
			StringWriter writer = new StringWriter();
			 XMLSerializer serializer = getXMLSerializer(writer);
			jaxbContext.createMarshaller().marshal(root, serializer.asContentHandler());
//			String gsonstr = gson.toJson(root);
			System.out.println("writer="+writer.toString());
			System.out.println("JSON="+json);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
			final Root root =
	            (Root) jaxbContext.createUnmarshaller().unmarshal(JaxbTestAC.class.getResourceAsStream("map/ProgramSetup.xml")
	            //    new File("F:/eclipse/workspace/charts/FEtranslator1/src/map/ProgramSetup.xml")
	            );
			System.out.println(root.getPanels().getPanel().get(0).getContent());
			jaxbContext.createMarshaller().marshal(root, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	private static XMLSerializer getXMLSerializer(StringWriter writer) {
        // configure an OutputFormat to handle CDATA
        OutputFormat of = new OutputFormat();

        // specify which of your elements you want to be handled as CDATA.
        // The use of the '^' between the namespaceURI and the localname
        // seems to be an implementation detail of the xerces code.
        // When processing xml that doesn't use namespaces, simply omit the
        // namespace prefix as shown in the third CDataElement below.
        of.setCDataElements(
            new String[] { //"^panel",   // <ns1:foo>
                   "^text",   // <ns2:bar>
               //<stylesheets>
                     });   // <baz>

        // set any other options you'd like
        of.setPreserveSpace(false);
        of.setIndenting(true);
        
        // create the serializer
        XMLSerializer serializer = new XMLSerializer(of);
        serializer.setOutputCharStream(writer);

        return serializer;
    }
	
	public void setScreenroot(Root screenroot) {
		this.screenroot = screenroot;
	}
	public Root getScreenroot() {
		return screenroot;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	  

}
