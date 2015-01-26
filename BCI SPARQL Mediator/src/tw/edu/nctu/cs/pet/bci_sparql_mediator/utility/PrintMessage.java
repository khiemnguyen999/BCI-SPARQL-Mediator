package tw.edu.nctu.cs.pet.bci_sparql_mediator.utility;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DebugConstants;

public class PrintMessage {

	public static < T > void showValue(String prefix, T value) {
		System.out.print(prefix);
		for (int i = prefix.length(); i < DebugConstants.DEBUG_STRING_LENGTH; ++i) System.out.print(" ");
		System.out.print(" = " + value);

		System.out.println();
	}
}
