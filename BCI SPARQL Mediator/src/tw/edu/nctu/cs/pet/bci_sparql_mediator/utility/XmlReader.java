package tw.edu.nctu.cs.pet.bci_sparql_mediator.utility;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;

public class XmlReader {

	private DocumentBuilder mDocumentBuilder;
	private  Document mDocument;
	private  XPath mXpath;

	public XmlReader(int loadFrom, String src) {

		try {
			mDocumentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			if (loadFrom == XmlReaderConstants.LOAD_INSIDE) {
				mDocument = mDocumentBuilder.parse(getClass()
						.getResourceAsStream(src));
			} else if (loadFrom == XmlReaderConstants.LOAD_OUTSIDE) {
				mDocument = mDocumentBuilder.parse(src);
			} else if (loadFrom == XmlReaderConstants.LOAD_STRING) {
				mDocument = mDocumentBuilder.parse(new InputSource(
						new StringReader(src)));
			}
			mXpath = XPathFactory.newInstance().newXPath();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	public int getDec(String expression) {

		int ret = 0;

		try {
			Node node = (Node) mXpath.evaluate(expression, mDocument,
					XPathConstants.NODE);
			if (node != null) {
				ret = Integer.parseInt(node.getNodeValue());
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public int getHex(String expression) {

		int ret = 0;

		try {
			Node node = (Node) mXpath.evaluate(expression, mDocument,
					XPathConstants.NODE);
			if (node != null) {
				ret = Integer.parseInt(node.getNodeValue(), 16);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String getString(String expression) {

		String ret = new String();

		try {
			Node node = (Node) mXpath.evaluate(expression, mDocument,
					XPathConstants.NODE);
			if (node != null) {
				ret = node.getNodeValue();
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String getString(String expression, Node nodeDocument) {

		String ret = new String();

		try {
			Node node = (Node) mXpath.evaluate(expression, nodeDocument,
					XPathConstants.NODE);
			if (node != null) {
				ret = node.getNodeValue();
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public Boolean getBoolean(String expression) {

		boolean ret = false;

		try {
			Node node = (Node) mXpath.evaluate(expression, mDocument,
					XPathConstants.NODE);
			if (node != null) {
				ret = Boolean.parseBoolean(node.getNodeValue());
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<String> getStringList(String expression) {

		ArrayList<String> ret = new ArrayList<String>();

		try {
			NodeList nodeList = (NodeList) mXpath.evaluate(expression,
					mDocument, XPathConstants.NODESET);
			if (nodeList != null) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					ret.add(nodeList.item(i).getNodeValue());
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<String> getStringList(String expression, Node nodeDocument) {

		ArrayList<String> ret = new ArrayList<String>();

		try {
			NodeList nodeList = (NodeList) mXpath.evaluate(expression,
					nodeDocument, XPathConstants.NODESET);
			if (nodeList != null) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					ret.add(nodeList.item(i).getNodeValue());
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<Integer> getDecList(String expression) {

		ArrayList<Integer> ret = new ArrayList<Integer>();

		try {
			NodeList nodeList = (NodeList) mXpath.evaluate(expression,
					mDocument, XPathConstants.NODESET);
			if (nodeList != null) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					ret.add(Integer.parseInt(nodeList.item(i).getNodeValue()));
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public NodeList getNodeList(String expression) {

		NodeList nodeList = null;

		try {
			nodeList = (NodeList) mXpath.evaluate(expression, mDocument,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return nodeList;
	}

	public NodeList getNodeList(String expression, Node nodeDocument) {

		NodeList nodeList = null;

		try {
			nodeList = (NodeList) mXpath.evaluate(expression, nodeDocument,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return nodeList;
	}

}
