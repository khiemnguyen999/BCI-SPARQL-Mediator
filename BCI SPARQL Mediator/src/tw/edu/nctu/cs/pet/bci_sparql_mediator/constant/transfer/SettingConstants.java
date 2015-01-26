package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer;

import org.json.JSONObject;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class SettingConstants {

	public static final String OPERATION;
	public static final String RESULT;
	public static final String SUCCESSFULLY;
	public static final String FAILED;

	static {
		System.out.println(StringConstants.LOAD_SETTING_TRANSFER);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.TRANSFER_FILE);
		
		OPERATION                                   = xmlReader.getString("/transfer/setting/OPERATION/text()");
		RESULT                                      = xmlReader.getString("/transfer/setting/RESULT/text()");
		SUCCESSFULLY                                = xmlReader.getString("/transfer/setting/SUCCESSFULLY/text()");
		FAILED                                      = xmlReader.getString("/transfer/setting/FAILED/text()");
	}
	
	public static void printValue() {
		System.out.println(StringConstants.SETTING_TRANSFER_VALUE);

		PrintMessage.showValue("OPERATION", OPERATION);
		PrintMessage.showValue("RESULT", RESULT);
		PrintMessage.showValue("SUCCESSFULLY", SUCCESSFULLY);
		PrintMessage.showValue("FAILED", FAILED);
		System.out.println();
	}
	
	public static String toJson() {
		
		JSONObject setting = new JSONObject();
		setting.put("OPERATION", OPERATION);
		setting.put("RESULT", RESULT);
		setting.put("SUCCESSFULLY", SUCCESSFULLY);
		setting.put("FAILED", FAILED);
		
		return setting.toString();
	}

}
