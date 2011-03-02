package map.generated;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JaxbTestAC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
			final Root root =
	            (Root) jaxbContext.createUnmarshaller().unmarshal(
	                new File("F:/eclipse/workspace/charts/FEtranslator1/src/map/ProgramSetup.xml"));
			System.out.println(root.getPanels().getPanel().get(0).getContent());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

}
