package tw.edu.nctu.cs.pet.bci_sparql_mediator.config;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class BciSparqlMediatorConfig {

	public static int PORT;
	public static String BIOMEDICAL_RESOURCE_LOCATION;
	public static String CHANNEL_LOCATIONS_LOCATION;
	public static String HEADIT_LOCATION;
	public static String ESS_2_LOCATION;

	public static void loadConfig(int loadFrom, String configFile) {

		System.out.println(StringConstants.LOAD_SPARQL_PROXY_CONFIG);

		XmlReader xmlReader = new XmlReader(loadFrom, configFile);

		PORT                         = xmlReader.getDec("/config/environment/BCI_SPARQL_MEDIATOR/PORT/text()");
		BIOMEDICAL_RESOURCE_LOCATION = xmlReader.getString("/config/environment/BCI_SPARQL_MEDIATOR/LOCATION/BIOMEDICAL_RESOURCE/text()");
		CHANNEL_LOCATIONS_LOCATION   = xmlReader.getString("/config/environment/BCI_SPARQL_MEDIATOR/LOCATION/CHANNEL_LOCATIONS/text()");
		HEADIT_LOCATION              = xmlReader.getString("/config/environment/BCI_SPARQL_MEDIATOR/LOCATION/HEADIT/text()");
		ESS_2_LOCATION               = xmlReader.getString("/config/environment/BCI_SPARQL_MEDIATOR/LOCATION/ESS_2/text()");

	}

	public static void printValue() {
		System.out.println(StringConstants.SPARQL_PROXY_CONFIG_VALUE);

		PrintMessage.showValue("PORT", PORT);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_LOCATION", BIOMEDICAL_RESOURCE_LOCATION);
		PrintMessage.showValue("CHANNEL_LOCATIONS_LOCATION", CHANNEL_LOCATIONS_LOCATION);
		PrintMessage.showValue("HEADIT_LOCATION", HEADIT_LOCATION);
		PrintMessage.showValue("ESS_2_LOCATION", ESS_2_LOCATION);
		System.out.println();
	}

}
