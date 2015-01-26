package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class NamespaceConstants {
	
	public static final String NAMESPACE_BCI;
	public static final String NAMESPACE_SSN;
	public static final String NAMESPACE_XSD;
	public static final String NAMESPACE_OWL;
	public static final String NAMESPACE_RDF;
	public static final String NAMESPACE_RDFS;
	
	public static final String URI_BCI;
	public static final String URI_SSN;
	public static final String URI_XSD;
	public static final String URI_OWL;
	public static final String URI_RDF;
	public static final String URI_RDFS;
	
	public static final String GRAPH_BCI;
	public static final String GRAPH_SSN;
	public static final String GRAPH_XSD;
	public static final String GRAPH_OWL;
	public static final String GRAPH_RDF;
	public static final String GRAPH_RDFS;
	public static final String GRAPH_BCI_STORE;
	
	public static final String PREFIX_BCI;
	public static final String PREFIX_SSN;
	public static final String PREFIX_XSD;
	public static final String PREFIX_OWL;
	public static final String PREFIX_RDF;
	public static final String PREFIX_RDFS;

	static {
		System.out.println(StringConstants.LOAD_ONTOLOGY_PREFIX_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		NAMESPACE_BCI  = xmlReader.getString("/constant/ontology/namespace/BCI/NAMESPACE/text()");
		NAMESPACE_SSN  = xmlReader.getString("/constant/ontology/namespace/SSN/NAMESPACE/text()");
		NAMESPACE_XSD  = xmlReader.getString("/constant/ontology/namespace/XSD/NAMESPACE/text()");
		NAMESPACE_OWL  = xmlReader.getString("/constant/ontology/namespace/OWL/NAMESPACE/text()");
		NAMESPACE_RDF  = xmlReader.getString("/constant/ontology/namespace/RDF/NAMESPACE/text()");
		NAMESPACE_RDFS = xmlReader.getString("/constant/ontology/namespace/RDFS/NAMESPACE/text()");

		URI_BCI  = xmlReader.getString("/constant/ontology/namespace/BCI/URI/text()");
		URI_SSN  = xmlReader.getString("/constant/ontology/namespace/SSN/URI/text()");
		URI_XSD  = xmlReader.getString("/constant/ontology/namespace/XSD/URI/text()");
		URI_OWL  = xmlReader.getString("/constant/ontology/namespace/OWL/URI/text()");
		URI_RDF  = xmlReader.getString("/constant/ontology/namespace/RDF/URI/text()");
		URI_RDFS = xmlReader.getString("/constant/ontology/namespace/RDFS/URI/text()");

		GRAPH_BCI       = xmlReader.getString("/constant/ontology/namespace/BCI/GRAPH/text()");
		GRAPH_SSN       = xmlReader.getString("/constant/ontology/namespace/SSN/GRAPH/text()");
		GRAPH_XSD       = xmlReader.getString("/constant/ontology/namespace/XSD/GRAPH/text()");
		GRAPH_OWL       = xmlReader.getString("/constant/ontology/namespace/OWL/GRAPH/text()");
		GRAPH_RDF       = xmlReader.getString("/constant/ontology/namespace/RDF/GRAPH/text()");
		GRAPH_RDFS      = xmlReader.getString("/constant/ontology/namespace/RDFS/GRAPH/text()");
		GRAPH_BCI_STORE = xmlReader.getString("/constant/ontology/namespace/BCI_STORE/GRAPH/text()");

		PREFIX_BCI  = xmlReader.getString("/constant/ontology/namespace/BCI/PREFIX/text()");
		PREFIX_SSN  = xmlReader.getString("/constant/ontology/namespace/SSN/PREFIX/text()");
		PREFIX_XSD  = xmlReader.getString("/constant/ontology/namespace/XSD/PREFIX/text()");
		PREFIX_OWL  = xmlReader.getString("/constant/ontology/namespace/OWL/PREFIX/text()");
		PREFIX_RDF  = xmlReader.getString("/constant/ontology/namespace/RDF/PREFIX/text()");
		PREFIX_RDFS = xmlReader.getString("/constant/ontology/namespace/RDFS/PREFIX/text()");
	}

	public static void printValue() {
		System.out.println(StringConstants.ONTOLOGY_PREFIX_CONSTANT_VALUE);

		PrintMessage.showValue("NAMESPACE_BCI", NAMESPACE_BCI);
		PrintMessage.showValue("NAMESPACE_SSN", NAMESPACE_SSN);
		PrintMessage.showValue("NAMESPACE_XSD", NAMESPACE_XSD);
		PrintMessage.showValue("NAMESPACE_OWL", NAMESPACE_OWL);
		PrintMessage.showValue("NAMESPACE_RDF", NAMESPACE_RDF);
		PrintMessage.showValue("NAMESPACE_RDFS", NAMESPACE_RDFS);
		System.out.println();

		PrintMessage.showValue("URI_BCI", URI_BCI);
		PrintMessage.showValue("URI_SSN", URI_SSN);
		PrintMessage.showValue("URI_XSD", URI_XSD);
		PrintMessage.showValue("URI_OWL", URI_OWL);
		PrintMessage.showValue("URI_RDF", URI_RDF);
		PrintMessage.showValue("URI_RDFS", URI_RDFS);
		System.out.println();

		PrintMessage.showValue("GRAPH_BCI", GRAPH_BCI);
		PrintMessage.showValue("GRAPH_SSN", GRAPH_SSN);
		PrintMessage.showValue("GRAPH_XSD", GRAPH_XSD);
		PrintMessage.showValue("GRAPH_OWL", GRAPH_OWL);
		PrintMessage.showValue("GRAPH_RDF", GRAPH_RDF);
		PrintMessage.showValue("GRAPH_RDFS", GRAPH_RDFS);
		PrintMessage.showValue("GRAPH_BCI_STORE", GRAPH_BCI_STORE);
		System.out.println();

		PrintMessage.showValue("PREFIX_BCI", PREFIX_BCI);
		PrintMessage.showValue("PREFIX_SSN", PREFIX_SSN);
		PrintMessage.showValue("PREFIX_XSD", PREFIX_XSD);
		PrintMessage.showValue("PREFIX_OWL", PREFIX_OWL);
		PrintMessage.showValue("PREFIX_RDF", PREFIX_RDF);
		PrintMessage.showValue("PREFIX_RDFS", PREFIX_RDFS);
		System.out.println();
	}

}
