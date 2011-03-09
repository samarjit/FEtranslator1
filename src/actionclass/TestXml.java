package actionclass;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.google.gson.JsonElement;

import repo.txnmap.generated.Root;
import repo.txnmap.generated.Txn;

public class TestXml {

	private Logger logger = Logger.getLogger(getClass());

	public void processTxn() {
		String resultHtml = null;
		String unique = new String();
		String application_name = "ICICI_BRUSER3_1298884319363";
		String transcode = "BNGPVW"; // will be coming form command
		JSONObject singleData = null;
		JSONArray multipleData = null;
		// creating a unique id.
		// unique id = transaction code.
		unique += "Henry";
		unique += "_" + System.currentTimeMillis();

		String xml = "<?xml version=\"1.0\"?>";
		xml += "<IDCT>";
		xml += "<TRANS_CODE>" + transcode + "</TRANS_CODE>";
		xml += "<IDCT_ID>" + application_name + "_" + unique + "</IDCT_ID>";
		xml += "<DATETIME>" + new Date().toString() + "</DATETIME>";
		xml += "<NET_ID>" + "BRUSER3" + "</NET_ID>";
		xml += "<MESSAGE_VER_NO>1.0</MESSAGE_VER_NO>";
		xml += "<CHANNEL_ID>WEB</CHANNEL_ID>";
		xml += "<MESSAGE_DIGEST>NO_DATA</MESSAGE_DIGEST>";
		xml += "<IDCT_STATUS>NO_DATA</IDCT_STATUS>";
		xml += "<IDCT_ERR_CODE>NO_DATA</IDCT_ERR_CODE>";
		xml += "<IDCT_MESSAGE_TYPE>01</IDCT_MESSAGE_TYPE>";

		logger.debug("input XML : " + xml);
		try {

			String submitdatatxncode = "{\"cmd\":\"STUCAP\",\"single\":{\"FF0151\":\"aaa\",\"FF0148\":\"bbb\",\"FF01258\":\"eee\"},\"multiple\":[{\"FF9000\":111,\"FF0151\":222,\"FF0152\":333},{\"FF0151\":555},{\"FF9000\":456,\"FF0151\":765,\"FF0152\":877}]}";

			submitdatatxncode = "{'cmd':'BNGPVW','multiple':[{'FF0143':'100001'},{'FF0143':'100002'}]}";

			logger.debug("submitdatatxncode : " + submitdatatxncode);

			JSONObject jobj1 = JSONObject.fromObject(submitdatatxncode);

			if (jobj1.containsKey("single"))
				singleData = jobj1.getJSONObject("single");

			if (jobj1.containsKey("cmd"))
				transcode = jobj1.getString("cmd");

			if (jobj1.containsKey("multiple"))
				multipleData = jobj1.getJSONArray("multiple");

			final JAXBContext jc = JAXBContext.newInstance(Root.class);
			final Root root = (Root) jc
					.createUnmarshaller()
					.unmarshal(
							new File(
									"C:/Eclipse/workspace/FEtranslator1/src/repo/txnmap/nrow_txnmap.xml"));
			String[] arSingle = null;
			String[] arMultiple = null;
			for (Txn txn : root.getTxn()) {
				if (txn.getId().equals(transcode)) {
					String strReqSingle = txn.getReq().getSingle();
					if (strReqSingle != null) {
						arSingle = strReqSingle.split(",");
						logger.debug("Single : " + strReqSingle);
					}
					String strReqMultiple = txn.getReq().getMultiple();
					if (strReqMultiple != null) {
						arMultiple = strReqMultiple.split(",");
						logger.debug("Multiple :" + strReqMultiple);
					}
				}
			}

			// update single data in XML
			if (arSingle != null) {
				xml += "<IDCT_DATA>";
				for (String columnName : arSingle) {
					int index = columnName.indexOf(":");
					if (index != -1)
						columnName = columnName.substring(0, index);
					Object snglDtval = null;
					if (singleData != null) {
						if (singleData.containsKey(columnName))
							snglDtval = singleData.get(columnName);
						if (snglDtval != null) {
							xml += "<" + columnName + ">"
									+ snglDtval.toString() + "</" + columnName
									+ ">";
						} else {
							xml += "<" + columnName + ">NO_DATA</" + columnName
									+ ">";
						}
					} else {
						xml += "<" + columnName + ">NO_DATA</" + columnName
								+ ">";
					}
				}
				xml += "</IDCT_DATA>";
			}
			// Update multiple data in XML
			if (arMultiple != null) {
				if (multipleData != null) {
					for (int i = 0; i < multipleData.size(); i++) {
						xml += "<IDCT_DATA>";
						for (String columnName : arMultiple) {
							int index = columnName.indexOf(":");
							if (index != -1)
								columnName = columnName.substring(0, index);
							String mltplDtValue = null;

							if (multipleData.getJSONObject(i).containsKey(
									columnName)) {
								mltplDtValue = multipleData.getJSONObject(i)
										.getString(columnName);
							}
							if (mltplDtValue != null) {
								xml += "<" + columnName + ">" + mltplDtValue
										+ "</" + columnName + ">";
							} else {
								xml += "<" + columnName + ">NO_DATA</"
										+ columnName + ">";
							}

						} // end for
						xml += "</IDCT_DATA>";
					} // end for
				} else {
					xml += "<IDCT_DATA>";
					for (String columnName : arMultiple) {
						int index = columnName.indexOf(":");
						if (index != -1)
							columnName = columnName.substring(0, index);
						xml += "<" + columnName + ">NO_DATA</" + columnName
								+ ">";
					}
					xml += "</IDCT_DATA>";
				}
			}
			xml += "</IDCT>";
			logger.debug("Input Xml :  " + xml);
			System.out.println(xml);
		} catch (JSONException e) {
			logger.debug("submitdata parsing error", e);
			e.printStackTrace();
		} catch (JAXBException e) {
			logger.debug("submitdata parsing error", e);
			e.printStackTrace();
		}

		// inputStream = new StringBufferInputStream(resultHtml );

	}

	public static void main(String[] args) {
		TestXml test = new TestXml();
		test.processTxn();
	}
}
