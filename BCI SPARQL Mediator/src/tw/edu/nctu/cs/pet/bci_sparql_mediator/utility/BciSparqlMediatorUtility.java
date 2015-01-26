package tw.edu.nctu.cs.pet.bci_sparql_mediator.utility;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DebugConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.ClassConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.NamespaceConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.PropertyConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlSyntaxConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlVariableConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.SettingConstants;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;
import virtuoso.jena.driver.VirtuosoUpdateFactory;

public class BciSparqlMediatorUtility {

	public static String genId(String str) {
		return (NamespaceConstants.URI_BCI + str
				+ SparqlSyntaxConstants.UNDERSCORE + UUID.randomUUID());
	}

	public static String genJsonId(String idPrefix, String id) {

		JSONObject ret = new JSONObject();
		ret.put(idPrefix, id);
		return ret.toString();
	}

	public static String getVariable(String str) {
		return (str.substring(SparqlVariableConstants.OFFSET, str.length()
				- SparqlVariableConstants.OFFSET));
	}

	public static String toUri(String str) {
		if (str.equals("")) {
			return "";
		}
		return (SparqlSyntaxConstants.LEFT_CHEVRON + str + SparqlSyntaxConstants.RIGHT_CHEVRON);
	}

	public static String getCurrentTime() {
		return (SparqlSyntaxConstants.DOUBLE_QUOTE
				+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
						.format(Calendar.getInstance().getTime())
				+ SparqlSyntaxConstants.DOUBLE_QUOTE
				+ SparqlSyntaxConstants.TYPE_CONVERSION + ClassConstants.XSD_DATE_TIME);
	}

	public static String virtuosoQuery(VirtGraph virtGraph, String query) {

		if (DebugConstants.DEBUG_SPARQL_REPOSITORY)
			System.out.println(query);

		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(
				query, virtGraph);
		ResultSet results = vqe.execSelect();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(baos, results);

		JSONArray ret = new JSONObject(baos.toString())
				.getJSONObject("results").getJSONArray("bindings");

		return ret.toString();
	}

	public static void virtuosoUpdate(VirtGraph virtGraph, String query) {

		if (DebugConstants.DEBUG_SPARQL_REPOSITORY)
			System.out.println(query);
		VirtuosoUpdateFactory.create(query, virtGraph).exec();
	}

	public static String queryResult(String response) {
		JSONArray jsonArray = new JSONArray();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(SettingConstants.RESULT, response);

		jsonArray.put(jsonObject);
		return jsonArray.toString();
	}

	public static String getSparqlResultValue(JSONObject jsonObject,
			String jsonPrefix) {

		if (jsonObject.isNull(jsonPrefix)) {
			return "";
		}
		return jsonObject.getJSONObject(jsonPrefix).getString("value");
	}

	public static String getSparqlUpdateValue(String str, String jsonPrefix) {

		return new JSONArray(str).getJSONObject(0).getJSONObject(jsonPrefix)
				.getString("value");
	}

	public static boolean isQueryEmpty(JSONArray response) {
		return (response.length() == 0);
	}

	public static String transformSubjectGender(String value) {
		if (value.equals("m") || value.equals("M") || value.equals("male")
				|| value.equals("Male")) {
			return "Male";
		} else if (value.equals("f") || value.equals("F")
				|| value.equals("female") || value.equals("Female")) {
			return "Female";
		}
		return "";
	}

	public static String transformSubjectYearOfBirth(String value) {
		if (isInteger(value)) {
			return value;
		}
		return "";
	}
	
	public static String transformSamplingRate(String value) {
		if (isInteger(value)) {
			return value;
		}
		return "";
	}
	
	public static boolean isValueEmpty(String value) {
		if (value.equals("") || value.equals("NA") || value.equals("N/A")
				|| value.equals("-")) {
			return true;
		}
		return false;
	}

	public static String transformSubjectHandedness(String value) {
		if (value.equals("r") || value.equals("R") || value.equals("right")
				|| value.equals("Right")) {
			return "Right";
		} else if (value.equals("l") || value.equals("L")
				|| value.equals("left") || value.equals("Left")) {
			return "Left";
		}
		return "";
	}

	public static JSONObject combineJsonObject(JSONObject... jsonObjects) {

		JSONObject ret = new JSONObject();
		for (JSONObject jsonObject : jsonObjects) {
			Iterator<?> iterator = jsonObject.keys();
			String jsonKey;
			while (iterator.hasNext()) {
				jsonKey = (String) iterator.next();
				ret.put(jsonKey, jsonObject.getString(jsonKey));
			}
		}

		return ret;
	}

	private static boolean isInteger(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return (str.length() > 0);
	}

	public static HashMap<String, Boolean> jsonArray2HashMap(
			JSONObject jsonObject, String key) {
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		
		if (!jsonObject.isNull(key)) {
			JSONArray jsonArray = jsonObject.getJSONArray(key);

			for (int i = 0; i < jsonArray.length(); i++) {
				hashMap.put(jsonArray.getString(i), true);
			}
		}

		return hashMap;
	}

	public static String getJsonObjectValue(JSONObject jsonObject, String key) {
		if (jsonObject.isNull(key)) {
			return "";
		}
		return jsonObject.getString(key);
	}

	public static String getJsonObjectUri(JSONObject jsonObject, String key) {
		if (jsonObject.isNull(key)) {
			return "";
		}
		return toUri(jsonObject.getString(key));
	}

	public static boolean isParameterExist(HashMap<String, Boolean> fields,
			String... strings) {
		for (String str : strings) {
			if (fields.containsKey(str)) {
				return true;
			}
		}
		return false;
	}
	
		
	public static File WriteFile(String fileName, String s){
		File file = new File(fileName);
		try {
			if(!file.exists()) { file.createNewFile(); }
			FileWriter fw= new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw= new BufferedWriter(fw);
			bw.write(s);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return file;
	}
	
}
