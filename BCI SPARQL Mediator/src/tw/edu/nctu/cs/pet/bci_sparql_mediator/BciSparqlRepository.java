package tw.edu.nctu.cs.pet.bci_sparql_mediator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.config.BciSparqlEndpointConfig;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.config.BciSparqlMediatorConfig;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DataRangeConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DebugConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.ClassConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.IdConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.NamespaceConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology.PropertyConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlSyntaxConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql.SparqlVariableConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.DataConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.OperationConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.SettingConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.ess.Ess1;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.ess.Ess2;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.BciSparqlMediatorUtility;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoUpdateFactory;

public class BciSparqlRepository {

	private static VirtGraph mVirtGraph;

	static {
		mVirtGraph = new VirtGraph(BciSparqlEndpointConfig.VUS_URL + ":"
				+ String.valueOf(BciSparqlEndpointConfig.VUS_PORT),
				BciSparqlEndpointConfig.VUS_ACCOUNT,
				BciSparqlEndpointConfig.VUS_PASSWORD);
		mVirtGraph.setReadFromAllGraphs(true);
	}
	
	//GENERAL SEARCH 
	
	public static StringBuffer whereClause_Search(HashMap<String,Boolean> fields ,JSONObject jsonObject){

		StringBuffer query= new StringBuffer();
		
		// /*- @sjrm(2014-10-24): this is DEBUG information. It generates the content of the general objects that we need. 
		//query.append(" *** JSON Object: [" + jsonObject.toString() + "] ***");
		//query.append(" *** fields: [" + fields.toString() + "] ***");
		//query.append(" *** exists: [" + exists.toString() + "] ***");


		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String studyTitle = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_TITLE);
		
		String studyStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_START_TIME);
		
		String studyEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_END_TIME);

		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String sessionIdNumber = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_ID_NUMBER);
		String sessionTaskLabel = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_TASK_LABEL);
		String sessionPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_PURPOSE);
		String sessionLabId = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_LAB_ID);
		
		String sessionStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_START_TIME);
		String sessionEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_END_TIME);
		
//General Attributes of Record
		
		String RecordNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MIN);
		String RecordNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MAX);
		String RecordSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE_MIN);
		String RecordSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE_MAX);
		String RecordStartTime = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_START_TIME);
		String RecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.RECORD_END_TIME);
		
		String recordChannelFormat = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.RECORD_CHANNEL_FORMAT);
		
//add channel, device...
		
		String eyeGazeChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_TYPE);
		String eyeGazeChannelReferTo = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO);
		String eyeGazeChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_UNIT);	

		String handGestureChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE);
		String handGestureChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT);
			
		String mouseClickChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE);
		String mouseClickChannelButton = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON);
//Subject
		
		String subjectGender = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_GENDER);
		String subjectYearOfBirthMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN);
		String subjectYearOfBirthMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX);
		String subjectHandedness = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_HANDEDNESS);

		// String recordedSubjectAtSessionLabId = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		// String recordedSubjectAtSessionGroup = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		// String recordedSubjectAtSessionAgeMin = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN);
		// String recordedSubjectAtSessionAgeMax = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX);
		// String recordedSubjectAtSessionChannelLocationType =
		// BciSparqlMediatorUtility
		// .getJsonObjectValue(
		// jsonObject,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);

		String measurementPropertySamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN);
		String measurementPropertySamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);

		String recordedModalityModalityType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_TYPE);
		 //ModalityType is replace by ModalitySignalType
		String recordedModalityModalitySignalType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);

		String biomedicalResourceTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE);
		String biomedicalResourceNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN);
		String biomedicalResourceNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX);
		String biomedicalResourceSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN);
		String biomedicalResourceSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX);
		String biomedicalResourceUtilization = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION);

		// String channelLocationsTitle = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.CHANNEL_LOCATIONS_TITLE);
		// String channelLocationsUtilization = BciSparqlMediatorUtility
		// .getJsonObjectValue(jsonObject,
		// DataConstants.CHANNEL_LOCATIONS_UTILIZATION);

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isStudyTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyTitle);
		
		boolean isStudyStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyStartTime);
		boolean isStudyEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyEndTime);

		boolean isSessionIdNumberEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionIdNumber);
		boolean isSessionTaskLabelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionTaskLabel);
		boolean isSessionPurposeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionPurpose);
		boolean isSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionLabId);
		boolean isSessionStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionStartTime);
		boolean isSessionEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionEndTime);

		boolean isRecordNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordNumberOfChannelsMin);
		boolean isRecordNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordNumberOfChannelsMax);
		boolean isRecordSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMin);
		boolean isRecordSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMax);
		
		boolean isRecordStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordStartTime);
		boolean isRecordEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordEndTime);

		boolean isSubjectGenderEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectGender);
		boolean isSubjectYearOfBirthMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMin);
		boolean isSubjectYearOfBirthMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMax);
		boolean isSubjectHandednessEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectHandedness);

		boolean isEyeChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelType);
		
		boolean isEyeChannelReferToEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelReferTo);

		boolean isEyeChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelUnit);
		
		
		
		boolean isRecordeSampingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMin);

		boolean isRecordeSampingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMax);

		
		boolean isRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordChannelFormat);

		boolean isHandGestureChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelType);
		
		boolean isHandGestureChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelUnit);

		boolean isMouseClickChannelButtonEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelButton);
		
		boolean isMouseClickChannelTypeEmty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelType);
		

		// boolean isRecordedSubjectAtSessionLabIdEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionLabId);
		// boolean isRecordedSubjectAtSessionGroupEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionGroup);
		// boolean isRecordedSubjectAtSessionAgeMinEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionAgeMin);
		// boolean isRecordedSubjectAtSessionAgeMaxEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionAgeMax);
		// boolean isRecordedSubjectAtSessionChannelLocationTypeEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionChannelLocationType);

		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMin);
		boolean isMeasurementPropertySamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMax);

		boolean isRecordedModalityModalityTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalityType);

		boolean isRecordedModalityModalitySignalTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalitySignalType);
		
		boolean isBiomedicalResourceTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceTitle);
		boolean isBiomedicalResourceNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMin);
		boolean isBiomedicalResourceNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMax);
		boolean isBiomedicalResourceSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMin);
		boolean isBiomedicalResourceSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMax);
		boolean isBiomedicalResourceUtilizationEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUtilization);

		query.append(SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
		// Study
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.STUDY_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_STUDY + SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.STUDY_TITLE,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlVariableConstants.STUDY_TITLE));
		
		query.append(genQueryTriple(fields, DataConstants.STUDY_PURPOSE,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_PURPOSE,
				SparqlVariableConstants.STUDY_PURPOSE));
		
		query.append(genQueryTriple(fields, DataConstants.STUDY_UUID,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_UUID,
				SparqlVariableConstants.STUDY_UUID));
		query.append(genQueryTriple(fields, DataConstants.STUDY_ROOT_URI,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_ROOT_URI,
				SparqlVariableConstants.STUDY_ROOT_URI));
		query.append(genQueryTriple(fields, DataConstants.STUDY_START_TIME,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.STUDY_START_TIME));
		query.append(genQueryTriple(fields, DataConstants.STUDY_END_TIME,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.STUDY_END_TIME));

		// Session
		if (BciSparqlMediatorUtility
				.isParameterExist(
						fields,
						DataConstants.SESSION_ID,
						DataConstants.SESSION_ID_NUMBER,
						DataConstants.SESSION_TASK_LABEL,
						DataConstants.SESSION_PURPOSE,
						DataConstants.SESSION_LAB_ID,
						DataConstants.SUBJECT_ID,
						DataConstants.SUBJECT_GENDER,
						DataConstants.SUBJECT_YEAR_OF_BIRTH,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX,
						DataConstants.SUBJECT_HANDEDNESS,
						
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
						
						DataConstants.RECORD_NUMBER_OF_CHANNELS,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MIN,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MAX,
						
						DataConstants.RECORD_SAMPLING_RATE,
						DataConstants.RECORD_SAMPLING_RATE_MIN,
						DataConstants.RECORD_SAMPLING_RATE_MAX,
						DataConstants.RECORD_CHANNEL_FORMAT,
						DataConstants.RECORD_START_TIME,
						DataConstants.RECORD_END_TIME,
						
						//RECORD
						
						DataConstants.EYE_GAZE_RECORD_ID,
						DataConstants.HAND_GESTURE_RECORD_ID,
						DataConstants.KEYBOARD_HIT_RECORD_ID,
						DataConstants.MOUSE_CLICK_RECORD_ID,    
						
						DataConstants.EYE_GAZE_CHANNEL_ID,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO,
						DataConstants.EYE_GAZE_CHANNEL_TYPE,
						DataConstants.EYE_GAZE_CHANNEL_UNIT,
				
						DataConstants.HAND_GESTURE_CHANNEL_ID,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT,
				
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
						DataConstants.MOUSE_CLICK_CHANNEL_ID,
						
						DataConstants.EEG_DEVICE_ID,
						DataConstants.EYE_GAZE_DEVICE_ID,
						DataConstants.HAND_GESTURE_DEVICE_ID,
						DataConstants.KEYBOARD_HIT_DEVICE_ID,
						DataConstants.MOUSE_CLICK_DEVICE_ID,

						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
						DataConstants.RECORDED_MODALITY_ID,
						DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
						DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
						DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
						DataConstants.RECORDED_MODALITY_REFERENCE_LABEL,
						
						DataConstants.BIOMEDICAL_RESOURCE_ID,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE,
						DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
						
						DataConstants.CHANNEL_LOCATIONS_ID,
						DataConstants.CHANNEL_LOCATIONS_TITLE,
						DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
						DataConstants.EVENT_INSTANCE_FILE_ID,
						DataConstants.EVENT_INSTANCE_FILE_TITLE,
						DataConstants.EVENT_INSTANCE_FILE_UTILIZATION,
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID,
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL)) {
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.STUDY_ID + PropertyConstants.BCI_HAS_SESSION
					+ SparqlVariableConstants.SESSION_ID + SparqlSyntaxConstants.END_TRIPLE);
			
			query.append(SparqlSyntaxConstants.SPACE
					+  SparqlVariableConstants.SESSION_ID + SparqlSyntaxConstants.A 
					+ ClassConstants.BCI_SESSION + SparqlSyntaxConstants.END_TRIPLE);
			

		}
		query.append(genQueryTriple(fields, DataConstants.SESSION_ID_NUMBER,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_ID_NUMBER,
				SparqlVariableConstants.SESSION_ID_NUMBER));
		query.append(genQueryTriple(fields, DataConstants.SESSION_TASK_LABEL,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_TASK_LABEL,
				SparqlVariableConstants.SESSION_TASK_LABEL));
		
		query.append(genQueryTriple(fields, DataConstants.SESSION_PURPOSE,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_PURPOSE,
				SparqlVariableConstants.SESSION_PURPOSE));
		query.append(genQueryTriple(fields, DataConstants.SESSION_LAB_ID,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_SESSION_LAB_ID,
				SparqlVariableConstants.SESSION_LAB_ID));
		query.append(genQueryTriple(fields, DataConstants.SESSION_START_TIME,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.SESSION_START_TIME));
		query.append(genQueryTriple(fields, DataConstants.SESSION_END_TIME,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.SESSION_END_TIME));

		// EEG Record
		if (BciSparqlMediatorUtility
				.isParameterExist(
						fields,
						DataConstants.SUBJECT_ID,
						DataConstants.SUBJECT_GENDER,
						DataConstants.SUBJECT_YEAR_OF_BIRTH,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX,
						DataConstants.SUBJECT_HANDEDNESS,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
						
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
						DataConstants.RECORDED_MODALITY_ID,
						DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
						DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
						DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
						DataConstants.RECORDED_MODALITY_REFERENCE_LABEL,
						
						DataConstants.RECORD_NUMBER_OF_CHANNELS,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MIN,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MAX,
						
						DataConstants.RECORD_SAMPLING_RATE,
						DataConstants.RECORD_SAMPLING_RATE_MIN,
						DataConstants.RECORD_SAMPLING_RATE_MAX,
						DataConstants.RECORD_CHANNEL_FORMAT,
						DataConstants.RECORD_START_TIME,
						DataConstants.RECORD_END_TIME,
						
						DataConstants.HAND_GESTURE_CHANNEL_ID,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT,
				
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
						DataConstants.MOUSE_CLICK_CHANNEL_ID,
						
						DataConstants.EEG_DEVICE_ID,
						DataConstants.EYE_GAZE_DEVICE_ID,
						DataConstants.HAND_GESTURE_DEVICE_ID,
						DataConstants.KEYBOARD_HIT_DEVICE_ID,
						DataConstants.MOUSE_CLICK_DEVICE_ID,
						
						DataConstants.BIOMEDICAL_RESOURCE_ID,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE,
						DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
						
						DataConstants.CHANNEL_LOCATIONS_ID,
						DataConstants.CHANNEL_LOCATIONS_TITLE,
						DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
						DataConstants.EVENT_INSTANCE_FILE_ID,
						DataConstants.EVENT_INSTANCE_FILE_TITLE,
						DataConstants.EVENT_INSTANCE_FILE_UTILIZATION,
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID,
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL)) {
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.SESSION_ID
					+ PropertyConstants.BCI_HAS_RECORD
					+ SparqlVariableConstants.RECORD_ID
					+ SparqlSyntaxConstants.END_TRIPLE);

			/*
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_EEG_RECORD
					+ SparqlSyntaxConstants.END_TRIPLE);
			*/
			/*
			query.append(genOptionQuery(SparqlVariableConstants.SUBJECT_ID, 
					SparqlSyntaxConstants.A, ClassConstants.BCI_SUBJECT));	
			
			
			query.append(genOptionQuery(SparqlVariableConstants.SUBJECT_ID,
					PropertyConstants.BCI_HAS_DATA_SET, SparqlVariableConstants.RECORD_ID));
			
					
			*/
			query.append(SparqlSyntaxConstants.SPACE
					+  SparqlVariableConstants.SUBJECT_ID 
					+ SparqlSyntaxConstants.A 
					+ ClassConstants.BCI_SUBJECT 
					+ SparqlSyntaxConstants.END_TRIPLE );
			
			
			query.append(SparqlSyntaxConstants.SPACE 
					+ SparqlVariableConstants.SUBJECT_ID
					+ PropertyConstants.BCI_HAS_DATA_SET
					+ SparqlVariableConstants.RECORD_ID 
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			
			

		}
		if (BciSparqlMediatorUtility
				.isParameterExist(
						fields,
						
						DataConstants.RECORD_NUMBER_OF_CHANNELS,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MIN,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MAX,
						
						DataConstants.RECORD_SAMPLING_RATE,
						DataConstants.RECORD_SAMPLING_RATE_MIN,
						DataConstants.RECORD_SAMPLING_RATE_MAX,
						DataConstants.RECORD_CHANNEL_FORMAT,
						DataConstants.RECORD_START_TIME,
						DataConstants.RECORD_END_TIME,
						
						//RECORD
						
						DataConstants.EYE_GAZE_RECORD_ID,
						DataConstants.HAND_GESTURE_RECORD_ID,
						DataConstants.KEYBOARD_HIT_RECORD_ID,
						DataConstants.MOUSE_CLICK_RECORD_ID,   
						
						DataConstants.EYE_GAZE_CHANNEL_ID,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO,
						DataConstants.EYE_GAZE_CHANNEL_TYPE,
						DataConstants.EYE_GAZE_CHANNEL_UNIT,
				
						DataConstants.HAND_GESTURE_CHANNEL_ID,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT,
				
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
						DataConstants.MOUSE_CLICK_CHANNEL_ID
						
						)){

			query.append(SparqlSyntaxConstants.SPACE 
					+ SparqlVariableConstants.SESSION_ID
					+ PropertyConstants.BCI_HAS_RECORD
					+ SparqlVariableConstants.RECORD_ID 
					+ SparqlSyntaxConstants.END_TRIPLE);
	
		}
			
		// EEG Record Start	
			query.append(genOptionQuery(SparqlVariableConstants.RECORD_ID, 
					PropertyConstants.BCI_HAS_SAMPLING_RATE, SparqlVariableConstants.RECORD_SAMPLING_RATE));
			
			
			query.append(genOptionQuery(SparqlVariableConstants.RECORD_ID, 
					PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS, SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS));
			
		

			query.append(genQueryTriple(fields, DataConstants.RECORD_START_TIME,
					SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.RECORD_START_TIME));
			
			query.append(genQueryTriple(fields, DataConstants.RECORD_END_TIME,
					SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.RECORD_END_TIME));
			
			query.append(genQueryTriple(fields, DataConstants.RECORD_CHANNEL_FORMAT,
					SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT,
					SparqlVariableConstants.RECORD_CHANNEL_FORMAT));
			
			query.append(genQueryTriple(fields, DataConstants.RECORD_SAMPLING_RATE,
					SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_HAS_SAMPLING_RATE,
					SparqlVariableConstants.RECORD_SAMPLING_RATE));
			
			query.append(genQueryTriple(fields, DataConstants.RECORD_NUMBER_OF_CHANNELS,
					SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
					SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS));		
		

		// CHANNEL
		if (BciSparqlMediatorUtility
				.isParameterExist(
						fields,
						DataConstants.EYE_GAZE_CHANNEL_ID,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO,
						DataConstants.EYE_GAZE_CHANNEL_TYPE,
						DataConstants.EYE_GAZE_CHANNEL_UNIT,
				
						DataConstants.HAND_GESTURE_CHANNEL_ID,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT,
				
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
						DataConstants.MOUSE_CLICK_CHANNEL_ID
						)){
			 
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.CHANNEL_ID
					+ PropertyConstants.BCI_IS_CHANNEL_OF
					+ SparqlVariableConstants.RECORD_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
		}

		//eyegazeChannel
		
		query.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_REFER_TO,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_REFERS_TO,
				SparqlVariableConstants.EYE_GAZE_CHANNEL_REFERTO));
		
		query.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_TYPE,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_TYPE,
				SparqlVariableConstants.EYE_GAZE_CHANNEL_TYPE));
		
		query.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_UNIT,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_UNIT,
				SparqlVariableConstants.EYE_GAZE_CHANNEL_UNIT));
		
		//HandGesture Channel
		
		query.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_CHANNEL_TYPE,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_TYPE,
				SparqlVariableConstants.HAND_GESTURE_CHANNEL_TYPE));
		
		query.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_CHANNEL_UNIT,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_UNIT,
				SparqlVariableConstants.HAND_GESTURE_CHANNEL_UNIT));
		//MouseClick
		
		query.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_BUTTON,
				SparqlVariableConstants.MOUSE_CLICK_CHANNEL_BUTTON));
		
		query.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
				SparqlVariableConstants.CHANNEL_ID,
				PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_TYPE,
				SparqlVariableConstants.MOUSE_CLICK_CHANNEL_TYPE));
		
		// Subject
		
		if (BciSparqlMediatorUtility
				.isParameterExist(
						fields,
						DataConstants.SUBJECT_ID,
						DataConstants.SUBJECT_GENDER,
						DataConstants.SUBJECT_YEAR_OF_BIRTH,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX,
						DataConstants.SUBJECT_HANDEDNESS,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
						DataConstants.CHANNEL_LOCATIONS_ID,
						DataConstants.CHANNEL_LOCATIONS_TITLE,
						DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL)) {
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
					+ PropertyConstants.BCI_IS_FROM_SUBJECT
					+ SparqlVariableConstants.SUBJECT_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			/*/* nnmk adjus to option clause
			query.append(genOptionQuery(SparqlVariableConstants.RECORD_ID,
					PropertyConstants.BCI_IS_FROM_SUBJECT, SparqlVariableConstants.SUBJECT_ID));
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.SUBJECT_ID
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_SUBJECT
					+ SparqlSyntaxConstants.END_TRIPLE);
			*/
		}

		query.append(genQueryTriple(fields, DataConstants.SUBJECT_GENDER,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_GENDER,
				SparqlVariableConstants.SUBJECT_GENDER));
		query.append(genQueryTriple(fields,
				DataConstants.SUBJECT_YEAR_OF_BIRTH,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_YEAR_OF_BIRTH,
				SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH));
		query.append(genQueryTriple(fields, DataConstants.SUBJECT_HANDEDNESS,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_HANDEDNESS,
				SparqlVariableConstants.SUBJECT_HANDEDNESS));

		
		// // Recorded Subject At Session
		// if (BciSparqlMediatorUtility
		// .isParameterExist(
		// fields,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL,
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
		// DataConstants.CHANNEL_LOCATIONS_ID,
		// DataConstants.CHANNEL_LOCATIONS_TITLE,
		// DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
		// DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
		// DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL)) {
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.OPTIONAL
		// + SparqlSyntaxConstants.LEFT_BRACE
		// + SparqlSyntaxConstants.NEW_LINE);
		//
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.SESSION_ID
		// + PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.SUBJECT_ID
		// + PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + SparqlSyntaxConstants.A
		// + ClassConstants.BCI_RECORDED_SUBJECT_AT_SESSION
		// + SparqlSyntaxConstants.END_TRIPLE);
		//
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_LAB_ID
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_IN_SESSION_NUMBER
		// +
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_GROUP
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_AGE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_VISION
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_VISION
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_HEARING
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_HEARING
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_HEIGHT
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_WEIGHT
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_MEDICATION_CAFFEINE
		// +
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_MEDICATION_ALCOHOL
		// +
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_CHANNEL_LOCATION_TYPE
		// +
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE
		// + SparqlSyntaxConstants.END_TRIPLE);
		//
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.RIGHT_BRACE
		// + SparqlSyntaxConstants.NEW_LINE);
		// }

		// Recorded Parameter Set
		if (BciSparqlMediatorUtility.isParameterExist(fields,
				DataConstants.EEG_DEVICE_ID,
				DataConstants.EYE_GAZE_DEVICE_ID,
				DataConstants.HAND_GESTURE_DEVICE_ID,
				DataConstants.KEYBOARD_HIT_DEVICE_ID,
				DataConstants.MOUSE_CLICK_DEVICE_ID,
				
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX,
				DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
				DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
				DataConstants.RECORDED_MODALITY_ID,
				DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
				DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
				DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
				DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
				DataConstants.RECORDED_MODALITY_REFERENCE_LABEL)) {
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
					+ PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET
					+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_RECORDED_PARAMETER_SET
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			
		}

		// EEG EYEGAZE HAND GESTURE ,....
		if (BciSparqlMediatorUtility.isParameterExist(fields,
				DataConstants.EEG_DEVICE_ID,
				DataConstants.EYE_GAZE_DEVICE_ID,
				DataConstants.HAND_GESTURE_DEVICE_ID,
				DataConstants.KEYBOARD_HIT_DEVICE_ID,
				DataConstants.MOUSE_CLICK_DEVICE_ID,
				
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX,
				DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
				DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL)) {
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID
					+ PropertyConstants.BCI_IS_GENERATED_BY
					+ SparqlVariableConstants.DEVICE_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.DEVICE_ID
					+ PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD
					+ SparqlVariableConstants.RECORD_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.DEVICE_ID
					+ PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY
					+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
					+ SparqlSyntaxConstants.END_TRIPLE);

			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.SSN_MEASUREMENT_CAPABILITY
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
					+ PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY
					+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
					+ SparqlSyntaxConstants.END_TRIPLE);

			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_MEASUREMENT_PROPERTY
					+ SparqlSyntaxConstants.END_TRIPLE);
		}
		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE));
		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_START_CHANNEL,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_START_CHANNEL));
		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_END_CHANNEL,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_END_CHANNEL));

		// Recorded Modality
		if (BciSparqlMediatorUtility.isParameterExist(fields,
				DataConstants.RECORDED_MODALITY_ID,
				DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
				DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
				DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
				DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
				DataConstants.RECORDED_MODALITY_REFERENCE_LABEL)) {
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID
					+ PropertyConstants.BCI_HAS_RECORDED_MODALITY
					+ SparqlVariableConstants.RECORDED_MODALITY_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			//Relationship between Record and recordModality 
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
				    + PropertyConstants.BCI_HAS_RECORDED_MODALITY
					+ SparqlVariableConstants.RECORDED_MODALITY_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORDED_MODALITY_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_RECORDED_MODALITY
					+ SparqlSyntaxConstants.END_TRIPLE);
			
		}

		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_MODALITY_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE));
		//Change to MODALITYSIGNALTYPE
		
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_MODALITY_SIGNAL_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE));
		
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_SAMPLING_RATE,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.RECORDED_MODALITY_SAMPLING_RATE));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
				SparqlVariableConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_START_CHANNEL,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_START_CHANNEL,
				SparqlVariableConstants.RECORDED_MODALITY_START_CHANNEL));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_END_CHANNEL,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_END_CHANNEL,
				SparqlVariableConstants.RECORDED_MODALITY_END_CHANNEL));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_REFERENCE_LOCATION,
				SparqlVariableConstants.RECORDED_MODALITY_REFERENCE_LOCATION));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_REFERENCE_LABEL,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_REFERENCE_LABEL,
				SparqlVariableConstants.RECORDED_MODALITY_REFERENCE_LABEL));

		// Biomedical Resource
		if (BciSparqlMediatorUtility.isParameterExist(fields,
				DataConstants.BIOMEDICAL_RESOURCE_ID,
				DataConstants.BIOMEDICAL_RESOURCE_TITLE,
				DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
				DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID,
				DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL)) {
//			query.append(SparqlSyntaxConstants.SPACE
//					+ SparqlSyntaxConstants.OPTIONAL
//					+ SparqlSyntaxConstants.LEFT_BRACE
//					+ SparqlSyntaxConstants.NEW_LINE);

			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
					+ PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_BIOMEDICAL_RESOURCE
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
					+ PropertyConstants.BCI_HAS_TITLE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
					+ PropertyConstants.BCI_IS_USED_FOR
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
					+ PropertyConstants.BCI_HAS_ACCESS_METHOD
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_ACCESS_METHOD_HTTP
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID
					+ PropertyConstants.BCI_HAS_URL
					+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL
					+ SparqlSyntaxConstants.END_TRIPLE);

//			query.append(SparqlSyntaxConstants.SPACE
//					+ SparqlSyntaxConstants.RIGHT_BRACE
//					+ SparqlSyntaxConstants.NEW_LINE);
		}

		// // Channel Locations
		// if (BciSparqlMediatorUtility.isParameterExist(fields,
		// DataConstants.CHANNEL_LOCATIONS_ID,
		// DataConstants.CHANNEL_LOCATIONS_TITLE,
		// DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
		// DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
		// DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL)) {
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.OPTIONAL
		// + SparqlSyntaxConstants.LEFT_BRACE
		// + SparqlSyntaxConstants.NEW_LINE);
		//
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
		// + PropertyConstants.BCI_HAS_RESOURCE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ID
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ID
		// + SparqlSyntaxConstants.A + ClassConstants.BCI_RESOURCE
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ID
		// + PropertyConstants.BCI_HAS_TITLE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_TITLE
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ID
		// + PropertyConstants.BCI_IS_USED_FOR
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_UTILIZATION
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ID
		// + PropertyConstants.BCI_HAS_ACCESS_METHOD
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID
		// + SparqlSyntaxConstants.A
		// + ClassConstants.BCI_ACCESS_METHOD_HTTP
		// + SparqlSyntaxConstants.END_TRIPLE);
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.SPACE
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID
		// + PropertyConstants.BCI_HAS_URL
		// + SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL
		// + SparqlSyntaxConstants.END_TRIPLE);
		//
		// query.append(SparqlSyntaxConstants.SPACE
		// + SparqlSyntaxConstants.RIGHT_BRACE
		// + SparqlSyntaxConstants.NEW_LINE);
		// }

		// Event Instance File
		if (BciSparqlMediatorUtility.isParameterExist(fields,
				DataConstants.EVENT_INSTANCE_FILE_ID,
				DataConstants.EVENT_INSTANCE_FILE_TITLE,
				DataConstants.EVENT_INSTANCE_FILE_UTILIZATION,
				DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID,
				DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL)) {
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.OPTIONAL
					+ SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.RECORD_ID
					+ PropertyConstants.BCI_HAS_RESOURCE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ID
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_RESOURCE
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ID
					+ PropertyConstants.BCI_HAS_TITLE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_TITLE
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ID
					+ PropertyConstants.BCI_IS_USED_FOR
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_UTILIZATION
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ID
					+ PropertyConstants.BCI_HAS_ACCESS_METHOD
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID
					+ SparqlSyntaxConstants.A
					+ ClassConstants.BCI_ACCESS_METHOD_HTTP
					+ SparqlSyntaxConstants.END_TRIPLE);
			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_ID
					+ PropertyConstants.BCI_HAS_URL
					+ SparqlVariableConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL
					+ SparqlSyntaxConstants.END_TRIPLE);

			query.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
		}

		// EEG Record End

		// Filter
		// Study
		if (!isStudyUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.STUDY_ID,
					studyUri));
		}
		if (!isStudyTitleEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.STUDY_TITLE, studyTitle));
		}
		if (!isStudyStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.STUDY_START_TIME,
					jsonObject.getString(DataConstants.STUDY_START_TIME)));

		}
		if (!isStudyEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.STUDY_END_TIME,
					jsonObject.getString(DataConstants.STUDY_END_TIME)));
		}

		// Session
		if (!isSessionUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
					sessionUri));
		}
		if (!isSessionIdNumberEmpty) {
			query.append(genFilterNumberEqual(
					SparqlVariableConstants.SESSION_ID_NUMBER,
					Integer.parseInt(sessionIdNumber)));
		}
		if (!isSessionTaskLabelEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_TASK_LABEL,
					sessionTaskLabel));
		}
		if (!isSessionPurposeEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_PURPOSE, sessionPurpose));
		}
		if (!isSessionLabIdEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_LAB_ID, sessionLabId));
		}
		if (!isSessionStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.SESSION_START_TIME,
					sessionStartTime));
		}
		if (!isSessionEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.SESSION_END_TIME, sessionEndTime));
		}

		// EEG Record
		if (!isRecordNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS,
					Integer.parseInt(RecordNumberOfChannelsMin)));
		}
		if (!isRecordNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS,
					Integer.parseInt(RecordNumberOfChannelsMax)));
		}
		if (!isRecordSamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.RECORD_SAMPLING_RATE,
					Integer.parseInt(RecordSamplingRateMin)));
		}

		if (!isRecordSamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.RECORD_SAMPLING_RATE,
					Integer.parseInt(RecordSamplingRateMax)));
		}
						
	
		
		if (!isRecordStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.RECORD_START_TIME,
					RecordStartTime));
		}
		if (!isRecordEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.RECORD_END_TIME,
					RecordEndTime));
		}

		if (!isRecordChannelFormatEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORD_CHANNEL_FORMAT,
					recordChannelFormat));
		}
		
				
		// Subject
		if (!isSubjectGenderEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.SUBJECT_GENDER, subjectGender));
		}
		if (!isSubjectYearOfBirthMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH,
					Integer.parseInt(subjectYearOfBirthMin)));
		}
		if (!isSubjectYearOfBirthMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH,
					Integer.parseInt(subjectYearOfBirthMax)));
		}
		if (!isSubjectHandednessEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.SUBJECT_HANDEDNESS,
					subjectHandedness));
		}
		
		//EyeGaze
		if (!isEyeChannelTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.EYE_GAZE_CHANNEL_TYPE,
					eyeGazeChannelType));
		}

		if (!isEyeChannelReferToEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.EYE_GAZE_CHANNEL_REFERTO,
					eyeGazeChannelReferTo));
		}

		if (!isEyeChannelUnitEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.EYE_GAZE_CHANNEL_UNIT,
					eyeGazeChannelUnit));
		}


		//HandGesture

		if (!isHandGestureChannelTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_TYPE,
					handGestureChannelType));
		}

		if (!isHandGestureChannelUnitEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_UNIT,
					handGestureChannelUnit));
		}
    /*
		if (!isHandGestureChannelFormatEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORD_CHANNEL_FORMAT,
					handGestureHasChannelFormat));
		}
		

		if (!isHandGestureRecordSamplingRateEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORD_SAMPLING_RATE,
					handGestureRecordSamplingRate));
		}
		*/
		// MouseClick

		if (!isMouseClickChannelButtonEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_BUTTON,
					mouseClickChannelButton));
		}
		if (!isMouseClickChannelTypeEmty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_TYPE,
					mouseClickChannelType));
		}
		
				// Recorded Subject At Session
		// if (!isRecordedSubjectAtSessionLabIdEmpty) {
		// query.append(genFilterStringRegex(
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
		// recordedSubjectAtSessionLabId));
		// }
		// if (!isRecordedSubjectAtSessionGroupEmpty) {
		// query.append(genFilterStringRegex(
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
		// recordedSubjectAtSessionGroup));
		// }
		// if (!isRecordedSubjectAtSessionAgeMinEmpty) {
		// query.append(genFilterNumberGreaterThan(
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
		// Integer.parseInt(recordedSubjectAtSessionAgeMin)));
		// }
		// if (!isRecordedSubjectAtSessionAgeMaxEmpty) {
		// query.append(genFilterNumberLessThan(
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
		// Integer.parseInt(recordedSubjectAtSessionAgeMax)));
		// }
		// if (!isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
		// query.append(genFilterStringRegex(
		// SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
		// recordedSubjectAtSessionChannelLocationType));
		// }

		// EEG Device
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
					Integer.parseInt(measurementPropertyStartChannel)));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
					Integer.parseInt(measurementPropertyEndChannel)));
		}
		if (!isMeasurementPropertySamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMin)));
		}
		if (!isMeasurementPropertySamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMax)));
		}

		// Recorded Modality
		if (!isRecordedModalityModalityTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE,
					recordedModalityModalityType));
		}
		
		//ModalityType is changed to ModalitySignalType
		
		if (!isRecordedModalityModalitySignalTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
					recordedModalityModalitySignalType));
		}
		
		// Biomedical Resource
		if (!isBiomedicalResourceTitleEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE,
					biomedicalResourceTitle));
		}
		if (!isBiomedicalResourceNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS,
					Integer.parseInt(biomedicalResourceNumberOfChannelsMin)));
		}
		if (!isBiomedicalResourceNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(biomedicalResourceNumberOfChannelsMax)));
		}
		if (!isBiomedicalResourceSamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE,
					Integer.parseInt(biomedicalResourceSamplingRateMin)));
		}
		if (!isBiomedicalResourceSamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE,
					Integer.parseInt(biomedicalResourceSamplingRateMax)));
		}
		if (!isBiomedicalResourceUtilizationEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
					biomedicalResourceUtilization));
		}
		
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
		return query;
	}
	public static String search(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = new HashMap<String, Boolean>();


		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String studyTitle = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_TITLE);
		
		String studyStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_START_TIME);
		
		String studyEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_END_TIME);

		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String sessionIdNumber = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_ID_NUMBER);
		String sessionTaskLabel = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_TASK_LABEL);
		String sessionPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_PURPOSE);
		String sessionLabId = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_LAB_ID);
		
		String sessionStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_START_TIME);
		String sessionEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_END_TIME);
   //same
		
		String RecordNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MIN);
		String RecordNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS_MAX);
		String RecordSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE_MIN);
		String RecordSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE_MAX);
		String RecordStartTime = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_START_TIME);
		String RecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.RECORD_END_TIME);
		
		String recordChannelFormat = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.RECORD_CHANNEL_FORMAT);
//add more bci record, channel, device...
		
		String eyeGazeChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_TYPE);
		String eyeGazeChannelReferTo = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO);
		String eyeGazeChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_UNIT);
   
		String handGestureChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE);
		String handGestureChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT);
		
		String mouseClickChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE);
		String mouseClickChannelButton = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON);

		String subjectGender = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_GENDER);
		String subjectYearOfBirthMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN);
		String subjectYearOfBirthMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX);
		String subjectHandedness = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_HANDEDNESS);

		String measurementPropertySamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN);
		String measurementPropertySamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);

		String recordedModalityModalityType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_TYPE);
		
		String recordedModalityModalitySignalType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);

		String biomedicalResourceTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE);
		String biomedicalResourceNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN);
		String biomedicalResourceNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX);
		String biomedicalResourceSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN);
		String biomedicalResourceSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX);
		String biomedicalResourceUtilization = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION);

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isStudyTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyTitle);
		
		boolean isStudyStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyStartTime);
		boolean isStudyEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyEndTime);

		boolean isSessionIdNumberEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionIdNumber);
		boolean isSessionTaskLabelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionTaskLabel);
		boolean isSessionPurposeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionPurpose);
		boolean isSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionLabId);
		boolean isSessionStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionStartTime);
		boolean isSessionEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionEndTime);

		boolean isRecordNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordNumberOfChannelsMin);
		boolean isRecordNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordNumberOfChannelsMax);
		boolean isRecordSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMin);
		boolean isRecordSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMax);
		
		boolean isRecordStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordStartTime);
		boolean isRecordEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordEndTime);

		boolean isSubjectGenderEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectGender);
		boolean isSubjectYearOfBirthMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMin);
		boolean isSubjectYearOfBirthMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMax);
		boolean isSubjectHandednessEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectHandedness);

		boolean isEyeChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelType);
		
		boolean isEyeChannelReferToEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelReferTo);

		boolean isEyeChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelUnit);
		
		
		boolean isRecordeSampingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMin);

		boolean isRecordeSampingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(RecordSamplingRateMax);

		
		boolean isRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordChannelFormat);

		boolean isHandGestureChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelType);
		
		boolean isHandGestureChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelUnit);

		boolean isMouseClickChannelButtonEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelButton);
		
		boolean isMouseClickChannelTypeEmty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelType);
		

		// boolean isRecordedSubjectAtSessionLabIdEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionLabId);
		// boolean isRecordedSubjectAtSessionGroupEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionGroup);
		// boolean isRecordedSubjectAtSessionAgeMinEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionAgeMin);
		// boolean isRecordedSubjectAtSessionAgeMaxEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionAgeMax);
		// boolean isRecordedSubjectAtSessionChannelLocationTypeEmpty =
		// BciSparqlMediatorUtility
		// .isValueEmpty(recordedSubjectAtSessionChannelLocationType);

		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMin);
		boolean isMeasurementPropertySamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMax);

		boolean isRecordedModalityModalityTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalityType);
		
		boolean isRecordedModalityModalitySignalTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalitySignalType);

		boolean isBiomedicalResourceTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceTitle);
		boolean isBiomedicalResourceNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMin);
		boolean isBiomedicalResourceNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMax);
		boolean isBiomedicalResourceSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMin);
		boolean isBiomedicalResourceSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMax);
		boolean isBiomedicalResourceUtilizationEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUtilization);

		// boolean isChannelLocationsTitleEmpty = BciSparqlMediatorUtility
		// .isValueEmpty(channelLocationsTitle);
		// boolean isChannelLocationsUtilizationEmpty = BciSparqlMediatorUtility
		// .isValueEmpty(channelLocationsUtilization);

		// Study
		if (!isStudyTitleEmpty) {
			exists.put(DataConstants.STUDY_TITLE, true);
		}
		if (!isStudyStartTimeEmpty) {
			exists.put(DataConstants.STUDY_START_TIME, true);
		}
		if (!isStudyEndTimeEmpty) {
			exists.put(DataConstants.STUDY_END_TIME, true);
		}

		// Session
		if (!isSessionIdNumberEmpty) {
			exists.put(DataConstants.SESSION_ID_NUMBER, true);
		}
		if (!isSessionTaskLabelEmpty) {
			exists.put(DataConstants.SESSION_TASK_LABEL, true);
		}
		if (!isSessionPurposeEmpty) {
			exists.put(DataConstants.SESSION_PURPOSE, true);
		}
		if (!isSessionLabIdEmpty) {
			exists.put(DataConstants.SESSION_LAB_ID, true);
		}
		if (!isSessionStartTimeEmpty) {
			exists.put(DataConstants.SESSION_START_TIME, true);
		}
		if (!isSessionEndTimeEmpty) {
			exists.put(DataConstants.SESSION_END_TIME, true);
		}

		// EEG Record
		if (!isRecordNumberOfChannelsMinEmpty
				|| !isRecordNumberOfChannelsMaxEmpty) {
			exists.put(DataConstants.RECORD_NUMBER_OF_CHANNELS, true);
		}
		if (!isRecordSamplingRateMinEmpty
				|| !isRecordSamplingRateMaxEmpty) {
			exists.put(DataConstants.RECORD_SAMPLING_RATE, true);
		}
		
		
		if (!isEyeChannelReferToEmpty) {
			exists.put(DataConstants.EYE_GAZE_CHANNEL_REFER_TO, true);
		}

		if (!isEyeChannelTypeEmpty) {
			exists.put(DataConstants.EYE_GAZE_CHANNEL_TYPE, true);
		}

		if (!isEyeChannelUnitEmpty) {
			exists.put(DataConstants.EYE_GAZE_CHANNEL_UNIT, true);
		}

		if (!isRecordChannelFormatEmpty) {
			exists.put(DataConstants.RECORD_CHANNEL_FORMAT, true);
		}

		if (!isRecordeSampingRateMaxEmpty 
				||!isRecordeSampingRateMinEmpty) {
			exists.put(DataConstants.RECORD_SAMPLING_RATE, true);
		}
		
		
		if (!isRecordStartTimeEmpty) {
			exists.put(DataConstants.RECORD_START_TIME, true);
		}

		if (!isRecordEndTimeEmpty) {
			exists.put(DataConstants.RECORD_END_TIME, true);
		}
// HAND GESTURE

		if (!isHandGestureChannelTypeEmpty) {
			exists.put(DataConstants.HAND_GESTURE_CHANNEL_TYPE, true);
		}

		if (!isHandGestureChannelUnitEmpty) {
			exists.put(DataConstants.HAND_GESTURE_CHANNEL_UNIT, true);
		}

	
//MOUSE_CLICK
		if (!isMouseClickChannelButtonEmpty) {
			exists.put(DataConstants.MOUSE_CLICK_CHANNEL_BOTTON, true);
		}
		if (!isMouseClickChannelTypeEmty) {
			exists.put(DataConstants.MOUSE_CLICK_CHANNEL_TYPE, true);
		}
		
		// Subject
		if (!isSubjectGenderEmpty) {
			exists.put(DataConstants.SUBJECT_GENDER, true);
		}
		if (!isSubjectYearOfBirthMinEmpty || !isSubjectYearOfBirthMaxEmpty) {
			exists.put(DataConstants.SUBJECT_YEAR_OF_BIRTH, true);
		}
		if (!isSubjectHandednessEmpty) {
			exists.put(DataConstants.SUBJECT_HANDEDNESS, true);
		}

		// // Recorded Subject At Session
		// if (!isRecordedSubjectAtSessionLabIdEmpty) {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID, true);
		// }
		// if (!isRecordedSubjectAtSessionGroupEmpty) {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP, true);
		// }
		// if (!isRecordedSubjectAtSessionAgeMinEmpty
		// || !isRecordedSubjectAtSessionAgeMaxEmpty) {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE, true);
		// }
		// if (!isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
		// exists.put(
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
		// true);
		// }

		// EEG Device
		if (!isMeasurementPropertyStartChannelEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL, true);
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL, true);
		}
		if (!isMeasurementPropertySamplingRateMinEmpty
				|| !isMeasurementPropertySamplingRateMaxEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE, true);
		}

		// Recorded Modality
		if (!isRecordedModalityModalityTypeEmpty) {
			exists.put(DataConstants.RECORDED_MODALITY_MODALITY_TYPE, true);
		}
		
		if (!isRecordedModalityModalitySignalTypeEmpty) {
			exists.put(DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE, true);
		}

		// Biomedical Resource
		if (!isBiomedicalResourceTitleEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_TITLE, true);
		}
		if (!isBiomedicalResourceNumberOfChannelsMinEmpty
				|| !isBiomedicalResourceNumberOfChannelsMaxEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS,
					true);
		}
		if (!isBiomedicalResourceSamplingRateMinEmpty
				|| !isBiomedicalResourceSamplingRateMaxEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE, true);
		}
		if (!isBiomedicalResourceUtilizationEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION, true);
		}

		
		

		
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT);

		query.append(SparqlVariableConstants.STUDY_ID);
		if (fields.containsKey(DataConstants.STUDY_TITLE)) {
			query.append(SparqlVariableConstants.STUDY_TITLE);
		}
		if (fields.containsKey(DataConstants.STUDY_PURPOSE)) {
			query.append(SparqlVariableConstants.STUDY_PURPOSE);
		}
		if (fields.containsKey(DataConstants.STUDY_UUID)) {
			query.append(SparqlVariableConstants.STUDY_UUID);
		}
		if (fields.containsKey(DataConstants.STUDY_ROOT_URI)) {
			query.append(SparqlVariableConstants.STUDY_ROOT_URI);
		}
		if (fields.containsKey(DataConstants.STUDY_START_TIME)) {
			query.append(SparqlVariableConstants.STUDY_START_TIME);
		}
		if (fields.containsKey(DataConstants.STUDY_END_TIME)) {
			query.append(SparqlVariableConstants.STUDY_END_TIME);
		}

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SESSION_ID_NUMBER)) {
			query.append(SparqlVariableConstants.SESSION_ID_NUMBER);
		}
		if (fields.containsKey(DataConstants.SESSION_TASK_LABEL)) {
			query.append(SparqlVariableConstants.SESSION_TASK_LABEL);
		}
		if (fields.containsKey(DataConstants.SESSION_PURPOSE)) {
			query.append(SparqlVariableConstants.SESSION_PURPOSE);
		}
		if (fields.containsKey(DataConstants.SESSION_LAB_ID)) {
			query.append(SparqlVariableConstants.SESSION_LAB_ID);
		}
		
		
		if (fields.containsKey(DataConstants.SESSION_START_TIME)) {
			query.append(SparqlVariableConstants.SESSION_START_TIME);
		}
		if (fields.containsKey(DataConstants.SESSION_END_TIME)) {
			query.append(SparqlVariableConstants.SESSION_END_TIME);
		}
        
		if (fields.containsKey(DataConstants.RECORD_ID)) {
			query.append(SparqlVariableConstants.RECORD_ID);
		}
		if (fields.containsKey(DataConstants.RECORD_NUMBER_OF_CHANNELS)) {
			query.append(SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS);
		}
		
		if (fields.containsKey(DataConstants.RECORD_CHANNEL_FORMAT)) {
			query.append(SparqlVariableConstants.RECORD_CHANNEL_FORMAT);	
		}
		
		if (fields.containsKey(DataConstants.RECORD_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.RECORD_SAMPLING_RATE);
		}
		
		if (fields.containsKey(DataConstants.RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.RECORD_START_TIME); 
		}
		
		if (fields.containsKey(DataConstants.RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME); 
		}
		
//EYEGAZE
		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_CHANNEL_ID);	
		}

		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_REFER_TO)) {
			query.append(SparqlVariableConstants.EYE_GAZE_CHANNEL_REFERTO);	
		}

		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_TYPE)) {
			query.append(SparqlVariableConstants.EYE_GAZE_CHANNEL_TYPE);	
		}

		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_UNIT)) {
			query.append(SparqlVariableConstants.EYE_GAZE_CHANNEL_UNIT);	
		}


	//HAND_GESTURE

		if (fields.containsKey(DataConstants.HAND_GESTURE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID);
		}
		if (fields.containsKey(DataConstants.HAND_GESTURE_CHANNEL_TYPE)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_CHANNEL_TYPE);
		}
		
		if (fields.containsKey(DataConstants.HAND_GESTURE_CHANNEL_UNIT)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_CHANNEL_UNIT);
		}
		//KEYBOARD
		/*
		if (fields.containsKey(DataConstants.KEYBOARD_HIT_RECORD_ID)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME); 
		}
		/*
		
		*/
			
		if (fields.containsKey(DataConstants.MOUSE_CLICK_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID); 
		}
		
		if (fields.containsKey(DataConstants.MOUSE_CLICK_CHANNEL_TYPE)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_CHANNEL_TYPE);
		}

		if (fields.containsKey(DataConstants.MOUSE_CLICK_CHANNEL_BOTTON)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_CHANNEL_BUTTON);
		}
		
		
		if (fields.containsKey(DataConstants.SUBJECT_GENDER)) {
			query.append(SparqlVariableConstants.SUBJECT_GENDER);
		}
		if (fields.containsKey(DataConstants.SUBJECT_YEAR_OF_BIRTH)) {
			query.append(SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH);
		}
		if (fields.containsKey(DataConstants.SUBJECT_HANDEDNESS)) {
			query.append(SparqlVariableConstants.SUBJECT_HANDEDNESS);
		}

		if (fields.containsKey(DataConstants.EYE_GAZE_DEVICE_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_DEVICE_ID);
		}
		
		if (fields.containsKey(DataConstants.HAND_GESTURE_DEVICE_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_DEVICE_ID);
		}
		
		if (fields.containsKey(DataConstants.KEYBOARD_HIT_DEVICE_ID)) {
			query.append(SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID);
		}

		if (fields.containsKey(DataConstants.MOUSE_CLICK_DEVICE_ID)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID);
		}
		
		
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);
		}
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		}
		if (fields.containsKey(DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		}

		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_MODALITY_TYPE)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_SAMPLING_RATE);
		}
		if (fields
				.containsKey(DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_START_CHANNEL)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_START_CHANNEL);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_END_CHANNEL)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_END_CHANNEL);
		}
		if (fields
				.containsKey(DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_REFERENCE_LOCATION);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_REFERENCE_LABEL)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_REFERENCE_LABEL);
		}

		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_TITLE)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION);
		}
		if (fields
				.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);
		}

		
		if (fields.containsKey(DataConstants.EVENT_INSTANCE_FILE_ID)) {
			query.append(SparqlVariableConstants.EVENT_INSTANCE_FILE_ID);
		}
		if (fields.containsKey(DataConstants.EVENT_INSTANCE_FILE_TITLE)) {
			query.append(SparqlVariableConstants.EVENT_INSTANCE_FILE_TITLE);
		}
		if (fields.containsKey(DataConstants.EVENT_INSTANCE_FILE_UTILIZATION)) {
			query.append(SparqlVariableConstants.EVENT_INSTANCE_FILE_UTILIZATION);
		}
		if (fields
				.containsKey(DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL)) {
			query.append(SparqlVariableConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL);
		}

		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		fields.putAll(exists);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(whereClause_Search(fields,jsonObject));// condition of localhost:8890
        //make a loop to get all of remote server
		 
	    query.append(genFederatedQuery(whereClause_Search(fields,jsonObject)));
		
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

	/*	
		query.append("ORDER BY DESC(xsd:dateTime(?session_startTime))"
				+ SparqlSyntaxConstants.NEW_LINE);
     */
		if(BciSparqlEndpointConfig.SPARQL_QUERY_DEBUG_FILE_ISACTIVE.compareTo("1") == 0){
			BciSparqlMediatorUtility.WriteFile(BciSparqlEndpointConfig.SPARQL_QUERY_DEBUG_FILENAME, query.toString());
		}
		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());

	}
	
	//Function create the FEDERATED QUERY structure
	
	public static StringBuffer genFederatedQuery(StringBuffer whereClause){
		StringBuffer union = new StringBuffer();
		NodeList nodeList = BciSparqlEndpointConfig.SPARQL_ENDPOINTS;

		Node sparql_endpoint;
		Attr service_uri;
		boolean isSilent = true;
		for (int i = 0; i < nodeList.getLength(); i++) {
			 sparql_endpoint = nodeList.item(i);
			 service_uri = (Attr) sparql_endpoint.getAttributes().getNamedItem("service");
			 isSilent = (sparql_endpoint.getAttributes().getNamedItem("silent").getNodeValue().compareTo("1") == 0);
			 union.append(SparqlSyntaxConstants.UNION + SparqlSyntaxConstants.LEFT_BRACE); //UNION {
			 union.append(SparqlSyntaxConstants.SERVICE + SparqlSyntaxConstants.SPACE
					 + ((isSilent) ? SparqlSyntaxConstants.SILENT : " " )
					 + SparqlSyntaxConstants.LEFT_CHEVRON
					 + service_uri.getValue().toString()
					 + SparqlSyntaxConstants.RIGHT_CHEVRON);
			 union.append(whereClause);
			 union.append(SparqlSyntaxConstants.RIGHT_BRACE);//} UNION 
		 }
		return union;
	}

	//Fuction create where clause
	
	public static StringBuffer whereClause_queryStudy(JSONObject jsonObject){
		 
		 
		 // ************* Generation of the WHERE Clause ********************* //
		 StringBuffer where= new StringBuffer();
		 
		 HashMap<String, Boolean> fields= BciSparqlMediatorUtility
				 .jsonArray2HashMap(jsonObject, OperationConstants.QUERY_FUNCTION_FIELDS);
		 String studyUri=BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				 DataConstants.STUDY_ID);
		 String sessionUri= BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				 DataConstants.SESSION_ID);
		 String studyTitle= BciSparqlMediatorUtility.getJsonObjectValue(jsonObject, 
				 DataConstants.STUDY_TITLE);
		 
		 String studyStartTime= BciSparqlMediatorUtility.getJsonObjectValue(jsonObject,
				 DataConstants.STUDY_START_TIME);
		 String studyEndTime= BciSparqlMediatorUtility.getJsonObjectValue(jsonObject,
				 DataConstants.STUDY_END_TIME);
		 
		 
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// Study
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.STUDY_ID + SparqlSyntaxConstants.A
					+ ClassConstants.BCI_STUDY + SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.SESSION_ID,
					SparqlVariableConstants.STUDY_ID,
					PropertyConstants.BCI_HAS_SESSION,
					SparqlVariableConstants.SESSION_ID));

			where.append(genQueryTriple(fields, DataConstants.STUDY_TITLE,
					SparqlVariableConstants.STUDY_ID,
					PropertyConstants.BCI_HAS_TITLE,
					SparqlVariableConstants.STUDY_TITLE));
			where.append(genQueryTriple(fields, DataConstants.STUDY_PURPOSE,
					SparqlVariableConstants.STUDY_ID,
					PropertyConstants.BCI_HAS_PURPOSE,
					SparqlVariableConstants.STUDY_PURPOSE));
			where.append(genQueryTriple(fields, DataConstants.STUDY_START_TIME,
					SparqlVariableConstants.STUDY_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.STUDY_START_TIME));
			where.append(genQueryTriple(fields, DataConstants.STUDY_END_TIME,
					SparqlVariableConstants.STUDY_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.STUDY_END_TIME));
			 boolean isStudyUriEmpty= BciSparqlMediatorUtility.isValueEmpty(studyUri);
			 boolean isStudyTitleEmpty=BciSparqlMediatorUtility.isValueEmpty(studyTitle);
			 boolean isStudyStartTimeEmpty=BciSparqlMediatorUtility.isValueEmpty(studyStartTime);
			 boolean isStudyEndTimeEmpty=BciSparqlMediatorUtility.isValueEmpty(studyEndTime);
			 boolean isSessionUriEmpty = BciSparqlMediatorUtility.isValueEmpty(sessionUri);
			// Filter
			if (!isStudyUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.STUDY_ID,
						studyUri));
			}
			if (!isStudyTitleEmpty) {
				where.append(genFilterStringRegex(
						SparqlVariableConstants.STUDY_TITLE,
						jsonObject.getString(DataConstants.STUDY_TITLE)));
			}
			if (!isStudyStartTimeEmpty) {
				where.append(genFilterDatetimeGreaterThan(
						SparqlVariableConstants.STUDY_START_TIME,
						jsonObject.getString(DataConstants.STUDY_START_TIME)));

			}
			if (!isStudyEndTimeEmpty) {
				where.append(genFilterDatetimeLessThan(
						SparqlVariableConstants.STUDY_END_TIME,
						jsonObject.getString(DataConstants.STUDY_END_TIME)));
			}
			if (!isSessionUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
						sessionUri));
			}

			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
		 return where;
		 
	 }
	
	public static String queryStudyFeder(JSONObject jsonObject){
		 HashMap<String, Boolean> fields= BciSparqlMediatorUtility
				 .jsonArray2HashMap(jsonObject, OperationConstants.QUERY_FUNCTION_FIELDS);
		 //sparql
		 StringBuffer query= new StringBuffer();
		 query.append(NamespaceConstants.PREFIX_BCI + SparqlSyntaxConstants.NEW_LINE);
		 query.append(NamespaceConstants.PREFIX_XSD + SparqlSyntaxConstants.NEW_LINE);
		 query.append(SparqlSyntaxConstants.NEW_LINE);
		 
		 query.append(SparqlSyntaxConstants.SELECT+ SparqlSyntaxConstants.DISTINCT+
				 SparqlVariableConstants.STUDY_ID);
		 
		 if (fields.containsKey(DataConstants.STUDY_TITLE)){
			 query.append(SparqlVariableConstants.STUDY_TITLE);
		 }
		 if (fields.containsKey(DataConstants.STUDY_PURPOSE)){
			 query.append(SparqlVariableConstants.STUDY_PURPOSE);
		 }
		 if (fields.containsKey(DataConstants.STUDY_START_TIME)){
			 query.append(SparqlVariableConstants.STUDY_START_TIME);
		 }
		 if(fields.containsKey(DataConstants.STUDY_END_TIME)){
			 query.append(SparqlVariableConstants.STUDY_END_TIME);
		 }
		 
			
		 if(fields.containsKey(DataConstants.SESSION_ID)){
			 query.append(SparqlVariableConstants.SESSION_ID);
		 }
		 query.append(SparqlSyntaxConstants.NEW_LINE);
		 //Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryStudy(jsonObject));// condition of localhost:8890
         //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryStudy(jsonObject)));
		 /*
		 NodeList nodeList= getNodeList(xmlFilePath);
		 for (int i = 0; i < nodeList.getLength(); i++) {
			 
			 Node node = nodeList.item(i);
			 Attr attr =(Attr) node.getAttributes().getNamedItem("service");
			 query.append(SparqlSyntaxConstants.UNION + SparqlSyntaxConstants.LEFT_BRACE);      //UNION {
			 query.append(SparqlSyntaxConstants.SERVICE 
					 + SparqlSyntaxConstants.LEFT_CHEVRON
					 + attr.getValue().toString()
					 + SparqlSyntaxConstants.RIGHT_CHEVRON);
			 query.append(whereClause_queryStudy(jsonObject));
			 query.append(SparqlSyntaxConstants.RIGHT_BRACE);//} UNION
			 
		 }
		 */
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}
		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
		 
	 }
	
	
	public static String queryStudy(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);

		String studyTitle = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_TITLE);
		String studyStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_START_TIME);
		String studyEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_END_TIME);

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isStudyTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyTitle);
		boolean isStudyStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyStartTime);
		boolean isStudyEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyEndTime);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);

		// SPARQL Generator
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.STUDY_ID);
		if (fields.containsKey(DataConstants.STUDY_TITLE)) {
			query.append(SparqlVariableConstants.STUDY_TITLE);
		}
		if (fields.containsKey(DataConstants.STUDY_PURPOSE)) {
			query.append(SparqlVariableConstants.STUDY_PURPOSE);
		}
		if (fields.containsKey(DataConstants.STUDY_START_TIME)) {
			query.append(SparqlVariableConstants.STUDY_START_TIME);
		}
		if (fields.containsKey(DataConstants.STUDY_END_TIME)) {
			query.append(SparqlVariableConstants.STUDY_END_TIME);
		}
		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Study
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.STUDY_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_STUDY + SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.SESSION_ID,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_SESSION,
				SparqlVariableConstants.SESSION_ID));

		query.append(genQueryTriple(fields, DataConstants.STUDY_TITLE,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlVariableConstants.STUDY_TITLE));
		query.append(genQueryTriple(fields, DataConstants.STUDY_PURPOSE,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_PURPOSE,
				SparqlVariableConstants.STUDY_PURPOSE));
		query.append(genQueryTriple(fields, DataConstants.STUDY_START_TIME,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.STUDY_START_TIME));
		query.append(genQueryTriple(fields, DataConstants.STUDY_END_TIME,
				SparqlVariableConstants.STUDY_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.STUDY_END_TIME));

		// Filter
		if (!isStudyUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.STUDY_ID,
					studyUri));
		}
		if (!isStudyTitleEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.STUDY_TITLE,
					jsonObject.getString(DataConstants.STUDY_TITLE)));
		}
		if (!isStudyStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.STUDY_START_TIME,
					jsonObject.getString(DataConstants.STUDY_START_TIME)));

		}
		if (!isStudyEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.STUDY_END_TIME,
					jsonObject.getString(DataConstants.STUDY_END_TIME)));
		}
		if (!isSessionUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
					sessionUri));
		}

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String querySession(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String recordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORD_ID);
		String recordedSubjectAtSessionUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);

		String sessionIdNumber = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_ID_NUMBER);
		String sessionTaskLabel = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_TASK_LABEL);
		String sessionPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_PURPOSE);
		String sessionLabId = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_LAB_ID);
		String sessionStartTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_START_TIME);
		String sessionEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_END_TIME);

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isRecordedSubjectAtSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionUri);

		boolean isSessionIdNumberEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionIdNumber);
		boolean isSessionTaskLabelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionTaskLabel);
		boolean isSessionPurposeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionPurpose);
		boolean isSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionLabId);
		boolean isSessionStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionStartTime);
		boolean isSessionEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionEndTime);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.SESSION_ID);

		if (fields.containsKey(DataConstants.STUDY_ID)) {
			query.append(SparqlVariableConstants.STUDY_ID);
		}
		if (fields.containsKey(DataConstants.RECORD_ID)) {
			query.append(SparqlVariableConstants.RECORD_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SESSION_ID_NUMBER)) {
			query.append(SparqlVariableConstants.SESSION_ID_NUMBER);
		}
		if (fields.containsKey(DataConstants.SESSION_TASK_LABEL)) {
			query.append(SparqlVariableConstants.SESSION_TASK_LABEL);
		}
		if (fields.containsKey(DataConstants.SESSION_PURPOSE)) {
			query.append(SparqlVariableConstants.SESSION_PURPOSE);
		}
		if (fields.containsKey(DataConstants.SESSION_LAB_ID)) {
			query.append(SparqlVariableConstants.SESSION_LAB_ID);
		}
		if (fields.containsKey(DataConstants.SESSION_START_TIME)) {
			query.append(SparqlVariableConstants.SESSION_START_TIME);
		}
		if (fields.containsKey(DataConstants.SESSION_END_TIME)) {
			query.append(SparqlVariableConstants.SESSION_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.SESSION_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_SESSION + SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.STUDY_ID,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_IS_SESSION_OF,
				SparqlVariableConstants.STUDY_ID));
		query.append(genQueryTriple(fields, DataConstants.RECORD_ID,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_RECORD,
				SparqlVariableConstants.RECORD_ID));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID));

		query.append(genQueryTriple(fields, DataConstants.SESSION_ID_NUMBER,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_ID_NUMBER,
				SparqlVariableConstants.SESSION_ID_NUMBER));
		query.append(genQueryTriple(fields, DataConstants.SESSION_TASK_LABEL,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_TASK_LABEL,
				SparqlVariableConstants.SESSION_TASK_LABEL));
		query.append(genQueryTriple(fields, DataConstants.SESSION_PURPOSE,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_PURPOSE,
				SparqlVariableConstants.SESSION_PURPOSE));
		query.append(genQueryTriple(fields, DataConstants.SESSION_LAB_ID,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_SESSION_LAB_ID,
				SparqlVariableConstants.SESSION_LAB_ID));
		query.append(genQueryTriple(fields, DataConstants.SESSION_START_TIME,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.SESSION_START_TIME));
		query.append(genQueryTriple(fields, DataConstants.SESSION_END_TIME,
				SparqlVariableConstants.SESSION_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.SESSION_END_TIME));

		// Filter
		if (!isStudyUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.STUDY_ID,
					studyUri));
		}
		if (!isSessionUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
					sessionUri));
		}
		if (!isRecordUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.RECORD_ID,
					recordUri));
		}
		if (!isRecordedSubjectAtSessionUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
					recordedSubjectAtSessionUri));
		}

		if (!isSessionIdNumberEmpty) {
			query.append(genFilterNumberEqual(
					SparqlVariableConstants.SESSION_ID_NUMBER,
					Integer.parseInt(sessionIdNumber)));
		}
		if (!isSessionTaskLabelEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_TASK_LABEL,
					sessionTaskLabel));
		}
		if (!isSessionPurposeEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_PURPOSE, sessionPurpose));
		}
		if (!isSessionLabIdEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.SESSION_LAB_ID, sessionLabId));
		}
		if (!isSessionStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.SESSION_START_TIME,
					sessionStartTime));
		}
		if (!isSessionEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.SESSION_END_TIME, sessionEndTime));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryEegRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);

		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		
		String eegRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EEG_RECORD_ID);
		
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		
		String eegDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EEG_DEVICE_ID);
		
		String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORDED_MODALITY_ID);
		
		String biomedicalResourceUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_ID);

		String eegRecordNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_RECORD_NUMBER_OF_CHANNELS_MIN);
		String eegRecordNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_RECORD_NUMBER_OF_CHANNELS_MAX);
		String eegRecordSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_RECORD_SAMPLING_RATE_MIN);
		String eegRecordSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_RECORD_SAMPLING_RATE_MAX);
		String eegRecordStartTime = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_RECORD_START_TIME);
		String eegRecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.EEG_RECORD_END_TIME);

		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isEegRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isEegDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegDeviceUri);
		boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityUri);
		boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUri);

		boolean isEegRecordNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordNumberOfChannelsMin);
		boolean isEegRecordNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordNumberOfChannelsMax);
		boolean isEegRecordSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordSamplingRateMin);
		boolean isEegRecordSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordSamplingRateMax);
		boolean isEegRecordStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordStartTime);
		boolean isEegRecordEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordEndTime);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.EEG_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.EEG_DEVICE_ID)) {
			query.append(SparqlVariableConstants.EEG_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		if (fields.containsKey(DataConstants.EEG_RECORD_NUMBER_OF_CHANNELS)) {
			query.append(SparqlVariableConstants.EEG_RECORD_NUMBER_OF_CHANNELS);
		}
		if (fields.containsKey(DataConstants.EEG_RECORD_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.EEG_RECORD_SAMPLING_RATE);
		}
		if (fields.containsKey(DataConstants.EEG_RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.EEG_RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.EEG_RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.EEG_RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EEG Record
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.EEG_RECORD_ID
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_EEG_RECORD
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.SESSION_ID,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_IS_RECORD_OF,
				SparqlVariableConstants.SESSION_ID));
		
		query.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_IS_FROM_SUBJECT,
				SparqlVariableConstants.SUBJECT_ID));
		
		query.append(genQueryTriple(fields, DataConstants.EEG_DEVICE_ID,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_IS_GENERATED_BY_EEG_DEVICE,
				SparqlVariableConstants.EEG_DEVICE_ID));
		query.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_RECORDED_MODALITY,
				SparqlVariableConstants.RECORDED_MODALITY_ID));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_ID,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

		query.append(genQueryTriple(fields,
				DataConstants.EEG_RECORD_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.EEG_RECORD_NUMBER_OF_CHANNELS));
		query.append(genQueryTriple(fields,
				DataConstants.EEG_RECORD_SAMPLING_RATE,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.EEG_RECORD_SAMPLING_RATE));
		query.append(genQueryTriple(fields,
				DataConstants.EEG_RECORD_START_TIME,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.EEG_RECORD_START_TIME));
		query.append(genQueryTriple(fields, DataConstants.EEG_RECORD_END_TIME,
				SparqlVariableConstants.EEG_RECORD_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.EEG_RECORD_END_TIME));

		// Filter
		if (!isSessionUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
					sessionUri));
		}
		if (!isEegRecordUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.EEG_RECORD_ID, eegRecordUri));
		}
		if (!isSubjectUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
					subjectUri));
		}
		if (!isEegDeviceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.EEG_DEVICE_ID, eegDeviceUri));
		}
		if (!isRecordedModalityUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_MODALITY_ID,
					recordedModalityUri));
		}
		if (!isBiomedicalResourceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
					biomedicalResourceUri));
		}

		if (!isEegRecordNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.RECORD_NUMBER_OF_CHANNELS,//EEG_RECORD_NUMBER_OF_CHANNELS,
					Integer.parseInt(eegRecordNumberOfChannelsMin)));
		}
		if (!isEegRecordNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.EEG_RECORD_NUMBER_OF_CHANNELS,
					Integer.parseInt(eegRecordNumberOfChannelsMax)));
		}
		if (!isEegRecordSamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.EEG_RECORD_SAMPLING_RATE,
					Integer.parseInt(eegRecordSamplingRateMin)));
		}
		if (!isEegRecordSamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.RECORD_SAMPLING_RATE,
					Integer.parseInt(eegRecordSamplingRateMax)));
		}
		if (!isEegRecordStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.EEG_RECORD_START_TIME,
					eegRecordStartTime));
		}
		if (!isEegRecordEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.EEG_RECORD_END_TIME,
					eegRecordEndTime));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	//EyeGaze
	
	public static StringBuffer whereClause_queryEyeGazeRecord(JSONObject jsonObject){
		 
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SESSION_ID);
			
			String eyeGazeRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_RECORD_ID);
			
			String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SUBJECT_ID);
			
			String eyeGazeDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_DEVICE_ID);
			
			String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.RECORDED_MODALITY_ID);
			
			String biomedicalResourceUri = BciSparqlMediatorUtility
					.getJsonObjectUri(jsonObject,
							DataConstants.BIOMEDICAL_RESOURCE_ID);

			String eyeGazeRecordChannelFormat = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject, 
							DataConstants.RECORD_CHANNEL_FORMAT);
			
			String eyeGazeRecordSamplingRate = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_SAMPLING_RATE);
			
			String eyeGazeRecordStartTime = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_START_TIME);
			String eyeGazeRecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.RECORD_END_TIME);

			boolean isSessionUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(sessionUri);
			boolean isEyeGazeRecordUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordUri);
			boolean isSubjectUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(subjectUri);
			boolean isEyeGazeDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeDeviceUri);
			boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(recordedModalityUri);
			boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(biomedicalResourceUri);

			boolean isEyeGazeRecordChannelFormatEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordChannelFormat);
			
			
			boolean isEyeGazeRecordSamplingRateEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordSamplingRate);
			
			boolean isEyeGazeRecordStartTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordStartTime);
			
			boolean isEyeGazeRecordEndTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordEndTime);
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// EYEGAZE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EYE_GAZE_RECORD_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_EYE_GAZE_RECORD
					+ SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.SESSION_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_IS_RECORD_OF,
					SparqlVariableConstants.SESSION_ID));
			where.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_IS_FROM_SUBJECT,
					SparqlVariableConstants.SUBJECT_ID));
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_DEVICE_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE,
					SparqlVariableConstants.EEG_DEVICE_ID));
			where.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_RECORDED_MODALITY,
					SparqlVariableConstants.RECORDED_MODALITY_ID));
			where.append(genQueryTriple(fields,
					DataConstants.BIOMEDICAL_RESOURCE_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

			where.append(genQueryTriple(fields,
					DataConstants.RECORD_CHANNEL_FORMAT,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT,
					SparqlVariableConstants.RECORD_CHANNEL_FORMAT));
			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_SAMPLING_RATE,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_SAMPLING_RATE,
					SparqlVariableConstants.RECORD_SAMPLING_RATE));
			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_START_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.RECORD_START_TIME));
			
			where.append(genQueryTriple(fields, DataConstants.RECORD_END_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.RECORD_END_TIME));

			// Filter
			if (!isSessionUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
						sessionUri));
			}
			if (!isEyeGazeRecordUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_RECORD_ID, eyeGazeRecordUri));
			}
			if (!isSubjectUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
						subjectUri));
			}
			if (!isEyeGazeDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_DEVICE_ID, eyeGazeDeviceUri));
			}
			if (!isRecordedModalityUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.RECORDED_MODALITY_ID,
						recordedModalityUri));
			}
			if (!isBiomedicalResourceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
						biomedicalResourceUri));
			}

			
			if (!isEyeGazeRecordSamplingRateEmpty) {
				where.append(genFilterNumberGreaterThan(
						SparqlVariableConstants.RECORD_SAMPLING_RATE,
						Integer.parseInt(eyeGazeRecordSamplingRate)));
			}
			
			if (!isEyeGazeRecordStartTimeEmpty) {
				where.append(genFilterDatetimeGreaterThan(
						SparqlVariableConstants.RECORD_START_TIME,
						eyeGazeRecordStartTime));
			}
			if (!isEyeGazeRecordEndTimeEmpty) {
				where.append(genFilterDatetimeLessThan(
						SparqlVariableConstants.RECORD_END_TIME,
						eyeGazeRecordEndTime));
			}

			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
	}
	
	public static String queryEyeGazeRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.EYE_GAZE_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.EYE_GAZE_DEVICE_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		
		if (fields.containsKey(DataConstants.RECORD_CHANNEL_FORMAT)) {
			query.append(SparqlVariableConstants.EYE_GAZE_RECORD_CHANNEL_FORMAT);
		}
		
		if (fields.containsKey(DataConstants.RECORD_SAMPLING_RATE )) {
			query.append(SparqlVariableConstants.RECORD_SAMPLING_RATE);
		}
		if (fields.containsKey(DataConstants.RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryEyeGazeRecord(jsonObject));// condition of localhost:8890
        //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryEyeGazeRecord(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}
		
		
		

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
	
	//HandGesture
	
	public static StringBuffer whereClause_queryHandGestureRecord(JSONObject jsonObject){
		 
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SESSION_ID);
			
			String handGestureRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.HAND_GESTURE_RECORD_ID);
			
			String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SUBJECT_ID);
			
			String handGestureDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.HAND_GESTURE_DEVICE_ID);
			
			String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.RECORDED_MODALITY_ID);
			
			String biomedicalResourceUri = BciSparqlMediatorUtility
					.getJsonObjectUri(jsonObject,
							DataConstants.BIOMEDICAL_RESOURCE_ID);

			String handGestureRecordChannelFormat = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject, 
							DataConstants.RECORD_CHANNEL_FORMAT);
			
			String handGestureRecordSamplingRate = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_SAMPLING_RATE);
			
			String handGestureRecordStartTime = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_START_TIME);
			String handGestureRecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.RECORD_END_TIME);

			boolean isSessionUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(sessionUri);
			boolean isHandGestureRecordUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordUri);
			boolean isSubjectUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(subjectUri);
			boolean isHandGestureDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureDeviceUri);
			boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(recordedModalityUri);
			boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(biomedicalResourceUri);

			boolean isHandGestureRecordChannelFormatEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordChannelFormat);
			
			
			boolean isHandGestureRecordSamplingRateEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordSamplingRate);
			
			boolean isHandGestureRecordStartTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordStartTime);
			
			boolean isHandGestureRecordEndTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordEndTime);
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// HAND GESTURE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.HAND_GESTURE_RECORD_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_HAND_GESTURE_RECORD
					+ SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.SESSION_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_IS_RECORD_OF,
					SparqlVariableConstants.SESSION_ID));
			where.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_IS_FROM_SUBJECT,
					SparqlVariableConstants.SUBJECT_ID));
			where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_DEVICE_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE,
					SparqlVariableConstants.HAND_GESTURE_DEVICE_ID));
			where.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_RECORDED_MODALITY,
					SparqlVariableConstants.RECORDED_MODALITY_ID));
			where.append(genQueryTriple(fields,
					DataConstants.BIOMEDICAL_RESOURCE_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

			where.append(genQueryTriple(fields,
					DataConstants.RECORD_CHANNEL_FORMAT,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT,
					SparqlVariableConstants.RECORD_CHANNEL_FORMAT));
			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_SAMPLING_RATE,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_SAMPLING_RATE,
					SparqlVariableConstants.RECORD_SAMPLING_RATE));
			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_START_TIME,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.RECORD_START_TIME));
			
			where.append(genQueryTriple(fields, DataConstants.RECORD_END_TIME,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.RECORD_END_TIME));

			// Filter
			if (!isSessionUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
						sessionUri));
			}
			if (!isHandGestureRecordUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.HAND_GESTURE_RECORD_ID, handGestureRecordUri));
			}
			if (!isSubjectUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
						subjectUri));
			}
			if (!isHandGestureDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.HAND_GESTURE_DEVICE_ID, handGestureDeviceUri));
			}
			if (!isRecordedModalityUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.RECORDED_MODALITY_ID,
						recordedModalityUri));
			}
			if (!isBiomedicalResourceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
						biomedicalResourceUri));
			}

			if (!isHandGestureRecordChannelFormatEmpty) {
				where.append(genFilterNumberGreaterThan(
						SparqlVariableConstants.RECORD_CHANNEL_FORMAT,
						Integer.parseInt(handGestureRecordChannelFormat)));
			}
			
			if (!isHandGestureRecordSamplingRateEmpty) {
				where.append(genFilterNumberGreaterThan(
						SparqlVariableConstants.RECORD_SAMPLING_RATE,
						Integer.parseInt(handGestureRecordSamplingRate)));
			}
			
			if (!isHandGestureRecordStartTimeEmpty) {
				where.append(genFilterDatetimeGreaterThan(
						SparqlVariableConstants.RECORD_START_TIME,
						handGestureRecordStartTime));
			}
			if (!isHandGestureRecordEndTimeEmpty) {
				where.append(genFilterDatetimeLessThan(
						SparqlVariableConstants.RECORD_END_TIME,
						handGestureRecordEndTime));
			}

			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
	}
	
    public static String queryHandGestureRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.HAND_GESTURE_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.HAND_GESTURE_DEVICE_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		if (fields.containsKey(DataConstants.RECORD_CHANNEL_FORMAT)) {
			query.append(SparqlVariableConstants.RECORD_CHANNEL_FORMAT);
		}
		if (fields.containsKey(DataConstants.RECORD_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.RECORD_SAMPLING_RATE);
		}
		if (fields.containsKey(DataConstants.RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryHandGestureRecord(jsonObject));// condition of localhost:8890
       //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryHandGestureRecord(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}


		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
	
	//KeyBoard Hit
    
	public static StringBuffer whereClause_queryKeyBoardHitRecord(JSONObject jsonObject){
		 
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SESSION_ID);
			
			String keyBoardHitRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.KEYBOARD_HIT_RECORD_ID);
			
			String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SUBJECT_ID);
			
			String keyBoardHitDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.KEYBOARD_HIT_DEVICE_ID);
			
			String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.RECORDED_MODALITY_ID);
			
			String biomedicalResourceUri = BciSparqlMediatorUtility
					.getJsonObjectUri(jsonObject,
							DataConstants.BIOMEDICAL_RESOURCE_ID);

			
			String keyBoardHitRecordStartTime = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_START_TIME);
			String keyBoardHitRecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.RECORD_END_TIME);

			boolean isSessionUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(sessionUri);
			boolean isKeyBoardHitRecordUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(keyBoardHitRecordUri);
			boolean isSubjectUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(subjectUri);
			
			boolean isKeyBoardHitDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(keyBoardHitDeviceUri);
			boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(recordedModalityUri);
			boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(biomedicalResourceUri);

			boolean isKeyBoardHitRecordStartTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(keyBoardHitRecordStartTime);
			
			boolean isKeyBoardHitRecordEndTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(keyBoardHitRecordEndTime);
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// HAND GESTURE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_KEYBOARD_HIT_RECORD
					+ SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.SESSION_ID,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_IS_RECORD_OF,
					SparqlVariableConstants.SESSION_ID));
			where.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_IS_FROM_SUBJECT,
					SparqlVariableConstants.SUBJECT_ID));
			where.append(genQueryTriple(fields, DataConstants.KEYBOARD_HIT_DEVICE_ID,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_KEYBOARD_HIT_DEVICE,
					SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID));
			where.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_HAS_RECORDED_MODALITY,
					SparqlVariableConstants.RECORDED_MODALITY_ID));
			where.append(genQueryTriple(fields,
					DataConstants.BIOMEDICAL_RESOURCE_ID,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_START_TIME,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.RECORD_START_TIME));
			
			where.append(genQueryTriple(fields, DataConstants.RECORD_END_TIME,
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.RECORD_END_TIME));

			// Filter
			if (!isSessionUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
						sessionUri));
			}
			if (!isKeyBoardHitRecordUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID, keyBoardHitRecordUri));
			}
			if (!isSubjectUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
						subjectUri));
			}
			if (!isKeyBoardHitDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID, keyBoardHitDeviceUri));
			}
			if (!isRecordedModalityUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.RECORDED_MODALITY_ID,
						recordedModalityUri));
			}
			if (!isBiomedicalResourceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
						biomedicalResourceUri));
			}

			if (!isKeyBoardHitRecordStartTimeEmpty) {
				where.append(genFilterDatetimeGreaterThan(
						SparqlVariableConstants.RECORD_START_TIME,
						keyBoardHitRecordStartTime));
			}
			if (!isKeyBoardHitRecordEndTimeEmpty) {
				where.append(genFilterDatetimeLessThan(
						SparqlVariableConstants.RECORD_END_TIME,
						keyBoardHitRecordEndTime));
			}

			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
	}
	
	public static String queryKeyBoardHitRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.KEYBOARD_HIT_DEVICE_ID)) {
			query.append(SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		
		if (fields.containsKey(DataConstants.RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryKeyBoardHitRecord(jsonObject));// condition of localhost:8890
      //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryKeyBoardHitRecord(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}


		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
    
	//MouseClick
	
	public static StringBuffer whereClause_queryMouseClickRecord(JSONObject jsonObject){
		 
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SESSION_ID);
			
			String mouseClickRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.MOUSE_CLICK_RECORD_ID);
			
			String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.SUBJECT_ID);
			
			String mouseClickDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.MOUSE_CLICK_DEVICE_ID);
			
			String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.RECORDED_MODALITY_ID);
			
			String biomedicalResourceUri = BciSparqlMediatorUtility
					.getJsonObjectUri(jsonObject,
							DataConstants.BIOMEDICAL_RESOURCE_ID);

			
			String mouseClickRecordStartTime = BciSparqlMediatorUtility
					.getJsonObjectValue(jsonObject,
							DataConstants.RECORD_START_TIME);
			String mouseClickRecordEndTime = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.RECORD_END_TIME);

			boolean isSessionUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(sessionUri);
			boolean isMouseClickRecordUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickRecordUri);
			boolean isSubjectUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(subjectUri);
			
			boolean isMouseClickDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickDeviceUri);
			boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(recordedModalityUri);
			boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(biomedicalResourceUri);

			boolean isMouseClickRecordStartTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickRecordStartTime);
			
			boolean isMouseClickRecordEndTimeEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickRecordEndTime);
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// HAND GESTURE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.MOUSE_CLICK_RECORD_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_MOUSE_CLICK_RECORD
					+ SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.SESSION_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_IS_RECORD_OF,
					SparqlVariableConstants.SESSION_ID));
			where.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_IS_FROM_SUBJECT,
					SparqlVariableConstants.SUBJECT_ID));
			where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_DEVICE_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE,
					SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID));
			where.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_HAS_RECORDED_MODALITY,
					SparqlVariableConstants.RECORDED_MODALITY_ID));
			where.append(genQueryTriple(fields,
					DataConstants.BIOMEDICAL_RESOURCE_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

			
			where.append(genQueryTriple(fields,
					DataConstants.RECORD_START_TIME,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.RECORD_START_TIME));
			
			where.append(genQueryTriple(fields, DataConstants.RECORD_END_TIME,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.RECORD_END_TIME));

			// Filter
			if (!isSessionUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
						sessionUri));
			}
			if (!isMouseClickRecordUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.MOUSE_CLICK_RECORD_ID, mouseClickRecordUri));
			}
			if (!isSubjectUriEmpty) {
				where.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
						subjectUri));
			}
			if (!isMouseClickDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID, mouseClickDeviceUri));
			}
			if (!isRecordedModalityUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.RECORDED_MODALITY_ID,
						recordedModalityUri));
			}
			if (!isBiomedicalResourceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
						biomedicalResourceUri));
			}

			if (!isMouseClickRecordStartTimeEmpty) {
				where.append(genFilterDatetimeGreaterThan(
						SparqlVariableConstants.RECORD_START_TIME,
						mouseClickRecordStartTime));
			}
			if (!isMouseClickRecordEndTimeEmpty) {
				where.append(genFilterDatetimeLessThan(
						SparqlVariableConstants.RECORD_END_TIME,
						mouseClickRecordEndTime));
			}

			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
	}
	
	public static String queryMouseClickRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.MOUSE_CLICK_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.KEYBOARD_HIT_DEVICE_ID)) {
			query.append(SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}
		
		if (fields.containsKey(DataConstants.RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryMouseClickRecord(jsonObject));// condition of localhost:8890
     //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryMouseClickRecord(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}


		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	
	public static String queryMotionCaptureRecord(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String motionCaptureRecordUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.MOTION_CAPTURE_RECORD_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		String motionCaptureDeviceUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.MOTION_CAPTURE_DEVICE_ID);
		String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORDED_MODALITY_ID);
		String biomedicalResourceUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_ID);

		String motionCaptureRecordStartTime = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOTION_CAPTURE_RECORD_START_TIME);
		String motionCaptureRecordEndTime = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOTION_CAPTURE_RECORD_END_TIME);

		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isMotionCaptureRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureRecordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isMotionCaptureDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureDeviceUri);
		boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityUri);
		boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUri);

		boolean isMotionCaptureRecordStartTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureRecordStartTime);
		boolean isMotionCaptureRecordEndTimeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureRecordEndTime);

		if (!isSessionUriEmpty) {
			exists.put(DataConstants.SESSION_ID, true);
		}
		if (!isSubjectUriEmpty) {
			exists.put(DataConstants.SUBJECT_ID, true);
		}
		if (!isMotionCaptureDeviceUriEmpty) {
			exists.put(DataConstants.MOTION_CAPTURE_DEVICE_ID, true);
		}
		if (!isRecordedModalityUriEmpty) {
			exists.put(DataConstants.RECORDED_MODALITY_ID, true);
		}
		if (!isBiomedicalResourceUriEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_ID, true);
		}

		if (!isMotionCaptureRecordStartTimeEmpty) {
			exists.put(DataConstants.MOTION_CAPTURE_RECORD_START_TIME, true);
		}
		if (!isMotionCaptureRecordEndTimeEmpty) {
			exists.put(DataConstants.MOTION_CAPTURE_RECORD_END_TIME, true);
		}

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID);

		if (fields.containsKey(DataConstants.SESSION_ID)) {
			query.append(SparqlVariableConstants.SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}
		if (fields.containsKey(DataConstants.MOTION_CAPTURE_DEVICE_ID)) {
			query.append(SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_ID)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_ID);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ID)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);
		}

		if (fields.containsKey(DataConstants.MOTION_CAPTURE_RECORD_START_TIME)) {
			query.append(SparqlVariableConstants.MOTION_CAPTURE_RECORD_START_TIME);
		}
		if (fields.containsKey(DataConstants.MOTION_CAPTURE_RECORD_END_TIME)) {
			query.append(SparqlVariableConstants.MOTION_CAPTURE_RECORD_END_TIME);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Motion Capture Record
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_MOTION_CAPTURE_RECORD
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.SESSION_ID,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_IS_RECORD_OF,
				SparqlVariableConstants.SESSION_ID));
		query.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_IS_FROM_SUBJECT,
				SparqlVariableConstants.SUBJECT_ID));
		query.append(genQueryTriple(fields,
				DataConstants.MOTION_CAPTURE_DEVICE_ID,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_IS_GENERATED_BY_MOTION_CAPTURE_DEVICE,
				SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID));
		query.append(genQueryTriple(fields, DataConstants.RECORDED_MODALITY_ID,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_RECORDED_MODALITY,
				SparqlVariableConstants.RECORDED_MODALITY_ID));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_ID,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_RESOURCE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID));

		query.append(genQueryTriple(fields,
				DataConstants.MOTION_CAPTURE_RECORD_START_TIME,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_START_TIME));
		query.append(genQueryTriple(fields,
				DataConstants.MOTION_CAPTURE_RECORD_END_TIME,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_END_TIME));

		// Filter
		if (!isSessionUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SESSION_ID,
					sessionUri));
		}
		if (!isMotionCaptureRecordUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
					motionCaptureRecordUri));
		}
		if (!isSubjectUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
					subjectUri));
		}
		if (!isMotionCaptureDeviceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID,
					motionCaptureDeviceUri));
		}
		if (!isRecordedModalityUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_MODALITY_ID,
					recordedModalityUri));
		}
		if (!isBiomedicalResourceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
					biomedicalResourceUri));
		}

		if (!isMotionCaptureRecordStartTimeEmpty) {
			query.append(genFilterDatetimeGreaterThan(
					SparqlVariableConstants.MOTION_CAPTURE_RECORD_START_TIME,
					motionCaptureRecordStartTime));
		}
		if (!isMotionCaptureRecordEndTimeEmpty) {
			query.append(genFilterDatetimeLessThan(
					SparqlVariableConstants.MOTION_CAPTURE_RECORD_END_TIME,
					motionCaptureRecordEndTime));
		}

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String querySubject(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String recordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORD_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		String recordedSubjectAtSessionUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);

		String subjectGender = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_GENDER);
		String subjectYearOfBirthMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN);
		String subjectYearOfBirthMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX);
		String subjectHandedness = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SUBJECT_HANDEDNESS);

		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isRecordedSubjectAtSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionUri);

		boolean isSubjectGenderEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectGender);
		boolean isSubjectYearOfBirthMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMin);
		boolean isSubjectYearOfBirthMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirthMax);
		boolean isSubjectHandednessEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectHandedness);

		if (!isRecordUriEmpty) {
			exists.put(DataConstants.RECORD_ID, true);
		}
		if (!isRecordedSubjectAtSessionUriEmpty) {
			exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID, true);
		}

		if (!isSubjectGenderEmpty) {
			exists.put(DataConstants.SUBJECT_GENDER, true);
		}
		if (!isSubjectYearOfBirthMinEmpty || !isSubjectYearOfBirthMaxEmpty) {
			exists.put(DataConstants.SUBJECT_YEAR_OF_BIRTH, true);
		}
		if (!isSubjectHandednessEmpty) {
			exists.put(DataConstants.SUBJECT_HANDEDNESS, true);
		}

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.SUBJECT_ID);

		if (fields.containsKey(DataConstants.RECORD_ID)) {
			query.append(SparqlVariableConstants.RECORD_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_GENDER)) {
			query.append(SparqlVariableConstants.SUBJECT_GENDER);
		}
		if (fields.containsKey(DataConstants.SUBJECT_YEAR_OF_BIRTH)) {
			query.append(SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH);
		}
		if (fields.containsKey(DataConstants.SUBJECT_HANDEDNESS)) {
			query.append(SparqlVariableConstants.SUBJECT_HANDEDNESS);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Subject
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.SUBJECT_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_SUBJECT + SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.RECORD_ID,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_DATA_SET,
				SparqlVariableConstants.RECORD_ID));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID));

		query.append(genQueryTriple(fields, DataConstants.SUBJECT_GENDER,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_GENDER,
				SparqlVariableConstants.SUBJECT_GENDER));
		query.append(genQueryTriple(fields,
				DataConstants.SUBJECT_YEAR_OF_BIRTH,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_YEAR_OF_BIRTH,
				SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH));
		query.append(genQueryTriple(fields, DataConstants.SUBJECT_HANDEDNESS,
				SparqlVariableConstants.SUBJECT_ID,
				PropertyConstants.BCI_HAS_HANDEDNESS,
				SparqlVariableConstants.SUBJECT_HANDEDNESS));

		// Filter
		if (!isRecordUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.RECORD_ID,
					recordUri));
		}
		if (!isSubjectUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
					subjectUri));
		}
		if (!isRecordedSubjectAtSessionUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
					recordedSubjectAtSessionUri));
		}

		if (!isSubjectGenderEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.SUBJECT_GENDER, subjectGender));
		}
		if (!isSubjectYearOfBirthMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH,
					Integer.parseInt(subjectYearOfBirthMin)));
		}
		if (!isSubjectYearOfBirthMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.SUBJECT_YEAR_OF_BIRTH,
					Integer.parseInt(subjectYearOfBirthMax)));
		}
		if (!isSubjectHandednessEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.SUBJECT_HANDEDNESS,
					subjectHandedness));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryRecordedSubjectAtSession(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String recordedSubjectAtSessionUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);
		String channelLocationsUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.CHANNEL_LOCATIONS_ID);

		String recordedSubjectAtSessionLabId = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		String recordedSubjectAtSessionGroup = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		String recordedSubjectAtSessionAgeMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN);
		String recordedSubjectAtSessionAgeMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX);
		String recordedSubjectAtSessionChannelLocationType = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);

		boolean isRecordedSubjectAtSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionUri);
		boolean isChannelLocationsUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsUri);

		boolean isRecordedSubjectAtSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionLabId);
		boolean isRecordedSubjectAtSessionGroupEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionGroup);
		boolean isRecordedSubjectAtSessionAgeMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionAgeMin);
		boolean isRecordedSubjectAtSessionAgeMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionAgeMax);
		boolean isRecordedSubjectAtSessionChannelLocationTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionChannelLocationType);

		if (!isChannelLocationsUriEmpty) {
			exists.put(DataConstants.CHANNEL_LOCATIONS_ID, true);
		}

		if (!isRecordedSubjectAtSessionLabIdEmpty) {
			exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID, true);
		}
		if (!isRecordedSubjectAtSessionGroupEmpty) {
			exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP, true);
		}
		if (!isRecordedSubjectAtSessionAgeMinEmpty
				|| !isRecordedSubjectAtSessionAgeMaxEmpty) {
			exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE, true);
		}
		if (!isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
			exists.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
					true);
		}

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID);

		if (fields.containsKey(DataConstants.CHANNEL_LOCATIONS_ID)) {
			query.append(SparqlVariableConstants.CHANNEL_LOCATIONS_ID);
		}
		if (fields
				.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		}
		if (fields.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		}
		if (fields.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE);
		}
		if (fields
				.containsKey(DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE)) {
			query.append(SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Subject At Session
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_RECORDED_SUBJECT_AT_SESSION
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.CHANNEL_LOCATIONS_ID,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_RESOURCE,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ID));

		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_LAB_ID,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_GROUP,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP));
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_AGE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE));
		
		query.append(genQueryTriple(
				fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_CHANNEL_LOCATION_TYPE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE));
		
		query.append(genQueryTriple(
				fields,
				DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				PropertyConstants.BCI_HAS_EEG_CHANNEL_LOCATION_TYPE,
				SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE));

		// Filter
		if (!isRecordedSubjectAtSessionUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_ID,
					recordedSubjectAtSessionUri));
		}
		if (!isChannelLocationsUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.CHANNEL_LOCATIONS_ID,
					channelLocationsUri));
		}

		if (!isRecordedSubjectAtSessionLabIdEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
					recordedSubjectAtSessionLabId));
		}
		if (!isRecordedSubjectAtSessionGroupEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
					recordedSubjectAtSessionGroup));
		}
		if (!isRecordedSubjectAtSessionAgeMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
					Integer.parseInt(recordedSubjectAtSessionAgeMin)));
		}
		if (!isRecordedSubjectAtSessionAgeMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
					Integer.parseInt(recordedSubjectAtSessionAgeMax)));
		}
		if (!isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
					recordedSubjectAtSessionChannelLocationType));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());

	}

	
	//WHERE CLAUSE OF DEVICE
	
	public static StringBuffer whereClause_queryEyeGazeDevice(JSONObject jsonObject){
		 
		 	StringBuffer where= new StringBuffer();
		 	HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);
		 	HashMap<String, Boolean> exists = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_EXISTS);
		 	
		 	String eyeGazeRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_RECORD_ID);
			String eyeGazeDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_DEVICE_ID);

			boolean isEyeGazeRecordUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordUri);
			boolean isEyeGazeDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeDeviceUri);

			if (!isEyeGazeRecordUriEmpty) {
				exists.put(DataConstants.EYE_GAZE_RECORD_ID, true);
			}

			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// EYEGAZE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EYE_GAZE_DEVICE_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_EYE_GAZE_DEVICE
					+ SparqlSyntaxConstants.END_TRIPLE);

			
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_DEVICE_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE,
					SparqlVariableConstants.EYE_GAZE_DEVICE_ID));
			
			where.append(genQueryTriple(fields,
					DataConstants.EYE_GAZE_RECORD_CHANNEL_FORMAT,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT,
					SparqlVariableConstants.EYE_GAZE_RECORD_CHANNEL_FORMAT));
			
			where.append(genQueryTriple(fields,
					DataConstants.EYE_GAZE_RECORD_SAMPLING_RATE,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_SAMPLING_RATE,
					SparqlVariableConstants.EYE_GAZE_RECORD_SAMPLING_RATE));
			
			where.append(genQueryTriple(fields,
					DataConstants.EYE_GAZE_RECORD_START_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_START_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_START_TIME));
			
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_RECORD_END_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_HAS_END_TIME,
					SparqlVariableConstants.EYE_GAZE_RECORD_END_TIME));

			// Filter
			if (!isEyeGazeRecordUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_RECORD_ID, eyeGazeRecordUri));
			}
        	if (!isEyeGazeDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_DEVICE_ID, eyeGazeDeviceUri));
			}
			
			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
	}
	
	public static StringBuffer whereClause_queryHandGestureDevice(JSONObject jsonObject){
		 
	 	StringBuffer where= new StringBuffer();
	 	
	 	HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
	 	HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);
	 	
	 	String handGestureRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.HAND_GESTURE_RECORD_ID);
		String handGestureDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.HAND_GESTURE_DEVICE_ID);

		boolean isHandGestureRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureRecordUri);
		boolean isHandGestureDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureDeviceUri);

		if (!isHandGestureRecordUriEmpty) {
			exists.put(DataConstants.HAND_GESTURE_RECORD_ID, true);
		}

		where.append(SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EYEGAZE Record
		where.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.HAND_GESTURE_DEVICE_ID
				+ SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_HAND_GESTURE_DEVICE
				+ SparqlSyntaxConstants.END_TRIPLE);

		
		where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_DEVICE_ID,
				SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
				PropertyConstants.BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE,
				SparqlVariableConstants.HAND_GESTURE_DEVICE_ID));
		
		where.append(genQueryTriple(fields,
				DataConstants.HAND_GESTURE_RECORD_CHANNEL_FORMAT,
				SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_CHANNEL_FORMAT,
				SparqlVariableConstants.HAND_GESTURE_RECORD_CHANNEL_FORMAT));
		
		where.append(genQueryTriple(fields,
				DataConstants.EYE_GAZE_RECORD_SAMPLING_RATE,
				SparqlVariableConstants.EYE_GAZE_RECORD_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.HAND_GESTURE_RECORD_SAMPLING_RATE));
		
		where.append(genQueryTriple(fields,
				DataConstants.HAND_GESTURE_RECORD_START_TIME,
				SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.HAND_GESTURE_RECORD_START_TIME));
		
		where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_RECORD_END_TIME,
				SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.HAND_GESTURE_RECORD_END_TIME));

		// Filter
		if (!isHandGestureRecordUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID, handGestureRecordUri));
		}
    	if (!isHandGestureDeviceUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.HAND_GESTURE_DEVICE_ID, handGestureDeviceUri));
		}
		
		// End
		where.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
	 
	 return where;
}
	
	public static StringBuffer whereClause_queryKeyBoardHitDevice(JSONObject jsonObject){
		 
	 	StringBuffer where= new StringBuffer();
	 	HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
	 	HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);
	 	
	 	String keyBoardHitRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.KEYBOARD_HIT_RECORD_ID);
		String keyBoardHitDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.KEYBOARD_HIT_DEVICE_ID);

		boolean isKeyBoardHitRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardHitRecordUri);
		boolean isKeyBoardHitDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardHitDeviceUri);

		if (!isKeyBoardHitRecordUriEmpty) {
			exists.put(DataConstants.KEYBOARD_HIT_RECORD_ID, true);
		}

		where.append(SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EYEGAZE Record
		where.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID
				+ SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_KEYBOARD_HIT_DEVICE
				+ SparqlSyntaxConstants.END_TRIPLE);

		
		where.append(genQueryTriple(fields, DataConstants.KEYBOARD_HIT_DEVICE_ID,
				SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
				PropertyConstants.BCI_IS_GENERATED_BY_KEYBOARD_HIT_DEVICE,
				SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID));
		
		where.append(genQueryTriple(fields,
				DataConstants.KEYBOARD_HIT_RECORD_START_TIME,
				SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.KEYBOARD_HIT_RECORD_START_TIME));
		
		where.append(genQueryTriple(fields, DataConstants.KEYBOARD_HIT_RECORD_END_TIME,
				SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.KEYBOARD_HIT_RECORD_END_TIME));

		// Filter
		if (!isKeyBoardHitRecordUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID, keyBoardHitRecordUri));
		}
    	if (!isKeyBoardHitDeviceUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID, keyBoardHitDeviceUri));
		}
		
		// End
		where.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
	 
	 return where;
}
	
	public static StringBuffer whereClause_queryMouseClickDevice(JSONObject jsonObject){
		 
	 	StringBuffer where= new StringBuffer();
	 	HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
	 	HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);
	 	
	 	String mouseClickRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.MOUSE_CLICK_RECORD_ID);
		String mouseClickDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.MOUSE_CLICK_DEVICE_ID);

		boolean isMouseClickRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickRecordUri);
		boolean isMouseClickDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickDeviceUri);

		if (!isMouseClickRecordUriEmpty) {
			exists.put(DataConstants.MOUSE_CLICK_RECORD_ID, true);
		}

		where.append(SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EYEGAZE Record
		where.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID
				+ SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_MOUSE_CLICK_DEVICE
				+ SparqlSyntaxConstants.END_TRIPLE);

		
		where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_DEVICE_ID,
				SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
				PropertyConstants.BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE,
				SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID));
		
		where.append(genQueryTriple(fields,
				DataConstants.MOUSE_CLICK_RECORD_START_TIME,
				SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
				PropertyConstants.BCI_HAS_START_TIME,
				SparqlVariableConstants.MOUSE_CLICK_RECORD_START_TIME));
		
		where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_RECORD_END_TIME,
				SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
				PropertyConstants.BCI_HAS_END_TIME,
				SparqlVariableConstants.MOUSE_CLICK_RECORD_END_TIME));

		// Filter
		if (!isMouseClickRecordUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID, mouseClickRecordUri));
		}
    	if (!isMouseClickDeviceUriEmpty) {
			where.append(genFilterIdEqual(
					SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID, mouseClickDeviceUri));
		}
		
		// End
		where.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
	 
	 return where;
}

	
	//WHERE CLAUSE OF CHANNEL
	
	public static StringBuffer whereClause_queryEyeGazeChannel(JSONObject jsonObject){
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String eyeGazeChannelUri= BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_CHANNEL_ID);
			
			String eyeGazeRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.EYE_GAZE_RECORD_ID);
			
			String channelType = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.EYE_GAZE_CHANNEL_TYPE);
			
			String channelReferTo = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.EYE_GAZE_CHANNEL_REFER_TO);
			
			String channelUnit = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.EYE_GAZE_CHANNEL_UNIT);
			
			
			boolean isEyeGazeChannelUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeChannelUri);
			
			boolean isEyeGazeRecordUriUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(eyeGazeRecordUri);
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// EYEGAZE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.EYE_GAZE_CHANNEL_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_EYE_GAZE_CHANNEL
					+ SparqlSyntaxConstants.END_TRIPLE);

			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_DEVICE_ID,
					SparqlVariableConstants.EYE_GAZE_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE,
					SparqlVariableConstants.EYE_GAZE_DEVICE_ID));
				
			if (!isEyeGazeChannelUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_CHANNEL_ID, eyeGazeChannelUri));
			}
			
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_TYPE,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_ID,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_TYPE,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_TYPE));
			
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_REFER_TO,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_ID,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_REFERS_TO,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_REFERTO));
			
			where.append(genQueryTriple(fields, DataConstants.EYE_GAZE_CHANNEL_UNIT,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_ID,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_UNIT,
					SparqlVariableConstants.EYE_GAZE_CHANNEL_UNIT));

		
			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
}
    
	
	public static StringBuffer whereClause_queryHandGestureChannel(JSONObject jsonObject){
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String handGestureChannelUri= BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.HAND_GESTURE_CHANNEL_ID);
			
			String handGestureRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.HAND_GESTURE_RECORD_ID);
			
			String handGestureDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.HAND_GESTURE_DEVICE_ID);
			
			String channelType = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.HAND_GESTURE_CHANNEL_TYPE);
			
			String channelUnit = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.HAND_GESTURE_CHANNEL_UNIT);
			
			
			boolean isHandGestureChannelUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureChannelUri);
			
			boolean ishandGestureRecordUriUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureRecordUri);
			
			boolean isHandGestureDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(handGestureDeviceUri);
			
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// EYEGAZE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_HAND_GESTURE_CHANNEL
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_RECORD_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE,
					SparqlVariableConstants.HAND_GESTURE_DEVICE_ID));
			
			where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_RECORD_ID,
					SparqlVariableConstants.HAND_GESTURE_RECORD_ID,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_DATA,
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID));
				
			if (!isHandGestureChannelUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID, handGestureChannelUri));
			}
			
			where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_CHANNEL_TYPE,
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID,
					PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_TYPE,
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_TYPE));
			
			where.append(genQueryTriple(fields, DataConstants.HAND_GESTURE_CHANNEL_UNIT,
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID,
					PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_UNIT,
					SparqlVariableConstants.HAND_GESTURE_CHANNEL_UNIT));
			
			
			if (!isHandGestureDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.EYE_GAZE_DEVICE_ID, handGestureDeviceUri));
			}
			
		
			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
}
 
	
	public static StringBuffer whereClause_queryMouseClickChannel(JSONObject jsonObject){
		 StringBuffer where= new StringBuffer();
		 HashMap<String, Boolean> fields = BciSparqlMediatorUtility
					.jsonArray2HashMap(jsonObject,
							OperationConstants.QUERY_FUNCTION_FIELDS);

			String mouseClickChannelUri= BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.MOUSE_CLICK_CHANNEL_ID);
			
			String mouseClickRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.MOUSE_CLICK_RECORD_ID);
			
			String mouseClickDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
					jsonObject, DataConstants.MOUSE_CLICK_DEVICE_ID);
			
			String channelButton = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.MOUSE_CLICK_CHANNEL_BOTTON);
			
			String channelType = BciSparqlMediatorUtility.getJsonObjectValue(
					jsonObject, DataConstants.MOUSE_CLICK_CHANNEL_TYPE);
			
			
			boolean isMouseClickChannelUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickChannelUri);
			
			boolean isMouseClickRecordUriUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickRecordUri);
			
			boolean isMouseClickDeviceUriEmpty = BciSparqlMediatorUtility
					.isValueEmpty(mouseClickDeviceUri);
			
			
			where.append(SparqlSyntaxConstants.LEFT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);

			// EYEGAZE Record
			where.append(SparqlSyntaxConstants.SPACE
					+ SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID
					+ SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.A + ClassConstants.BCI_MOUSE_CLICK_CHANNEL
					+ SparqlSyntaxConstants.END_TRIPLE);
			
			where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_RECORD_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE,
					SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID));
			
			where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_RECORD_ID,
					SparqlVariableConstants.MOUSE_CLICK_RECORD_ID,
					PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_DATA,
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID));
				
			if (!isMouseClickChannelUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID, mouseClickChannelUri));
			}
			
			where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_CHANNEL_TYPE,
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID,
					PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_TYPE,
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_TYPE));
			
			where.append(genQueryTriple(fields, DataConstants.MOUSE_CLICK_CHANNEL_BOTTON,
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID,
					PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_BUTTON,
					SparqlVariableConstants.MOUSE_CLICK_CHANNEL_BUTTON));
			
			
			if (!isMouseClickDeviceUriEmpty) {
				where.append(genFilterIdEqual(
						SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID, mouseClickDeviceUri));
			}
			
		
			// End
			where.append(SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.NEW_LINE);
			
		 
		 return where;
}

	
	//QUERY OF CHANNEL
	
    public static String queryFerderate_EyeGazeChannel(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.EYE_GAZE_CHANNEL_ID);

		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_RECORD_ID);
		}
		 
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.EYE_GAZE_CHANNEL_REFERTO +
				SparqlSyntaxConstants.SPACE);
		
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.EYE_GAZE_CHANNEL_TYPE +
				SparqlSyntaxConstants.SPACE);
 
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.EYE_GAZE_CHANNEL_UNIT +
				SparqlSyntaxConstants.SPACE);
		
		if (fields.containsKey(DataConstants.EYE_GAZE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_DEVICE_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryEyeGazeChannel(jsonObject));// condition of localhost:8890
        //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryEyeGazeChannel(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}
		
		
		

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	
    public static String queryFerderate_HandGestureChannel(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.HAND_GESTURE_CHANNEL_ID);

		if (fields.containsKey(DataConstants.HAND_GESTURE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_RECORD_ID);
		}
		 
		
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.HAND_GESTURE_CHANNEL_TYPE +
				SparqlSyntaxConstants.SPACE);
 
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.HAND_GESTURE_CHANNEL_UNIT +
				SparqlSyntaxConstants.SPACE);
		
		if (fields.containsKey(DataConstants.HAND_GESTURE_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_DEVICE_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryHandGestureChannel(jsonObject));// condition of localhost:8890
        //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryHandGestureChannel(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}
		
		
		

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	
    public static String queryFerderate_MouseClickChannel(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.MOUSE_CLICK_CHANNEL_ID);

		if (fields.containsKey(DataConstants.MOUSE_CLICK_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_RECORD_ID);
		}
		 
		
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.MOUSE_CLICK_CHANNEL_TYPE +
				SparqlSyntaxConstants.SPACE);
 
		query.append(SparqlSyntaxConstants.SPACE +
				SparqlVariableConstants.MOUSE_CLICK_CHANNEL_BUTTON +
				SparqlSyntaxConstants.SPACE);
		
		if (fields.containsKey(DataConstants.MOUSE_CLICK_CHANNEL_ID)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);
		
		//Append where clause
		 query.append(SparqlSyntaxConstants.WHERE);
		 query.append(SparqlSyntaxConstants.LEFT_BRACE); //{
		 query.append(whereClause_queryMouseClickChannel(jsonObject));// condition of localhost:8890
        //make a loop to get all of remote server
		 
		 query.append(genFederatedQuery(whereClause_queryMouseClickChannel(jsonObject)));
		
		 query.append(SparqlSyntaxConstants.RIGHT_BRACE); //}
		
		
		

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}


	//QUERY OF DEVICE	
	
	public static String queryEegDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String eegRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EEG_RECORD_ID);
		String eegDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EEG_DEVICE_ID);

		String measurementPropertyNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN);
		String measurementPropertyNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX);
		String measurementPropertySamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN);
		String measurementPropertySamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX);

		boolean isEegRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordUri);
		boolean isEegDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegDeviceUri);

		boolean isMeasurementPropertyNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyNumberOfChannelsMin);
		boolean isMeasurementPropertyNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyNumberOfChannelsMax);
		boolean isMeasurementPropertySamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMin);
		boolean isMeasurementPropertySamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMax);

		if (!isEegRecordUriEmpty) {
			exists.put(DataConstants.EEG_RECORD_ID, true);
		}

		if (!isMeasurementPropertyNumberOfChannelsMinEmpty
				|| isMeasurementPropertyNumberOfChannelsMaxEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					true);
		}
		if (!isMeasurementPropertySamplingRateMinEmpty
				|| isMeasurementPropertySamplingRateMaxEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE, true);
		}

		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.EEG_DEVICE_ID);

		if (fields.containsKey(DataConstants.EEG_RECORD_ID)) {
			query.append(SparqlVariableConstants.EEG_RECORD_ID);
		}
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS);
		}
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EEG Device
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.EEG_DEVICE_ID
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_EEG_DEVICE
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.EEG_DEVICE_ID
				+ PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ SparqlSyntaxConstants.END_TRIPLE);
		query.append(genQueryTriple(fields, DataConstants.EEG_RECORD_ID,
				SparqlVariableConstants.EEG_DEVICE_ID,
				PropertyConstants.BCI_IS_USED_FOR_GENERATE_EEG_RECORD,
				SparqlVariableConstants.EEG_RECORD_ID));

		// Measurement Capability
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.SSN_MEASUREMENT_CAPABILITY
				+ SparqlSyntaxConstants.END_TRIPLE);
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY
				+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
				+ SparqlSyntaxConstants.END_TRIPLE);

		// Measurement Property
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_MEASUREMENT_PROPERTY
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS));
		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE));

		// Filter
		if (!isEegRecordUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.EEG_RECORD_ID, eegRecordUri));
		}
		if (!isEegDeviceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.EEG_DEVICE_ID, eegDeviceUri));
		}

		if (!isMeasurementPropertyNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(measurementPropertyNumberOfChannelsMin)));
		}
		if (!isMeasurementPropertyNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(measurementPropertyNumberOfChannelsMax)));
		}
		if (!isMeasurementPropertySamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMin)));
		}
		if (!isMeasurementPropertySamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMax)));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	
	public static String queryFerderate_EyeGazeDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();
        
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.EYE_GAZE_DEVICE_ID);

		if (fields.containsKey(DataConstants.EYE_GAZE_RECORD_ID)) {
			query.append(SparqlVariableConstants.EYE_GAZE_RECORD_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		 query.append(whereClause_queryEyeGazeDevice(jsonObject));// condition of localhost:8890
	        //make a loop to get all of remote server
			 
		query.append(genFederatedQuery(whereClause_queryEyeGazeDevice(jsonObject)));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
	public static String queryFerderate_HandGestureDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.HAND_GESTURE_DEVICE_ID);

		if (fields.containsKey(DataConstants.HAND_GESTURE_RECORD_ID)) {
			query.append(SparqlVariableConstants.HAND_GESTURE_RECORD_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		 query.append(whereClause_queryHandGestureDevice(jsonObject));// condition of localhost:8890
	        //make a loop to get all of remote server
			 
		query.append(genFederatedQuery(whereClause_queryHandGestureDevice(jsonObject)));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
	public static String queryFerderate_KeyBoardHitDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.KEYBOARD_HIT_DEVICE_ID);

		if (fields.containsKey(DataConstants.KEYBOARD_HIT_RECORD_ID)) {
			query.append(SparqlVariableConstants.KEYBOARD_HIT_RECORD_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		 query.append(whereClause_queryKeyBoardHitDevice(jsonObject));// condition of localhost:8890
	        //make a loop to get all of remote server
			 
		query.append(genFederatedQuery(whereClause_queryKeyBoardHitDevice(jsonObject)));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
	public static String queryFerderate_MouseClickDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.MOUSE_CLICK_DEVICE_ID);

		if (fields.containsKey(DataConstants.MOUSE_CLICK_RECORD_ID)) {
			query.append(SparqlVariableConstants.MOUSE_CLICK_RECORD_ID);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		 query.append(whereClause_queryMouseClickDevice(jsonObject));// condition of localhost:8890
	        //make a loop to get all of remote server
			 
		query.append(genFederatedQuery(whereClause_queryMouseClickDevice(jsonObject)));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}
    
	
	
   	public static String queryMotionCaptureDevice(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String motionCaptureRecordUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.MOTION_CAPTURE_RECORD_ID);
		String motionCaptureDeviceUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.MOTION_CAPTURE_DEVICE_ID);

		String measurementPropertyNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN);
		String measurementPropertyNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX);
		String measurementPropertySamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN);
		String measurementPropertySamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX);

		boolean isMotionCaptureRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureRecordUri);
		boolean isMotionCaptureDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureDeviceUri);

		boolean isMeasurementPropertyNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyNumberOfChannelsMin);
		boolean isMeasurementPropertyNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyNumberOfChannelsMax);
		boolean isMeasurementPropertySamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMin);
		boolean isMeasurementPropertySamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRateMax);

		if (!isMotionCaptureRecordUriEmpty) {
			exists.put(DataConstants.MOTION_CAPTURE_RECORD_ID, true);
		}

		if (!isMeasurementPropertyNumberOfChannelsMinEmpty
				|| !isMeasurementPropertyNumberOfChannelsMaxEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					true);
		}
		if (!isMeasurementPropertySamplingRateMinEmpty
				|| !isMeasurementPropertySamplingRateMaxEmpty) {
			exists.put(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE, true);
		}

		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID);

		if (fields.containsKey(DataConstants.MOTION_CAPTURE_RECORD_ID)) {
			query.append(SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID);
		}
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS);
		}
		if (fields
				.containsKey(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// MOTION_CAPTURE Device
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_MOTION_CAPTURE_DEVICE
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID
				+ PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ SparqlSyntaxConstants.END_TRIPLE);
		query.append(genQueryTriple(
				fields,
				DataConstants.MOTION_CAPTURE_RECORD_ID,
				SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID,
				PropertyConstants.BCI_IS_USED_FOR_GENERATE_MOTION_CAPTURE_RECORD,
				SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID));

		// Measurement Capability
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.SSN_MEASUREMENT_CAPABILITY
				+ SparqlSyntaxConstants.END_TRIPLE);
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_CAPABILITY_ID
				+ PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY
				+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
				+ SparqlSyntaxConstants.END_TRIPLE);

		// Measurement Property
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.MEASUREMENT_PROPERTY_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_MEASUREMENT_PROPERTY
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS));
		query.append(genQueryTriple(fields,
				DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE));

		// Filter
		// ID
		if (!isMotionCaptureRecordUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.MOTION_CAPTURE_RECORD_ID,
					motionCaptureRecordUri));
		}
		if (!isMotionCaptureDeviceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.MOTION_CAPTURE_DEVICE_ID,
					motionCaptureDeviceUri));
		}

		if (!isMeasurementPropertyNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(measurementPropertyNumberOfChannelsMin)));
		}
		if (!isMeasurementPropertyNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(measurementPropertyNumberOfChannelsMax)));
		}
		if (!isMeasurementPropertySamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMin)));
		}
		if (!isMeasurementPropertySamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
					Integer.parseInt(measurementPropertySamplingRateMax)));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryRecordedParameterSet(JSONObject jsonObject) {

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);

		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID);

		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Modality
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.RECORDED_PARAMETER_SET_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_RECORDED_PARAMETER_SET
				+ SparqlSyntaxConstants.END_TRIPLE);

		// Filter
		if (!isRecordedParameterSetUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_PARAMETER_SET_ID,
					recordedParameterSetUri));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryRecordedModality(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String recordedModalityUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORDED_MODALITY_ID);

		String recordedModalityModalityType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_TYPE);
		
		String recordedModalityModalitySignalType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);

		boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityUri);

		boolean isRecordedModalityModalityTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalityType);
		
		boolean isRecordedModalityModalitySignalTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalitySignalType);

		if (!isRecordedModalityModalityTypeEmpty) {
			exists.put(DataConstants.RECORDED_MODALITY_MODALITY_TYPE, true);
		}

		if (!isRecordedModalityModalitySignalTypeEmpty) {
			exists.put(DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE, true);
		}
		StringBuffer query = new StringBuffer();

		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.RECORDED_MODALITY_ID);

		if (fields.containsKey(DataConstants.RECORDED_MODALITY_MODALITY_TYPE)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE);
		}
		
		if (fields.containsKey(DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE)) {
			query.append(SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);
		}
		
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Modality
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.RECORDED_MODALITY_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_RECORDED_MODALITY
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_MODALITY_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE));
		
		query.append(genQueryTriple(fields,
				DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_ID,
				PropertyConstants.BCI_HAS_MODALITY_SIGNAL_TYPE,
				SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE));


		// Filter
		if (!isRecordedModalityUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.RECORDED_MODALITY_ID,
					recordedModalityUri));
		}

		if (!isRecordedModalityModalityTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORDED_MODALITY_MODALITY_TYPE,
					recordedModalityModalityType));
		}
		
		if (!isRecordedModalityModalitySignalTypeEmpty) {
			query.append(genFilterStringEqual(
					SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
					recordedModalityModalitySignalType));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryBiomedicalResource(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String recordUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.RECORD_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		String biomedicalResourceUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_ID);

		String biomedicalResourceTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE);
		String biomedicalResourceNumberOfChannelsMin = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN);
		String biomedicalResourceNumberOfChannelsMax = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX);
		String biomedicalResourceSamplingRateMin = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN);
		String biomedicalResourceSamplingRateMax = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX);
		String biomedicalResourceUtilization = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION);

		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isBiomedicalResourceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUri);

		boolean isBiomedicalResourceTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceTitle);
		boolean isBiomedicalResourceNumberOfChannelsMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMin);
		boolean isBiomedicalResourceNumberOfChannelsMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceNumberOfChannelsMax);
		boolean isBiomedicalResourceSamplingRateMinEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMin);
		boolean isBiomedicalResourceSamplingRateMaxEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceSamplingRateMax);
		boolean isBiomedicalResourceUtilizationEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceUtilization);

		if (!isRecordUriEmpty) {
			exists.put(DataConstants.RECORD_ID, true);
		}
		if (!isSubjectUriEmpty) {
			exists.put(DataConstants.SUBJECT_ID, true);
		}

		if (!isBiomedicalResourceTitleEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_TITLE, true);
		}
		if (!isBiomedicalResourceNumberOfChannelsMinEmpty
				|| !isBiomedicalResourceNumberOfChannelsMaxEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS,
					true);
		}
		if (!isBiomedicalResourceSamplingRateMinEmpty
				|| !isBiomedicalResourceSamplingRateMaxEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE, true);
		}
		if (!isBiomedicalResourceUtilizationEmpty) {
			exists.put(DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION, true);
		}

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID);

		if (fields.containsKey(DataConstants.RECORD_ID)) {
			query.append(SparqlVariableConstants.RECORD_ID);
		}
		if (fields.containsKey(DataConstants.SUBJECT_ID)) {
			query.append(SparqlVariableConstants.SUBJECT_ID);
		}

		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_TITLE)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE);
		}
		if (fields
				.containsKey(DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE);
		}
		if (fields.containsKey(DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION);
		}
		if (fields
				.containsKey(DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL)) {
			query.append(SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Biomedical Resource
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID
				+ SparqlSyntaxConstants.A
				+ ClassConstants.BCI_BIOMEDICAL_RESOURCE
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields, DataConstants.RECORD_ID,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_IS_BIOMEDICAL_RESOURCE_OF,
				SparqlVariableConstants.RECORD_ID));
		query.append(genQueryTriple(fields, DataConstants.SUBJECT_ID,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_IS_FROM_SUBJECT,
				SparqlVariableConstants.SUBJECT_ID));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID));

		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_TITLE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE));
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION));

		// Access Method
		query.append(genQueryTriple(fields,
				DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID,
				PropertyConstants.BCI_HAS_URL,
				SparqlVariableConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL));

		// Filter
		if (!isRecordUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.RECORD_ID,
					recordUri));
		}
		if (!isSubjectUriEmpty) {
			query.append(genFilterIdEqual(SparqlVariableConstants.SUBJECT_ID,
					subjectUri));
		}
		if (!isBiomedicalResourceUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_ID,
					biomedicalResourceUri));
		}

		if (!isBiomedicalResourceTitleEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_TITLE,
					biomedicalResourceTitle));
		}
		if (!isBiomedicalResourceNumberOfChannelsMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS,
					Integer.parseInt(biomedicalResourceNumberOfChannelsMin)));
		}
		if (!isBiomedicalResourceNumberOfChannelsMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS,
					Integer.parseInt(biomedicalResourceNumberOfChannelsMax)));
		}
		if (!isBiomedicalResourceSamplingRateMinEmpty) {
			query.append(genFilterNumberGreaterThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE,
					Integer.parseInt(biomedicalResourceSamplingRateMin)));
		}
		if (!isBiomedicalResourceSamplingRateMaxEmpty) {
			query.append(genFilterNumberLessThan(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_SAMPLING_RATE,
					Integer.parseInt(biomedicalResourceSamplingRateMax)));
		}
		if (!isBiomedicalResourceUtilizationEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.BIOMEDICAL_RESOURCE_UTILIZATION,
					biomedicalResourceUtilization));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String queryChannelLocations(JSONObject jsonObject) {

		HashMap<String, Boolean> fields = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		HashMap<String, Boolean> exists = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_EXISTS);

		String channelLocationsUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.CHANNEL_LOCATIONS_ID);

		String channelLocationsTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.CHANNEL_LOCATIONS_TITLE);
		String channelLocationsUtilization = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.CHANNEL_LOCATIONS_UTILIZATION);

		boolean isChannelLocationsUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsUri);

		boolean isChannelLocationsTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsTitle);
		boolean isChannelLocationsUtilizationEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsUtilization);

		if (!isChannelLocationsTitleEmpty) {
			exists.put(DataConstants.CHANNEL_LOCATIONS_TITLE, true);
		}
		if (!isChannelLocationsUtilizationEmpty) {
			exists.put(DataConstants.CHANNEL_LOCATIONS_UTILIZATION, true);
		}

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.CHANNEL_LOCATIONS_ID);

		if (fields.containsKey(DataConstants.CHANNEL_LOCATIONS_TITLE)) {
			query.append(SparqlVariableConstants.CHANNEL_LOCATIONS_TITLE);
		}
		if (fields.containsKey(DataConstants.CHANNEL_LOCATIONS_UTILIZATION)) {
			query.append(SparqlVariableConstants.CHANNEL_LOCATIONS_UTILIZATION);
		}
		if (fields
				.containsKey(DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL)) {
			query.append(SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL);
		}
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Channel Locations
		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.CHANNEL_LOCATIONS_ID
				+ SparqlSyntaxConstants.A + ClassConstants.BCI_RESOURCE
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(genQueryTriple(fields,
				DataConstants.CHANNEL_LOCATIONS_TITLE,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ID,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlVariableConstants.CHANNEL_LOCATIONS_TITLE));
		query.append(genQueryTriple(fields,
				DataConstants.CHANNEL_LOCATIONS_UTILIZATION,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ID,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlVariableConstants.CHANNEL_LOCATIONS_UTILIZATION));
		query.append(genQueryTriple(fields,
				DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ID,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID));

		// Access Method
		query.append(genQueryTriple(fields,
				DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_ID,
				PropertyConstants.BCI_HAS_URL,
				SparqlVariableConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL));

		// Filter
		if (!isChannelLocationsUriEmpty) {
			query.append(genFilterIdEqual(
					SparqlVariableConstants.CHANNEL_LOCATIONS_ID,
					channelLocationsUri));
		}

		if (!isChannelLocationsTitleEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.CHANNEL_LOCATIONS_TITLE,
					channelLocationsTitle));
		}
		if (!isChannelLocationsUtilizationEmpty) {
			query.append(genFilterStringRegex(
					SparqlVariableConstants.CHANNEL_LOCATIONS_UTILIZATION,
					channelLocationsUtilization));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String listSessionTaskLabel() {

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.SESSION_TASK_LABEL
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.SESSION_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_SESSION
				+ SparqlSyntaxConstants.CONTINUE_TRIPLE);
		query.append(SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ PropertyConstants.BCI_HAS_PURPOSE
				+ SparqlVariableConstants.SESSION_TASK_LABEL
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String listSessionPurpose() {

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT
				+ SparqlVariableConstants.SESSION_PURPOSE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE
				+ SparqlVariableConstants.SESSION_ID + SparqlSyntaxConstants.A
				+ ClassConstants.BCI_SESSION
				+ SparqlSyntaxConstants.CONTINUE_TRIPLE);
		query.append(SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ PropertyConstants.BCI_HAS_PURPOSE
				+ SparqlVariableConstants.SESSION_PURPOSE
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
				query.toString());
	}

	public static String listSubjectGender() {

		return BciSparqlMediatorUtility.virtuosoQuery(
				mVirtGraph,
				genFixedList(SparqlVariableConstants.SUBJECT_GENDER,
						PropertyConstants.BCI_HAS_GENDER));
	}

	public static String listSubjectHandedness() {

		return BciSparqlMediatorUtility.virtuosoQuery(
				mVirtGraph,
				genFixedList(SparqlVariableConstants.SUBJECT_HANDEDNESS,
						PropertyConstants.BCI_HAS_HANDEDNESS));
	}

	public static String listRecordedSubjectAtSessionGroup() {

		return BciSparqlMediatorUtility
				.virtuosoQuery(
						mVirtGraph,
						genFixedList(
								SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
								PropertyConstants.BCI_HAS_GROUP));
	}

	public static String listRecordedSubjectAtSessionChannelLocationType() {

		return BciSparqlMediatorUtility
				.virtuosoQuery(
						mVirtGraph,
						genFixedList(
								SparqlVariableConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
								PropertyConstants.BCI_HAS_EEG_CHANNEL_LOCATION_TYPE));
	}

	public static String listRecordedModalityType() {

		return BciSparqlMediatorUtility
				.virtuosoQuery(
						mVirtGraph,
						genFixedList(
								SparqlVariableConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
								PropertyConstants.BCI_HAS_MODALITY_SIGNAL_TYPE));
	}

	public static String updateStudy(JSONObject jsonObject) {

		JSONObject request = null;
     
		//1. Get needed value in JsonObject
		
		String studyTitle = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_TITLE);
		String studyPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_PURPOSE);
		String studyUuid = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_UUID);
		String studyRootUri = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_ROOT_URI);
        
		//2. Check the existance of required value
		
		boolean isStudyTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyTitle);
		boolean isStudyPurposeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyPurpose);
		boolean isStudyUuidEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUuid);
		boolean isStudyRootUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyRootUri);

		//3. Decide the fail status if required value is missing
		
		if (isStudyTitleEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Find whether it is exist the study by study_title
		// If yes, return
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_VALUE);
		request.put(DataConstants.STUDY_TITLE, studyTitle);
		JSONArray response = new JSONArray(queryStudy(request));
		if (!BciSparqlMediatorUtility.isQueryEmpty(response)) {
			return response.toString();
		}
		// 4. Else, create a study
		
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		String studyId = BciSparqlMediatorUtility.genId(IdConstants.STUDY);
		String studyUri = BciSparqlMediatorUtility.toUri(studyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// 5. Create the relationship among these required value
		
		query.append(genUpdateTriple(studyUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_STUDY));
		query.append(genUpdateTriple(studyUri, PropertyConstants.BCI_HAS_TITLE,
				SparqlSyntaxConstants.DOUBLE_QUOTE + studyTitle
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		//6. Create the relationship among optional value
		
		if (!isStudyPurposeEmpty) {
			query.append(genUpdateTriple(studyUri,
					PropertyConstants.BCI_HAS_PURPOSE,
					SparqlSyntaxConstants.DOUBLE_QUOTE + studyPurpose
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isStudyUuidEmpty) {
			query.append(genUpdateTriple(studyUri,
					PropertyConstants.BCI_HAS_UUID,
					SparqlSyntaxConstants.DOUBLE_QUOTE + studyUuid
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isStudyRootUriEmpty) {
			query.append(genUpdateTriple(studyUri,
					PropertyConstants.BCI_HAS_ROOT_URI,
					SparqlSyntaxConstants.DOUBLE_QUOTE + studyRootUri
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		query.append(genUpdateTriple(studyUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(studyUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));

		// End sparql
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
        
		//7. Revoke insert
		
		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());
		
        // 8. Call query to return the jsonObject for search
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.STUDY_ID, studyId);
		return queryStudy(request);
	}

	public static String updateSession(JSONObject jsonObject) {

		JSONObject request = null;

		String studyId = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_ID);
		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionTaskLabel = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_TASK_LABEL);
		String sessionPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_PURPOSE);
		String sessionLabId = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.SESSION_LAB_ID);

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionTaskLabelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionTaskLabel);
		boolean isSessionPurposeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionPurpose);
		boolean isSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionLabId);

		if (isStudyUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// create a session
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);

		JSONArray fields = new JSONArray();
		fields.put(DataConstants.STUDY_ID);

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_VALUE);
		request.put(DataConstants.STUDY_ID, studyId);
		request.put(OperationConstants.QUERY_FUNCTION_FIELDS, fields);

		int sessionNum = new JSONArray(querySession(request)).length();

		String sessionId = BciSparqlMediatorUtility.genId(IdConstants.SESSION);
		String sessionUri = BciSparqlMediatorUtility.toUri(sessionId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Study
		query.append(genUpdateTriple(studyUri,
				PropertyConstants.BCI_HAS_SESSION, sessionUri));

		// Session
		query.append(genUpdateTriple(sessionUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_SESSION));
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_IS_SESSION_OF, studyUri));
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_ID_NUMBER,
				Integer.toString(sessionNum + 1)));

		if (!isSessionTaskLabelEmpty) {
			query.append(genUpdateTriple(sessionUri,
					PropertyConstants.BCI_HAS_TASK_LABEL,
					SparqlSyntaxConstants.DOUBLE_QUOTE + sessionTaskLabel
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isSessionPurposeEmpty) {
			query.append(genUpdateTriple(sessionUri,
					PropertyConstants.BCI_HAS_PURPOSE,
					SparqlSyntaxConstants.DOUBLE_QUOTE + sessionPurpose
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isSessionLabIdEmpty) {
			query.append(genUpdateTriple(sessionUri,
					PropertyConstants.BCI_HAS_SESSION_LAB_ID,
					SparqlSyntaxConstants.DOUBLE_QUOTE + sessionLabId
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.SESSION_ID, sessionId);
		return querySession(request);
	}

	public static String updateEyeGazeRecord(JSONObject jsonObject) {

		JSONObject request = null;

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		
		String eyeGazeDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EYE_GAZE_DEVICE_ID);
		
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
//ADD
		
		String eyeGazeRecordNumberOfChannels =BciSparqlMediatorUtility
				.transformSamplingRate(
						BciSparqlMediatorUtility
							.getJsonObjectValue(jsonObject,
									DataConstants.RECORD_NUMBER_OF_CHANNELS));
		
		String eyeGazeRecordChannelFormat = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_CHANNEL_FORMAT);
		
	    String eyeGazeRecordSamplingRate = BciSparqlMediatorUtility
				.transformSamplingRate(
						BciSparqlMediatorUtility.getJsonObjectValue(jsonObject,
								DataConstants.RECORD_SAMPLING_RATE));
//ADD						
		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		
		boolean isEyeGazeDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeDeviceUri);
//ADD
		boolean isEyeGazeRecordNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeRecordNumberOfChannels);
		
		boolean isEyeGazeRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeRecordChannelFormat);

		boolean isEyeGazeRecordSamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeRecordSamplingRate);
//ADD

		if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty
				||isRecordedParameterSetUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}
		

		// Create EYE_GAZE Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String eyeGazeRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.EYE_GAZE_RECORD);
		
		String eyeGazeRecordUri = BciSparqlMediatorUtility.toUri(eyeGazeRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, eyeGazeRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, eyeGazeRecordUri));

		// EEG record
		query.append(genUpdateTriple(eyeGazeRecordUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_EYE_GAZE_RECORD));
		
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));
		
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
		
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));
//ADD
		// Device
		if (!isEyeGazeDeviceUriEmpty) {
				query.append(genUpdateTriple(eyeGazeDeviceUri,
						PropertyConstants.BCI_IS_USED_FOR_GENERATE_EYE_GAZE_RECORD,
						eyeGazeRecordUri));
				
				query.append(genUpdateTriple(eyeGazeDeviceUri,
						PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD,
						eyeGazeRecordUri));
				
		}
		if (!isEyeGazeRecordNumberOfChannelsEmpty) {
			query.append(genUpdateTriple(eyeGazeRecordUri,
					PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS, 
					eyeGazeRecordNumberOfChannels));
		}
		
		if (!isEyeGazeRecordChannelFormatEmpty) {
			query.append(genUpdateTriple(eyeGazeRecordUri,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT, 
					SparqlSyntaxConstants.SPACE+
					SparqlSyntaxConstants.DOUBLE_QUOTE+ eyeGazeRecordChannelFormat +
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isEyeGazeRecordSamplingRateEmpty) {
			query.append(genUpdateTriple(eyeGazeRecordUri,
					PropertyConstants.BCI_HAS_SAMPLING_RATE, 
					eyeGazeRecordSamplingRate));
		}
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));
//ADD
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.EYE_GAZE_RECORD_ID, eyeGazeRecordId);
		return queryEyeGazeRecord(request);
	}

	public static String updateEegRecord(JSONObject jsonObject) {
		JSONObject request = null;

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		
		String eegDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.EEG_DEVICE_ID);
		
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);

		String eegRecordNumberOfChannels = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS);  //EEG_RECORD_NUMBER_OF_CHANNELS

		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
	
		boolean isEegRecordNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegRecordNumberOfChannels);
		
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);

		boolean isEegDeviceUriEmpty=BciSparqlMediatorUtility
				.isValueEmpty(eegDeviceUri);
		/*if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty
				|| isRecordedParameterSetUriEmpty) {*/
		if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty||isRecordedParameterSetUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create EEG Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String eegRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.EEG_RECORD);
		String eegRecordUri = BciSparqlMediatorUtility.toUri(eegRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, eegRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, eegRecordUri));

		// Device
		
		if (!isEegDeviceUriEmpty) {
			query.append(genUpdateTriple(eegDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_EEG_RECORD,
					eegRecordUri));
			
			query.append(genUpdateTriple(eegDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD,
					eegRecordUri));
			
	    }
		
		
		// EEG record
		query.append(genUpdateTriple(eegRecordUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_EEG_RECORD));
		
		query.append(genUpdateTriple(eegRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));
		
		query.append(genUpdateTriple(eegRecordUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
		
		query.append(genUpdateTriple(eegRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));

		if (!isEegRecordNumberOfChannelsEmpty) {
			query.append(genUpdateTriple(eegRecordUri,
					PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
					eegRecordNumberOfChannels));
		}
		query.append(genUpdateTriple(eegRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(eegRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.EEG_RECORD_ID, eegRecordId);
		return queryEegRecord(request);
	}
    
	
	public static String updateEyeGazeChannel(JSONObject jsonObject) {
		JSONObject request = null;

		String eyeGazeRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.EYE_GAZE_RECORD_ID);
		
		String eyeGazeChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_TYPE);
		
		String eyeGazeChannelReferTo = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_REFER_TO);
		
		String eyeGazeChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_CHANNEL_UNIT);
		
		boolean isEyeGazeRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeRecordUri);
		
		boolean isEyeGazeChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelType);
		
		boolean isEyeGazeChannelReferToEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelReferTo);
		
		boolean isEyeGazeChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeChannelUnit);
		
		if (isEyeGazeRecordUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create EEG Channel
		
		String eyeGazeChannelId = BciSparqlMediatorUtility
				.genId(IdConstants.EYE_GAZE_CHANNEL);
		
		String eyeGazeChannelUri = BciSparqlMediatorUtility.toUri(eyeGazeChannelId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// record to bci
		query.append(genUpdateTriple(eyeGazeChannelUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_EYE_GAZE_CHANNEL));
		
		query.append(genUpdateTriple(eyeGazeRecordUri,
				PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_DATA,
				eyeGazeChannelUri));
		
		query.append(genUpdateTriple(eyeGazeChannelUri,
				PropertyConstants.BCI_IS_EYE_GAZE_CHANNEL_DATA_OF,eyeGazeRecordUri ));
		
		query.append(genUpdateTriple(eyeGazeChannelUri,
				PropertyConstants.BCI_IS_CHANNEL_OF,eyeGazeRecordUri ));
		// EYE GAZE Channel
		

		if (!isEyeGazeChannelTypeEmpty) {
			query.append(genUpdateTriple(eyeGazeChannelUri,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_TYPE,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					eyeGazeChannelType + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		
		if (!isEyeGazeChannelReferToEmpty) {
			query.append(genUpdateTriple(eyeGazeChannelUri,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_REFERS_TO,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					eyeGazeChannelReferTo + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		
		if (!isEyeGazeChannelUnitEmpty) {
			query.append(genUpdateTriple(eyeGazeChannelUri,
					PropertyConstants.BCI_HAS_EYE_GAZE_CHANNEL_UNIT,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					eyeGazeChannelUnit + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		//end
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.EYE_GAZE_CHANNEL_ID, eyeGazeChannelId);
		return queryFerderate_EyeGazeChannel(request);
	}

	public static String updateHandGestureChannel(JSONObject jsonObject) {
		JSONObject request = null;

		String handGestureRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.HAND_GESTURE_RECORD_ID);
		
		String handGestureChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_TYPE);
		
		String handGestureChannelUnit = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_CHANNEL_UNIT);
		
		boolean isHandGestureRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureRecordUri);
		
		boolean isHandGestureChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelType);
	
		boolean isHandGestureChannelUnitEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureChannelUnit);
		
		if (isHandGestureRecordUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create EEG Channel
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		
		String handGestureChannelId = BciSparqlMediatorUtility
				.genId(IdConstants.HAND_GESTURE_CHANNEL);
		
		String handGestureChannelUri = BciSparqlMediatorUtility.toUri(handGestureChannelId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// record to bci
		query.append(genUpdateTriple(handGestureChannelUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_HAND_GESTURE_CHANNEL));
		
		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_DATA, handGestureChannelUri));
		
		query.append(genUpdateTriple(handGestureChannelUri,
				PropertyConstants.BCI_IS_HAND_GESTURE_CHANNEL_DATA_OF,handGestureRecordUri ));
		
		query.append(genUpdateTriple(handGestureChannelUri,
				PropertyConstants.BCI_IS_CHANNEL_OF,handGestureRecordUri ));
		
		// EEG record
		

		if (!isHandGestureChannelTypeEmpty) {
			query.append(genUpdateTriple(handGestureChannelUri,
					PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_TYPE,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					handGestureChannelType + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		
		
		if (!isHandGestureChannelUnitEmpty) {
			query.append(genUpdateTriple(handGestureChannelUri,
					PropertyConstants.BCI_HAS_HAND_GESTURE_CHANNEL_UNIT,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					handGestureChannelUnit + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		//end
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.HAND_GESTURE_CHANNEL_ID, handGestureChannelId);
		return queryFerderate_HandGestureChannel(request);
	}

	public static String updateMouseClickChannel(JSONObject jsonObject) {
		JSONObject request = null;

		String mouseClickRecordUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.MOUSE_CLICK_RECORD_ID);
		
		String mouseClickChannelType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_TYPE);
		
		String mouseClickChannelButton = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_CHANNEL_BOTTON);
		
		boolean ismouseClickRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickRecordUri);
		
		boolean ismouseClickChannelTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelType);
	
		boolean ismouseClickChannelButtonEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickChannelButton);
		
		if (ismouseClickRecordUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create EEG Channel
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		
		String mouseClickChannelId = BciSparqlMediatorUtility
				.genId(IdConstants.MOUSE_CLICK_CHANNEL);
		
		String mouseClickChannelUri = BciSparqlMediatorUtility.toUri(mouseClickChannelId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// record to bci
		query.append(genUpdateTriple(mouseClickChannelUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_MOUSE_CLICK_CHANNEL));
		
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_DATA, mouseClickChannelUri));
		
		query.append(genUpdateTriple(mouseClickChannelUri,
				PropertyConstants.BCI_IS_MOUSE_CLICK_CHANNEL_DATA_OF,mouseClickRecordUri ));
		
		query.append(genUpdateTriple(mouseClickChannelUri,
				PropertyConstants.BCI_IS_CHANNEL_OF,mouseClickRecordUri ));
		
		// EEG record
		

		if (!ismouseClickChannelButtonEmpty) {
			query.append(genUpdateTriple(mouseClickChannelUri,
					PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_BUTTON,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					mouseClickChannelButton + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		
		
		if (!ismouseClickChannelTypeEmpty) {
			query.append(genUpdateTriple(mouseClickChannelUri,
					PropertyConstants.BCI_HAS_MOUSE_CLICK_CHANNEL_TYPE,
					SparqlSyntaxConstants.DOUBLE_QUOTE +
					mouseClickChannelType + 
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		//end
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.MOUSE_CLICK_CHANNEL_ID, mouseClickChannelId);
		return queryFerderate_MouseClickChannel(request);
	}

	
	public static String updateHandGestureRecord(JSONObject jsonObject) {

		JSONObject request = null;

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		
		String handGestureDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.HAND_GESTURE_DEVICE_ID);
		
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		
		String handGestureRecordNumberOfChannels = BciSparqlMediatorUtility
				.transformSamplingRate(
						BciSparqlMediatorUtility
							.getJsonObjectValue(jsonObject,
									DataConstants.RECORD_NUMBER_OF_CHANNELS));
		
		String handGestureRecordChannelFormat = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_CHANNEL_FORMAT);
		
		String handGestureRecordSamplingRate = BciSparqlMediatorUtility
				.transformSamplingRate(
						BciSparqlMediatorUtility
							.getJsonObjectValue(jsonObject,
									DataConstants.RECORD_SAMPLING_RATE));
		
		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		
		boolean isHandGestureRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureRecordChannelFormat);
		
		boolean isHandGestureRecordNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureRecordNumberOfChannels);

		boolean isHandGestureRecordSamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureRecordSamplingRate);
		
		boolean isHandGestureDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureDeviceUri);
		
		if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty
				||isRecordedParameterSetUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create HAND Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String handGestureRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.HAND_GESTURE_RECORD);
		String handGestureRecordUri = BciSparqlMediatorUtility.toUri(handGestureRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, handGestureRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, handGestureRecordUri));

		// Hand record
		query.append(genUpdateTriple(handGestureRecordUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_HAND_GESTURE_RECORD));
		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));

		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
		
		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));

	   if (!isHandGestureRecordNumberOfChannelsEmpty) {
		     query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS, 
				handGestureRecordNumberOfChannels));
	   }
	
	   if (!isHandGestureRecordChannelFormatEmpty) {
		   query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_CHANNEL_FORMAT, 
				SparqlSyntaxConstants.SPACE+
				SparqlSyntaxConstants.DOUBLE_QUOTE+ handGestureRecordChannelFormat +
				SparqlSyntaxConstants.DOUBLE_QUOTE));
	   }
	   if (!isHandGestureRecordSamplingRateEmpty) {
		   query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE, 
				handGestureRecordSamplingRate));
	   }
	   
	// Device
	   if (!isHandGestureDeviceUriEmpty) {
			query.append(genUpdateTriple(handGestureDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_HAND_GESTURE_RECORD,
					handGestureRecordUri));
			query.append(genUpdateTriple(handGestureDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD,
					handGestureRecordUri));
	   }
		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(handGestureRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.HAND_GESTURE_RECORD_ID, handGestureRecordId);
		return queryHandGestureRecord(request);
	}
 
	public static String updateKeyBoardHitRecord(JSONObject jsonObject) {

		JSONObject request = null;

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String keyBoardHitDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.KEYBOARD_HIT_DEVICE_ID);
		
		String keyBoardRecordNumberOfChannels = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS);
		
		String keyBoardRecordChannelFormat = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_CHANNEL_FORMAT);
		
		String keyBoardRecordSamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE);
		
		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		
		boolean isKeyBoardHitDeviceUri = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardHitDeviceUri);
	
		boolean isKeyBoardHitRecordNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardRecordNumberOfChannels);
		
		boolean isKeyBoardHitRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardRecordChannelFormat);

		boolean isKeyBoardHitRecordSamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardRecordSamplingRate);
		
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		
		if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty
				||isRecordedParameterSetUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create KEY BOARD HIT Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String keyBoardHitRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.KEYBOARD_HIT_RECORD);
		String keyBoardHitRecordUri = BciSparqlMediatorUtility.toUri(keyBoardHitRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, keyBoardHitRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, keyBoardHitRecordUri));

		// EEG record
		query.append(genUpdateTriple(keyBoardHitRecordUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_KEYBOARD_HIT_RECORD));
		
		query.append(genUpdateTriple(keyBoardHitRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));

		query.append(genUpdateTriple(keyBoardHitRecordUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
		
		query.append(genUpdateTriple(keyBoardHitRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));

		query.append(genUpdateTriple(keyBoardHitRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(keyBoardHitRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));
		// Device
		if (!isKeyBoardHitDeviceUri) {
						query.append(genUpdateTriple(keyBoardHitDeviceUri,
								PropertyConstants.BCI_IS_USED_FOR_GENERATE_KEYBOARD_HIT_RECORD,
								keyBoardHitRecordUri));
						query.append(genUpdateTriple(keyBoardHitDeviceUri,
								PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD,
								keyBoardHitRecordUri));
				}
		if (!isKeyBoardHitRecordNumberOfChannelsEmpty) {
					query.append(genUpdateTriple(keyBoardHitRecordUri,
							PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS, 
							keyBoardRecordNumberOfChannels));
				}
				
		if (!isKeyBoardHitRecordChannelFormatEmpty) {
					query.append(genUpdateTriple(keyBoardHitRecordUri,
							PropertyConstants.BCI_HAS_CHANNEL_FORMAT, 
							SparqlSyntaxConstants.SPACE+
							SparqlSyntaxConstants.DOUBLE_QUOTE+ keyBoardRecordChannelFormat +
							SparqlSyntaxConstants.DOUBLE_QUOTE));
				}
		if (!isKeyBoardHitRecordSamplingRateEmpty) {
					query.append(genUpdateTriple(keyBoardHitRecordUri,
							PropertyConstants.BCI_HAS_SAMPLING_RATE, 
							keyBoardRecordSamplingRate));
				}
				query.append(genUpdateTriple(keyBoardHitRecordUri,
						PropertyConstants.BCI_HAS_START_TIME, timeStamp));
				query.append(genUpdateTriple(keyBoardHitRecordUri,
						PropertyConstants.BCI_HAS_END_TIME, timeStamp));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.KEYBOARD_HIT_RECORD_ID, keyBoardHitRecordId);
		
		return queryKeyBoardHitRecord(request);
	}
	
	public static String updateMouseClickRecord(JSONObject jsonObject) {

		JSONObject request = null;

		String studyUri = BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
				DataConstants.STUDY_ID);
		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String mouseClickDeviceUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.MOUSE_CLICK_DEVICE_ID);
		
		String mouseClickRecordNumberOfChannels = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_NUMBER_OF_CHANNELS);
		
		String mouseClickRecordChannelFormat = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_CHANNEL_FORMAT);
		
		String mouseClickRecordSamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORD_SAMPLING_RATE);
		
		boolean isMouseClickDeviceUri = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickDeviceUri);
		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		
		boolean isMouseClickRecordNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickRecordNumberOfChannels);
		
		boolean isMouseClickRecordChannelFormatEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickRecordChannelFormat);

		boolean isMouseClickRecordSamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickRecordSamplingRate);
		
		if (isStudyUriEmpty || isSessionUriEmpty || isSubjectUriEmpty
				||isRecordedParameterSetUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create MOUSECLICK Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String mouseClickRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.MOUSE_CLICK_RECORD);
		String mouseClickRecordUri = BciSparqlMediatorUtility.toUri(mouseClickRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, mouseClickRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, mouseClickRecordUri));

		// Mouse click record
		query.append(genUpdateTriple(mouseClickRecordUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_MOUSE_CLICK_RECORD));
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));

		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
		
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));

		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));
		
		// Device
		if (!isMouseClickDeviceUri) {
			query.append(genUpdateTriple(mouseClickDeviceUri,
				PropertyConstants.BCI_IS_USED_FOR_GENERATE_MOUSE_CLICK_RECORD,
				mouseClickRecordUri));
			
			query.append(genUpdateTriple(mouseClickDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_RECORD,
					mouseClickRecordUri));
		}
		if (!isMouseClickRecordNumberOfChannelsEmpty) {
			query.append(genUpdateTriple(mouseClickRecordUri,
					PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS, 
					mouseClickRecordNumberOfChannels));
		}
		
		if (!isMouseClickRecordChannelFormatEmpty) {
			query.append(genUpdateTriple(mouseClickRecordUri,
					PropertyConstants.BCI_HAS_CHANNEL_FORMAT, 
					SparqlSyntaxConstants.SPACE+
					SparqlSyntaxConstants.DOUBLE_QUOTE+ mouseClickRecordChannelFormat +
					SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isMouseClickRecordSamplingRateEmpty) {
			query.append(genUpdateTriple(mouseClickRecordUri,
					PropertyConstants.BCI_HAS_SAMPLING_RATE, 
					mouseClickRecordSamplingRate));
		}
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(mouseClickRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));
		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		//request.put(DataConstants.HAND_GESTURE_RECORD_ID, mouseClickRecordId);
		request.put(DataConstants.MOUSE_CLICK_RECORD_ID, mouseClickRecordId);
		return queryMouseClickRecord(request);
	}

	
	public static String updateMotionCaptureRecord(JSONObject jsonObject) {
		JSONObject request = null;

		String studyUri = "";
		String sessionUri = "";
		String subjectUri = "";
		String motionCaptureDeviceUri = "";
		String recordedModalityUri = "";
		String recordedParameterSetUri = "";

		if (!jsonObject.isNull(DataConstants.STUDY_ID)) {
			studyUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.STUDY_ID));
		}
		if (!jsonObject.isNull(DataConstants.SESSION_ID)) {
			sessionUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.SESSION_ID));
		}
		if (!jsonObject.isNull(DataConstants.SUBJECT_ID)) {
			subjectUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.SUBJECT_ID));
		}
		if (!jsonObject.isNull(DataConstants.MOTION_CAPTURE_DEVICE_ID)) {
			motionCaptureDeviceUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.MOTION_CAPTURE_DEVICE_ID));
		}
		if (!jsonObject.isNull(DataConstants.RECORDED_MODALITY_ID)) { // It's OPTIONAL. It's updated afterwards.
			recordedModalityUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.RECORDED_MODALITY_ID));
		}
        // add recordParameterSet
		if (!jsonObject.isNull(DataConstants.RECORDED_PARAMETER_SET_ID)) { 
			recordedParameterSetUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.RECORDED_PARAMETER_SET_ID));
		}
		
		boolean isStudyUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(studyUri);
		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isMotionCaptureDeviceUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(motionCaptureDeviceUri);
		boolean isRecordedModalityUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityUri);
		boolean isRecordedParameterSetEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);

		if (isStudyUriEmpty || isSessionUriEmpty ||isRecordedParameterSetEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Motion Capture Record
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		changeStudyEndTime(studyUri, timeStamp);
		changeSessionEndTime(sessionUri, timeStamp);

		String motionCaptureRecordId = BciSparqlMediatorUtility
				.genId(IdConstants.MOTION_CAPTURE_RECORD);
		String motionCaptureRecordUri = BciSparqlMediatorUtility
				.toUri(motionCaptureRecordId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORD, motionCaptureRecordUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_DATA_SET, motionCaptureRecordUri));

		

		// Motion Capture record
		query.append(genUpdateTriple(motionCaptureRecordUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MOTION_CAPTURE_RECORD));
		query.append(genUpdateTriple(motionCaptureRecordUri,
				PropertyConstants.BCI_IS_RECORD_OF, sessionUri));

		query.append(genUpdateTriple(motionCaptureRecordUri,
				PropertyConstants.BCI_HAS_RECORDED_PARAMETER_SET,
				recordedParameterSetUri));

		if (!isSubjectUriEmpty) {
			query.append(genUpdateTriple(motionCaptureRecordUri,
					PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));
			}
		
		if (!isMotionCaptureDeviceUriEmpty) {
			query.append(genUpdateTriple(
					motionCaptureRecordUri,
					PropertyConstants.BCI_IS_GENERATED_BY_MOTION_CAPTURE_DEVICE,
					motionCaptureDeviceUri));
			
			// Motion Capture Device
			
			query.append(genUpdateTriple(
					motionCaptureDeviceUri,
					PropertyConstants.BCI_IS_USED_FOR_GENERATE_MOTION_CAPTURE_RECORD,
					motionCaptureRecordUri));
		}
		if (!isRecordedModalityUriEmpty) {
			query.append(genUpdateTriple(motionCaptureRecordUri,
					PropertyConstants.BCI_HAS_RECORDED_MODALITY,
					recordedModalityUri));
		}
		query.append(genUpdateTriple(motionCaptureRecordUri,
				PropertyConstants.BCI_HAS_START_TIME, timeStamp));
		query.append(genUpdateTriple(motionCaptureRecordUri,
				PropertyConstants.BCI_HAS_END_TIME, timeStamp));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.MOTION_CAPTURE_RECORD_ID,
				motionCaptureRecordId);
		return queryMotionCaptureRecord(request);
	}

	
	public static String updateSubject(JSONObject jsonObject) {

		JSONObject request = null;

		String subjectGender = BciSparqlMediatorUtility
				.transformSubjectGender(BciSparqlMediatorUtility
						.getJsonObjectValue(jsonObject,
								DataConstants.SUBJECT_GENDER));
		String subjectYearOfBirth = BciSparqlMediatorUtility
				.transformSubjectYearOfBirth(BciSparqlMediatorUtility
						.getJsonObjectValue(jsonObject,
								DataConstants.SUBJECT_YEAR_OF_BIRTH));
		String subjectHandedness = BciSparqlMediatorUtility
				.transformSubjectHandedness(BciSparqlMediatorUtility
						.getJsonObjectValue(jsonObject,
								DataConstants.SUBJECT_HANDEDNESS));

		boolean isSubjectGenderEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectGender);
		boolean isSubjectYearOfBirthEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectYearOfBirth);
		boolean isSubjectHandednessEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectHandedness);

		// JSONArray exists = new JSONArray();
		// JSONArray notExists = new JSONArray();
		// if (isSubjectGenderEmpty) {
		// notExists.put(DataConstants.SUBJECT_GENDER);
		// } else {
		// exists.put(DataConstants.SUBJECT_GENDER);
		// }
		// if (isSubjectYearOfBirthEmpty) {
		// notExists.put(DataConstants.SUBJECT_YEAR_OF_BIRTH);
		// } else {
		// exists.put(DataConstants.SUBJECT_YEAR_OF_BIRTH);
		// }
		// if (isSubjectHandednessEmpty) {
		// notExists.put(DataConstants.SUBJECT_HANDEDNESS);
		// } else {
		// exists.put(DataConstants.SUBJECT_HANDEDNESS);
		// }

		// request = new JSONObject();
		// request.put(OperationConstants.QUERY_MODE_TYPE,
		// OperationConstants.QUERY_MODE_VALUE);
		// request.put(DataConstants.SUBJECT_GENDER, subjectGender);
		// request.put(DataConstants.SUBJECT_YEAR_OF_BIRTH_MIN,
		// subjectYearOfBirth);
		// request.put(DataConstants.SUBJECT_YEAR_OF_BIRTH_MAX,
		// subjectYearOfBirth);
		// request.put(DataConstants.SUBJECT_HANDEDNESS, subjectHandedness);
		// request.put(OperationConstants.QUERY_FUNCTION_EXISTS, exists);
		// request.put(OperationConstants.QUERY_FUNCTION_NOT_EXISTS, notExists);
		// JSONArray response = new JSONArray(querySubject(request));
		// if (!BciSparqlMediatorUtility.isQueryEmpty(response)) {
		// return response.toString();
		// }

		// Create Subject
		String subjectId = BciSparqlMediatorUtility.genId(IdConstants.SUBJECT);
		String subjectUri = BciSparqlMediatorUtility.toUri(subjectId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Subject
		query.append(genUpdateTriple(subjectUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_SUBJECT));

		if (!isSubjectGenderEmpty) {
			query.append(genUpdateTriple(subjectUri,
					PropertyConstants.BCI_HAS_GENDER,
					SparqlSyntaxConstants.DOUBLE_QUOTE + subjectGender
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isSubjectYearOfBirthEmpty) {
			query.append(genUpdateTriple(subjectUri,
					PropertyConstants.BCI_HAS_YEAR_OF_BIRTH, subjectYearOfBirth));
		}
		if (!isSubjectHandednessEmpty) {
			query.append(genUpdateTriple(subjectUri,
					PropertyConstants.BCI_HAS_HANDEDNESS,
					SparqlSyntaxConstants.DOUBLE_QUOTE + subjectHandedness
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.SUBJECT_ID, subjectId);
		return querySubject(request);
	}

	public static String updateRecordedSubjectAtSession(JSONObject jsonObject) {

		JSONObject request = null;

		String sessionUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SESSION_ID);
		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);

		String recordedSubjectAtSessionLabId = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		String recordedSubjectAtSessionInSessionNumber = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER);
		String recordedSubjectAtSessionGroup = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		String recordedSubjectAtSessionAge = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE);
		String recordedSubjectAtSessionVision = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION);
		String recordedSubjectAtSessionHearing = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING);
		String recordedSubjectAtSessionHeight = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT);
		String recordedSubjectAtSessionWeight = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT);
		String recordedSubjectAtSessionMedicationCaffeine = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE);
		String recordedSubjectAtSessionMedicationAlcohol = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL);
		String recordedSubjectAtSessionChannelLocationType = BciSparqlMediatorUtility
				.getJsonObjectValue(
						jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);

		boolean isSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(sessionUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isRecordedSubjectAtSessionLabIdEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionLabId);
		boolean isRecordedSubjectAtSessionInSessionNumberEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionInSessionNumber);
		boolean isRecordedSubjectAtSessionGroupEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionGroup);
		boolean isRecordedSubjectAtSessionAgeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionAge);
		boolean isRecordedSubjectAtSessionVisionEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionVision);
		boolean isRecordedSubjectAtSessionHearingEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionHearing);
		boolean isRecordedSubjectAtSessionHeightEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionHeight);
		boolean isRecordedSubjectAtSessionWeightEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionWeight);
		boolean isRecordedSubjectAtSessionMedicationCaffeineEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionMedicationCaffeine);
		boolean isRecordedSubjectAtSessionMedicationAlcoholEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionMedicationAlcohol);
		boolean isRecordedSubjectAtSessionChannelLocationTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionChannelLocationType);

		// JSONArray exists = new JSONArray();
		// JSONArray notExists = new JSONArray();
		// if (isRecordedSubjectAtSessionLabIdEmpty) {
		// notExists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		// } else {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		// }
		// if (isRecordedSubjectAtSessionGroupEmpty) {
		// notExists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		// } else {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP);
		// }
		// if (isRecordedSubjectAtSessionAgeEmpty) {
		// notExists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE);
		// } else {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE);
		// }
		// if (isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
		// notExists
		// .put(DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		// } else {
		// exists.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		// }

		// request = new JSONObject();
		// request.put(OperationConstants.QUERY_MODE_TYPE,
		// OperationConstants.QUERY_MODE_VALUE);
		// request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
		// recordedSubjectAtSessionLabId);
		// request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
		// recordedSubjectAtSessionGroup);
		// request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MIN,
		// recordedSubjectAtSessionAge);
		// request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE_MAX,
		// recordedSubjectAtSessionAge);
		// request.put(
		// DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
		// recordedSubjectAtSessionChannelLocationType);
		// request.put(OperationConstants.QUERY_FUNCTION_EXISTS, exists);
		// request.put(OperationConstants.QUERY_FUNCTION_NOT_EXISTS, notExists);
		// JSONArray response = new JSONArray(
		// queryRecordedSubjectAtSession(request));
		// if (!BciSparqlMediatorUtility.isQueryEmpty(response)) {
		// return response.toString();
		// }

		if (isSessionUriEmpty || isSubjectUriEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Recorded Subject At Session
		String recordedSubjectAtSessionId = BciSparqlMediatorUtility
				.genId(IdConstants.RECORDED_SUBJECT_AT_SESSION);
		String recordedSubjectAtSessionUri = BciSparqlMediatorUtility
				.toUri(recordedSubjectAtSessionId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Session
		query.append(genUpdateTriple(sessionUri,
				PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION,
				recordedSubjectAtSessionUri));

		// Subject
		query.append(genUpdateTriple(subjectUri,
				PropertyConstants.BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION,
				recordedSubjectAtSessionUri));

		// Recorded Subject At Session
		query.append(genUpdateTriple(recordedSubjectAtSessionUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_RECORDED_SUBJECT_AT_SESSION));

		if (!isRecordedSubjectAtSessionLabIdEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_LAB_ID,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionLabId
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionInSessionNumberEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_IN_SESSION_NUMBER,
					recordedSubjectAtSessionInSessionNumber));
		}
		if (!isRecordedSubjectAtSessionGroupEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_GROUP,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionGroup
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionAgeEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_AGE, recordedSubjectAtSessionAge));
		}
		if (!isRecordedSubjectAtSessionVisionEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_VISION,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionVision
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionHearingEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_HEARING,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionHearing
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionHeightEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_HEIGHT,
					recordedSubjectAtSessionHeight));
		}
		if (!isRecordedSubjectAtSessionWeightEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_WEIGHT,
					recordedSubjectAtSessionWeight));
		}
		if (!isRecordedSubjectAtSessionMedicationCaffeineEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_MEDICATION_CAFFEINE,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionMedicationCaffeine
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionMedicationAlcoholEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_MEDICATION_ALCOHOL,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionMedicationAlcohol
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedSubjectAtSessionChannelLocationTypeEmpty) {
			query.append(genUpdateTriple(recordedSubjectAtSessionUri,
					PropertyConstants.BCI_HAS_EEG_CHANNEL_LOCATION_TYPE,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedSubjectAtSessionChannelLocationType
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
				recordedSubjectAtSessionId);
		return queryRecordedSubjectAtSession(request);
	}

	public static String updateRecordedParameterSet(JSONObject jsonObject) {

		JSONObject request = null;

		// Create Recorded Parameter Set
		String recordedParameterSetId = BciSparqlMediatorUtility
				.genId(IdConstants.RECORDED_PARAMETER_SET);
		String recordedParameteSetrUri = BciSparqlMediatorUtility
				.toUri(recordedParameterSetId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameteSetrUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_RECORDED_PARAMETER_SET));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
				recordedParameterSetId);
		return queryRecordedParameterSet(request);
	}
    
	
	//UPDATE DEVICE
	
	public static String updateEyeGazeDevice(JSONObject jsonObject){
    	JSONObject request = null;

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String eyeGazeDeviceHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EYE_GAZE_DEVICE_HARDWARE_MANUFACTURER);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		String measurementPropertySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		boolean isMeasurementPropertyHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eyeGazeDeviceHardwareManufacturer);
		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);

		if (isRecordedParameterSetUriEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create EYE Device
		String eyeGazeDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.EYE_GAZE_DEVICE);
		String eyeGazeDeviceUri = BciSparqlMediatorUtility.toUri(eyeGazeDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE, eyeGazeDeviceUri));
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY, eyeGazeDeviceUri));

		// EYEGAZE Device
		query.append(genUpdateTriple(eyeGazeDeviceUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_EYE_GAZE_DEVICE));
		query.append(genUpdateTriple(eyeGazeDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		if (!isMeasurementPropertyHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(eyeGazeDeviceUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ eyeGazeDeviceHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					measurementPropertyStartChannel));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					measurementPropertyEndChannel));
		}
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.EYE_GAZE_DEVICE_ID, eyeGazeDeviceId);
		return queryFerderate_EyeGazeDevice(request);
    }
	public static String updateEegDevice(JSONObject jsonObject) {

		JSONObject request = null;

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		
		String eegDeviceHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EEG_DEVICE_HARDWARE_MANUFACTURER);
		
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		String measurementPropertySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		boolean isMeasurementPropertyHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eegDeviceHardwareManufacturer);
		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);
/*
		if (isRecordedParameterSetUriEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}
*/
		// Create EEG Device
		String eegDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.EEG_DEVICE);
		String eegDeviceUri = BciSparqlMediatorUtility.toUri(eegDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY_EEG_DEVICE, eegDeviceUri));

		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY, eegDeviceUri));
		
		// EEG Device
		query.append(genUpdateTriple(eegDeviceUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_EEG_DEVICE));
		query.append(genUpdateTriple(eegDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		if (!isMeasurementPropertyHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(eegDeviceUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ eegDeviceHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					measurementPropertyStartChannel));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					measurementPropertyEndChannel));
		}
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.EEG_DEVICE_ID, eegDeviceId);
		return queryEegDevice(request);
	}

	public static String updateHandGestureDevice(JSONObject jsonObject) {

    	JSONObject request = null;

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String handGestureDeviceHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.HAND_GESTURE_DEVICE_HARDWARE_MANUFACTURER);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		String measurementPropertySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		boolean isMeasurementPropertyHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(handGestureDeviceHardwareManufacturer);
		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);

		if (isRecordedParameterSetUriEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create HAND_GESTURE Device
		String handGestureDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.HAND_GESTURE_DEVICE);
		String handGestureDeviceUri = BciSparqlMediatorUtility.toUri(handGestureDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE, handGestureDeviceUri));
		
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY, handGestureDeviceUri));

		// HAND GESTURE Device
		query.append(genUpdateTriple(handGestureDeviceUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_HAND_GESTURE_DEVICE));
		query.append(genUpdateTriple(handGestureDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		if (!isMeasurementPropertyHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(handGestureDeviceUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ handGestureDeviceHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					measurementPropertyStartChannel));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					measurementPropertyEndChannel));
		}
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.HAND_GESTURE_DEVICE_ID, handGestureDeviceId);
		return queryFerderate_HandGestureDevice(request);
	}
	
	public static String updateKeyBoardHitDevice(JSONObject jsonObject) {

		JSONObject request = null;

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String keyBoardHitDeviceHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.KEYBOARD_HIT_DEVICE_HARDWARE_MANUFACTURER);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		String measurementPropertySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		boolean isMeasurementPropertyHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(keyBoardHitDeviceHardwareManufacturer);
		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);

		if (isRecordedParameterSetUriEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create KEYBOARD HIT Device
		String keyBoardHitDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.KEYBOARD_HIT_DEVICE);
		String keyBoardHitDeviceUri = BciSparqlMediatorUtility.toUri(keyBoardHitDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY_KEYBOARD_HIT_DEVICE, keyBoardHitDeviceUri));
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY, keyBoardHitDeviceUri));
		// KEYBOARD HIT Device
		query.append(genUpdateTriple(keyBoardHitDeviceUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_KEYBOARD_HIT_DEVICE));
		query.append(genUpdateTriple(keyBoardHitDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		if (!isMeasurementPropertyHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(keyBoardHitDeviceUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ keyBoardHitDeviceHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					measurementPropertyStartChannel));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					measurementPropertyEndChannel));
		}
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());
        
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.KEYBOARD_HIT_DEVICE_ID, keyBoardHitDeviceId);
		return queryFerderate_KeyBoardHitDevice(request);
	}

	public static String updateMouseClickDevice(JSONObject jsonObject) {

		JSONObject request = null;

		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String mouseClickDeviceHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MOUSE_CLICK_DEVICE_HARDWARE_MANUFACTURER);
		String measurementPropertyStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL);
		String measurementPropertyEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL);
		String measurementPropertySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);

		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		boolean isMeasurementPropertyHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(mouseClickDeviceHardwareManufacturer);
		boolean isMeasurementPropertyStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyStartChannel);
		boolean isMeasurementPropertyEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyEndChannel);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);

		if (isRecordedParameterSetUriEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create KEYBOARD HIT Device
		String mouseClickDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.MOUSE_CLICK_DEVICE);
		String mouseClickDeviceUri = BciSparqlMediatorUtility.toUri(mouseClickDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE, mouseClickDeviceUri));
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_IS_GENERATED_BY, mouseClickDeviceUri));


		// mouseClick Device
		query.append(genUpdateTriple(mouseClickDeviceUri, SparqlSyntaxConstants.A,
				ClassConstants.BCI_MOUSE_CLICK_DEVICE));
		query.append(genUpdateTriple(mouseClickDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		if (!isMeasurementPropertyHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(mouseClickDeviceUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ mouseClickDeviceHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		if (!isMeasurementPropertyStartChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					measurementPropertyStartChannel));
		}
		if (!isMeasurementPropertyEndChannelEmpty) {
			query.append(genUpdateTriple(measurementPropertyUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					measurementPropertyEndChannel));
		}
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());
        
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.MOUSE_CLICK_DEVICE_ID, mouseClickDeviceId);
		return queryFerderate_MouseClickDevice(request);
	}

	

	//UPDATE
	
	public static String updateMotionCaptureDevice(JSONObject jsonObject) {

		JSONObject request = null;

		String measurementPropertyNumberOfChannels = "";
		String measurementPropertySamplingRate = "";

		if (!jsonObject
				.isNull(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS)) {
			measurementPropertyNumberOfChannels = jsonObject
					.getString(DataConstants.MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS);
		}
		if (!jsonObject
				.isNull(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE)) {
			measurementPropertySamplingRate = jsonObject
					.getString(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE);
		}

		boolean isMeasurementPropertyNumberOfChannelsEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertyNumberOfChannels);
		boolean isMeasurementPropertySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(measurementPropertySamplingRate);

		if (isMeasurementPropertyNumberOfChannelsEmpty
				|| isMeasurementPropertySamplingRateEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Motion Capture
		String motionCaptureDeviceId = BciSparqlMediatorUtility
				.genId(IdConstants.MOTION_CAPTURE_DEVICE);
		String motionCaptureDeviceUri = BciSparqlMediatorUtility
				.toUri(motionCaptureDeviceId);
		String measurementCapabilityId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_CAPABILITY);
		String measurementCapabilityUri = BciSparqlMediatorUtility
				.toUri(measurementCapabilityId);
		String measurementPropertyId = BciSparqlMediatorUtility
				.genId(IdConstants.MEASUREMENT_PROPERTY);
		String measurementPropertyUri = BciSparqlMediatorUtility
				.toUri(measurementPropertyId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_SSN
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Motion Capture Device
		query.append(genUpdateTriple(motionCaptureDeviceUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MOTION_CAPTURE_DEVICE));
		query.append(genUpdateTriple(motionCaptureDeviceUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_CAPABILITY,
				measurementCapabilityUri));

		// Measurement Capability
		query.append(genUpdateTriple(measurementCapabilityUri,
				SparqlSyntaxConstants.A,
				ClassConstants.SSN_MEASUREMENT_CAPABILITY));
		query.append(genUpdateTriple(measurementCapabilityUri,
				PropertyConstants.SSN_HAS_MEASUREMENT_PROPERTY,
				measurementPropertyUri));

		// Measurement Property
		query.append(genUpdateTriple(measurementPropertyUri,
				SparqlSyntaxConstants.A,
				ClassConstants.BCI_MEASUREMENT_PROPERTY));
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_NUMBER_OF_CHANNELS,
				measurementPropertyNumberOfChannels));
		query.append(genUpdateTriple(measurementPropertyUri,
				PropertyConstants.BCI_HAS_SAMPLING_RATE,
				measurementPropertySamplingRate));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.MOTION_CAPTURE_DEVICE_ID,
				motionCaptureDeviceId);
		return queryMotionCaptureDevice(request);
	}

	public static String updateRecordedModality(JSONObject jsonObject) {

		JSONObject request = null;

		// Add record_id to make relationship with Recordmodality_id
		String recordUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORD_ID);
		
		String recordedParameterSetUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_PARAMETER_SET_ID);
		String recordedModalityModalitySignalType = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE);
		
		String recordedModalitySamplingRate = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_SAMPLING_RATE);
		String recordedModalityHardwareManufacturer = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER);
		String recordedModalityStartChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_START_CHANNEL);
		String recordedModalityEndChannel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_END_CHANNEL);
		String recordedModalityReferenceLocation = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION);
		String recordedModalityReferenceLabel = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.RECORDED_MODALITY_REFERENCE_LABEL);
	 
		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
	
		boolean isRecordedParameterSetUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedParameterSetUri);
		
		boolean isRecordedModalityModalitySignalTypeEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityModalitySignalType);
		
		boolean isRecordedModalitySamplingRateEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalitySamplingRate);
		boolean isRecordedModalityHardwareManufacturerEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityHardwareManufacturer);
		boolean isRecordedModalityStartChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityStartChannel);
		boolean isRecordedModalityEndChannelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityEndChannel);
		boolean isRecordedModalityReferenceLocationEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityReferenceLocation);
		boolean isRecordedModalityReferenceLabelEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedModalityReferenceLabel);

		if (isRecordedParameterSetUriEmpty ||isRecordUriEmpty
				|| isRecordedModalityModalitySignalTypeEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Recorded Modality
		String recordedModalityId = BciSparqlMediatorUtility
				.genId(IdConstants.RECORDED_MODALITY);
		String recordedModalityUri = BciSparqlMediatorUtility
				.toUri(recordedModalityId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Record id
		query.append(genUpdateTriple(recordUri,
				PropertyConstants.BCI_HAS_RECORDED_MODALITY,
				recordedModalityUri));
		
		// Recorded Parameter Set
		query.append(genUpdateTriple(recordedParameterSetUri,
				PropertyConstants.BCI_HAS_RECORDED_MODALITY,
				recordedModalityUri));

		// Recorded Modality
		query.append(genUpdateTriple(recordedModalityUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_RECORDED_MODALITY));
		
		query.append(genUpdateTriple(recordedModalityUri,
				PropertyConstants.BCI_HAS_MODALITY_SIGNAL_TYPE,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ recordedModalityModalitySignalType
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		if (!isRecordedModalitySamplingRateEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_SAMPLING_RATE,
					recordedModalitySamplingRate));
		}
		if (!isRecordedModalityHardwareManufacturerEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_HARDWARE_MANUFACTURER,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedModalityHardwareManufacturer
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedModalityStartChannelEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_START_CHANNEL,
					recordedModalityStartChannel));
		}
		if (!isRecordedModalityEndChannelEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_END_CHANNEL,
					recordedModalityEndChannel));
		}
		if (!isRecordedModalityReferenceLocationEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_REFERENCE_LOCATION,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedModalityReferenceLocation
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		if (!isRecordedModalityReferenceLabelEmpty) {
			query.append(genUpdateTriple(recordedModalityUri,
					PropertyConstants.BCI_HAS_REFERENCE_LABEL,
					SparqlSyntaxConstants.DOUBLE_QUOTE
							+ recordedModalityReferenceLabel
							+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.RECORDED_MODALITY_ID, recordedModalityId);
		return queryRecordedModality(request);
	}

	public static String updateBiomedicalResource(JSONObject jsonObject) {

		JSONObject request = null;

		String recordUri = "";
		if (!jsonObject.isNull(DataConstants.EEG_RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.EEG_RECORD_ID));
		} else if (!jsonObject.isNull(DataConstants.MOTION_CAPTURE_RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.MOTION_CAPTURE_RECORD_ID));
		}

		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);

		String biomedicalResourceTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE);
		String biomedicalResourceAccessMethodUrl = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);

		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isBiomedicalResourceTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceTitle);
		boolean isBiomedicalResourceAccessMethodUrlEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceAccessMethodUrl);

		if (isRecordUriEmpty || isSubjectUriEmpty
				|| isBiomedicalResourceTitleEmpty
				|| isBiomedicalResourceAccessMethodUrlEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Biomedical Resource
		String biomedicalResouceId = BciSparqlMediatorUtility
				.genId(IdConstants.BIOMEDICAL_RESOURCE);
		String biomedicalResouceUri = BciSparqlMediatorUtility
				.toUri(biomedicalResouceId);
		String bimedicalResourceAccessMethodId = BciSparqlMediatorUtility
				.genId(IdConstants.ACCESS_METHOD_HTTP);
		String bimedicalResourceAccessMethodUri = BciSparqlMediatorUtility
				.toUri(bimedicalResourceAccessMethodId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EEG Record | Motion Capture Record
		query.append(genUpdateTriple(recordUri,
				PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
				biomedicalResouceUri));

		// Biomedical Resource
		query.append(genUpdateTriple(biomedicalResouceUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_BIOMEDICAL_RESOURCE));
		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_BIOMEDICAL_RESOURCE_OF, recordUri));
		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlSyntaxConstants.DOUBLE_QUOTE + biomedicalResourceTitle
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ DataRangeConstants.RESOURCE_UTILIZATION.get(3)
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));

		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				bimedicalResourceAccessMethodUri));

		// Access Method
		query.append(genUpdateTriple(bimedicalResourceAccessMethodUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_ACCESS_METHOD_HTTP));
		query.append(genUpdateTriple(bimedicalResourceAccessMethodUri,
				PropertyConstants.BCI_HAS_URL,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ biomedicalResourceAccessMethodUrl
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.BIOMEDICAL_RESOURCE_ID, biomedicalResouceId);
		return queryBiomedicalResource(request);
	}

	public static String updateGeneralBiomedicalResource(JSONObject jsonObject) {

		JSONObject request = null;

		String recordUri = "";
		if (!jsonObject.isNull(DataConstants.RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.RECORD_ID));
		} else if (!jsonObject.isNull(DataConstants.MOTION_CAPTURE_RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.MOTION_CAPTURE_RECORD_ID));
		}

		String subjectUri = BciSparqlMediatorUtility.getJsonObjectUri(
				jsonObject, DataConstants.SUBJECT_ID);

		String biomedicalResourceTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_TITLE);
		String biomedicalResourceAccessMethodUrl = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);

		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isSubjectUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(subjectUri);
		boolean isBiomedicalResourceTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceTitle);
		boolean isBiomedicalResourceAccessMethodUrlEmpty = BciSparqlMediatorUtility
				.isValueEmpty(biomedicalResourceAccessMethodUrl);

		if (isRecordUriEmpty || isSubjectUriEmpty
				|| isBiomedicalResourceTitleEmpty
				|| isBiomedicalResourceAccessMethodUrlEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Biomedical Resource
		String biomedicalResouceId = BciSparqlMediatorUtility
				.genId(IdConstants.BIOMEDICAL_RESOURCE);
		String biomedicalResouceUri = BciSparqlMediatorUtility
				.toUri(biomedicalResouceId);
		String bimedicalResourceAccessMethodId = BciSparqlMediatorUtility
				.genId(IdConstants.ACCESS_METHOD_HTTP);
		String bimedicalResourceAccessMethodUri = BciSparqlMediatorUtility
				.toUri(bimedicalResourceAccessMethodId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EEG Record | Motion Capture Record
		query.append(genUpdateTriple(recordUri,
				PropertyConstants.BCI_HAS_BIOMEDICAL_RESOURCE,
				biomedicalResouceUri));

		// Biomedical Resource
		query.append(genUpdateTriple(biomedicalResouceUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_BIOMEDICAL_RESOURCE));
		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_BIOMEDICAL_RESOURCE_OF, recordUri));
		
		if(!isBiomedicalResourceTitleEmpty){
		 query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlSyntaxConstants.DOUBLE_QUOTE + biomedicalResourceTitle
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		}
		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ DataRangeConstants.RESOURCE_UTILIZATION.get(3)
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_IS_FROM_SUBJECT, subjectUri));

		query.append(genUpdateTriple(biomedicalResouceUri,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				bimedicalResourceAccessMethodUri));

		// Access Method
		query.append(genUpdateTriple(bimedicalResourceAccessMethodUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_ACCESS_METHOD_HTTP));
		query.append(genUpdateTriple(bimedicalResourceAccessMethodUri,
				PropertyConstants.BCI_HAS_URL,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ biomedicalResourceAccessMethodUrl
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.BIOMEDICAL_RESOURCE_ID, biomedicalResouceId);
		return queryBiomedicalResource(request);
	}

	public static String updateChannelLocations(JSONObject jsonObject) {

		JSONObject request = null;

		String recordedSubjectAtSessionUri = BciSparqlMediatorUtility
				.getJsonObjectUri(jsonObject,
						DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);

		String channelLocationsTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.CHANNEL_LOCATIONS_TITLE);
		String channelLocationsAccessMethodUrl = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL);

		boolean isRecordedSubjectAtSessionUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordedSubjectAtSessionUri);
		boolean isChannelLocationsTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsTitle);
		boolean isChannelLocationsAccessMethodUrlEmpty = BciSparqlMediatorUtility
				.isValueEmpty(channelLocationsAccessMethodUrl);

		if (isRecordedSubjectAtSessionUriEmpty || isChannelLocationsTitleEmpty
				|| isChannelLocationsAccessMethodUrlEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Channel Locations
		String channelLocationsId = BciSparqlMediatorUtility
				.genId(IdConstants.CHANNEL_LOCATIONS);
		String channelLocationsUri = BciSparqlMediatorUtility
				.toUri(channelLocationsId);
		String channelLocationsAccessMethodId = BciSparqlMediatorUtility
				.genId(IdConstants.ACCESS_METHOD_HTTP);
		String channelLocationsAccessMethodUri = BciSparqlMediatorUtility
				.toUri(channelLocationsAccessMethodId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Recorded Subject At Session
		query.append(genUpdateTriple(recordedSubjectAtSessionUri,
				PropertyConstants.BCI_HAS_RESOURCE, channelLocationsUri));

		// Channel Locations
		query.append(genUpdateTriple(channelLocationsUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_RESOURCE));
		query.append(genUpdateTriple(channelLocationsUri,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlSyntaxConstants.DOUBLE_QUOTE + channelLocationsTitle
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		query.append(genUpdateTriple(channelLocationsUri,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ DataRangeConstants.RESOURCE_UTILIZATION.get(4)
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));
		query.append(genUpdateTriple(channelLocationsUri,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				channelLocationsAccessMethodUri));

		// Access Method
		query.append(genUpdateTriple(channelLocationsAccessMethodUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_ACCESS_METHOD_HTTP));
		query.append(genUpdateTriple(channelLocationsAccessMethodUri,
				PropertyConstants.BCI_HAS_URL,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ channelLocationsAccessMethodUrl
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.CHANNEL_LOCATIONS_ID, channelLocationsId);
		return queryChannelLocations(request);
	}

	public static String updateEventInstanceFile(JSONObject jsonObject) {

		JSONObject request = null;

		String recordUri = "";
		if (!jsonObject.isNull(DataConstants.EEG_RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.EEG_RECORD_ID));
		} else if (!jsonObject.isNull(DataConstants.MOTION_CAPTURE_RECORD_ID)) {
			recordUri = BciSparqlMediatorUtility.toUri(jsonObject
					.getString(DataConstants.MOTION_CAPTURE_RECORD_ID));
		}

		String eventInstanceFileTitle = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EVENT_INSTANCE_FILE_TITLE);
		String eventInstanceFileAccessMethodUrl = BciSparqlMediatorUtility
				.getJsonObjectValue(jsonObject,
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL);

		boolean isRecordUriEmpty = BciSparqlMediatorUtility
				.isValueEmpty(recordUri);
		boolean isEventInstanceFileTitleEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eventInstanceFileTitle);
		boolean isEventInstanceFileAccessMethodUrlEmpty = BciSparqlMediatorUtility
				.isValueEmpty(eventInstanceFileAccessMethodUrl);

		if (isRecordUriEmpty || isEventInstanceFileTitleEmpty
				|| isEventInstanceFileAccessMethodUrlEmpty) {
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}

		// Create Biomedical Resource
		String eventInstanceFileId = BciSparqlMediatorUtility
				.genId(IdConstants.EVENT_INSTANCE_FILE);
		String eventInstanceFileeUri = BciSparqlMediatorUtility
				.toUri(eventInstanceFileId);
		String eventInstanceFileAccessMethodId = BciSparqlMediatorUtility
				.genId(IdConstants.ACCESS_METHOD_HTTP);
		String eventInstanceFileAccessMethodUri = BciSparqlMediatorUtility
				.toUri(eventInstanceFileAccessMethodId);

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// EEG Record | Motion Capture Record
		query.append(genUpdateTriple(recordUri,
				PropertyConstants.BCI_HAS_RESOURCE, eventInstanceFileeUri));

		// Biomedical Resource
		query.append(genUpdateTriple(eventInstanceFileeUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_RESOURCE));
		query.append(genUpdateTriple(eventInstanceFileeUri,
				PropertyConstants.BCI_HAS_TITLE,
				SparqlSyntaxConstants.DOUBLE_QUOTE + eventInstanceFileTitle
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		query.append(genUpdateTriple(eventInstanceFileeUri,
				PropertyConstants.BCI_IS_USED_FOR,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ DataRangeConstants.RESOURCE_UTILIZATION.get(5)
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		query.append(genUpdateTriple(eventInstanceFileeUri,
				PropertyConstants.BCI_HAS_ACCESS_METHOD,
				eventInstanceFileAccessMethodUri));

		// Access Method
		query.append(genUpdateTriple(eventInstanceFileAccessMethodUri,
				SparqlSyntaxConstants.A, ClassConstants.BCI_ACCESS_METHOD_HTTP));
		query.append(genUpdateTriple(eventInstanceFileAccessMethodUri,
				PropertyConstants.BCI_HAS_URL,
				SparqlSyntaxConstants.DOUBLE_QUOTE
						+ eventInstanceFileAccessMethodUrl
						+ SparqlSyntaxConstants.DOUBLE_QUOTE));

		// End
		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.BIOMEDICAL_RESOURCE_ID, eventInstanceFileId);
		return queryBiomedicalResource(request);
	}

	public static String updateEss1(JSONObject jsonObject) {

		JSONObject request = null;

		Ess1 ess1 = new Ess1(jsonObject.getString(DataConstants.ESS_XML));

		// Recorded Parameter Set
		request = new JSONObject();
		String recordedParameterSetId = BciSparqlMediatorUtility
				.getSparqlUpdateValue(updateRecordedParameterSet(request),
						DataConstants.RECORDED_PARAMETER_SET_ID);

		// EEG Device
		request = new JSONObject();
		request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
				recordedParameterSetId);
		request.put(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
				ess1.STUDY.SESSIONS.get(0).EEG_SAMPLING_RATE);
		request.put(DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
				ess1.STUDY.SESSIONS.get(0).CHANNELS);
		request.put(DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
				ess1.STUDY.SESSIONS.get(0).CHANNELS);
		BciSparqlMediatorUtility.getSparqlUpdateValue(updateEegDevice(request),
				DataConstants.EEG_DEVICE_ID);

		// Recorded Modality
		request = new JSONObject();
		request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
				recordedParameterSetId);
		request.put(DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
				ess1.STUDY.SUMMARY.RECORDED_MODALITIES.get(0).NAME);
		request.put(DataConstants.RECORDED_MODALITY_START_CHANNEL,
				ess1.STUDY.SESSIONS.get(0).CHANNELS);
		request.put(DataConstants.RECORDED_MODALITY_END_CHANNEL,
				ess1.STUDY.SESSIONS.get(0).CHANNELS);
		BciSparqlMediatorUtility.getSparqlUpdateValue(
				updateRecordedModality(request),
				DataConstants.RECORDED_MODALITY_ID);

		// Study
		request = new JSONObject();
		request.put(DataConstants.STUDY_TITLE, ess1.STUDY.TITLE);
		request.put(DataConstants.STUDY_PURPOSE, ess1.STUDY.DESCRIPTION);
		String studyId = BciSparqlMediatorUtility.getSparqlUpdateValue(
				updateStudy(request), DataConstants.STUDY_ID);

		for (int i = 0; i < ess1.STUDY.SESSIONS.size(); i++) {
			// Session
			request = new JSONObject();
			request.put(DataConstants.STUDY_ID, studyId);
			request.put(DataConstants.SESSION_TASK_LABEL,
					ess1.STUDY.SESSIONS.get(i).TASK_LABEL);
			request.put(DataConstants.SESSION_PURPOSE,
					ess1.STUDY.SESSIONS.get(i).PURPOSE);
			request.put(DataConstants.SESSION_LAB_ID,
					ess1.STUDY.SESSIONS.get(i).LAB_ID);
			String sessionId = BciSparqlMediatorUtility.getSparqlUpdateValue(
					updateSession(request), DataConstants.SESSION_ID);

			// Subject
			request = new JSONObject();
			request.put(DataConstants.SUBJECT_GENDER,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.GENDER);
			request.put(DataConstants.SUBJECT_YEAR_OF_BIRTH,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.YOB);
			request.put(DataConstants.SUBJECT_HANDEDNESS,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.HAND);
			String subjectId = BciSparqlMediatorUtility.getSparqlUpdateValue(
					updateSubject(request), DataConstants.SUBJECT_ID);

			// Recorded Subject At Session
			request = new JSONObject();
			request.put(DataConstants.SESSION_ID, sessionId);
			request.put(DataConstants.SUBJECT_ID, subjectId);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.LAB_ID);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.GROUP);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.AGE);
			request.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATION_TYPE);
			String recordedSubjectAtSessionId = BciSparqlMediatorUtility
					.getSparqlUpdateValue(
							updateRecordedSubjectAtSession(request),
							DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);

			// Channel Locations
			request = new JSONObject();
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
					recordedSubjectAtSessionId);
			request.put(DataConstants.CHANNEL_LOCATIONS_TITLE,
					ess1.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATIONS);
			request.put(
					DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
					BciSparqlMediatorConfig.HEADIT_LOCATION
							+ ess1.STUDY.TITLE
							+ SparqlSyntaxConstants.SLASH
							+ "session"
							+ SparqlSyntaxConstants.SLASH
							+ String.valueOf(i + 1)
							+ SparqlSyntaxConstants.SLASH
							+ ess1.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATIONS);
			updateChannelLocations(request);

			for (int k = 0; k < ess1.STUDY.SESSIONS.get(i).EEG_RECORDINGS
					.size(); k++) {

				// EEG Record
				request = new JSONObject();
				request.put(DataConstants.STUDY_ID, studyId);
				request.put(DataConstants.SESSION_ID, sessionId);
				request.put(DataConstants.SUBJECT_ID, subjectId);
				request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
						recordedParameterSetId);
				request.put(DataConstants.EEG_RECORD_NUMBER_OF_CHANNELS,
						ess1.STUDY.SESSIONS.get(0).CHANNELS);
				String eegRecordId = BciSparqlMediatorUtility
						.getSparqlUpdateValue(updateEegRecord(request),
								DataConstants.EEG_RECORD_ID);

				// Biomedical Resource
				request = new JSONObject();
				request.put(DataConstants.EEG_RECORD_ID, eegRecordId);
				request.put(DataConstants.SUBJECT_ID, subjectId);
				request.put(DataConstants.BIOMEDICAL_RESOURCE_TITLE,
						ess1.STUDY.SESSIONS.get(i).EEG_RECORDINGS.get(k));
				request.put(
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
						BciSparqlMediatorConfig.HEADIT_LOCATION
								+ ess1.STUDY.TITLE
								+ SparqlSyntaxConstants.SLASH
								+ "session"
								+ SparqlSyntaxConstants.SLASH
								+ String.valueOf(i + 1)
								+ SparqlSyntaxConstants.SLASH
								+ ess1.STUDY.SESSIONS.get(i).EEG_RECORDINGS
										.get(k));
				updateBiomedicalResource(request);
			}
		}

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.STUDY_ID, studyId);
		return queryStudy(request);
	}

	public static String updateEss2(JSONObject jsonObject) {

		JSONObject request = null;

		Ess2 ess2 = new Ess2(jsonObject.getString(DataConstants.ESS_XML));

		HashMap<String, String> recordedParameterSet = new HashMap<String, String>();

		for (int i = 0; i < ess2.STUDY.RECORDING_PARAMETER_SETS.size(); i++) {
			String recordedParameterSetLabel = ess2.STUDY.RECORDING_PARAMETER_SETS
					.get(i).RECORDING_PARAMETER_SET_LABEL;

			// Recorded Parameter Set
			request = new JSONObject();
			String recordedParameterSetId = BciSparqlMediatorUtility
					.getSparqlUpdateValue(updateRecordedParameterSet(request),
							DataConstants.RECORDED_PARAMETER_SET_ID);
			recordedParameterSet.put(recordedParameterSetLabel,
					recordedParameterSetId);

			for (int k = 0; k < ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
					.size(); k++) {
				// Recorded Modality
				request = new JSONObject();
				request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
						recordedParameterSetId);
				request.put(DataConstants.RECORDED_MODALITY_MODALITY_TYPE,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).TYPE);
				//ADD MODALITYSIGNALTYPE 
				request.put(DataConstants.RECORDED_MODALITY_MODALITY_SIGNAL_TYPE,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).TYPE);
				request.put(DataConstants.RECORDED_MODALITY_SAMPLING_RATE,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).SAMPLING_RATE);
				request.put(
						DataConstants.RECORDED_MODALITY_HARDWARE_MANUFACTURER,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).NAME);
				request.put(DataConstants.RECORDED_MODALITY_START_CHANNEL,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).START_CHANNEL);
				request.put(DataConstants.RECORDED_MODALITY_END_CHANNEL,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).END_CHANNEL);
				request.put(DataConstants.RECORDED_MODALITY_REFERENCE_LOCATION,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).REFERENCE_LOCATION);
				request.put(DataConstants.RECORDED_MODALITY_REFERENCE_LABEL,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).REFERENCE_LABEL);
				BciSparqlMediatorUtility.getSparqlUpdateValue(
						updateRecordedModality(request),
						DataConstants.RECORDED_MODALITY_ID);

				// EEG Device
				request = new JSONObject();
				request.put(DataConstants.RECORDED_PARAMETER_SET_ID,
						recordedParameterSetId);
				request.put(DataConstants.EEG_DEVICE_HARDWARE_MANUFACTURER,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).NAME);
				request.put(DataConstants.MEASUREMENT_PROPERTY_START_CHANNEL,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).START_CHANNEL);
				request.put(DataConstants.MEASUREMENT_PROPERTY_END_CHANNEL,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).END_CHANNEL);
				request.put(DataConstants.MEASUREMENT_PROPERTY_SAMPLING_RATE,
						ess2.STUDY.RECORDING_PARAMETER_SETS.get(i).CHANNEL_TYPE
								.get(k).SAMPLING_RATE);
				BciSparqlMediatorUtility.getSparqlUpdateValue(
						updateEegDevice(request), DataConstants.EEG_DEVICE_ID);
			}
		}

		// Study
		request = new JSONObject();
		request.put(DataConstants.STUDY_TITLE, ess2.STUDY.TITLE);
		request.put(DataConstants.STUDY_PURPOSE, ess2.STUDY.SHORT_DESCRIPTION);
		request.put(DataConstants.STUDY_UUID, ess2.STUDY.UUID);
		request.put(DataConstants.STUDY_ROOT_URI, ess2.STUDY.ROOT_URI);
		String studyId = BciSparqlMediatorUtility.getSparqlUpdateValue(
				updateStudy(request), DataConstants.STUDY_ID);

		for (int i = 0; i < ess2.STUDY.SESSIONS.size(); i++) {
			// Session
			request = new JSONObject();
			request.put(DataConstants.STUDY_ID, studyId);
			request.put(DataConstants.SESSION_TASK_LABEL,
					ess2.STUDY.SESSIONS.get(i).TASK_LABEL);
			request.put(DataConstants.SESSION_PURPOSE,
					ess2.STUDY.SESSIONS.get(i).PURPOSE);
			request.put(DataConstants.SESSION_LAB_ID,
					ess2.STUDY.SESSIONS.get(i).LAB_ID);
			String sessionId = BciSparqlMediatorUtility.getSparqlUpdateValue(
					updateSession(request), DataConstants.SESSION_ID);

			// Subject
			request = new JSONObject();
			request.put(DataConstants.SUBJECT_GENDER,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.GENDER);
			request.put(DataConstants.SUBJECT_YEAR_OF_BIRTH,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.YOB);
			request.put(DataConstants.SUBJECT_HANDEDNESS,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.HAND);
			String subjectId = BciSparqlMediatorUtility.getSparqlUpdateValue(
					updateSubject(request), DataConstants.SUBJECT_ID);

			// Recorded Subject At Session
			request = new JSONObject();
			request.put(DataConstants.SESSION_ID, sessionId);
			request.put(DataConstants.SUBJECT_ID, subjectId);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_LAB_ID,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.LAB_ID);
			request.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.IN_SESSION_NUMBER);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_GROUP,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.GROUP);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_AGE,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.AGE);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_VISION,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.VISION);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_HEARING,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.HEARING);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_HEIGHT,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.HEIGHT);
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_WEIGHT,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.WEIGHT);
			request.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.MEDICATION.CAFFEINE);
			request.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.MEDICATION.ALCOHOL);
			request.put(
					DataConstants.RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATION_TYPE);
			String recordedSubjectAtSessionId = BciSparqlMediatorUtility
					.getSparqlUpdateValue(
							updateRecordedSubjectAtSession(request),
							DataConstants.RECORDED_SUBJECT_AT_SESSION_ID);

			// Channel Locations
			request = new JSONObject();
			request.put(DataConstants.RECORDED_SUBJECT_AT_SESSION_ID,
					recordedSubjectAtSessionId);
			request.put(DataConstants.CHANNEL_LOCATIONS_TITLE,
					ess2.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATIONS);
			request.put(
					DataConstants.CHANNEL_LOCATIONS_ACCESS_METHOD_URL,
					BciSparqlMediatorConfig.HEADIT_LOCATION
							+ ess2.STUDY.TITLE
							+ SparqlSyntaxConstants.SLASH
							+ "session"
							+ SparqlSyntaxConstants.SLASH
							+ String.valueOf(i + 1)
							+ SparqlSyntaxConstants.SLASH
							+ ess2.STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATIONS);
			updateChannelLocations(request);

			for (int k = 0; k < ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS
					.size(); k++) {

				// EEG Record
				request = new JSONObject();
				request.put(DataConstants.STUDY_ID, studyId);
				request.put(DataConstants.SESSION_ID, sessionId);
				request.put(DataConstants.SUBJECT_ID, subjectId);
				request.put(
						DataConstants.RECORDED_PARAMETER_SET_ID,
						recordedParameterSet.get(ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS
								.get(k).RECORDING_PARAMETER_SET_LABEL));
				String eegRecordId = BciSparqlMediatorUtility
						.getSparqlUpdateValue(updateEegRecord(request),
								DataConstants.EEG_RECORD_ID);

				// Biomedical Resource
				request = new JSONObject();
				request.put(DataConstants.EEG_RECORD_ID, eegRecordId);
				request.put(DataConstants.SUBJECT_ID, subjectId);
				request.put(
						DataConstants.BIOMEDICAL_RESOURCE_TITLE,
						ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS.get(k).FILE_NAME);
				request.put(
						DataConstants.BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL,
						BciSparqlMediatorConfig.ESS_2_LOCATION
								+ ess2.STUDY.TITLE
								+ SparqlSyntaxConstants.SLASH
								+ "session"
								+ SparqlSyntaxConstants.SLASH
								+ String.valueOf(i + 1)
								+ SparqlSyntaxConstants.SLASH
								+ ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS
										.get(k).FILE_NAME);
				updateBiomedicalResource(request);

				// Event Instance File
				request = new JSONObject();
				request.put(DataConstants.EEG_RECORD_ID, eegRecordId);
				request.put(
						DataConstants.EVENT_INSTANCE_FILE_TITLE,
						ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS.get(k).EVENT_INSTANCE_FILE);
				request.put(
						DataConstants.EVENT_INSTANCE_FILE_ACCESS_METHOD_URL,
						BciSparqlMediatorConfig.ESS_2_LOCATION
								+ ess2.STUDY.TITLE
								+ SparqlSyntaxConstants.SLASH
								+ "session"
								+ SparqlSyntaxConstants.SLASH
								+ String.valueOf(i + 1)
								+ SparqlSyntaxConstants.SLASH
								+ ess2.STUDY.SESSIONS.get(i).DATA_RECORDINGS
										.get(k).EVENT_INSTANCE_FILE);
				updateEventInstanceFile(request);

			}
		}

		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.STUDY_ID, studyId);
		return queryStudy(request);
	}

	private static String genFixedList(String variable, String property) {

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_OWL
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_RDF
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_RDFS
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SELECT
				+ SparqlSyntaxConstants.DISTINCT + variable
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + property
				+ PropertyConstants.RDFS_RANGE + SparqlSyntaxConstants.SLASH
				+ PropertyConstants.OWL_ONE_OF + SparqlSyntaxConstants.SLASH
				+ PropertyConstants.RDF_REST + SparqlSyntaxConstants.ASTERISK
				+ SparqlSyntaxConstants.SLASH + PropertyConstants.RDF_FIRST
				+ variable + SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		return query.toString();
	}

	private static String genFilterIdEqual(String variable, String value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE 
				+ SparqlSyntaxConstants.FILTER 
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.EQUAL + value
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterStringEqual(String variable, String value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.EQUAL
				+ SparqlSyntaxConstants.DOUBLE_QUOTE + value
				+ SparqlSyntaxConstants.DOUBLE_QUOTE
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES  + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterNumberGreaterThan(String variable, int value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.GREATER_EQUAL_THAN + value
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterNumberLessThan(String variable, int value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.LESS_EQUAL_THAN + value
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterNumberEqual(String variable, int value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.EQUAL + value
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterDatetimeGreaterThan(String variable,
			String value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.GREATER_EQUAL_THAN
				+ SparqlSyntaxConstants.DOUBLE_QUOTE + value
				+ SparqlSyntaxConstants.DOUBLE_QUOTE
				+ SparqlSyntaxConstants.TYPE_CONVERSION
				+ ClassConstants.XSD_DATE_TIME
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterDatetimeLessThan(String variable,
			String value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.LESS_EQUAL_THAN
				+ SparqlSyntaxConstants.DOUBLE_QUOTE + value
				+ SparqlSyntaxConstants.DOUBLE_QUOTE
				+ SparqlSyntaxConstants.TYPE_CONVERSION
				+ ClassConstants.XSD_DATE_TIME
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genFilterStringRegex(String variable, String value) {

		return (SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.SPACE
				+ SparqlSyntaxConstants.SPACE + SparqlSyntaxConstants.FILTER
				+ SparqlSyntaxConstants.REGEX
				+ SparqlSyntaxConstants.LEFT_PARENTHESES + variable
				+ SparqlSyntaxConstants.COMMA
				+ SparqlSyntaxConstants.DOUBLE_QUOTE + value
				+ SparqlSyntaxConstants.DOUBLE_QUOTE
				+ SparqlSyntaxConstants.RIGHT_PARENTHESES + SparqlSyntaxConstants.NEW_LINE);
	}

	private static String genQueryTriple(HashMap<String, Boolean> fields,
			String key, String s, String p, String o) {
		StringBuffer triple = new StringBuffer();

		// /*- @sjrm(2014-10-24): this is DEBUG information to check the content of the HashMap object.
		//triple.append(" [Key: " + key + "], [ fields.containsKey(key):  " + fields.containsKey(key)  + "]  ");
		if (fields.containsKey(key)) {
			triple.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.OPTIONAL
					+ SparqlSyntaxConstants.LEFT_BRACE + s + p + o
					+ SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.END_TRIPLE);
		}

		return triple.toString();
	}
	
	private static String genOptionQuery(String s, String p, String o) {
		StringBuffer triple = new StringBuffer();

			triple.append(SparqlSyntaxConstants.SPACE
					+ SparqlSyntaxConstants.OPTIONAL
					+ SparqlSyntaxConstants.LEFT_BRACE + s + p + o
					+ SparqlSyntaxConstants.RIGHT_BRACE
					+ SparqlSyntaxConstants.END_TRIPLE);

		return triple.toString();
	}

	
	private static String genUpdateTriple(String s, String p, String o) {
		StringBuffer triple = new StringBuffer();

		triple.append(SparqlSyntaxConstants.SPACE);
		triple.append(s + p + o);
		triple.append(SparqlSyntaxConstants.END_TRIPLE);

		return triple.toString();
	}

	private static void changeStudyEndTime(String studyId, String timeStamp) {

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		// DELETE Study End Time
		query.append(SparqlSyntaxConstants.DELETE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + studyId
				+ PropertyConstants.BCI_HAS_END_TIME
				+ SparqlVariableConstants.STUDY_END_TIME
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Insert Study End Time
		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + studyId
				+ PropertyConstants.BCI_HAS_END_TIME + timeStamp
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Where Study End Time
		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + studyId
				+ PropertyConstants.BCI_HAS_END_TIME
				+ SparqlVariableConstants.STUDY_END_TIME
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		if (DebugConstants.DEBUG_SPARQL_REPOSITORY)
			System.out.println(query.toString());
		VirtuosoUpdateFactory.create(query.toString(), mVirtGraph).exec();
	}

	private static void changeSessionEndTime(String sessionId, String timeStamp) {

		StringBuffer query = new StringBuffer();
		query.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		query.append(SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		// DELETE Session End Time
		query.append(SparqlSyntaxConstants.DELETE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + sessionId
				+ PropertyConstants.BCI_HAS_END_TIME
				+ SparqlVariableConstants.SESSION_END_TIME
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Insert Session End Time
		query.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + sessionId
				+ PropertyConstants.BCI_HAS_END_TIME + timeStamp
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		// Where Session End Time
		query.append(SparqlSyntaxConstants.WHERE
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		query.append(SparqlSyntaxConstants.SPACE + sessionId
				+ PropertyConstants.BCI_HAS_END_TIME
				+ SparqlVariableConstants.SESSION_END_TIME
				+ SparqlSyntaxConstants.END_TRIPLE);

		query.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);

		if (DebugConstants.DEBUG_SPARQL_REPOSITORY)
			System.out.println(query.toString());
		VirtuosoUpdateFactory.create(query.toString(), mVirtGraph).exec();
	}
    
	private static String getNameOfRecordType(String a ){
		String b="";
			b= a.substring(a.indexOf("#")+1, a.indexOf("_"));
		return b;
	}
	
	public static String describeURI(JSONObject jsonObject) {

			
			StringBuffer query = new StringBuffer();
			
			String id= BciSparqlMediatorUtility.getJsonObjectUri(jsonObject,
					 DataConstants.ANYID);
			
			query.append(NamespaceConstants.PREFIX_BCI
					+ SparqlSyntaxConstants.NEW_LINE);
			query.append(SparqlSyntaxConstants.NEW_LINE);

			query.append(SparqlSyntaxConstants.DESCRIBE + id);
			return BciSparqlMediatorUtility.virtuosoQuery(mVirtGraph,
					query.toString());
		}


	//OPTIMIZE CODE
	//1. Get needed value in JsonObject
	
	private static HashMap<String, Boolean> getParameterValue(JSONObject jsonObject){
		HashMap<String, Boolean> neededValue = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		
		return neededValue;
		
	}
	
	public static String update_Optimized_Study(JSONObject jsonObject) {
		

		JSONObject request = null;
     
		//1. Get needed value in JsonObject
		
		HashMap<String, Boolean> store = BciSparqlMediatorUtility
				.jsonArray2HashMap(jsonObject,
						OperationConstants.QUERY_FUNCTION_FIELDS);
		
		String studyTitle= BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_TITLE);
		String studyPurpose = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_PURPOSE);
		String studyUuid = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_UUID);
		String studyRootUri = BciSparqlMediatorUtility.getJsonObjectValue(
				jsonObject, DataConstants.STUDY_ROOT_URI);
		
		String[] requiredParam={DataConstants.STUDY_TITLE};
		String[] param= {DataConstants.STUDY_TITLE,DataConstants.STUDY_PURPOSE,DataConstants.STUDY_UUID,DataConstants.STUDY_ROOT_URI };
		
		
		if(checkExistance_Required(store,requiredParam)){
			return BciSparqlMediatorUtility
					.queryResult(SettingConstants.FAILED);
		}
		
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_VALUE);
		request.put(DataConstants.STUDY_TITLE, studyTitle);
		JSONArray response = new JSONArray(queryStudy(request));
		if (!BciSparqlMediatorUtility.isQueryEmpty(response)) {
			return response.toString();
		}
		
		String timeStamp = BciSparqlMediatorUtility.getCurrentTime();

		String studyId = BciSparqlMediatorUtility.genId(IdConstants.STUDY);
		String studyUri = BciSparqlMediatorUtility.toUri(studyId);

		StringBuffer query = new StringBuffer();
      
		query.append(genHeaderSparql());
		
		String[]predicate={SparqlSyntaxConstants.A,PropertyConstants.BCI_HAS_TITLE,PropertyConstants.BCI_HAS_PURPOSE,
				PropertyConstants.BCI_HAS_UUID,PropertyConstants.BCI_HAS_START_TIME,};
		String[]object={ClassConstants.BCI_STUDY,studyTitle,studyPurpose,studyRootUri };
        
		// End sparql
		
		query.append(genEndSparql());
		
		//7. Revoke insert
		
		BciSparqlMediatorUtility.virtuosoUpdate(mVirtGraph, query.toString());
		
        // 8. Call query to return the jsonObject for search
		request = new JSONObject();
		request.put(OperationConstants.QUERY_MODE_TYPE,
				OperationConstants.QUERY_MODE_ID);
		request.put(DataConstants.STUDY_ID, studyId);
		return queryStudy(request);
	}
	
	//If each element in param is not exist in HashMap, it will be fail
	public static boolean checkExistance_Required(HashMap<String,Boolean> store,String[]requiredParam){
		for(int i=0; i<requiredParam.length - 1; i++){
			
			if(!store.containsKey(requiredParam[i]))
					return false;
		}
		
		return true;
	}
	
	//generate relationship
	public static StringBuffer genRelationship(String s, String[] p, String[] o, HashMap<String,Boolean> store)
	{
		StringBuffer relation = new StringBuffer();
		for(int i=0;i<o.length-1; i++){
			if(store.containsKey(o[i]))
				relation.append(genUpdateTriple(s,p[i],o[i]));
		}
		
		return relation;
	}
	
	//Generate the header of sparql
	
	public static StringBuffer genHeaderSparql(){
		
		StringBuffer header= new StringBuffer();
		
		header.append(NamespaceConstants.PREFIX_BCI
				+ SparqlSyntaxConstants.NEW_LINE);
		header.append(NamespaceConstants.PREFIX_XSD
				+ SparqlSyntaxConstants.NEW_LINE);
		header.append(SparqlSyntaxConstants.NEW_LINE);

		header.append(SparqlSyntaxConstants.WITH
				+ NamespaceConstants.GRAPH_BCI_STORE
				+ SparqlSyntaxConstants.NEW_LINE);

		header.append(SparqlSyntaxConstants.INSERT
				+ SparqlSyntaxConstants.LEFT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		return header;
	}
	
	public static StringBuffer genEndSparql(){
		
		StringBuffer footer = new StringBuffer();
		
		footer.append(SparqlSyntaxConstants.RIGHT_BRACE
				+ SparqlSyntaxConstants.NEW_LINE);
		
		return footer;
	}
	
	

}
