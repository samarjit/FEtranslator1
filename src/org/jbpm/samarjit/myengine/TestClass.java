package org.jbpm.samarjit.myengine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.drools.definition.process.Node;
import org.drools.definition.process.Process;
import org.drools.definition.process.WorkflowProcess;
import org.drools.xml.SemanticModules;
import org.jbpm.bpmn2.xml.BPMNDISemanticModule;
import org.jbpm.bpmn2.xml.BPMNExtensionsSemanticModule;
import org.jbpm.bpmn2.xml.BPMNSemanticModule;
import org.jbpm.compiler.xml.XmlProcessReader;
import org.xml.sax.SAXException;

public class TestClass {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException {
		SemanticModules modules = new SemanticModules();
//		modules.addSemanticModule(new ProcessSemanticModule());
		// modules.initSemanticModules();
		modules.addSemanticModule(new BPMNSemanticModule());
		modules.addSemanticModule(new BPMNDISemanticModule());
		modules.addSemanticModule(new BPMNExtensionsSemanticModule());
		XmlProcessReader reader = new XmlProcessReader(modules);
		reader.read(new FileReader("src/test/resources/BPMN2-Lane.bpmn2"));
		 List<Process> processes = reader.getProcess();
		 System.out.println("Process read...");
		WorkflowProcess process = (WorkflowProcess) processes.get(0);
		for (Node node : process.getNodes()) {
			System.out.println(node);
		}
	}

}
