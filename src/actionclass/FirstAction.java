package actionclass;

import com.opensymphony.xwork2.ActionSupport;

public class FirstAction extends ActionSupport {

	private static final long serialVersionUID = -1040772028934340987L;

	public String preview() throws Exception {

		System.out.println("ExampleXSLTAction:showing preview in XSLT");

		return "input";
	}
}
