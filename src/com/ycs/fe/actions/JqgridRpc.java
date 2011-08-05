package com.ycs.fe.actions;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.ycs.fe.businesslogic.BaseBL;
import com.ycs.fe.cache.BusinessLogicFactory;
import com.ycs.fe.crud.CommandProcessor;
import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.PaginationDTO;
import com.ycs.fe.dto.ResultDTO;
import com.ycs.fe.util.ScreenMapRepo;


public class JqgridRpc extends ActionSupport {
	 
	private static final long serialVersionUID = -623830420192157346L;


	private Logger logger = Logger.getLogger(JqgridRpc.class);
	
	
	private String command;
	private String screenName;
	private InputStream inputStream;
	private String panelName;
	private String submitdata = "{}";
    private String data;
	private int page;
	private int rows;
	private String sidx;
	private String sord;
	
	public JqgridRpc() {
		super();
		screenName="ProgramSetup";
		panelName="form1";
		command="selectonload";
	}
	
	
	
	@Action(value="jqgrid",params={"configxml","ProductSetup.xml"},
			results={@Result(name="success",type="stream",params={"contentType","text/html","inputName","inputStream","resultxml","ProductSetup.xml"})}
//	results={@Result(name="success",location="/test.jsp")}
	)
	public String execute(){
		System.out.println("js RPC called with command:"+command+" for screen:"+screenName);
		BaseBL bl = BusinessLogicFactory.getBusinessLogic(screenName);
		
		if(bl != null)
		  bl.preJsRPCListerner(ActionContext.getContext().getActionInvocation());
		
		String path = ScreenMapRepo.findMapXMLPath(screenName);
		String parsedquery = "";
		ResultDTO resDTO = new ResultDTO();
		
		ValueStack stack = ActionContext.getContext().getValueStack();
		try {
			 logger.debug(path);
				Document doc = new SAXReader().read(path);
				Element root = doc.getRootElement();
				 
				logger.debug("JsonRPC with submitdata="+submitdata);
				JSON submitdataObj = JSONObject.fromObject(submitdata);
			
				InputDTO inputDTO = new InputDTO();
				inputDTO.setData((JSONObject) submitdataObj);
				
				PaginationDTO pageDetails = new PaginationDTO(page,rows,sidx,sord);
				inputDTO.setPagination(pageDetails);
				
				ActionContext.getContext().getValueStack().getContext().put("inputDTO", inputDTO);
				
				CommandProcessor cmdpr = new CommandProcessor();
				resDTO = cmdpr.commandProcessor(submitdataObj, screenName, inputDTO);  
			 
		} catch (DocumentException e) {
			resDTO.addError("ERROR:"+e);
			e.printStackTrace();
		} catch (Exception e) {
			resDTO.addError("ERROR:"+e);
			e.printStackTrace();
		}
		
				
		logger.debug(stack.getContext().get("resultDTO"));
		
		if(bl !=null)
		 bl.postJsRPCListerner(ActionContext.getContext().getActionInvocation());
		
		Gson gson = new Gson();
		String json1 = gson.toJson(stack.getContext().get("resultDTO"));
		logger.debug("Gson result(not sent back to client):"+json1);
//		setResultDTO((ResultDTO)stack.getContext().get("resultDTO"));
		
//		try {
//			OgnlContext context = new OgnlContext();
//			Object expression = Ognl.parseExpression("resultDTO.data.form1[0].txtnewprogname");
//			logger.debug(Ognl.getValue(expression,stack.getContext()));
			logger.debug(stack.findString("#resultDTO.data.form1[0].countryofissue" ));
//		} catch (OgnlException e1) {
//			e1.printStackTrace();
//		}
		
			String jj = "{'page':'1','total':2,'records':'13','rows':[{'id':'13','cell':['13','2007-10-06','Client3','1000.00','0.00','1000.00','']}," +
					"{'id':'12','cell':['12','2007-10-06','Client2','700.00','140.00','840.00','']}," +
					"{'id':'11','cell':['11','2007-10-06','Client1','600.00','120.00','720.00','']}," +
					"{'id':'10','cell':['10','2007-10-06','Client2','100.00','20.00','120.00','']}," +
					"{'id':'9','cell':['9','2007-10-06','Client1','200.00','40.00','240.00','']}," +
					"{'id':'8','cell':['8','2007-10-06','Client3','200.00','0.00','200.00','']}," +
					"{'id':'7','cell':['7','2007-10-05','Client2','120.00','12.00','134.00','']}," +
					"{'id':'6','cell':['6','2007-10-05','Client1','50.00','10.00','60.00','']}," +
					"{'id':'5','cell':['5','2007-10-05','Client3','100.00','0.00','100.00','no tax at all']}," +
					"{'id':'4','cell':['4','2007-10-04','Client3','150.00','0.00','150.00','no tax']}]," +
					"'userdata':{'amount':3220,'tax':342,'total':3564,'name':'Totals:'}}";
			
		JSONObject jobj = JSONObject.fromObject(jj);
//		JSONObject jobj = JSONObject.fromObject(resDTO);
//		try {
//			jobj.put("data",resDTO.getData());
//			jobj.put("pagination",resDTO.getPagination());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		String json = jobj.toString();
		
		
		logger.debug("Sent back to client:"+json);
		inputStream = new StringBufferInputStream(json);
		 
		return "success";
	}
	public static void main(String[] args) {
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCommand() {
		return command;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setSubmitdata(String submitdata) {
		this.submitdata = submitdata;
	}
	public String getSubmitdata() {
		return submitdata;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
}
