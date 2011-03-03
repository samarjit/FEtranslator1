package map;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.opensymphony.xwork2.ActionContext;

 
 

public class ScreenMapRepo {
	private static  Logger logger = Logger.getLogger(ScreenMapRepo.class);
	
	
	/**
	 * This returns the mappings of screenName to XML config
	 * @param scrName
	 * @return path of mapping XML
	 */
	public static String findMapXML(String scrName){
		String path = null;
		//String tplpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/map"); 
		InputStream scrxml = ScreenMapRepo.class.getResourceAsStream("screenmap.xml");
		
		Document doc;
		try {
		
			doc = new SAXReader().read(scrxml);
			Element root = doc.getRootElement();
			Element n = (Element) root.selectSingleNode("screen[@name='"+scrName+"']");
			if(n == null){
				logger.debug("Mapping of <screen name="+scrName+" /> is not defined in screenmap.xml!");
				return null;
			}
			
			path = n.attributeValue("mappingxml");
			String tplpath = ServletActionContext.getServletContext().getRealPath("WEB-INF/classes");
			
			File f = new File(tplpath+"/"+path);
			path = f.getAbsolutePath();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	public static Element findMapXMLRoot(String scrName){
		String path = findMapXML(scrName);
		Element root = null;
		try {
			Document doc = new SAXReader().read(path);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			logger.debug("XML Load Exception"+path);
		}
		return root;
	}
	
	public static void main(String[] args) {
		System.out.println(ScreenMapRepo.findMapXML("ProgramSetup"));
	}
}
