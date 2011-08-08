package com.ycs.fe.commandprocessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ycs.fe.businesslogic.BaseBL;
import com.ycs.fe.cache.BusinessLogicFactory;
import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.ResultDTO;
import com.ycs.fe.util.ParseSentenceOgnl;
import com.ycs.fe.util.ScreenMapRepo;
import com.ycs.fe.util.SentenceParseException;

public class BlCommandProcessor implements BaseCommandProcessor {
	private static Logger logger = Logger.getLogger(BlCommandProcessor.class);

	@Override
	public ResultDTO processCommand(String screenName, String querynodeXpath, JSONObject jsonRecord, InputDTO inputDTO, ResultDTO resultDTO) {
		logger.debug("Currently processing record:");
		BaseBL bl = BusinessLogicFactory.getBusinessLogic(screenName);
		ResultDTO resDTO = bl.executeCommand(screenName, querynodeXpath, jsonRecord, inputDTO, resultDTO /*previous result*/);
		return resDTO;
	
	public static void main(String[] args){
		BlCommandProcessor bl =  new BlCommandProcessor();
		bl.processCommand("cardOrder", "buslogic[@id='plasticDetails']", null , null , null);
	}

}
