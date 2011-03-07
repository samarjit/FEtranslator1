package com.ycs.fe.commandprocessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ycs.fe.dto.InputDTO;
import com.ycs.fe.dto.ResultDTO;

/**
 * jsonRecord will be like
 * “txnrec”:[{single:"",multiple:[{aaa:’11’,bbb:’22’,ccc:
 * ’33’},{aaa:’1’,bbb:’2’,ccc:’3’}], command=”TXNPROC1”}]}
 * 
 * @author Samarjit
 * 
 */
public class DmCommandProcessor implements BaseCommandProcessor {

	/**
	 * jsonRecord will be like “txnrec”:[{single:"",multiple:[{aaa:’11’,bbb:’22’,ccc:’33’},{aaa:’1’,bbb:’2’,ccc:’3’}],
	 *  command=”TXNPROC1”}]}
	 * 
	 */
	 
	@Override
	public ResultDTO processCommand(String screenName, String querynodeXpath, JSONObject jsonRecord, InputDTO inputDTO, ResultDTO resultDTO) {
		JSONObject singleRec = jsonRecord.getJSONObject("simple");
		JSONArray multipleRec = jsonRecord.getJSONArray("multiple");
		//TODO: Jamuna can you pls integrate the Data Manager part that you have written
		return null;
	}

}
