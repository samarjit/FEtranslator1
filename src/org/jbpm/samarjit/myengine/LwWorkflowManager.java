package org.jbpm.samarjit.myengine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.drools.definition.process.Process;
import org.drools.xml.SemanticModules;
import org.jbpm.bpmn2.xml.BPMNDISemanticModule;
import org.jbpm.bpmn2.xml.BPMNExtensionsSemanticModule;
import org.jbpm.bpmn2.xml.BPMNSemanticModule;
import org.jbpm.compiler.xml.XmlProcessReader;
import org.xml.sax.SAXException;

public class LwWorkflowManager {

	private List<Process> processes = null;

	public Object readWorkflowFiles(FileInputStream fileInputStream) throws SAXException, IOException {
		SemanticModules modules = new SemanticModules();
//		modules.addSemanticModule(new ProcessSemanticModule());
		// modules.initSemanticModules();
		modules.addSemanticModule(new BPMNSemanticModule());
		modules.addSemanticModule(new BPMNDISemanticModule());
		modules.addSemanticModule(new BPMNExtensionsSemanticModule());
		XmlProcessReader reader = new XmlProcessReader(modules);
		reader.read(fileInputStream);
		processes = reader.getProcess();
		return processes;
	}
	
	
}
