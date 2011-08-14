package com.ycs.fe.actions;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.ycs.exception.FrontendException;
import com.ycs.exception.ValidationException;
import com.ycs.fe.commandprocessor.ReturnCommandProcessor;
import com.ycs.fe.crud.CommandProcessor;
import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.PageReturnType;
import com.ycs.fe.dto.ResultDTO;
import com.ycs.fe.util.FEValidator;


public class CommonActionSupport extends ActionSupport {
	private Logger logger = Logger.getLogger(getClass());
	

	private static final long serialVersionUID = 1L;
	
	protected String submitdata;
	private String desc;
	protected InputStream inputStream;
	protected String screenName;
	protected Map<String, Object> session;
	private String submitdatatxncode; 
	protected String resultPage;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String commonExecute() throws Exception{
		String resultHtml = "";
		logger.debug("submitdata:"+submitdata);
		JSONObject jsonRecord =   JSONObject.fromObject(submitdata);
		InputDTO inputDTO = populateInputDTO(jsonRecord);
	
		try {
			 
			ResultDTO resDTO = validate(jsonRecord);
			resDTO = commandProcessor(jsonRecord, resDTO);
			
			populateActionErrors(resDTO);
			
			JSONObject resjsonResult = JSONObject.fromObject(resDTO);
			resultHtml = resjsonResult.toString();
			
		}catch (Exception e){
			logger.error("unknown exception",e);
//			throw new Exception("error.global");
		}
		System.out.println("result beginning to process");
		PageReturnType pg = setResult(resultHtml, jsonRecord);
		return pg.resultName;
	}

	public String execute() throws Exception{
		return commonExecute();
	}
	
	/**
	 * @param resDTO
	 */
	protected void populateActionErrors(ResultDTO resDTO) {
		if(resDTO!=null && resDTO.getErrors() != null && resDTO.getErrors().size() >0){
			for (String errorStr : resDTO.getErrors()) {
				addActionError(errorStr);
			}
		}
	}

	/**
	 * @param jsonRecord
	 * @return
	 */
	protected InputDTO populateInputDTO(JSONObject jsonRecord) {
		InputDTO inputDTO = new InputDTO();
		inputDTO.setData((JSONObject) jsonRecord);
		return inputDTO;
	}
	
	/**
	 * @depends screenName
	 * @param jsonRecord
	 * @param validationResultDTO if error is there it will not process just return the same resultDTO, suitable to chain 
	 * on top of validation resultDTO
	 * @return resultDTO with error, message, pagination
	 */
	protected ResultDTO commandProcessor(JSONObject jsonRecord, ResultDTO validationResultDTO) {
		if (validationResultDTO != null && validationResultDTO.getErrors() != null && validationResultDTO.getErrors().size() > 0) {
			return validationResultDTO;
		}
		CommandProcessor cmdpr = new CommandProcessor();
		ResultDTO resDTO = cmdpr.commandProcessor(jsonRecord, screenName);
		
		if(resDTO == null) {
			resDTO = new ResultDTO();
			resDTO.addError("error.commandprocessing");
		}
		
		return resDTO;
	}

	/**
	 * @param resultHtml to be converted into inputStream for ajax
	 * @param jsonRecord
	 * @return nextScreenName *.page, resultName = struts result name, resultPage= *.ftl,*.jsp,*.vm 
	 * @throws FrontendException
	 * @throws Exception
	 */
	private PageReturnType setResult(String resultHtml, JSONObject jsonRecord) throws FrontendException, Exception {
		PageReturnType pg = null;
		try{
			pg = new ReturnCommandProcessor().getReturnType(screenName, jsonRecord);
			screenName = pg.nextScreenName;
			resultPage = pg.resultPage;
		
			if("ajax".equals(pg.resultName)){
				inputStream = new StringBufferInputStream(resultHtml );
			}
		}catch(FrontendException e){
			logger.error("error.processingresult",e);
			throw new FrontendException("error.nextpagenotfound");
		}catch (Exception e){
			logger.error("error.processingresult",e);
			throw new Exception("error.global");
		}
		logger.debug("resultName = "+pg.resultName);
		logger.debug("screenName = "+pg.nextScreenName);
		logger.debug("resultPage = "+pg.resultPage);
		
		return pg;
	}

	/**
	 * It will populate the errors in returned resultDTO. For subsequent validation check for errors in resultDTO
	 * @param jsonRecord
	 * @return
	 * @throws ValidationException
	 */
	private ResultDTO validate(JSONObject jsonRecord) throws ValidationException {
		FEValidator validator = new FEValidator();
		ResultDTO validatorDTO = validator.validate(screenName, jsonRecord);
//		if (validatorDTO != null && validatorDTO.getErrors() != null && validatorDTO.getErrors().size() > 0) {
//				return false;
//		}
		return validatorDTO;
	}

	public String getSubmitdata() {
		return submitdata;
	}

	public void setSubmitdata(String submitdata) {
		this.submitdata = submitdata;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getResultPage() {
		return resultPage;
	}

	public void setResultPage(String resultPage) {
		this.resultPage = resultPage;
	}
	
	
}
