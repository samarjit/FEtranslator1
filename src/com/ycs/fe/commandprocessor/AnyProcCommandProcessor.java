package com.ycs.fe.commandprocessor;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.ResultDTO;

public class AnyProcCommandProcessor implements BaseCommandProcessor {

	private static Logger logger = Logger.getLogger(AnyProcCommandProcessor.class);

	@Override
	public ResultDTO processCommand(String screenName, String querynodeXpath,JSONObject jsonRecord, InputDTO inputDTO, ResultDTO resultDTO) {
		logger.debug("Processing AnyProc call");
		return null;
	}

}
