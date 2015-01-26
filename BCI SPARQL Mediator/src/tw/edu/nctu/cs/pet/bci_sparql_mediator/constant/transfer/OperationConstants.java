package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class OperationConstants {

	// SPARQL Operation: 0
	public static final int EXIT;

	// SPARQL Operation: 1
	
	public static final String QUERY_MODE_TYPE;
	public static final String QUERY_MODE_ID;
	public static final String QUERY_MODE_VALUE;
	
	public static final String QUERY_FUNCTION_FIELDS;
	public static final String QUERY_FUNCTION_EXISTS;
	public static final String QUERY_FUNCTION_NOT_EXISTS;	
	
	public static final int QUERY_ALL_ALL;
	
	public static final int QUERY_STUDY_ALL;
	
	public static final int QUERY_SESSION_ALL;
	public static final int QUERY_SESSION_TASK_LABEL;
	public static final int QUERY_SESSION_PURPOSE;
	
	public static final int QUERY_EEG_RECORD_ALL;
	public static final int QUERY_MOTION_CAPTURE_RECORD_ALL;
	
	public static final int QUERY_SUBJECT_ALL;
	public static final int QUERY_SUBJECT_GENDER;
	public static final int QUERY_SUBJECT_HANDEDNESS;
	
	public static final int QUERY_RECORDED_SUBJECT_AT_SESSION_ALL;
	public static final int QUERY_RECORDED_SUBJECT_AT_SESSION_GROUP;
	public static final int QUERY_RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE;

	public static final int QUERY_EEG_DEVICE_ALL;
	public static final int QUERY_MOTION_CAPTURE_DEVICE_ALL;
	
	public static final int QUERY_RECORDED_MODALITY_ALL;
	public static final int QUERY_RECORDED_MODALITY_MODALITY_TYPE;
	
	public static final int QUERY_BIOMEDICAL_RESOURCE_ALL;
	
	public static final int QUERY_CHANNEL_LOCATIONS_ALL;

	// SPARQL Operation: 2
	public static final int UPDATE_STUDY;
	public static final int UPDATE_SESSION;
	public static final int UPDATE_EEG_RECORD;
	public static final int UPDATE_EYE_GAZE_RECORD;
	public static final int UPDATE_HAND_GESTURE_RECORD;
	public static final int UPDATE_KEYBOARD_HIT_RECORD;
	public static final int UPDATE_MOUSE_CLICK_RECORD;
	
	public static final int UPDATE_EYE_GAZE_CHANNEL;
	public static final int UPDATE_HAND_GESTURE_CHANNEL;
	public static final int UPDATE_MOUSE_CLICK_CHANNEL;
	
	
	public static final int UPDATE_MOTION_CAPTURE_RECORD;
	public static final int UPDATE_SUBJECT;
	public static final int UPDATE_RECORDED_SUBJECT_AT_SESSION;
	public static final int UPDATE_RECORDED_PARAMETER_SET;
	public static final int UPDATE_EEG_DEVICE;
	public static final int UPDATE_EYE_GAZE_DEVICE;
	public static final int UPDATE_HAND_GESTURE_DEVICE;
	public static final int UPDATE_KEYBOARD_HIT_DEVICE;
	public static final int UPDATE_MOUSE_CLICK_DEVICE;
	
	public static final int UPDATE_GENERAL_BIOMEDICAL_RESOURCE;
	
	public static final int UPDATE_MOTION_CAPTURE_DEVICE;
	public static final int UPDATE_RECORDED_MODALITY;
	public static final int UPDATE_BIOMEDICAL_RESOURCE;
	public static final int UPDATE_CHANNEL_LOCATIONS;
	public static final int UPDATE_EVENT_INSTANCE_FILE;

	public static final int UPDATE_ESS_1;
	public static final int UPDATE_ESS_2;
	//DESCRIBE
	public static final int DESCRIBE;

	static {
		System.out.println(StringConstants.LOAD_OPERATION_TRANSFER);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.TRANSFER_FILE);
		
		EXIT                               = xmlReader.getHex("/transfer/operation/EXIT/text()");
		
		QUERY_MODE_TYPE                  = xmlReader.getString("/transfer/operation/QUERY/MODE/TYPE/text()");
		QUERY_MODE_ID                    = xmlReader.getString("/transfer/operation/QUERY/MODE/ID/text()");
		QUERY_MODE_VALUE                 = xmlReader.getString("/transfer/operation/QUERY/MODE/VALUE/text()");
		
		QUERY_FUNCTION_FIELDS            = xmlReader.getString("/transfer/operation/QUERY/FUNCTION/FIELDS/text()");
		QUERY_FUNCTION_EXISTS            = xmlReader.getString("/transfer/operation/QUERY/FUNCTION/EXISTS/text()");
		QUERY_FUNCTION_NOT_EXISTS        = xmlReader.getString("/transfer/operation/QUERY/FUNCTION/NOT_EXISTS/text()");
		
		QUERY_ALL_ALL                                           = xmlReader.getHex("/transfer/operation/QUERY/ALL/ALL/ALL/text()");
		
		QUERY_STUDY_ALL                                         = xmlReader.getHex("/transfer/operation/QUERY/ALL/STUDY/ALL/text()");
		
		QUERY_SESSION_ALL                                       = xmlReader.getHex("/transfer/operation/QUERY/ALL/SESSION/ALL/text()");
		QUERY_SESSION_TASK_LABEL                                = xmlReader.getHex("/transfer/operation/QUERY/ALL/SESSION/TASK_LABEL/text()");
		QUERY_SESSION_PURPOSE                                   = xmlReader.getHex("/transfer/operation/QUERY/ALL/SESSION/PURPOSE/text()");
		
		QUERY_EEG_RECORD_ALL                                    = xmlReader.getHex("/transfer/operation/QUERY/EEG/RECORD/ALL/text()");
		QUERY_MOTION_CAPTURE_RECORD_ALL                         = xmlReader.getHex("/transfer/operation/QUERY/MOTION_CAPTURE/RECORD/ALL/text()");
		
		QUERY_SUBJECT_ALL                                       = xmlReader.getHex("/transfer/operation/QUERY/ALL/SUBJECT/ALL/text()");
		QUERY_SUBJECT_GENDER                                    = xmlReader.getHex("/transfer/operation/QUERY/ALL/SUBJECT/GENDER/text()");
		QUERY_SUBJECT_HANDEDNESS                                = xmlReader.getHex("/transfer/operation/QUERY/ALL/SUBJECT/HANDEDNESS/text()");
		
		QUERY_RECORDED_SUBJECT_AT_SESSION_ALL                   = xmlReader.getHex("/transfer/operation/QUERY/ALL/RECORDED_SUBJECT_AT_SESSION/ALL/text()");
		QUERY_RECORDED_SUBJECT_AT_SESSION_GROUP                 = xmlReader.getHex("/transfer/operation/QUERY/ALL/RECORDED_SUBJECT_AT_SESSION/GROUP/text()");
		QUERY_RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE = xmlReader.getHex("/transfer/operation/QUERY/ALL/RECORDED_SUBJECT_AT_SESSION/CHANNEL_LOCATION_TYPE/text()");
		
		QUERY_EEG_DEVICE_ALL                                    = xmlReader.getHex("/transfer/operation/QUERY/EEG/DEVICE/ALL/text()");
		QUERY_MOTION_CAPTURE_DEVICE_ALL                         = xmlReader.getHex("/transfer/operation/QUERY/MOTION_CAPTURE/DEVICE/ALL/text()");

		QUERY_RECORDED_MODALITY_ALL                             = xmlReader.getHex("/transfer/operation/QUERY/ALL/RECORDED_MODALITY/ALL/text()");
		QUERY_RECORDED_MODALITY_MODALITY_TYPE                   = xmlReader.getHex("/transfer/operation/QUERY/ALL/RECORDED_MODALITY/MODALITY_TYPE/text()");
		
		QUERY_BIOMEDICAL_RESOURCE_ALL                           = xmlReader.getHex("/transfer/operation/QUERY/ALL/BIOMEDICAL_RESOURCE/ALL/text()");
		
		QUERY_CHANNEL_LOCATIONS_ALL                             = xmlReader.getHex("/transfer/operation/QUERY/ALL/CHANNEL_LOCATIONS/ALL/text()");
		
		UPDATE_STUDY                       = xmlReader.getHex("/transfer/operation/UPDATE/ALL/STUDY/text()");
		UPDATE_SESSION                     = xmlReader.getHex("/transfer/operation/UPDATE/ALL/SESSION/text()");
		UPDATE_EEG_RECORD                  = xmlReader.getHex("/transfer/operation/UPDATE/EEG/RECORD/text()");
		
		UPDATE_EYE_GAZE_RECORD                  = xmlReader.getHex("/transfer/operation/UPDATE/EYE_GAZE/RECORD/text()");
		UPDATE_HAND_GESTURE_RECORD              = xmlReader.getHex("/transfer/operation/UPDATE/HAND_GESTURE/RECORD/text()");
		UPDATE_KEYBOARD_HIT_RECORD              = xmlReader.getHex("/transfer/operation/UPDATE/KEYBOARD_HIT/RECORD/text()");
		UPDATE_MOUSE_CLICK_RECORD               = xmlReader.getHex("/transfer/operation/UPDATE/MOUSE_CLICK/RECORD/text()");
		
		UPDATE_EYE_GAZE_CHANNEL               = xmlReader.getHex("/transfer/operation/UPDATE/EYE_GAZE/CHANNEL/text()");
		UPDATE_HAND_GESTURE_CHANNEL               = xmlReader.getHex("/transfer/operation/UPDATE/HAND_GESTURE/CHANNEL/text()");
		UPDATE_MOUSE_CLICK_CHANNEL               = xmlReader.getHex("/transfer/operation/UPDATE/MOUSE_CLICK/CHANNEL/text()");
		
		
		UPDATE_MOTION_CAPTURE_RECORD       = xmlReader.getHex("/transfer/operation/UPDATE/MOTION_CAPTURE/RECORD/text()");
		UPDATE_SUBJECT                     = xmlReader.getHex("/transfer/operation/UPDATE/ALL/SUBJECT/text()");
		UPDATE_RECORDED_SUBJECT_AT_SESSION = xmlReader.getHex("/transfer/operation/UPDATE/ALL/RECORDED_SUBJECT_AT_SESSION/text()");
		UPDATE_RECORDED_PARAMETER_SET      = xmlReader.getHex("/transfer/operation/UPDATE/ALL/RECORDED_PARAMETER_SET/text()");
		UPDATE_EEG_DEVICE                  = xmlReader.getHex("/transfer/operation/UPDATE/EEG/DEVICE/text()");
		
		UPDATE_EYE_GAZE_DEVICE                  = xmlReader.getHex("/transfer/operation/UPDATE/EYE_GAZE/DEVICE/text()");
		UPDATE_KEYBOARD_HIT_DEVICE              = xmlReader.getHex("/transfer/operation/UPDATE/KEYBOARD_HIT/DEVICE/text()");
		UPDATE_MOUSE_CLICK_DEVICE               = xmlReader.getHex("/transfer/operation/UPDATE/MOUSE_CLICK/DEVICE/text()");
		UPDATE_HAND_GESTURE_DEVICE              = xmlReader.getHex("/transfer/operation/UPDATE/HAND_GESTURE/DEVICE/text()");
		
		UPDATE_GENERAL_BIOMEDICAL_RESOURCE      = xmlReader.getHex("/transfer/operation/UPDATE/ALL/GENERAL_BIOMEDICAL_RESOURCE/text()");
		
		UPDATE_MOTION_CAPTURE_DEVICE       = xmlReader.getHex("/transfer/operation/UPDATE/MOTION_CAPTURE/DEVICE/text()");
		UPDATE_RECORDED_MODALITY           = xmlReader.getHex("/transfer/operation/UPDATE/ALL/RECORDED_MODALITY/text()");
		UPDATE_BIOMEDICAL_RESOURCE         = xmlReader.getHex("/transfer/operation/UPDATE/ALL/BIOMEDICAL_RESOURCE/text()");
		UPDATE_CHANNEL_LOCATIONS           = xmlReader.getHex("/transfer/operation/UPDATE/ALL/CHANNEL_LOCATIONS/text()");
		UPDATE_EVENT_INSTANCE_FILE         = xmlReader.getHex("/transfer/operation/UPDATE/ALL/EVENT_INSTANCE_FILE/text()");

		UPDATE_ESS_1                       = xmlReader.getHex("/transfer/operation/UPDATE/ALL/ESS_1/text()");
		UPDATE_ESS_2                       = xmlReader.getHex("/transfer/operation/UPDATE/ALL/ESS_2/text()");
	
		DESCRIBE                           = xmlReader.getHex("/transfer/operation/UPDATE/DESCRIBE/ALL/text()");

	}
	
	public static void printValue() {
		System.out.println(StringConstants.OPERATION_TRANSFER_VALUE);

		PrintMessage.showValue("EXIT", EXIT);
		System.out.println();
		
		PrintMessage.showValue("QUERY_MODE_TYPE", QUERY_MODE_TYPE);
		PrintMessage.showValue("QUERY_MODE_SEARCH_ID", QUERY_MODE_ID);
		PrintMessage.showValue("QUERY_MODE_SEARCH_VALUE", QUERY_MODE_VALUE);
		System.out.println();
		
		PrintMessage.showValue("QUERY_FUNCTION_FIELDS", QUERY_FUNCTION_FIELDS);
		PrintMessage.showValue("QUERY_FUNCTION_EXISTS", QUERY_FUNCTION_EXISTS);
		PrintMessage.showValue("QUERY_FUNCTION_NOT_EXISTS", QUERY_FUNCTION_NOT_EXISTS);
		System.out.println();

		PrintMessage.showValue("QUERY_ALL_ALL", QUERY_ALL_ALL);
		PrintMessage.showValue("QUERY_SESSION_ALL", QUERY_SESSION_ALL);
		PrintMessage.showValue("QUERY_SESSION_TASK_LABEL", QUERY_SESSION_TASK_LABEL);
		PrintMessage.showValue("QUERY_SESSION_PURPOSE", QUERY_SESSION_PURPOSE);
		System.out.println();
		
		PrintMessage.showValue("QUERY_EEG_RECORD_ALL", QUERY_EEG_RECORD_ALL);
		PrintMessage.showValue("QUERY_MOTION_CAPTURE_RECORD_ALL", QUERY_MOTION_CAPTURE_RECORD_ALL);
		System.out.println();
		
		PrintMessage.showValue("QUERY_SUBJECT_ALL", QUERY_SUBJECT_ALL);
		PrintMessage.showValue("QUERY_SUBJECT_GENDER", QUERY_SUBJECT_GENDER);
		PrintMessage.showValue("QUERY_SUBJECT_HANDEDNESS", QUERY_SUBJECT_HANDEDNESS);
		System.out.println();
		
		PrintMessage.showValue("QUERY_RECORDED_SUBJECT_AT_SESSION_ALL", QUERY_RECORDED_SUBJECT_AT_SESSION_ALL);
		PrintMessage.showValue("QUERY_RECORDED_SUBJECT_AT_SESSION_GROUP", QUERY_RECORDED_SUBJECT_AT_SESSION_GROUP);
		PrintMessage.showValue("QUERY_RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE", QUERY_RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		System.out.println();
		
		PrintMessage.showValue("QUERY_DEVICE_EEG_ALL", QUERY_EEG_DEVICE_ALL);
		PrintMessage.showValue("QUERY_DEVICE_MOTION_CAPTURE_ALL", QUERY_MOTION_CAPTURE_DEVICE_ALL);
		System.out.println();
		
		PrintMessage.showValue("QUERY_RECORDED_MODALITY_ALL", QUERY_RECORDED_MODALITY_ALL);
		PrintMessage.showValue("QUERY_RECORDED_MODALITY_MODALITY_TYPE", QUERY_RECORDED_MODALITY_MODALITY_TYPE);
		System.out.println();
		
		PrintMessage.showValue("QUERY_BIOMEDICAL_RESOURCE_ALL", QUERY_BIOMEDICAL_RESOURCE_ALL);
		System.out.println();
		
		PrintMessage.showValue("QUERY_CHANNEL_LOCATIONS_ALL", QUERY_CHANNEL_LOCATIONS_ALL);
		System.out.println();

		PrintMessage.showValue("UPDATE_STUDY", UPDATE_STUDY);
		PrintMessage.showValue("UPDATE_SESSION", UPDATE_SESSION);
		PrintMessage.showValue("UPDATE_EEG_RECORD", UPDATE_EEG_RECORD);
		PrintMessage.showValue("UPDATE_MOTION_CAPTURE_RECORD", UPDATE_MOTION_CAPTURE_RECORD);
		PrintMessage.showValue("UPDATE_SUBJECT", UPDATE_SUBJECT);
		PrintMessage.showValue("UPDATE_RECORDED_SUBJECT_AT_SESSION", UPDATE_RECORDED_SUBJECT_AT_SESSION);
		PrintMessage.showValue("UPDATE_RECORDED_PARAMETER_SET", UPDATE_RECORDED_PARAMETER_SET);
		PrintMessage.showValue("UPDATE_EEG_DEVICE", UPDATE_EEG_DEVICE);
		PrintMessage.showValue("UPDATE_MOTION_CAPTURE_DEVICE", UPDATE_MOTION_CAPTURE_DEVICE);
		PrintMessage.showValue("UPDATE_RECORDED_MODALITY", UPDATE_RECORDED_MODALITY);
		PrintMessage.showValue("UPDATE_BIOMEDICAL_RESOURCE", UPDATE_BIOMEDICAL_RESOURCE);
		PrintMessage.showValue("UPDATE_CHANNEL_LOCATIONS", UPDATE_CHANNEL_LOCATIONS);
		PrintMessage.showValue("UPDATE_EVENT_INSTANCE_FILE", UPDATE_EVENT_INSTANCE_FILE);
		System.out.println();

		PrintMessage.showValue("UPDATE_ESS_1", UPDATE_ESS_1);
		PrintMessage.showValue("UPDATE_ESS_2", UPDATE_ESS_2);
		System.out.println();

	}
}
