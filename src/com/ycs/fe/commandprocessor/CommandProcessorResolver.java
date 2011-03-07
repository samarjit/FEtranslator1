package com.ycs.fe.commandprocessor;

import org.apache.log4j.Logger;

/**
 * Edit this class in case new CommandProcessor is introduced
 * @author Samarjit
 *
 */
public class CommandProcessorResolver {
	private static Logger logger = Logger.getLogger(CommandProcessorResolver.class);
	
	public static BaseCommandProcessor getCommandProcessor(String strProcessor) {
		if(strProcessor.equals("crud"))return new CrudCommandProcessor();
		else if(strProcessor.equals("dm"))return new DmCommandProcessor();
		else if(strProcessor.equals("bl"))return new BlCommandProcessor();
		else {
			logger.error("Command Processor Not found for processor type:"+strProcessor);
			return null; //not implemented
		}
	}

	 
}
