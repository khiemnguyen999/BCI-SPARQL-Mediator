package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class IdConstants {

	public static final String STUDY;
	public static final String SESSION;
	public static final String EEG_RECORD;
	
	public static final String HAND_GESTURE_RECORD;
	public static final String EYE_GAZE_RECORD;
	public static final String KEYBOARD_HIT_RECORD;
	public static final String MOUSE_CLICK_RECORD;
	
	public static final String MOTION_CAPTURE_RECORD;
	public static final String SUBJECT;
	public static final String RECORDED_SUBJECT_AT_SESSION;
	public static final String EEG_DEVICE;
	
	public static final String HAND_GESTURE_DEVICE;
	public static final String EYE_GAZE_DEVICE;
	public static final String KEYBOARD_HIT_DEVICE;
	public static final String MOUSE_CLICK_DEVICE;
	
	public static final String HAND_GESTURE_CHANNEL;
	public static final String EYE_GAZE_CHANNEL;
	public static final String MOUSE_CLICK_CHANNEL;
	
	public static final String MOTION_CAPTURE_DEVICE;
	public static final String RECORDED_PARAMETER_SET;
	public static final String RECORDED_MODALITY;
	public static final String BIOMEDICAL_RESOURCE;
	public static final String CHANNEL_LOCATIONS;
	public static final String EVENT_INSTANCE_FILE;
	public static final String ACCESS_METHOD_HTTP;

	public static final String MEASUREMENT_CAPABILITY;
	public static final String MEASUREMENT_PROPERTY;

	static {
		System.out.println(StringConstants.LOAD_ONTOLOGY_ID_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		STUDY                             = xmlReader.getString("/constant/ontology/id/STUDY/text()");
		SESSION                           = xmlReader.getString("/constant/ontology/id/SESSION/text()");
		EEG_RECORD                        = xmlReader.getString("/constant/ontology/id/EEG_RECORD/text()");
		
		EYE_GAZE_RECORD					  = xmlReader.getString("/constant/ontology/id/EYE_GAZE_RECORD/text()");
		HAND_GESTURE_RECORD				  = xmlReader.getString("/constant/ontology/id/HAND_GESTURE_RECORD/text()");
		KEYBOARD_HIT_RECORD				  = xmlReader.getString("/constant/ontology/id/KEYBOARD_HIT_RECORD/text()");
		MOUSE_CLICK_RECORD				  = xmlReader.getString("/constant/ontology/id/MOUSE_CLICK_RECORD/text()");
		
		EYE_GAZE_DEVICE					  = xmlReader.getString("/constant/ontology/id/EYE_GAZE_DEVICE/text()");
		HAND_GESTURE_DEVICE				  = xmlReader.getString("/constant/ontology/id/HAND_GESTURE_DEVICE/text()");
		KEYBOARD_HIT_DEVICE				  = xmlReader.getString("/constant/ontology/id/KEYBOARD_HIT_DEVICE/text()");
		MOUSE_CLICK_DEVICE				  = xmlReader.getString("/constant/ontology/id/MOUSE_CLICK_DEVICE/text()");

		EYE_GAZE_CHANNEL				  = xmlReader.getString("/constant/ontology/id/EYE_GAZE_CHANNEL/text()");
		HAND_GESTURE_CHANNEL			  = xmlReader.getString("/constant/ontology/id/HAND_GESTURE_CHANNEL/text()");
		MOUSE_CLICK_CHANNEL				  = xmlReader.getString("/constant/ontology/id/MOUSE_CLICK_CHANNEL/text()");

		
		MOTION_CAPTURE_RECORD             = xmlReader.getString("/constant/ontology/id/MOTION_CAPTURE_RECORD/text()");
		SUBJECT                           = xmlReader.getString("/constant/ontology/id/SUBJECT/text()");
		RECORDED_SUBJECT_AT_SESSION       = xmlReader.getString("/constant/ontology/id/RECORDED_SUBJECT_AT_SESSION/text()");
		EEG_DEVICE                        = xmlReader.getString("/constant/ontology/id/EEG_DEVICE/text()");
		MOTION_CAPTURE_DEVICE             = xmlReader.getString("/constant/ontology/id/MOTION_CAPTURE_DEVICE/text()");
		RECORDED_PARAMETER_SET            = xmlReader.getString("/constant/ontology/id/RECORDED_PARAMETER_SET/text()");
		RECORDED_MODALITY                 = xmlReader.getString("/constant/ontology/id/RECORDED_MODALITY/text()");
		BIOMEDICAL_RESOURCE               = xmlReader.getString("/constant/ontology/id/BIOMEDICAL_RESOURCE/text()");
		CHANNEL_LOCATIONS                 = xmlReader.getString("/constant/ontology/id/CHANNEL_LOCATIONS/text()");
		EVENT_INSTANCE_FILE               = xmlReader.getString("/constant/ontology/id/EVENT_INSTANCE_FILE/text()");
		ACCESS_METHOD_HTTP                = xmlReader.getString("/constant/ontology/id/ACCESS_METHOD_HTTP/text()");

		MEASUREMENT_CAPABILITY            = xmlReader.getString("/constant/ontology/id/MEASUREMENT_CAPABILITY/text()");
		MEASUREMENT_PROPERTY              = xmlReader.getString("/constant/ontology/id/MEASUREMENT_PROPERTY/text()");
	}
	
	public static void printValue() {
		System.out.println(StringConstants.ONTOLOGY_ID_CONSTANT_VALUE);

		PrintMessage.showValue("STUDY", STUDY);
		PrintMessage.showValue("SESSION", SESSION);
		PrintMessage.showValue("EEG_RECORD", EEG_RECORD);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD", MOTION_CAPTURE_RECORD);
		PrintMessage.showValue("SUBJECT", SUBJECT);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION", RECORDED_SUBJECT_AT_SESSION);
		PrintMessage.showValue("EEG_DEVICE", EEG_DEVICE);
		PrintMessage.showValue("MOTION_CAPTURE_DEVICE", MOTION_CAPTURE_DEVICE);
		PrintMessage.showValue("RECORDED_PARAMETER_SET", RECORDED_PARAMETER_SET);
		PrintMessage.showValue("RECORDED_MODALITY", RECORDED_MODALITY);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE", BIOMEDICAL_RESOURCE);
		PrintMessage.showValue("CHANNEL_LOCATIONS", CHANNEL_LOCATIONS);
		PrintMessage.showValue("EVENT_INSTANCE_FILE", EVENT_INSTANCE_FILE);
		PrintMessage.showValue("ACCESS_METHOD_HTTP", ACCESS_METHOD_HTTP);
		System.out.println();
		
		PrintMessage.showValue("MEASUREMENT_CAPABILITY", MEASUREMENT_CAPABILITY);
		PrintMessage.showValue("MEASUREMENT_PROPERTY", MEASUREMENT_PROPERTY);
		System.out.println();
	}

}
