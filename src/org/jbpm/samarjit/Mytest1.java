package org.jbpm.samarjit;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DroolsError;
import org.drools.compiler.PackageBuilder;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.process.Process;
import org.drools.definition.process.WorkflowProcess;
import org.drools.definitions.impl.KnowledgePackageImp;
import org.drools.event.process.ProcessCompletedEvent;
import org.drools.event.process.ProcessEvent;
import org.drools.event.process.ProcessEventListener;
import org.drools.event.process.ProcessNodeLeftEvent;
import org.drools.event.process.ProcessNodeTriggeredEvent;
import org.drools.event.process.ProcessStartedEvent;
import org.drools.event.process.ProcessVariableChangedEvent;
import org.drools.io.ResourceFactory;
import org.drools.lang.descr.PackageDescr;
import org.drools.rule.Package;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.drools.runtime.process.WorkItem;
import org.drools.xml.SemanticModules;
import org.jbpm.JbpmJUnitTestCase.TestWorkItemHandler;
import org.jbpm.bpmn2.core.Definitions;
import org.jbpm.bpmn2.xml.BPMNDISemanticModule;
import org.jbpm.bpmn2.xml.BPMNExtensionsSemanticModule;
import org.jbpm.bpmn2.xml.BPMNSemanticModule;
import org.jbpm.bpmn2.xml.XmlBPMNProcessDumper;
import org.jbpm.compiler.xml.XmlProcessReader;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.xml.sax.SAXException;

public class Mytest1 {
	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		PackageBuilder builder = new PackageBuilder();
		// Set the system property so that automatic conversion can happen
		System.setProperty("drools.ruleflow.port", "true");
		InputStream in = new FileInputStream("src/test/resources/BPMN2-Lane.bpmn2");
		// InputStream in =
		// Mytest.class.getResourceAsStream("/org/jbpm/integrationtests/test_ProcessMultithreadEvent.rf"
		// );

		builder.addPackage(new PackageDescr("com.sample"));
		builder.addRuleFlow(new InputStreamReader(in));

		if (!builder.getErrors().isEmpty()) {
			for (DroolsError error : builder.getErrors().getErrors()) {
				System.err.println(error);
			}
			System.out.println("Could not build process");
		}
		Package pkg = builder.getPackage();
		System.out.println(pkg.getName());

		List<KnowledgePackage> pkgs = new ArrayList<KnowledgePackage>();
		pkgs.add(new KnowledgePackageImp(pkg));

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newFileResource("src/test/resources/BPMN2-Lane.bpmn2"), ResourceType.BPMN2);
		// // KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		// kbase.addKnowledgePackages( pkgs );
		KnowledgeBase kbase = kbuilder.newKnowledgeBase();
		SemanticModules modules = new SemanticModules();
//		modules.addSemanticModule(new ProcessSemanticModule());
		// modules.initSemanticModules();
		modules.addSemanticModule(new BPMNSemanticModule());
		modules.addSemanticModule(new BPMNDISemanticModule());
		modules.addSemanticModule(new BPMNExtensionsSemanticModule());
		XmlProcessReader reader = new XmlProcessReader(modules);
		reader.read(new FileReader("src/test/resources/BPMN2-Lane.bpmn2"));
		 List<Process> processes = reader.getProcess();
//		Collection<Process> processes = kbase.getProcesses();
		for (Process process : processes) {
			System.out.println(process.getId());
			RuleFlowProcess ruleFlowProcess = (RuleFlowProcess) process;
			System.out.println(XmlBPMNProcessDumper.INSTANCE.dump(ruleFlowProcess));
			for (Entry<String, Object> map : process.getMetaData().entrySet()) {
				System.out.println("map:" + map.getKey() + " " + map.getValue());
			}
			System.out.println(process.getId());
			Definitions def = (Definitions) process.getMetaData("Definitions");
			System.out.println(def.getTargetNamespace());
			System.out.println(process.getName());
			System.out.println(process.getPackageName());
			System.out.println(process.getType());
			Definitions def2 = (Definitions) process.getMetaData().get("Process");
			WorkflowProcessImpl wflp = (WorkflowProcessImpl)process;
			WorkflowProcess proc = (WorkflowProcess) process;


			System.out.println(wflp.getNodes());
			System.out.println(proc.getNodes());
		}
		
if (1 == 0 )return ;
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		final List<ProcessEvent> processEventList = new ArrayList<ProcessEvent>();
		final ProcessEventListener processEventListener = new ProcessEventListener() {
			public void afterNodeLeft(ProcessNodeLeftEvent event) {
//				processEventList.add(event);
			}

			public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
				processEventList.add(event);
			}

			public void afterProcessCompleted(ProcessCompletedEvent event) {
//				processEventList.add(event);
			}

			public void afterProcessStarted(ProcessStartedEvent event) {
//				processEventList.add(event);
			}

			public void beforeNodeLeft(ProcessNodeLeftEvent event) {
//				processEventList.add(event);
			}

			public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
//				processEventList.add(event);
			}

			public void beforeProcessCompleted(ProcessCompletedEvent event) {
//				processEventList.add(event);
			}

			public void beforeProcessStarted(ProcessStartedEvent event) {
//				processEventList.add(event);
			}

			public void beforeVariableChanged(ProcessVariableChangedEvent event) {
				processEventList.add(event);
			}

			public void afterVariableChanged(ProcessVariableChangedEvent event) {
				processEventList.add(event);
			}
		};
		ksession.addEventListener(processEventListener);
		// execute the process
		ksession.signalEvent("SamarjitMyTestWflId_UserTask", null);

		TestWorkItemHandler workItemHandler = new TestWorkItemHandler();
		ksession.getWorkItemManager().registerWorkItemHandler("Human Task", workItemHandler);
		ProcessInstance processInstance = ksession.startProcess("SamarjitMyTestWflId_UserTask");
		
		
		System.out.println(processInstance.getState() == ProcessInstance.STATE_ACTIVE);
        HashMap<String, Object> results =new HashMap<String, Object>();
        results.put("ActorId", "mary");
        WorkItem workItem = workItemHandler.getWorkItem();
        System.out.println(workItem.getParameter("ActorId")); //expect john
		ksession.getWorkItemManager()
			.completeWorkItem(workItem.getId(), null);
		
		workItem = workItemHandler.getWorkItem();
		System.out.println(workItem.getParameter("ActorId")); //expect mary
		System.out.println(processInstance.getState() == ProcessInstance.STATE_ACTIVE);

		ksession.getWorkItemManager()
			.completeWorkItem(workItem.getId(), null);
		System.out.println(processInstance.getState() == ProcessInstance.STATE_ACTIVE);
		System.out.println("Process Events=" + processEventList);
	}
}