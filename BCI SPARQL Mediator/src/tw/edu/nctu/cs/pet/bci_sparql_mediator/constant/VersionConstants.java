package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class VersionConstants {

	public final static int VERSION;

	static {
		System.out.println(StringConstants.LOAD_VERSION_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		VERSION = xmlReader.getDec("/constant/VERSION/text()");
	}

	public static void printValue() {
		System.out.println(StringConstants.VERSION_CONSTANT_VALUE);

		PrintMessage.showValue("VERSION", VERSION);
	}
}
