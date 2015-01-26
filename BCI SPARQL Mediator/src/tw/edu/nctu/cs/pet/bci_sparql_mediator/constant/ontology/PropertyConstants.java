package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class PropertyConstants {

	public static final String BCI_HAS_TITLE;
	public static final String BCI_HAS_PURPOSE;
	public static final String BCI_HAS_UUID;
	public static final String BCI_HAS_ROOT_URI;
	public static final String BCI_HAS_START_TIME;
	public static final String BCI_HAS_END_TIME;
	public static final String BCI_HAS_SESSION;

	public static final String BCI_HAS_ID_NUMBER;
	public static final String BCI_HAS_TASK_LABEL;
	public static final String BCI_HAS_SESSION_LAB_ID;
	public static final String BCI_HAS_RECORD;
	public static final String BCI_IS_CHANNEL_OF;
	
	public static final String BCI_HAS_EYE_GAZE_CHANNEL_DATA;
	public static final String BCI_HAS_HAND_GESTURE_CHANNEL_DATA;
	public static final String BCI_HAS_MOUSE_CLICK_CHANNEL_DATA;
	
	public static final String BCI_IS_EYE_GAZE_CHANNEL_DATA_OF;
	public static final String BCI_IS_HAND_GESTURE_CHANNEL_DATA_OF;
	public static final String BCI_IS_MOUSE_CLICK_CHANNEL_DATA_OF;
	
	public static final String BCI_HAS_EYE_GAZE_CHANNEL_TYPE;
	public static final String BCI_HAS_HAND_GESTURE_CHANNEL_TYPE;
	public static final String BCI_HAS_MOUSE_CLICK_CHANNEL_TYPE;
	
	public static final String BCI_HAS_EYE_GAZE_CHANNEL_REFERS_TO;
	public static final String BCI_HAS_EYE_GAZE_CHANNEL_UNIT;
	public static final String BCI_HAS_HAND_GESTURE_CHANNEL_UNIT;
	public static final String BCI_HAS_MOUSE_CLICK_CHANNEL_BUTTON;
	
	public static final String BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION;

	public static final String BCI_IS_SESSION_OF;
	public static final String BCI_IS_RECORD_OF;
	public static final String BCI_IS_FROM_SUBJECT;
	
	public static final String BCI_IS_GENERATED_BY_EEG_DEVICE;
	public static final String BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE;
	public static final String BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE;
	public static final String BCI_IS_GENERATED_BY_KEYBOARD_HIT_DEVICE;
	public static final String BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE;
	
	
	public static final String BCI_IS_GENERATED_BY_MOTION_CAPTURE_DEVICE;
	public static final String BCI_HAS_RECORDED_MODALITY;
	public static final String BCI_HAS_RECORDED_PARAMETER_SET;

	public static final String BCI_HAS_DATA_SET;
	public static final String BCI_HAS_GENDER;
	public static final String BCI_HAS_YEAR_OF_BIRTH;
	public static final String BCI_HAS_HANDEDNESS;

	public static final String BCI_HAS_LAB_ID;
	public static final String BCI_HAS_IN_SESSION_NUMBER;
	public static final String BCI_HAS_GROUP;
	public static final String BCI_HAS_AGE;
	public static final String BCI_HAS_VISION;
	public static final String BCI_HAS_HEARING;
	public static final String BCI_HAS_HEIGHT;
	public static final String BCI_HAS_WEIGHT;
	public static final String BCI_HAS_MEDICATION_CAFFEINE;
	public static final String BCI_HAS_MEDICATION_ALCOHOL;
	public static final String BCI_HAS_CHANNEL_LOCATION_TYPE;
	public static final String BCI_HAS_EEG_CHANNEL_LOCATION_TYPE;
	
	public static final String BCI_HAS_RESOURCE;
	public static final String BCI_HAS_BIOMEDICAL_RESOURCE;

	public static final String BCI_IS_GENERATED_BY;
	public static final String BCI_IS_USED_FOR_GENERATE_RECORD;
	
	public static final String BCI_IS_USED_FOR_GENERATE_EEG_RECORD;
	public static final String BCI_IS_USED_FOR_GENERATE_EYE_GAZE_RECORD;
	public static final String BCI_IS_USED_FOR_GENERATE_HAND_GESTURE_RECORD;
	public static final String BCI_IS_USED_FOR_GENERATE_KEYBOARD_HIT_RECORD;
	public static final String BCI_IS_USED_FOR_GENERATE_MOUSE_CLICK_RECORD;
	
	public static final String BCI_IS_USED_FOR_GENERATE_MOTION_CAPTURE_RECORD;

	public static final String BCI_HAS_HARDWARE_MANUFACTURER;
	public static final String SSN_HAS_MEASUREMENT_CAPABILITY;
	public static final String SSN_HAS_MEASUREMENT_PROPERTY;
	public static final String BCI_HAS_NUMBER_OF_CHANNELS;
	public static final String BCI_HAS_CHANNEL_FORMAT;
	public static final String BCI_HAS_SAMPLING_RATE;

	public static final String BCI_HAS_MODALITY_TYPE;
	public static final String BCI_HAS_MODALITY_SIGNAL_TYPE;
	public static final String BCI_HAS_START_CHANNEL;
	public static final String BCI_HAS_END_CHANNEL;
	public static final String BCI_HAS_REFERENCE_LOCATION;
	public static final String BCI_HAS_REFERENCE_LABEL;
	public static final String BCI_HAS_CHANNEL_LABEL;

	public static final String BCI_IS_USED_FOR;
	public static final String BCI_IS_BIOMEDICAL_RESOURCE_OF;
	public static final String BCI_HAS_ACCESS_METHOD;

	public static final String BCI_HAS_URL;
	
	public static final String OWL_ONE_OF;

	public static final String RDF_FIRST;
	public static final String RDF_REST;

	public static final String RDFS_RANGE;

	static {
		System.out.println(StringConstants.LOAD_ONTOLOGY_PROPERTY_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		BCI_HAS_TITLE                          = xmlReader.getString("/constant/ontology/property/BCI/HAS_TITLE/text()");
		BCI_HAS_PURPOSE                        = xmlReader.getString("/constant/ontology/property/BCI/HAS_PURPOSE/text()");
		BCI_HAS_UUID                           = xmlReader.getString("/constant/ontology/property/BCI/HAS_UUID/text()");
		BCI_HAS_ROOT_URI                       = xmlReader.getString("/constant/ontology/property/BCI/HAS_ROOT_URI/text()");
		BCI_HAS_START_TIME                     = xmlReader.getString("/constant/ontology/property/BCI/HAS_START_TIME/text()");
		BCI_HAS_END_TIME                       = xmlReader.getString("/constant/ontology/property/BCI/HAS_END_TIME/text()");
		BCI_HAS_SESSION                        = xmlReader.getString("/constant/ontology/property/BCI/HAS_SESSION/text()");

		BCI_HAS_ID_NUMBER                      = xmlReader.getString("/constant/ontology/property/BCI/HAS_ID_NUMBER/text()");
		BCI_HAS_TASK_LABEL                     = xmlReader.getString("/constant/ontology/property/BCI/HAS_TASK_LABEL/text()");
		BCI_HAS_SESSION_LAB_ID                 = xmlReader.getString("/constant/ontology/property/BCI/HAS_SESSION_LAB_ID/text()");
		BCI_HAS_RECORD                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_RECORD/text()");
		BCI_IS_CHANNEL_OF                      = xmlReader.getString("/constant/ontology/property/BCI/IS_CHANNEL_OF/text()");
		
		BCI_HAS_EYE_GAZE_CHANNEL_DATA                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_EYE_GAZE_CHANNEL_DATA/text()");
		BCI_HAS_HAND_GESTURE_CHANNEL_DATA                     = xmlReader.getString("/constant/ontology/property/BCI/HAS_HAND_GESTURE_CHANNEL_DATA/text()");
		BCI_HAS_MOUSE_CLICK_CHANNEL_DATA                      = xmlReader.getString("/constant/ontology/property/BCI/HAS_MOUSE_CLICK_CHANNEL_DATA/text()");
		
		BCI_IS_EYE_GAZE_CHANNEL_DATA_OF                         = xmlReader.getString("/constant/ontology/property/BCI/IS_EYE_GAZE_CHANNEL_DATA_OF/text()");
		BCI_IS_HAND_GESTURE_CHANNEL_DATA_OF                     = xmlReader.getString("/constant/ontology/property/BCI/IS_HAND_GESTURE_CHANNEL_DATA_OF/text()");
		BCI_IS_MOUSE_CLICK_CHANNEL_DATA_OF                      = xmlReader.getString("/constant/ontology/property/BCI/IS_MOUSE_CLICK_CHANNEL_DATA_OF/text()");

		BCI_HAS_EYE_GAZE_CHANNEL_TYPE							= xmlReader.getString("/constant/ontology/property/BCI/HAS_EYE_GAZE_CHANNEL_TYPE/text()");				
		BCI_HAS_HAND_GESTURE_CHANNEL_TYPE						= xmlReader.getString("/constant/ontology/property/BCI/HAS_HAND_GESTURE_CHANNEL_TYPE/text()");
		BCI_HAS_MOUSE_CLICK_CHANNEL_TYPE						= xmlReader.getString("/constant/ontology/property/BCI/HAS_MOUSE_CLICK_CHANNEL_TYPE/text()");
		
		BCI_HAS_EYE_GAZE_CHANNEL_UNIT						= xmlReader.getString("/constant/ontology/property/BCI/HAS_EYE_GAZE_CHANNEL_UNIT/text()");
		BCI_HAS_HAND_GESTURE_CHANNEL_UNIT					= xmlReader.getString("/constant/ontology/property/BCI/HAS_HAND_GESTURE_CHANNEL_UNIT/text()");
		
		BCI_HAS_EYE_GAZE_CHANNEL_REFERS_TO					= xmlReader.getString("/constant/ontology/property/BCI/HAS_EYE_GAZE_CHANNEL_REFERS_TO/text()");
		BCI_HAS_MOUSE_CLICK_CHANNEL_BUTTON					= xmlReader.getString("/constant/ontology/property/BCI/HAS_MOUSE_CLICK_CHANNEL_BUTTON/text()");



	
		
		
		
		
		BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION = xmlReader.getString("/constant/ontology/property/BCI/HAS_RECORDED_SPECS_SUBJECT_SESSION/text()");

		BCI_IS_SESSION_OF                         = xmlReader.getString("/constant/ontology/property/BCI/IS_SESSION_OF/text()");
		BCI_IS_RECORD_OF                          = xmlReader.getString("/constant/ontology/property/BCI/IS_RECORD_OF/text()");
		BCI_IS_FROM_SUBJECT                       = xmlReader.getString("/constant/ontology/property/BCI/IS_FROM_SUBJECT/text()");
		BCI_IS_GENERATED_BY_EEG_DEVICE            = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_EEG_DEVICE/text()");
		BCI_IS_GENERATED_BY_MOTION_CAPTURE_DEVICE = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_MOTION_CAPTURE_DEVICE/text()");
		
		BCI_IS_GENERATED_BY_EYE_GAZE_DEVICE	      = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_EYE_GAZE_DEVICE/text()");
		BCI_IS_GENERATED_BY_HAND_GESTURE_DEVICE   = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_HAND_GESTURE_DEVICE/text()");
		BCI_IS_GENERATED_BY_KEYBOARD_HIT_DEVICE   = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_KEYBOARD_HIT_DEVICE/text()");
		BCI_IS_GENERATED_BY_MOUSE_CLICK_DEVICE   = xmlReader.getString("/constant/ontology/property/BCI/IS_GENERATED_BY_MOUSE_CLICK_DEVICE/text()");

		
		BCI_HAS_RECORDED_MODALITY                 = xmlReader.getString("/constant/ontology/property/BCI/HAS_RECORDED_MODALITY/text()");
		BCI_HAS_RECORDED_PARAMETER_SET            = xmlReader.getString("/constant/ontology/property/BCI/HAS_RECORDED_PARAMETER_SET/text()");

		BCI_HAS_DATA_SET                       = xmlReader.getString("/constant/ontology/property/BCI/HAS_DATA_SET/text()");
		BCI_HAS_GENDER                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_GENDER/text()");
		BCI_HAS_YEAR_OF_BIRTH                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_YEAR_OF_BIRTH/text()");
		BCI_HAS_HANDEDNESS                     = xmlReader.getString("/constant/ontology/property/BCI/HAS_HANDEDNESS/text()");

		BCI_HAS_LAB_ID                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_LAB_ID/text()");
		BCI_HAS_IN_SESSION_NUMBER              = xmlReader.getString("/constant/ontology/property/BCI/HAS_IN_SESSION_NUMBER/text()");
		BCI_HAS_GROUP                          = xmlReader.getString("/constant/ontology/property/BCI/HAS_GROUP/text()");
		BCI_HAS_AGE                            = xmlReader.getString("/constant/ontology/property/BCI/HAS_AGE/text()");
		BCI_HAS_VISION                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_VISION/text()");
		BCI_HAS_HEARING                        = xmlReader.getString("/constant/ontology/property/BCI/HAS_HEARING/text()");
		BCI_HAS_HEIGHT                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_HEIGHT/text()");
		BCI_HAS_WEIGHT                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_WEIGHT/text()");
		BCI_HAS_MEDICATION_CAFFEINE            = xmlReader.getString("/constant/ontology/property/BCI/HAS_MEDICATION_CAFFEINE/text()");
		BCI_HAS_MEDICATION_ALCOHOL             = xmlReader.getString("/constant/ontology/property/BCI/HAS_MEDICATION_ALCOHOL/text()");
		BCI_HAS_CHANNEL_LOCATION_TYPE          = xmlReader.getString("/constant/ontology/property/BCI/HAS_CHANNEL_LOCATION_TYPE/text()");
		BCI_HAS_EEG_CHANNEL_LOCATION_TYPE          = xmlReader.getString("/constant/ontology/property/BCI/HAS_EEG_CHANNEL_LOCATION_TYPE/text()");
		
		BCI_HAS_RESOURCE                       = xmlReader.getString("/constant/ontology/property/BCI/HAS_RESOURCE/text()");
		BCI_HAS_BIOMEDICAL_RESOURCE            = xmlReader.getString("/constant/ontology/property/BCI/HAS_BIOMEDICAL_RESOURCE/text()");

		BCI_IS_USED_FOR_GENERATE_RECORD        = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_RECORD/text()");
		BCI_IS_GENERATED_BY                    = xmlReader.getString("/constant/ontology/property/BCI/BCI_IS_GENERATED_BY/text()");

		BCI_IS_USED_FOR_GENERATE_EEG_RECORD            = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_EEG_RECORD/text()");
		BCI_IS_USED_FOR_GENERATE_EYE_GAZE_RECORD	   = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_EYE_GAZE_RECORD/text()");
		BCI_IS_USED_FOR_GENERATE_HAND_GESTURE_RECORD	   = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_HAND_GESTURE/text()");
		BCI_IS_USED_FOR_GENERATE_KEYBOARD_HIT_RECORD	   = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_KEYBOARD_HIT/text()");
		BCI_IS_USED_FOR_GENERATE_MOUSE_CLICK_RECORD	   = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_MOUSE_CLICK/text()");
		
		BCI_IS_USED_FOR_GENERATE_MOTION_CAPTURE_RECORD = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR_GENERATE_MOTION_CAPTURE_RECORD/text()");

		BCI_HAS_HARDWARE_MANUFACTURER                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_HARDWARE_MANUFACTURER/text()");
		SSN_HAS_MEASUREMENT_CAPABILITY                 = xmlReader.getString("/constant/ontology/property/SSN/HAS_MEASUREMENT_CAPABILITY/text()");
		SSN_HAS_MEASUREMENT_PROPERTY                   = xmlReader.getString("/constant/ontology/property/SSN/HAS_MEASUREMENT_PROPERTY/text()");
		BCI_HAS_NUMBER_OF_CHANNELS                     = xmlReader.getString("/constant/ontology/property/BCI/HAS_NUMBER_OF_CHANNELS/text()");
		BCI_HAS_CHANNEL_FORMAT                         = xmlReader.getString("/constant/ontology/property/BCI/HAS_CHANNEL_FORMAT/text()");

		
		BCI_HAS_SAMPLING_RATE                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_SAMPLING_RATE/text()");

		BCI_HAS_MODALITY_TYPE                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_MODALITY_TYPE/text()");
		BCI_HAS_MODALITY_SIGNAL_TYPE           = xmlReader.getString("/constant/ontology/property/BCI/HAS_MODALITY_SIGNAL_TYPE/text()");

		BCI_HAS_START_CHANNEL                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_START_CHANNEL/text()");
		BCI_HAS_END_CHANNEL                    = xmlReader.getString("/constant/ontology/property/BCI/HAS_END_CHANNEL/text()");
		BCI_HAS_REFERENCE_LOCATION             = xmlReader.getString("/constant/ontology/property/BCI/HAS_REFERENCE_LOCATION/text()");
		BCI_HAS_REFERENCE_LABEL                = xmlReader.getString("/constant/ontology/property/BCI/HAS_REFERENCE_LABEL/text()");
		BCI_HAS_CHANNEL_LABEL                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_CHANNEL_LABEL/text()");

		BCI_IS_USED_FOR                        = xmlReader.getString("/constant/ontology/property/BCI/IS_USED_FOR/text()");
		BCI_IS_BIOMEDICAL_RESOURCE_OF          = xmlReader.getString("/constant/ontology/property/BCI/IS_BIOMEDICAL_RESOURCE_OF/text()");
		BCI_HAS_ACCESS_METHOD                  = xmlReader.getString("/constant/ontology/property/BCI/HAS_ACCESS_METHOD/text()");

		BCI_HAS_URL                            = xmlReader.getString("/constant/ontology/property/BCI/HAS_URL/text()");
		
		OWL_ONE_OF                             = xmlReader.getString("/constant/ontology/property/OWL/ONE_OF/text()");

		RDF_FIRST                              = xmlReader.getString("/constant/ontology/property/RDF/FIRST/text()");
		RDF_REST                               = xmlReader.getString("/constant/ontology/property/RDF/REST/text()");

		RDFS_RANGE                             = xmlReader.getString("/constant/ontology/property/RDFS/RANGE/text()");
		
	}
	
	public static void printValue() {
		System.out.println(StringConstants.ONTOLOGY_PROPERTY_CONSTANT_VALUE);

		PrintMessage.showValue("BCI_HAS_TITLE", BCI_HAS_TITLE);
		PrintMessage.showValue("BCI_HAS_PURPOSE", BCI_HAS_PURPOSE);
		PrintMessage.showValue("BCI_HAS_UUID", BCI_HAS_UUID);
		PrintMessage.showValue("BCI_HAS_ROOT_URI", BCI_HAS_ROOT_URI);
		PrintMessage.showValue("BCI_HAS_START_TIME", BCI_HAS_START_TIME);
		PrintMessage.showValue("BCI_HAS_END_TIME", BCI_HAS_END_TIME);
		PrintMessage.showValue("BCI_HAS_SESSION", BCI_HAS_SESSION);
		System.out.println();

		PrintMessage.showValue("BCI_HAS_ID_NUMBER", BCI_HAS_ID_NUMBER);
		PrintMessage.showValue("BCI_HAS_TASK_LABEL", BCI_HAS_TASK_LABEL);
		PrintMessage.showValue("BCI_HAS_SESSION_LAB_ID", BCI_HAS_SESSION_LAB_ID);
		PrintMessage.showValue("BCI_HAS_RECORD", BCI_HAS_RECORD);
		PrintMessage.showValue("BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION", BCI_HAS_RECORDED_SPECS_SUBJECT_SESSION);
		System.out.println();

		PrintMessage.showValue("BCI_IS_FROM_SUBJECT", BCI_IS_FROM_SUBJECT);
		PrintMessage.showValue("BCI_IS_GENERATED_BY_EEG_DEVICE", BCI_IS_GENERATED_BY_EEG_DEVICE);
		PrintMessage.showValue("BCI_HAS_RECORDED_MODALITY", BCI_HAS_RECORDED_MODALITY);
		PrintMessage.showValue("BCI_HAS_RECORDED_PARAMETER_SET", BCI_HAS_RECORDED_PARAMETER_SET);
		System.out.println();
		
		PrintMessage.showValue("BCI_HAS_GENDER", BCI_HAS_GENDER);
		PrintMessage.showValue("BCI_HAS_YEAR_OF_BIRTH", BCI_HAS_YEAR_OF_BIRTH);
		PrintMessage.showValue("BCI_HAS_HANDEDNESS", BCI_HAS_HANDEDNESS);
		System.out.println();
		
		PrintMessage.showValue("BCI_HAS_LAB_ID", BCI_HAS_LAB_ID);
		PrintMessage.showValue("BCI_HAS_IN_SESSION_NUMBER", BCI_HAS_IN_SESSION_NUMBER);
		PrintMessage.showValue("BCI_HAS_GROUP", BCI_HAS_GROUP);
		PrintMessage.showValue("BCI_HAS_AGE", BCI_HAS_AGE);
		PrintMessage.showValue("BCI_HAS_VISION", BCI_HAS_VISION);
		PrintMessage.showValue("BCI_HAS_HEARING", BCI_HAS_HEARING);
		PrintMessage.showValue("BCI_HAS_HEIGHT", BCI_HAS_HEIGHT);
		PrintMessage.showValue("BCI_HAS_WEIGHT", BCI_HAS_WEIGHT);
		PrintMessage.showValue("BCI_HAS_MEDICATION_CAFFEINE", BCI_HAS_MEDICATION_CAFFEINE);
		PrintMessage.showValue("BCI_HAS_MEDICATION_ALCOHOL", BCI_HAS_MEDICATION_ALCOHOL);
		PrintMessage.showValue("BCI_HAS_CHANNEL_LOCATION_TYPE", BCI_HAS_CHANNEL_LOCATION_TYPE);
		System.out.println();

		PrintMessage.showValue("BCI_HAS_RESOURCE", BCI_HAS_RESOURCE);
		PrintMessage.showValue("BCI_HAS_BIOMEDICAL_RESOURCE", BCI_HAS_BIOMEDICAL_RESOURCE);

		PrintMessage.showValue("BCI_IS_USED_FOR_GENERATE_EEG_RECORD", BCI_IS_USED_FOR_GENERATE_EEG_RECORD);

		PrintMessage.showValue("BCI_HAS_HARDWARE_MANUFACTURER", BCI_HAS_HARDWARE_MANUFACTURER);
		PrintMessage.showValue("SSN_HAS_MEASUREMENT_CAPABILITY", SSN_HAS_MEASUREMENT_CAPABILITY);
		PrintMessage.showValue("SSN_HAS_MEASUREMENT_PROPERTY", SSN_HAS_MEASUREMENT_PROPERTY);
		PrintMessage.showValue("BCI_HAS_NUMBER_OF_CHANNELS", BCI_HAS_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("BCI_HAS_SAMPLING_RATE", BCI_HAS_SAMPLING_RATE);
		System.out.println();
		
		PrintMessage.showValue("BCI_HAS_MODALITY_TYPE", BCI_HAS_MODALITY_TYPE);
		PrintMessage.showValue("BCI_HAS_START_CHANNEL", BCI_HAS_START_CHANNEL);
		PrintMessage.showValue("BCI_HAS_END_CHANNEL", BCI_HAS_END_CHANNEL);
		PrintMessage.showValue("BCI_HAS_REFERENCE_LOCATION", BCI_HAS_REFERENCE_LOCATION);
		PrintMessage.showValue("BCI_HAS_REFERENCE_LABEL", BCI_HAS_REFERENCE_LABEL);
		PrintMessage.showValue("BCI_HAS_CHANNEL_LABEL", BCI_HAS_CHANNEL_LABEL);
		System.out.println();

		PrintMessage.showValue("BCI_IS_USED_FOR", BCI_IS_USED_FOR);
		PrintMessage.showValue("BCI_IS_BIOMEDICAL_RESOURCE_OF", BCI_IS_BIOMEDICAL_RESOURCE_OF);
		PrintMessage.showValue("BCI_HAS_ACCESS_METHOD", BCI_HAS_ACCESS_METHOD);
		System.out.println();
		
		PrintMessage.showValue("BCI_HAS_URL", BCI_HAS_URL);
		System.out.println();
		
		PrintMessage.showValue("OWL_ONE_OF", OWL_ONE_OF);
		System.out.println();
		
		PrintMessage.showValue("RDF_FIRST", RDF_FIRST);
		PrintMessage.showValue("RDF_REST", RDF_REST);
		System.out.println();
		
		PrintMessage.showValue("RDFS_RANGE", RDFS_RANGE);
		System.out.println();
	}

}
