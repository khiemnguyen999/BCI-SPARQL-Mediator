package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class DebugConstants {

	public static final int DEBUG_STRING_LENGTH;
	public static final boolean DEBUG_SPARQL_REPOSITORY;
	public static final boolean DEBUG_VERSION_CONSTANTS;
	public static final boolean DEBUG_SPARQL_PROXY_CONSTANTS;
	public static final boolean DEBUG_SPARQL_SYNTAX_CONSTANTS;
	public static final boolean DEBUG_SPARQL_VARIABLE_CONSTANTS;
	public static final boolean DEBUG_ONTOLOGY_NAMESPACE_CONSTANTS;
	public static final boolean DEBUG_ONTOLOGY_ID_CONSTANTS;
	public static final boolean DEBUG_ONTOLOGY_CLASS_CONSTANTS;
	public static final boolean DEBUG_ONTOLOGY_PROPERTY_CONSTANTS;
	public static final boolean DEBUG_DATA_RANGE_CONSTANTS;
	public static final boolean DEBUG_DISPLAY_CONSTANTS;
	public static final boolean DEBUG_SETTING_TRANSFER_CONSTANTS;
	public static final boolean DEBUG_OPERATION_TRANSFER_CONSTANTS;
	public static final boolean DEBUG_DATA_TRANSFER_CONSTANTS;
	public static final boolean DEBUG_SPARQL_PROXY_CONFIG;
	public static final boolean DEBUG_SPARQL_ENDPOINT_CONFIG;

	static {

		System.out.println(StringConstants.LOAD_SPARQL_PROXY_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		DEBUG_STRING_LENGTH                = xmlReader.getDec("/constant/DEBUG/STRING_LENGTH/text()");
		
		DEBUG_SPARQL_REPOSITORY            = xmlReader.getBoolean("/constant/DEBUG/SPARQL_REPOSITORY/text()");
		
		DEBUG_VERSION_CONSTANTS            = xmlReader.getBoolean("/constant/DEBUG/VERSION_CONSTANTS/text()");
		DEBUG_SPARQL_PROXY_CONSTANTS       = xmlReader.getBoolean("/constant/DEBUG/SPARQL_PROXY_CONSTANTS/text()");
		DEBUG_SPARQL_SYNTAX_CONSTANTS      = xmlReader.getBoolean("/constant/DEBUG/SPARQL_SYNTAX_CONSTANTS/text()");
		DEBUG_SPARQL_VARIABLE_CONSTANTS    = xmlReader.getBoolean("/constant/DEBUG/SPARQL_VARIABLE_CONSTANTS/text()");
		DEBUG_ONTOLOGY_NAMESPACE_CONSTANTS = xmlReader.getBoolean("/constant/DEBUG/ONTOLOGY_NAMESPACE_CONSTANTS/text()");
		DEBUG_ONTOLOGY_ID_CONSTANTS        = xmlReader.getBoolean("/constant/DEBUG/ONTOLOGY_ID_CONSTANTS/text()");
		DEBUG_ONTOLOGY_CLASS_CONSTANTS     = xmlReader.getBoolean("/constant/DEBUG/ONTOLOGY_CLASS_CONSTANTS/text()");
		DEBUG_ONTOLOGY_PROPERTY_CONSTANTS  = xmlReader.getBoolean("/constant/DEBUG/ONTOLOGY_PROPERTY_CONSTANTS/text()");
		DEBUG_DATA_RANGE_CONSTANTS         = xmlReader.getBoolean("/constant/DEBUG/DATA_RANGE_CONSTANTS/text()");
		DEBUG_DISPLAY_CONSTANTS            = xmlReader.getBoolean("/constant/DEBUG/DISPLAY_CONSTANTS/text()");
		DEBUG_SETTING_TRANSFER_CONSTANTS   = xmlReader.getBoolean("/constant/DEBUG/SETTING_TRANSFER_CONSTANTS/text()");
		DEBUG_OPERATION_TRANSFER_CONSTANTS = xmlReader.getBoolean("/constant/DEBUG/OPERATION_TRANSFER_CONSTANTS/text()");
		DEBUG_DATA_TRANSFER_CONSTANTS      = xmlReader.getBoolean("/constant/DEBUG/DATA_TRANSFER_CONSTANTS/text()");
		DEBUG_SPARQL_PROXY_CONFIG          = xmlReader.getBoolean("/constant/DEBUG/SPARQL_PROXY_CONFIG/text()");
		DEBUG_SPARQL_ENDPOINT_CONFIG       = xmlReader.getBoolean("/constant/DEBUG/SPARQL_ENDPOINT_CONFIG/text()");
	}
	
	public static void printValue() {
		System.out.println(StringConstants.SPARQL_PROXY_CONSTANT_VALUE);

		PrintMessage.showValue("DEBUG_STRING_LENGTH", DEBUG_STRING_LENGTH);
		PrintMessage.showValue("DEBUG_SPARQL_REPOSITORY", DEBUG_SPARQL_REPOSITORY);

		PrintMessage.showValue("DEBUG_VERSION_CONSTANTS", DEBUG_VERSION_CONSTANTS);
		PrintMessage.showValue("DEBUG_SPARQL_PROXY_CONSTANTS", DEBUG_SPARQL_PROXY_CONSTANTS);
		PrintMessage.showValue("DEBUG_SPARQL_SYNTAX_CONSTANTS", DEBUG_SPARQL_SYNTAX_CONSTANTS);
		PrintMessage.showValue("DEBUG_SPARQL_VARIABLE_CONSTANTS", DEBUG_SPARQL_VARIABLE_CONSTANTS);
		PrintMessage.showValue("DEBUG_ONTOLOGY_NAMESPACE_CONSTANTS", DEBUG_ONTOLOGY_NAMESPACE_CONSTANTS);
		PrintMessage.showValue("DEBUG_DATA_RANGE_CONSTANTS", DEBUG_DATA_RANGE_CONSTANTS);
		PrintMessage.showValue("DEBUG_DISPLAY_CONSTANTS", DEBUG_DISPLAY_CONSTANTS);
		PrintMessage.showValue("DEBUG_SETTING_TRANSFER_CONSTANTS", DEBUG_SETTING_TRANSFER_CONSTANTS);
		PrintMessage.showValue("DEBUG_OPERATION_TRANSFER_CONSTANTS", DEBUG_OPERATION_TRANSFER_CONSTANTS);
		PrintMessage.showValue("DEBUG_DATA_TRANSFER_CONSTANTS", DEBUG_DATA_TRANSFER_CONSTANTS);
		PrintMessage.showValue("DEBUG_SPARQL_PROXY_CONFIG", DEBUG_SPARQL_PROXY_CONFIG);
		PrintMessage.showValue("DEBUG_SPARQL_ENDPOINT_CONFIG", DEBUG_SPARQL_ENDPOINT_CONFIG);
		System.out.println();
	}
}
