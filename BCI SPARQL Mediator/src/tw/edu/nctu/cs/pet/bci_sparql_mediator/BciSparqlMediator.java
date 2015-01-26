package tw.edu.nctu.cs.pet.bci_sparql_mediator;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.config.BciSparqlEndpointConfig;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.config.BciSparqlMediatorConfig;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DataRangeConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DebugConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.VersionConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.ClassConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.IdConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.NamespaceConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.PropertyConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlSyntaxConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlVariableConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.DataConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.OperationConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.SettingConstants;

public class BciSparqlMediator {

	public static void main(String[] args) {
		if (!loadOptions(args)) {
			return;
		}

		printValue();

		socketListener();
	}

	private static void socketListener() {
		ServerSocket serverSocket = null;
		boolean listening = true;

		try {
			serverSocket = new ServerSocket(BciSparqlMediatorConfig.PORT);

			while (listening) {
				new BciSparqlMediatorThread(serverSocket.accept()).start();
			}

			serverSocket.close();

		} catch (IOException e) {
			System.err.println("Could not listen on port: "
					+ BciSparqlMediatorConfig.PORT + ".");
			System.exit(-1);
		}

	}

	private static boolean loadOptions(String[] args) {

		System.out.println(StringConstants.LOAD_OPTIONS);

		CommandLineParser parser = new PosixParser();

		Options options = new Options();
		options.addOption("h", "help", false, "show usage");
		options.addOption("c", "config", true, "load configuration file");

		try {
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("help")) {
				printUsage(options);
			} else {
				if (line.hasOption("config")) {
					loadConfig(XmlReaderConstants.LOAD_OUTSIDE,
							line.getOptionValue("config"));
				} else {
					loadConfig(XmlReaderConstants.LOAD_INSIDE,
							FilePathConstants.DEFAULT_CONFIG_FILE);
				}
				return true;
			}

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			printUsage(options);
		}

		return false;
	}

	private static void loadConfig(int loadFrom, String configFile) {
		BciSparqlMediatorConfig.loadConfig(loadFrom, configFile);
		BciSparqlEndpointConfig.loadConfig(loadFrom, configFile);
	}

	private static void printValue() {
		if (DebugConstants.DEBUG_SPARQL_PROXY_CONSTANTS)
			DebugConstants.printValue();
		if (DebugConstants.DEBUG_VERSION_CONSTANTS)
			VersionConstants.printValue();
		if (DebugConstants.DEBUG_SPARQL_SYNTAX_CONSTANTS)
			SparqlSyntaxConstants.printValue();
		if (DebugConstants.DEBUG_SPARQL_VARIABLE_CONSTANTS)
			SparqlVariableConstants.printValue();

		if (DebugConstants.DEBUG_ONTOLOGY_NAMESPACE_CONSTANTS)
			NamespaceConstants.printValue();
		if (DebugConstants.DEBUG_ONTOLOGY_ID_CONSTANTS)
			IdConstants.printValue();
		if (DebugConstants.DEBUG_ONTOLOGY_CLASS_CONSTANTS)
			ClassConstants.printValue();
		if (DebugConstants.DEBUG_ONTOLOGY_PROPERTY_CONSTANTS)
			PropertyConstants.printValue();

		if (DebugConstants.DEBUG_DATA_RANGE_CONSTANTS)
			DataRangeConstants.printValue();

		if (DebugConstants.DEBUG_SETTING_TRANSFER_CONSTANTS)
			SettingConstants.printValue();
		if (DebugConstants.DEBUG_OPERATION_TRANSFER_CONSTANTS)
			OperationConstants.printValue();
		if (DebugConstants.DEBUG_DATA_TRANSFER_CONSTANTS)
			DataConstants.printValue();

		if (DebugConstants.DEBUG_SPARQL_PROXY_CONFIG)
			BciSparqlMediatorConfig.printValue();
		if (DebugConstants.DEBUG_SPARQL_ENDPOINT_CONFIG)
			BciSparqlEndpointConfig.printValue();
	}

	private static void printUsage(Options options) {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter
				.printHelp("java -jar SparqlProxy.jar [-options]", options);
		System.out.println();
	}

}
