package tw.edu.nctu.pet.bci_test;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.BciSparqlRepository;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.config.BciSparqlEndpointConfig;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlSyntaxConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class testFunction {
	
public static final String xmlFilePath="/docs/BCI-SPARQL-Mediator-Config.xml";
public static NodeList getServerList(String xmlFilePath){
		
		NodeList nodeList = null;
		try{
			File xmlFile= new File(xmlFilePath);
			DocumentBuilderFactory docBuiFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBui= docBuiFac.newDocumentBuilder();
			Document doc = docBui.parse(xmlFile);
			
			XPath xPath= XPathFactory.newInstance().newXPath();
			String expression ="/config/environment/SPARQL-Federated-Query/SPARQL-endpoint[@active='1'][@location='remote']";
			nodeList= (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			
		    }catch (Exception e) {
			 e.printStackTrace();
			}
		return nodeList;
	}
	
	public static StringBuffer genFederatedQuery(StringBuffer whereClause){
		StringBuffer union= new StringBuffer();
		NodeList nodeList= getServerList(xmlFilePath);
		// ArrayList<Node> servers= null;//BciSparqlEndpointConfig.FEDERATED_URI;
		 for (int i = 0; i < nodeList.getLength(); i++) {
			 Node node = nodeList.item(i);
			 Attr attr =(Attr) node.getAttributes().getNamedItem("service");
			 union.append(SparqlSyntaxConstants.UNION + SparqlSyntaxConstants.LEFT_BRACE);      //UNION {
			 union.append(SparqlSyntaxConstants.SERVICE 
					 + SparqlSyntaxConstants.LEFT_CHEVRON
					 + attr.getValue().toString()
					 + SparqlSyntaxConstants.RIGHT_CHEVRON);
			 union.append(whereClause);
			 union.append(SparqlSyntaxConstants.RIGHT_BRACE);//} UNION 
		 }
		return union;
	}
	public static void main(String[] args) {
		StringBuffer a= new StringBuffer();
		a.append("123");
		StringBuffer b= new StringBuffer();
		b.append(genFederatedQuery(a));
		
		System.out.println(b);
	}

}
