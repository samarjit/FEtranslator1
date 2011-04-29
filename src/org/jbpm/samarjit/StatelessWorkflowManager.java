package org.jbpm.samarjit;

import java.util.Map;

import org.jbpm.JbpmJUnitTestCase.TestWorkItemHandler;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;

public class StatelessWorkflowManager {
	public void getNextTask(){
		
	}
	
	public void startProcess(WorkflowProcessImpl procc){
		StatelessRuntime.eINSTANCE.startProcess(procc);
	}
	
	public StatelessRuntime getRuntime(){
		return StatelessRuntime.eINSTANCE;
	}
	
	public void completeWorkItem(long id, Map<String, Object> results){
		StatelessRuntime.eINSTANCE.getWorkItemManager().completeWorkItem(id, results);
	}
	
	public void signalEvent(){
		
	}

	public void registerWorkItemHandler(String workItemName, TestWorkItemHandler workItemHandler) {
		StatelessRuntime.eINSTANCE.getWorkItemManager().registerWorkItemHandler(workItemName, workItemHandler);
	}
	
}
