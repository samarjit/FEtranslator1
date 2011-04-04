package actionclass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;

public class AnyProcCall {
	private Logger logger = Logger.getLogger(getClass());
	
	public void callProcedure(){
		String jsondata = "{'procname':'WS_TEST_PROC','inputparam':[{'array':[{'struct':" +
				"{'name':'AAA','email':'aaa@f'}},{'struct':{'name':'AAA','email':'aaa@f'}}]}," +
				"{'data1':{'count':'2'}},{'array':[{'name':'AAA','email':'aaa@f'},{'name':'AAA','email':'aaa@f'}]}]," +
				"'outputparam':{'parameter':'param3'}}";
		
		
		String inputDTO = "{form1:[{'txtdata1':'val2',  command:'ANYPROC'}, {'txtdata1':'val1', command:'ANYPROC'}]}";
		String json = "{'name':'TESTTYPE', 'data1':[{'name':'NAME', 'type':'VARCHAR' ,'value':'Jam'},{'name':'NAME', 'type':'VARCHAR' ,'value':'Jam'}]}";
//		String json = "{ 'data1':'MULTI_ROW', 'data2':'trt' }  ";
		
		
//		Struct struct = new Struct();
//		JSONObject jsonRecord = JSONObject.fromObject(json);
//		JSONObject jstruct = jsonRecord.getJSONObject("struct");
//		struct.setName(jstruct.getString("name"));
//		JSONArray jdata = jstruct.getJSONArray("data1");
//		ArrayList<Data> datalist = new ArrayList<Data>();
//			for(int i=0;i<jdata.size();i++){
//				Data data = struct.new Data();
//				JSONObject jdataObj = jdata.getJSONObject(i);
//				data.setName(jdataObj.getString("name"));
//				data.setType(jdataObj.getString("type"));
//				data.setValue(jdataObj.getString("value"));
//				datalist.add(data)	;		
//			}
//			struct.setDatalist(datalist);	
//		System.out.println("name : " + struct.getName());
		
		
		Gson gson = new Gson();
		
		Struct bean = gson.fromJson(json, Struct.class);
		System.out.println("TestJsonFromObject "+bean.name);
		System.out.println("TestJsonFromObject "+gson.toJsonTree(json));
		
		try {
			JSONObject jobj = JSONObject.fromObject(jsondata);
			XMLSerializer xl = new XMLSerializer();
			String str = xl.write(jobj);
		//	System.out.println(str);
			org.json.JSONObject jb = new org.json.JSONObject(jsondata);
			XML ml  = new XML();
			String str2 = ml.toString(jb);
		//	System.out.println(str2);
			
			 
			JAXBContext jaxbContext = JAXBContext.newInstance(Struct.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			//marshaller.marshal(bean, new FileOutputStream( "C:\\Users\\Jamuna\\Desktop\\test.xml"));
			marshaller.marshal(bean, System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateXML(){
		String jsondata = "{ 'procedure':{ 'procname':'WS_TEST_PROC', " +
		"'inputparam':{ 'parameter':[ { 'array':{ 'name':'TESTTYPE_ARRAY', 'struct':[ { 'name':'TESTTYPE', 'data1':[ { 'name':'NAME', 'type':'VARCHAR' }, " +
		"{ 'name':'EMAIL', 'type':'VARCHAR' } ] }, { 'name':'TESTTYPE', 'data1':[ { 'name':'NAME', 'type':'VARCHAR' }, { 'name':'EMAIL', 'type':'VARCHAR' } ] } ] } }," +
		" { 'data1':[{ 'type':'VARCHAR2' }] }, { 'array':{ 'name':'MULTI_ROW', 'data1':[ { 'type':'NUMBER' }, { 'type':'NUMBER' }, { 'type':'NUMBER' } ] } }]}, " +
		"'outputparam':{ 'parameter':'param3' } } } ";
		
		JSONObject jsonRecord = JSONObject.fromObject(jsondata);
		JSONObject procedure = jsonRecord.getJSONObject("procedure");
		String procname = procedure.getString("procname");
		String xml = "<root><procedure><procname>"+procname+"</procname>";
		JSONObject inputparam = procedure.getJSONObject("inputparam");
		JSONObject outputparam = procedure.getJSONObject("outputparam");
		JSONArray inputparamlist = inputparam.getJSONArray("parameter");
		xml+= "<inputparam>";
		for(int i=0;i<inputparamlist.size();i++){
			JSONObject inparam = inputparamlist.getJSONObject(i);
			System.out.println(inparam.toString());
			xml+= "<parameter>";
			if(inparam.containsKey("array")){
				JSONObject array = inparam.getJSONObject("array");
				String arrayname = array.getString("name");
				xml+= "<ARRAY name=\""+arrayname+"\">";
				if(array.containsKey("struct")){
					xml = structparser(array,xml);
				}else if(array.containsKey("data1")){
					if(array.isArray()){
						JSONArray dataarr = array.getJSONArray("data1");
						xml = arrayDataParser((JSONArray) array.get("data1"),xml);
						}else{
							xml = dataParser(array, xml);
						}
				}
				
				xml+="</ARRAY>";
			}else if(inparam.containsKey("data1")){
				Object oo = inparam.get("data1");
				
				System.out.println("hello"+oo);
		//		JSONObject jj = inparam.getJSONObject("data1");
			//	System.out.println("hello"+jj);
//				JSONArray dataarr = inparam.getJSONArray("data1");
//				xml = arrayDataParser(dataarr,xml);
				
			}else if(inparam.containsKey("struct")){
				xml = structparser(inparam,xml);
			}
			
			xml+= "</parameter>";	
		}//end for
		xml+= "</inputparam>";
		//outputparam
		xml += "</procedure></root>";
		System.out.println(xml);
		

	}
	
	public String arrayDataParser(JSONArray dataarr, String xml ){
		
		//JSONArray dataarr = jsonobj.getJSONArray("data1");
		for(int k=0;k<dataarr.size();k++){
			JSONObject dataele = dataarr.getJSONObject(k);
			xml = dataParser(dataele,xml);
		}
		return xml;
	}
	
	public String dataParser(JSONObject dataele, String xml ){
		String value = "";
			xml += "<data1";
			if(dataele.containsKey("name")){
				String dataname = dataele.getString("name");
				xml += " name="+dataname;
			}	
			if(dataele.containsKey("type")){
				String dataname = dataele.getString("type");
				xml += " type="+dataname;
			}	
			if(dataele.containsKey("value")){
				 value = dataele.getString("value");
			}	
			xml += ">"+value+"</data1>";
		return xml;
	}
	
	
	public String structparser(JSONObject json, String xml){
		JSONArray structarr = json.getJSONArray("struct");
		for(int j=0;j<structarr.size();j++){
			JSONObject structele = structarr.getJSONObject(j);
			String sname = structele.getString("name");
			xml += "<STRUCT name=\""+sname+"\">";
			if(structele.containsKey("data1")){
				JSONArray dataarr = structele.getJSONArray("data1");
				xml = arrayDataParser(dataarr,xml);
			}
			xml += "</STRUCT>";
		}
		return xml;
	}
	
	
	public void createXMLDoc(){
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder buildr = null;
		try {
			buildr = fact.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document xmldoc = buildr.newDocument(); 
		Element ele = xmldoc.createElement("root"); 
		ele.appendChild(xmldoc.createTextNode("D"));
		System.out.println("xmlcod:"+xmldoc.toString());
		
	}
	
	public void anyprocCallXMl(){
		String jsondata = "{'procname':'WS_TEST_PROC','inputparam':[{'array':[{'struct':" +
		"{'name':'AAA','email':'aaa@f'}},{'struct':{'name':'AAA','email':'aaa@f'}}]}," +
		"{'data1':{'count':'2'}},{'array':[{'name':'AAA','email':'aaa@f'},{'name':'AAA','email':'aaa@f'}]}]," +
		"'outputparam':{'parameter':'param3'}}";
	}
	
	public static void main(String[] args){
		AnyProcCall test = new AnyProcCall();
		test.callProcedure();
//		test.generateXML();
	}
}
