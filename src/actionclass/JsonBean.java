package actionclass;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonArray;

@XmlRootElement
public class JsonBean {
	public String data1;
	public String data2;
	public String procname;
	public InputParam inputparam;
	public OutputParam outputparam;
}

@XmlRootElement
 class Struct {
//	@XmlAttribute
	public String name;
//	public String data1;
	 
	public List<Data> data1;
	@XmlRootElement
	static	   class Data {
//		    @XmlAttribute
			public String name;
//			@XmlAttribute
			public String type;
//			@javax.xml.bind.annotation.XmlValue
			public String value;
			
			
		}
}

	


@XmlRootElement
class InputParam {
	public Parameter parameter;

}

@XmlRootElement
class Parameter {
	public JsonArray array;
	public Struct struct;
	// public Data data;
	// public ArrayList<Data> multirow;
	 
	
}

@XmlRootElement
class OutputParam {
	public Parameter parameter;
}
