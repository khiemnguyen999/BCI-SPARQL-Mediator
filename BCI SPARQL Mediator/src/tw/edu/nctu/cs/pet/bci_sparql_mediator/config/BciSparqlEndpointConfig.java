package tw.edu.nctu.cs.pet.bci_sparql_mediator.config;


import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class BciSparqlEndpointConfig {

	public static String VUS_URL;
	public static int VUS_PORT;
	public static String VUS_ACCOUNT;
	public static String VUS_PASSWORD;
	public static String SPARQL_QUERY_DEBUG_FILE_ISACTIVE;
	public static String SPARQL_QUERY_DEBUG_FILENAME;
	public static NodeList SPARQL_ENDPOINTS;
	

	public static void loadConfig(int loadFrom, String configFile) {

		System.out.println(StringConstants.LOAD_SPARQL_ENDPOINT_CONFIG);

		XmlReader xmlReader = new XmlReader(loadFrom, configFile);

		VUS_URL      = xmlReader.getString("/config/environment/VUS/URL/text()");
		VUS_PORT     = xmlReader.getDec("/config/environment/VUS/PORT/text()");
		VUS_ACCOUNT  = xmlReader.getString("/config/environment/VUS/ACCOUNT/text()");
		VUS_PASSWORD = xmlReader.getString("/config/environment/VUS/PASSWORD/text()");

		SPARQL_QUERY_DEBUG_FILE_ISACTIVE = xmlReader.getString("/config/environment/DEBUG/SPARQL-query-file/@active/text()");
		SPARQL_QUERY_DEBUG_FILENAME = xmlReader.getString("/config/environment/DEBUG/SPARQL-query-file/@filename/text()");
		
		SPARQL_ENDPOINTS = xmlReader.getNodeList("/config/environment/SPARQL-Federated-Query/SPARQL-endpoint[@active='1'][@location='remote']");
		
	}

	public static void printValue() {
		System.out.println(StringConstants.SPARQL_ENDPOINT_CONFIG_VALUE);

		PrintMessage.showValue("VUS_URL", VUS_URL);
		PrintMessage.showValue("VUS_PORT", VUS_PORT);
		PrintMessage.showValue("VUS_ACCOUNT", VUS_ACCOUNT);
		PrintMessage.showValue("VUS_PASSWORD", VUS_PASSWORD);
		System.out.println();
	}
}
