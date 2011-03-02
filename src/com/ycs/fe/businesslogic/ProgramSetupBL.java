package com.ycs.fe.businesslogic;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionInvocation;

public class ProgramSetupBL implements BaseBL {

	@Override
	public HashMap preJsRPCListerner(ActionInvocation invocation) {
		System.out.println("pre JsRPC BL");
		return null;
	}

	@Override
	public HashMap postJsRPCListerner(ActionInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap preCrudListener(ActionInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap postCrudListener(ActionInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap preWorkflowListener(ActionInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap postWorkflowListener(ActionInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
