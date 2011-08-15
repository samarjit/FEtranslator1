package com.ycs.fe.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.ycs.exception.FrontendException;

public class LabelFactory implements LocaleProvider{
	private static Logger logger = Logger.getLogger(LabelFactory.class);
	private  TextProvider textProvider;
	
	public static LabelFactory INSTANCE = new LabelFactory();
	
	//Singleton 
	private LabelFactory(){}
	
	/**
	 * @param screenName
	 * @param fieldName
	 * @return label from screen map xml or fieldName if label not found 
	 */
	public String getLabel(String screenName, String fieldName){
		String label = null;
		try {
			Element root = ScreenMapRepo.findMapXMLRoot(screenName);
			Element labelElm = (Element) root.selectSingleNode("/root/panels/panel/fields/field/label[@forname='"+fieldName+"']");
			String key = labelElm.attributeValue("key");
			String value = labelElm.attributeValue("value");
			label = getTextProvider().getText(key);
			
//			AppCacheManager.putElementInCache(screenName+"_label", "label", labelList);
			
			if(!(label != null && !"".equals(label))){
				//label not found
				label = value;
			}
		} catch (FrontendException e) {
			logger.debug("Label retrieval failed for label"+fieldName + "screen "+screenName);
			label = fieldName;
		}
			
		return label;
	}
	
	
	private TextProvider getTextProvider()
	{
	  if (this.textProvider == null) {
	    TextProviderFactory tpf = new TextProviderFactory();
	     
	    this.textProvider = tpf.createInstance(super.getClass(), this);
	  }
	  return   this.textProvider;
	}

	@Override
	public Locale getLocale() {
		 ActionContext ctx = ActionContext.getContext();
		  if (ctx != null) {
		    return ctx.getLocale();
		  }
		  System.out.println("Action context not initialized");
		  return null;
	}
}
