package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class SparqlSyntaxConstants {

	public static final String PREFIX;
	public static final String SELECT;
	public static final String DESCRIBE;
	public static final String WHERE;
	public static final String WITH;
	public static final String INSERT;
	public static final String DELETE;
	public static final String INTO;
	public static final String GRAPH;
	public static final String DISTINCT;
	public static final String FILTER;
	public static final String OPTIONAL;
	public static final String NOT;
	public static final String EXISTS;
	public static final String UNION;
	public static final String REGEX;
	public static final String A;

	public static final String LEFT_PARENTHESES;
	public static final String RIGHT_PARENTHESES;
	public static final String LEFT_BRACE;
	public static final String RIGHT_BRACE;
	public static final String LEFT_CHEVRON;
	public static final String RIGHT_CHEVRON;
	public static final String DOUBLE_QUOTE;
	public static final String SLASH;
	public static final String UNDERSCORE;
	public static final String COLON;
	public static final String COMMA;
	public static final String ASTERISK;
	public static final String SPACE;
	public static final String NEW_LINE;

	public static final String EQUAL;
	public static final String GREATER_EQUAL_THAN;
	public static final String LESS_EQUAL_THAN;
	public static final String TYPE_CONVERSION;

	public static final String CONTINUE_TRIPLE;
	public static final String END_TRIPLE;
	
	public static final String SERVICE;
	public static final String SILENT;

	static {
		System.out.println(StringConstants.LOAD_SPARQL_SYNTAX_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);
		
		SERVICE 		   = xmlReader.getString("/constant/SPARQL/syntax/SERVICE/text()");
		SILENT 		       = xmlReader.getString("/constant/SPARQL/syntax/SILENT/text()");
		
		PREFIX             = xmlReader.getString("/constant/SPARQL/syntax/PREFIX/text()");
		SELECT             = xmlReader.getString("/constant/SPARQL/syntax/SELECT/text()");
		DESCRIBE             = xmlReader.getString("/constant/SPARQL/syntax/DESCRIBE/text()");
		WHERE              = xmlReader.getString("/constant/SPARQL/syntax/WHERE/text()");
		WITH               = xmlReader.getString("/constant/SPARQL/syntax/WITH/text()");
		INSERT             = xmlReader.getString("/constant/SPARQL/syntax/INSERT/text()");
		DELETE             = xmlReader.getString("/constant/SPARQL/syntax/DELETE/text()");
		INTO               = xmlReader.getString("/constant/SPARQL/syntax/INTO/text()");
		GRAPH              = xmlReader.getString("/constant/SPARQL/syntax/GRAPH/text()");
		DISTINCT           = xmlReader.getString("/constant/SPARQL/syntax/DISTINCT/text()");
		FILTER             = xmlReader.getString("/constant/SPARQL/syntax/FILTER/text()");
		OPTIONAL           = xmlReader.getString("/constant/SPARQL/syntax/OPTIONAL/text()");
		NOT                = xmlReader.getString("/constant/SPARQL/syntax/NOT/text()");
		EXISTS             = xmlReader.getString("/constant/SPARQL/syntax/EXISTS/text()");
		UNION              = xmlReader.getString("/constant/SPARQL/syntax/UNION/text()");
		REGEX              = xmlReader.getString("/constant/SPARQL/syntax/REGEX/text()");
		A                  = xmlReader.getString("/constant/SPARQL/syntax/A/text()");
		
		LEFT_PARENTHESES   = xmlReader.getString("/constant/SPARQL/syntax/LEFT_PARENTHESES/text()");
		RIGHT_PARENTHESES  = xmlReader.getString("/constant/SPARQL/syntax/RIGHT_PARENTHESES/text()");
		LEFT_BRACE         = xmlReader.getString("/constant/SPARQL/syntax/LEFT_BRACE/text()");
		RIGHT_BRACE        = xmlReader.getString("/constant/SPARQL/syntax/RIGHT_BRACE/text()");
		LEFT_CHEVRON       = xmlReader.getString("/constant/SPARQL/syntax/LEFT_CHEVRON/text()");
		RIGHT_CHEVRON      = xmlReader.getString("/constant/SPARQL/syntax/RIGHT_CHEVRON/text()");
		DOUBLE_QUOTE       = xmlReader.getString("/constant/SPARQL/syntax/DOUBLE_QUOTE/text()");
		SLASH              = xmlReader.getString("/constant/SPARQL/syntax/SLASH/text()");
		UNDERSCORE         = xmlReader.getString("/constant/SPARQL/syntax/UNDERSCORE/text()");
		COLON              = xmlReader.getString("/constant/SPARQL/syntax/COLON/text()");
		COMMA              = xmlReader.getString("/constant/SPARQL/syntax/COMMA/text()");
		ASTERISK           = xmlReader.getString("/constant/SPARQL/syntax/ASTERISK/text()");
		SPACE              = xmlReader.getString("/constant/SPARQL/syntax/SPACE/text()");
		NEW_LINE           = xmlReader.getString("/constant/SPARQL/syntax/NEW_LINE/text()");
		EQUAL              = xmlReader.getString("/constant/SPARQL/syntax/EQUAL/text()");
		GREATER_EQUAL_THAN = xmlReader.getString("/constant/SPARQL/syntax/GREATER_EQUAL_THAN/text()");
		LESS_EQUAL_THAN    = xmlReader.getString("/constant/SPARQL/syntax/LESS_EQUAL_THAN/text()");
		TYPE_CONVERSION    = xmlReader.getString("/constant/SPARQL/syntax/TYPE_CONVERSION/text()");
		
		CONTINUE_TRIPLE    = xmlReader.getString("/constant/SPARQL/syntax/CONTINUE_TRIPLE/text()");
		END_TRIPLE         = xmlReader.getString("/constant/SPARQL/syntax/END_TRIPLE/text()");
	}
	
	public static void printValue() {
		System.out.println(StringConstants.SPARQL_SYNTAX_CONSTANT_VALUE);

		PrintMessage.showValue("PREFIX", PREFIX);
		PrintMessage.showValue("SELECT", SELECT);
		PrintMessage.showValue("WHERE", WHERE);
		PrintMessage.showValue("WITH", WITH);
		PrintMessage.showValue("INSERT", INSERT);
		PrintMessage.showValue("DELETE", DELETE);
		PrintMessage.showValue("INTO", INTO);
		PrintMessage.showValue("GRAPH", GRAPH);
		PrintMessage.showValue("DISTINCT", DISTINCT);
		PrintMessage.showValue("FILTER", FILTER);
		PrintMessage.showValue("OPTIONAL", OPTIONAL);
		PrintMessage.showValue("NOT", NOT);
		PrintMessage.showValue("EXISTS", EXISTS);
		PrintMessage.showValue("UNION", UNION);
		PrintMessage.showValue("REGEX", REGEX);
		PrintMessage.showValue("A", A);
		System.out.println();

		PrintMessage.showValue("LEFT_PARENTHESES", LEFT_PARENTHESES);
		PrintMessage.showValue("RIGHT_PARENTHESES", RIGHT_PARENTHESES);
		PrintMessage.showValue("LEFT_BRACE", LEFT_BRACE);
		PrintMessage.showValue("RIGHT_BRACE", RIGHT_BRACE);
		PrintMessage.showValue("LEFT_CHEVRON", LEFT_CHEVRON);
		PrintMessage.showValue("RIGHT_CHEVRON", RIGHT_CHEVRON);
		PrintMessage.showValue("DOUBLE_QUOTE", DOUBLE_QUOTE);
		PrintMessage.showValue("SLASH", SLASH);
		PrintMessage.showValue("UNDERSCORE", UNDERSCORE);
		PrintMessage.showValue("COLON", COLON);
		PrintMessage.showValue("COMMA", COMMA);
		PrintMessage.showValue("ASTERISK", ASTERISK);
		PrintMessage.showValue("SPACE", SPACE);
		PrintMessage.showValue("NEW_LINE", NEW_LINE);
		PrintMessage.showValue("EQUAL", EQUAL);
		PrintMessage.showValue("GREATER_EQUAL_THAN", GREATER_EQUAL_THAN);
		PrintMessage.showValue("LESS_EQUAL_THAN", LESS_EQUAL_THAN);
		PrintMessage.showValue("TYPE_CONVERSION", TYPE_CONVERSION);
		System.out.println();
		
		PrintMessage.showValue("CONTINUE_TRIPLE", CONTINUE_TRIPLE);
		PrintMessage.showValue("END_TRIPLE", END_TRIPLE);
		System.out.println();
	}
}
