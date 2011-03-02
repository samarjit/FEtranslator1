package actionclass;

import java.util.ArrayList;
import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;


public class ExampleXSLTAction extends ActionSupport{
	

	private static final long serialVersionUID = -8056602884103702806L;
	
	private String testHeader;
	private String testFooter;
	private String genderlist;
	private String checklist;
	private String list;
	
	
	public void setTestHeader(String arg) {
		testHeader = arg;
	}
	public String getTestHeader(){
		return testHeader;
	}
	public void setTestFooter(String arg) {
		testFooter = arg;
	}
	public String getTestFooter(){
		return testFooter;
	}
	
	public String getGenderlist() {
		HashMap hashMap = new HashMap();
		hashMap.put("Male", "Male");
		hashMap.put("Female", "Female");
		genderlist = hashMap.toString();
		return genderlist;
	}
	public String getChecklist() {
		HashMap hashMap = new HashMap();
		hashMap.put("1", "one");
		hashMap.put("2", "two");
		hashMap.put("3", "three");
		checklist = hashMap.toString();
		return checklist;
	}
	public String getList() {
		HashMap hashMap = new HashMap();
		hashMap.put("India", "India");
		hashMap.put("Singapore", "Singapore");
		hashMap.put("Malaysia", "Malaysia");
		list = hashMap.toString();
		return list;
	}
	
	public String preview() throws Exception {
		if((testHeader != null && testHeader.length() >0) &&
			(testFooter != null && testFooter.length() >0)){
           System.out.println("ExampleXSLTAction:showing preview in XSLT");
			return "preview";
		}else{
			System.out.println("ExampleXSLTAction: getting input header="+testHeader+", footer="+testFooter);
			return "input";
		}
	}
	
}