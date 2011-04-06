package com.ycs.workflow.actions;

import java.util.List;

import com.ycs.user.Task;

public class WorkflowController {
	
	public String getNextTask(String taskName){
		if(taskName.equals("LOGIN")){
			return "TASK1";
		}else if(taskName.equals("TASK1")){
			return "TASK2";
		}else if(taskName.equals("TASK2")){
			return "FLOW_COMPLETE";
		}
		
		return "TASK_NAME";
	}
	
	public void doTask(String taskName){
		System.out.println("Doing task"+taskName);
	}
	
	public List<Task> getCurrentTasks(){
		System.out.println("Not implemented in stateless workflow!");
		return null;
	}
	
}
