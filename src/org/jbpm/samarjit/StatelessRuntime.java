package org.jbpm.samarjit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.drools.SessionConfiguration;
import org.drools.event.ProcessEventSupport;
import org.drools.event.process.ProcessEventListener;
import org.drools.runtime.process.ProcessInstance;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.time.impl.JDKTimerService;
import org.jbpm.process.instance.ProcessInstanceManager;
import org.jbpm.process.instance.impl.DefaultProcessInstanceManager;
import org.jbpm.process.instance.timer.TimerManager;
import org.drools.definition.process.Process;


public class StatelessRuntime {

	public static StatelessRuntime eINSTANCE = new StatelessRuntime();
	private StatelessSignalManager signalManager = new StatelessSignalManager(); 
	private TimerManager timerManager = new TimerManager(null/*kruntime*/, new JDKTimerService());
	private StatelessProcessInstance processInstance = null;
	
	
	private ProcessEventSupport eventSupport;
	private StatelessWorkItemManager workItemManager = null;
	private ProcessInstanceManager processInstanceManager  = new ProcessInstanceManager() {
		private Map<Long, ProcessInstance> processInstances = new HashMap<Long, ProcessInstance>();
	    private int processCounter = 0;

	    public void addProcessInstance(ProcessInstance processInstance) {
	        ((StatelessProcessInstance) processInstance).setId(++processCounter);
	        internalAddProcessInstance(processInstance);
	    }
	    
	    public void internalAddProcessInstance(ProcessInstance processInstance) {
	    	processInstances.put(((ProcessInstance)processInstance).getId(), processInstance);
	    }

	    public Collection<ProcessInstance> getProcessInstances() {
	        return Collections.unmodifiableCollection(processInstances.values());
	    }

	    public ProcessInstance getProcessInstance(long id) {
	    	if(processInstances.isEmpty()){
	    		System.out.println("Accessing ProcessInstance after process ended!");
	    		return processInstance;
	    	}
	    	return (ProcessInstance) processInstances.get(id);
	    }

	    public void removeProcessInstance(ProcessInstance processInstance) {
	        internalRemoveProcessInstance(processInstance);
	    }

	    public void internalRemoveProcessInstance(ProcessInstance processInstance) {
	        processInstances.remove(((ProcessInstance)processInstance).getId());
	        System.out.println("ProcessInstance getting removed");
	    }
	    
	    public void clearProcessInstances() {
	    	processInstances.clear();
	    	System.out.println("Clearing processInstance");
	    }
	}; // we are stateless but this class can have multiple processes.
	
	private StatelessRuntime(){
		eventSupport = new ProcessEventSupport();
	}

	public void setProcessInstanceManager(ProcessInstanceManager processInstanceManager) {
		System.out.println("We are stateless this should not come!!");
		this.processInstanceManager = processInstanceManager;
	}

	public ProcessEventSupport getEventSupport() {
		return eventSupport;
	}

	public void setEventSupport(ProcessEventSupport eventSupport) {
		this.eventSupport = eventSupport;
	}
	public void addEventListener(ProcessEventListener l){
		eventSupport.addEventListener(l);
	}
	public void removeEventListener(ProcessEventListener l){
		eventSupport.removeEventListener(l);
	}
	public TimerManager getTimerManager() {
		return  timerManager;
	}

	public void setNodeInstance(StatelessNodeInstanceImpl statelessNodeInstanceImpl) {
		 //Part of Runtime ProcessContext which has Runtime
	}

	public StatelessWorkItemManager getWorkItemManager(){
		if ( workItemManager == null ) {
            workItemManager =  new StatelessWorkItemManager();
            
            Map<String, WorkItemHandler> workItemHandlers = SessionConfiguration.getDefaultInstance().getWorkItemHandlers();
            if (workItemHandlers != null) {
                for (Map.Entry<String, WorkItemHandler> entry: workItemHandlers.entrySet()) {
                    workItemManager.registerWorkItemHandler(entry.getKey(), entry.getValue());
                }
            }
        }
		
        return      workItemManager;
//		return this.workItemMananger ;
	}

	public void setSignalManager(StatelessSignalManager signalManager) {
		this.signalManager = signalManager;
	}

	public StatelessSignalManager getSignalManager() {
		return signalManager;
	}

	public ProcessInstanceManager getProcessInstanceManager() {
		
		return (ProcessInstanceManager )processInstanceManager ; //we are stateless
	}
	//use factory
	@Deprecated 
	public void createProcessInstance(Process process){
	//I think registry is required;
		processInstance = new StatelessProcessInstance(process);
		processInstanceManager.addProcessInstance(processInstance);
	}
	
	public void startProcess(Process process){
		processInstance = new StatelessProcessInstance(process); //later on make it static to have only one instance
		processInstanceManager.addProcessInstance(processInstance);
		processInstance.start();
	}
}
