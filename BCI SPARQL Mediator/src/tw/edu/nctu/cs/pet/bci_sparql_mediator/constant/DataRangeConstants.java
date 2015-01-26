package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant;

import java.util.ArrayList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class DataRangeConstants {

	public static final ArrayList<String> RESOURCE_UTILIZATION;

	static {
		System.out.println(StringConstants.LOAD_DATA_RANGE_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		RESOURCE_UTILIZATION = xmlReader.getStringList("/constant/ontology/dataRange/array/RESOURCE/UTILIZATION/i/text()");

	}
	
	public static void printValue() {
		System.out.println(StringConstants.DATA_RANGE_CONSTANT_VALUE);

		PrintMessage.showValue("RESOURCE_UTILITY", RESOURCE_UTILIZATION);
		System.out.println();

	}

}
