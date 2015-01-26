package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.sparql;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class SparqlVariableConstants {

	public static final int OFFSET;

	public static final String STUDY_ID;
	public static final String STUDY_TITLE;
	public static final String STUDY_PURPOSE;
	public static final String STUDY_UUID;
	public static final String STUDY_ROOT_URI;
	public static final String STUDY_START_TIME;
	public static final String STUDY_END_TIME;

	public static final String SESSION_ID;
	public static final String SESSION_ID_NUMBER;
	public static final String SESSION_TASK_LABEL;
	public static final String SESSION_PURPOSE;
	public static final String SESSION_LAB_ID;
	public static final String SESSION_START_TIME;
	public static final String SESSION_END_TIME;
	
	public static final String RECORD_ID;
	public static final String CHANNEL_ID;
	
	public static final String RECORD_NUMBER_OF_CHANNELS;
	public static final String RECORD_CHANNEL_FORMAT;
	public static final String RECORD_SAMPLING_RATE;
	public static final String RECORD_START_TIME;
	public static final String RECORD_END_TIME;

	public static final String EEG_RECORD_ID;
	public static final String EEG_RECORD_NUMBER_OF_CHANNELS;
	public static final String EEG_RECORD_SAMPLING_RATE;
	public static final String EEG_RECORD_START_TIME;
	public static final String EEG_RECORD_END_TIME;
	
	public static final String EYE_GAZE_RECORD_ID;
	public static final String EYE_GAZE_RECORD_START_TIME;
	public static final String EYE_GAZE_RECORD_END_TIME;
	public static final String EYE_GAZE_RECORD_CHANNEL_FORMAT;
	public static final String EYE_GAZE_RECORD_SAMPLING_RATE;
	
	
	public static final String HAND_GESTURE_RECORD_ID;
	public static final String HAND_GESTURE_RECORD_START_TIME;
	public static final String HAND_GESTURE_RECORD_END_TIME;
	public static final String HAND_GESTURE_RECORD_CHANNEL_FORMAT;
	public static final String HAND_GESTURE_RECORD_SAMPLING_RATE;
	
	public static final String KEYBOARD_HIT_RECORD_ID;
	public static final String KEYBOARD_HIT_RECORD_START_TIME;
	public static final String KEYBOARD_HIT_RECORD_END_TIME;
	
	public static final String MOUSE_CLICK_RECORD_ID;
	public static final String MOUSE_CLICK_RECORD_START_TIME;
	public static final String MOUSE_CLICK_RECORD_END_TIME;
	
	
	public static final String MOTION_CAPTURE_RECORD_ID;
	public static final String MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS;
	public static final String MOTION_CAPTURE_RECORD_SAMPLING_RATE;
	public static final String MOTION_CAPTURE_RECORD_START_TIME;
	public static final String MOTION_CAPTURE_RECORD_END_TIME;

	public static final String SUBJECT_ID;
	public static final String SUBJECT_GENDER;
	public static final String SUBJECT_YEAR_OF_BIRTH;
	public static final String SUBJECT_HANDEDNESS;

	public static final String RECORDED_SUBJECT_AT_SESSION_ID;
	public static final String RECORDED_SUBJECT_AT_SESSION_LAB_ID;
	public static final String RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER;
	public static final String RECORDED_SUBJECT_AT_SESSION_GROUP;
	public static final String RECORDED_SUBJECT_AT_SESSION_AGE;
	public static final String RECORDED_SUBJECT_AT_SESSION_VISION;
	public static final String RECORDED_SUBJECT_AT_SESSION_HEARING;
	public static final String RECORDED_SUBJECT_AT_SESSION_HEIGHT;
	public static final String RECORDED_SUBJECT_AT_SESSION_WEIGHT;
	public static final String RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE;
	public static final String RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL;
	public static final String RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE;
	
	public static final String DEVICE_ID;

	public static final String EEG_DEVICE_ID;
	public static final String EYE_GAZE_DEVICE_ID;
	public static final String HAND_GESTURE_DEVICE_ID;
	public static final String MOUSE_CLICK_DEVICE_ID;
	public static final String KEYBOARD_HIT_DEVICE_ID;
	
	
	public static final String EYE_GAZE_CHANNEL_ID;
	public static final String HAND_GESTURE_CHANNEL_ID;
	public static final String MOUSE_CLICK_CHANNEL_ID;

	
	
	public static final String EYE_GAZE_CHANNEL_TYPE;
	public static final String EYE_GAZE_CHANNEL_REFERTO;
	public static final String EYE_GAZE_CHANNEL_UNIT;
	
	public static final String HAND_GESTURE_CHANNEL_TYPE;
	public static final String HAND_GESTURE_CHANNEL_UNIT;
	
	public static final String MOUSE_CLICK_CHANNEL_TYPE;
	public static final String MOUSE_CLICK_CHANNEL_BUTTON;

	
	
	
	

	public static final String MOTION_CAPTURE_DEVICE_ID;

	public static final String RECORDED_PARAMETER_SET_ID;

	public static final String RECORDED_MODALITY_ID;
	public static final String RECORDED_MODALITY_MODALITY_TYPE;
	public static final String RECORDED_MODALITY_MODALITY_SIGNAL_TYPE;
	public static final String RECORDED_MODALITY_SAMPLING_RATE;
	public static final String RECORDED_MODALITY_HARDWARE_MANUFACTURER;
	public static final String RECORDED_MODALITY_START_CHANNEL;
	public static final String RECORDED_MODALITY_END_CHANNEL;
	public static final String RECORDED_MODALITY_REFERENCE_LOCATION;
	public static final String RECORDED_MODALITY_REFERENCE_LABEL;

	public static final String MEASUREMENT_CAPABILITY_ID;

	public static final String MEASUREMENT_PROPERTY_ID;
	public static final String MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS;
	public static final String MEASUREMENT_PROPERTY_SAMPLING_RATE;
	public static final String MEASUREMENT_PROPERTY_START_CHANNEL;
	public static final String MEASUREMENT_PROPERTY_END_CHANNEL;

	public static final String BIOMEDICAL_RESOURCE_ID;
	public static final String BIOMEDICAL_RESOURCE_TITLE;
	public static final String BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS;
	public static final String BIOMEDICAL_RESOURCE_SAMPLING_RATE;
	public static final String BIOMEDICAL_RESOURCE_UTILIZATION;
	public static final String BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID;
	public static final String BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL;

	public static final String CHANNEL_LOCATIONS_ID;
	public static final String CHANNEL_LOCATIONS_TITLE;
	public static final String CHANNEL_LOCATIONS_UTILIZATION;
	public static final String CHANNEL_LOCATIONS_ACCESS_METHOD_ID;
	public static final String CHANNEL_LOCATIONS_ACCESS_METHOD_URL;

	public static final String EVENT_INSTANCE_FILE_ID;
	public static final String EVENT_INSTANCE_FILE_TITLE;
	public static final String EVENT_INSTANCE_FILE_UTILIZATION;
	public static final String EVENT_INSTANCE_FILE_ACCESS_METHOD_ID;
	public static final String EVENT_INSTANCE_FILE_ACCESS_METHOD_URL;

	static {
		System.out.println(StringConstants.LOAD_SPARQL_VARIABLE_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		OFFSET = xmlReader.getDec("/constant/SPARQL/variable/OFFSET/text()");

		STUDY_ID         = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/ID/text()");
		STUDY_TITLE      = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/TITLE/text()");
		STUDY_PURPOSE    = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/PURPOSE/text()");
		STUDY_UUID       = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/UUID/text()");
		STUDY_ROOT_URI   = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/ROOT_URI/text()");
		STUDY_START_TIME = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/START_TILE/text()");
		STUDY_END_TIME   = xmlReader.getString("/constant/SPARQL/variable/ALL/STUDY/END_TIME/text()");

		SESSION_ID         = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/ID/text()");
		SESSION_ID_NUMBER  = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/ID_NUMBER/text()");
		SESSION_TASK_LABEL = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/TASK_LABEL/text()");
		SESSION_PURPOSE    = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/PURPOSE/text()");
		SESSION_LAB_ID     = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/LAB_ID/text()");
		SESSION_START_TIME = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/START_TILE/text()");
		SESSION_END_TIME   = xmlReader.getString("/constant/SPARQL/variable/ALL/SESSION/END_TIME/text()");

		CHANNEL_ID                     = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL/ID/text()");
		RECORD_ID                     = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/ID/text()");
		RECORD_NUMBER_OF_CHANNELS     = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/NUMBER_OF_CHANNELS/text()");
		RECORD_CHANNEL_FORMAT         = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/CHANNEL_FORMAT/text()");
		RECORD_SAMPLING_RATE          = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/SAMPLING_RATE/text()");
		RECORD_START_TIME             = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/START_TIME/text()");
		RECORD_END_TIME               = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORD/END_TIME/text()");
		
		EEG_RECORD_ID                 = xmlReader.getString("/constant/SPARQL/variable/EEG/RECORD/ID/text()");
		EEG_RECORD_NUMBER_OF_CHANNELS = xmlReader.getString("/constant/SPARQL/variable/EEG/RECORD/NUMBER_OF_CHANNELS/text()");
		EEG_RECORD_SAMPLING_RATE      = xmlReader.getString("/constant/SPARQL/variable/EEG/RECORD/SAMPLING_RATE/text()");
		EEG_RECORD_START_TIME         = xmlReader.getString("/constant/SPARQL/variable/EEG/RECORD/START_TIME/text()");
		EEG_RECORD_END_TIME           = xmlReader.getString("/constant/SPARQL/variable/EEG/RECORD/END_TIME/text()");
		
		EYE_GAZE_RECORD_ID			  = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/RECORD/ID/text()");
		EYE_GAZE_RECORD_START_TIME    = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/RECORD/START_TIME/text()");
		EYE_GAZE_RECORD_END_TIME	  = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/RECORD/END_TIME/text()");
		EYE_GAZE_RECORD_CHANNEL_FORMAT = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/RECORD/CHANNEL_FORMAT/text()");
		EYE_GAZE_RECORD_SAMPLING_RATE  = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/RECORD/SAMPLING_RATE/text()");
		
		HAND_GESTURE_RECORD_ID		      = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/RECORD/ID/text()");
		HAND_GESTURE_RECORD_START_TIME    = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/RECORD/START_TIME/text()");
		HAND_GESTURE_RECORD_END_TIME	  = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/RECORD/END_TIME/text()");
		HAND_GESTURE_RECORD_CHANNEL_FORMAT = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/RECORD/CHANNEL_FORMAT/text()");
		HAND_GESTURE_RECORD_SAMPLING_RATE  = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/RECORD/SAMPLING_RATE/text()");
		
		KEYBOARD_HIT_RECORD_ID            = xmlReader.getString("/constant/SPARQL/variable/KEYBOARD_HIT/RECORD/ID/text()");
		KEYBOARD_HIT_RECORD_START_TIME    = xmlReader.getString("/constant/SPARQL/variable/KEYBOARD_HIT/RECORD/START_TIME/text()");
		KEYBOARD_HIT_RECORD_END_TIME	  = xmlReader.getString("/constant/SPARQL/variable/KEYBOARD_HIT/RECORD/END_TIME/text()");

		MOUSE_CLICK_RECORD_ID             = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/RECORD/ID/text()");
		MOUSE_CLICK_RECORD_START_TIME     = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/RECORD/START_TIME/text()");
		MOUSE_CLICK_RECORD_END_TIME	      = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/RECORD/END_TIME/text()");

		
		
		
		MOTION_CAPTURE_RECORD_ID                 = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/RECORD/ID/text()");
		MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/RECORD/NUMBER_OF_CHANNELS/text()");
		MOTION_CAPTURE_RECORD_SAMPLING_RATE      = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/RECORD/SAMPLING_RATE/text()");
		MOTION_CAPTURE_RECORD_START_TIME         = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/RECORD/START_TIME/text()");
		MOTION_CAPTURE_RECORD_END_TIME           = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/RECORD/END_TIME/text()");
		
		SUBJECT_ID            = xmlReader.getString("/constant/SPARQL/variable/ALL/SUBJECT/ID/text()");
		SUBJECT_GENDER        = xmlReader.getString("/constant/SPARQL/variable/ALL/SUBJECT/GENDER/text()");
		SUBJECT_YEAR_OF_BIRTH = xmlReader.getString("/constant/SPARQL/variable/ALL/SUBJECT/YEAR_OF_BIRTH/text()");
		SUBJECT_HANDEDNESS    = xmlReader.getString("/constant/SPARQL/variable/ALL/SUBJECT/HANDEDNESS/text()");
		
		RECORDED_SUBJECT_AT_SESSION_ID                    = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/ID/text()");
		RECORDED_SUBJECT_AT_SESSION_LAB_ID                = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/LAB_ID/text()");
		RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER     = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/IN_SESSION_NUMBER/text()");
		RECORDED_SUBJECT_AT_SESSION_GROUP                 = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/GROUP/text()");
		RECORDED_SUBJECT_AT_SESSION_AGE                   = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/AGE/text()");
		RECORDED_SUBJECT_AT_SESSION_VISION                = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/VISION/text()");
		RECORDED_SUBJECT_AT_SESSION_HEARING               = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/HEARING/text()");
		RECORDED_SUBJECT_AT_SESSION_HEIGHT                = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/HEIGHT/text()");
		RECORDED_SUBJECT_AT_SESSION_WEIGHT                = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/WEIGHT/text()");
		RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE   = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/MEDICATION_CAFFEINE/text()");
		RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL    = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/MEDICATION_ALCOHOL/text()");
		RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_SUBJECT_AT_SESSION/CHANNEL_LOCATION_TYPE/text()");
		
		DEVICE_ID                = xmlReader.getString("/constant/SPARQL/variable/ALL/DEVICE/ID/text()");
		
		EEG_DEVICE_ID            = xmlReader.getString("/constant/SPARQL/variable/EEG/DEVICE/ID/text()");
		EYE_GAZE_DEVICE_ID       = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/DEVICE/ID/text()");
		HAND_GESTURE_DEVICE_ID      = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/DEVICE/ID/text()");
		KEYBOARD_HIT_DEVICE_ID   = xmlReader.getString("/constant/SPARQL/variable/KEYBOARD_HIT/DEVICE/ID/text()");
        MOUSE_CLICK_DEVICE_ID    = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/DEVICE/ID/text()");
        
		EYE_GAZE_CHANNEL_ID		 = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/CHANNEL/ID/text()");
		HAND_GESTURE_CHANNEL_ID		 = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/CHANNEL/ID/text()");
		MOUSE_CLICK_CHANNEL_ID		 = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/CHANNEL/ID/text()");
        
		MOTION_CAPTURE_DEVICE_ID = xmlReader.getString("/constant/SPARQL/variable/MOTION_CAPTURE/DEVICE/ID/text()");

		RECORDED_PARAMETER_SET_ID       = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_PARAMETER_SET/ID/text()");

		RECORDED_MODALITY_ID                    = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/ID/text()");
		RECORDED_MODALITY_MODALITY_TYPE         = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/MODALITY_TYPE/text()");
		RECORDED_MODALITY_MODALITY_SIGNAL_TYPE  = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/MODALITY_SIGNAL_TYPE/text()");
		RECORDED_MODALITY_SAMPLING_RATE         = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/SAMPLING_RATE/text()");
		RECORDED_MODALITY_HARDWARE_MANUFACTURER = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/HARDWARE_MANUFACTURER/text()");
		RECORDED_MODALITY_START_CHANNEL         = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/START_CHANNEL/text()");
		RECORDED_MODALITY_END_CHANNEL           = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/END_CHANNEL/text()");
		RECORDED_MODALITY_REFERENCE_LOCATION    = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/REFERENCE_LOCATION/text()");
		RECORDED_MODALITY_REFERENCE_LABEL       = xmlReader.getString("/constant/SPARQL/variable/ALL/RECORDED_MODALITY/REFERENCE_LABEL/text()");
		
		MEASUREMENT_CAPABILITY_ID = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_CAPABILITY/ID/text()");
		
		MEASUREMENT_PROPERTY_ID                 = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_PROPERTY/ID/text()");
		MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_PROPERTY/NUMBER_OF_CHANNELS/text()");
		MEASUREMENT_PROPERTY_SAMPLING_RATE      = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_PROPERTY/SAMPLING_RATE/text()");
		MEASUREMENT_PROPERTY_START_CHANNEL      = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_PROPERTY/START_CHANNEL/text()");
		MEASUREMENT_PROPERTY_END_CHANNEL        = xmlReader.getString("/constant/SPARQL/variable/ALL/MEASUREMENT_PROPERTY/END_CHANNEL/text()");
		
		BIOMEDICAL_RESOURCE_ID                 = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/ID/text()");
		BIOMEDICAL_RESOURCE_TITLE              = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/TITLE/text()");
		BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/NUMBER_OF_CHANNELS/text()");
		BIOMEDICAL_RESOURCE_SAMPLING_RATE = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/SAMPLING_RATE/text()");
		BIOMEDICAL_RESOURCE_UTILIZATION        = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/UTILIZATION/text()");
		BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID   = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/ACCESS_METHOD/ID/text()");
		BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL  = xmlReader.getString("/constant/SPARQL/variable/ALL/BIOMEDICAL_RESOURCE/ACCESS_METHOD/URL/text()");
		
		CHANNEL_LOCATIONS_ID                = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL_LOCATIONS/ID/text()");
		CHANNEL_LOCATIONS_TITLE             = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL_LOCATIONS/TITLE/text()");
		CHANNEL_LOCATIONS_UTILIZATION       = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL_LOCATIONS/UTILIZATION/text()");
		CHANNEL_LOCATIONS_ACCESS_METHOD_ID  = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL_LOCATIONS/ACCESS_METHOD/ID/text()");
		CHANNEL_LOCATIONS_ACCESS_METHOD_URL = xmlReader.getString("/constant/SPARQL/variable/ALL/CHANNEL_LOCATIONS/ACCESS_METHOD/URL/text()");
		
		EVENT_INSTANCE_FILE_ID                = xmlReader.getString("/constant/SPARQL/variable/ALL/EVENT_INSTANCE_FILE/ID/text()");
		EVENT_INSTANCE_FILE_TITLE             = xmlReader.getString("/constant/SPARQL/variable/ALL/EVENT_INSTANCE_FILE/TITLE/text()");
		EVENT_INSTANCE_FILE_UTILIZATION       = xmlReader.getString("/constant/SPARQL/variable/ALL/EVENT_INSTANCE_FILE/UTILIZATION/text()");
		EVENT_INSTANCE_FILE_ACCESS_METHOD_ID  = xmlReader.getString("/constant/SPARQL/variable/ALL/EVENT_INSTANCE_FILE/ACCESS_METHOD/ID/text()");
		EVENT_INSTANCE_FILE_ACCESS_METHOD_URL = xmlReader.getString("/constant/SPARQL/variable/ALL/EVENT_INSTANCE_FILE/ACCESS_METHOD/URL/text()");
		
		EYE_GAZE_CHANNEL_TYPE           = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/CHANNEL/TYPE/text()");
		EYE_GAZE_CHANNEL_REFERTO        = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/CHANNEL/REFERTO/text()");
		EYE_GAZE_CHANNEL_UNIT           = xmlReader.getString("/constant/SPARQL/variable/EYE_GAZE/CHANNEL/UNIT/text()");
		
		HAND_GESTURE_CHANNEL_TYPE           = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/CHANNEL/TYPE/text()");
		HAND_GESTURE_CHANNEL_UNIT           = xmlReader.getString("/constant/SPARQL/variable/HAND_GESTURE/CHANNEL/UNIT/text()");

		MOUSE_CLICK_CHANNEL_TYPE           = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/CHANNEL/TYPE/text()");
		MOUSE_CLICK_CHANNEL_BUTTON           = xmlReader.getString("/constant/SPARQL/variable/MOUSE_CLICK/CHANNEL/BUTTON/text()");
	}

	public static void printValue() {
		System.out.println(StringConstants.SPARQL_VARIABLE_CONSTANT_VALUE);

		PrintMessage.showValue("OFFSET", OFFSET);
		System.out.println();

		PrintMessage.showValue("STUDY_ID", STUDY_ID);
		PrintMessage.showValue("STUDY_TITLE", STUDY_TITLE);
		PrintMessage.showValue("STUDY_PURPOSE", STUDY_PURPOSE);
		PrintMessage.showValue("STUDY_START_TIME", STUDY_START_TIME);
		PrintMessage.showValue("STUDY_END_TIME", STUDY_END_TIME);
		System.out.println();

		PrintMessage.showValue("SESSION_ID", SESSION_ID);
		PrintMessage.showValue("SESSION_ID_NUMBER", SESSION_ID_NUMBER);
		PrintMessage.showValue("SESSION_TASK_LABEL", SESSION_TASK_LABEL);
		PrintMessage.showValue("SESSION_PURPOSE", SESSION_PURPOSE);
		PrintMessage.showValue("SESSION_LAB_ID", SESSION_LAB_ID);
		PrintMessage.showValue("SESSION_START_TIME", SESSION_START_TIME);
		PrintMessage.showValue("SESSION_END_TIME", SESSION_END_TIME);
		System.out.println();

		PrintMessage.showValue("RECORD_ID", RECORD_ID);
		PrintMessage.showValue("RECORD_NUMBER_OF_CHANNELS", RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("RECORD_SAMPLING_RATE", RECORD_SAMPLING_RATE);
		PrintMessage.showValue("RECORD_START_TIME", RECORD_START_TIME);
		PrintMessage.showValue("RECORD_END_TIME", RECORD_END_TIME);

		PrintMessage.showValue("EEG_RECORD_ID", EEG_RECORD_ID);
		PrintMessage.showValue("EEG_RECORD_NUMBER_OF_CHANNELS", EEG_RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("EEG_RECORD_SAMPLING_RATE", EEG_RECORD_SAMPLING_RATE);
		PrintMessage.showValue("EEG_RECORD_START_TIME", EEG_RECORD_START_TIME);
		PrintMessage.showValue("EEG_RECORD_END_TIME", EEG_RECORD_END_TIME);
		System.out.println();
		
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_ID", MOTION_CAPTURE_RECORD_ID);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS", MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_SAMPLING_RATE", MOTION_CAPTURE_RECORD_SAMPLING_RATE);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_START_TIME", MOTION_CAPTURE_RECORD_START_TIME);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_END_TIME", MOTION_CAPTURE_RECORD_END_TIME);
		System.out.println();

		PrintMessage.showValue("SUBJECT_ID", SUBJECT_ID);
		PrintMessage.showValue("SUBJECT_GENDER", SUBJECT_GENDER);
		PrintMessage.showValue("SUBJECT_YEAR_OF_BIRTH", SUBJECT_YEAR_OF_BIRTH);
		PrintMessage.showValue("SUBJECT_HANDEDNESS", SUBJECT_HANDEDNESS);
		System.out.println();

		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_ID", RECORDED_SUBJECT_AT_SESSION_ID);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_LAB_ID", RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER", RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_GROUP", RECORDED_SUBJECT_AT_SESSION_GROUP);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_AGE", RECORDED_SUBJECT_AT_SESSION_AGE);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_VISION", RECORDED_SUBJECT_AT_SESSION_VISION);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_HEARING", RECORDED_SUBJECT_AT_SESSION_HEARING);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_HEIGHT", RECORDED_SUBJECT_AT_SESSION_HEIGHT);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_WEIGHT", RECORDED_SUBJECT_AT_SESSION_WEIGHT);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE", RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL", RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE", RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		System.out.println();

		PrintMessage.showValue("DEVICE_ID", DEVICE_ID);
		PrintMessage.showValue("EEG_DEVICE_ID", EEG_DEVICE_ID);
		PrintMessage.showValue("MOTION_CAPTURE_DEVICE_ID", MOTION_CAPTURE_DEVICE_ID);
		System.out.println();

		PrintMessage.showValue("RECORDED_PARAMETER_SET_ID", RECORDED_PARAMETER_SET_ID);
		
		PrintMessage.showValue("RECORDED_MODALITY_ID", RECORDED_MODALITY_ID);
		PrintMessage.showValue("RECORDED_MODALITY_TYPE", RECORDED_MODALITY_MODALITY_TYPE);
		PrintMessage.showValue("RECORDED_MODALITY_SAMPLING_RATE", RECORDED_MODALITY_SAMPLING_RATE);
		PrintMessage.showValue("RECORDED_MODALITY_HARDWARE_MANUFACTURER", RECORDED_MODALITY_HARDWARE_MANUFACTURER);
		PrintMessage.showValue("RECORDED_MODALITY_START_CHANNEL", RECORDED_MODALITY_START_CHANNEL);
		PrintMessage.showValue("RECORDED_MODALITY_END_CHANNEL", RECORDED_MODALITY_END_CHANNEL);
		PrintMessage.showValue("RECORDED_MODALITY_REFERENCE_LOCATION", RECORDED_MODALITY_REFERENCE_LOCATION);
		PrintMessage.showValue("RECORDED_MODALITY_REFERENCE_LABEL", RECORDED_MODALITY_REFERENCE_LABEL);
		System.out.println();

		PrintMessage.showValue("MEASUREMENT_CAPABILITY_ID", MEASUREMENT_CAPABILITY_ID);
		System.out.println();

		PrintMessage.showValue("MEASUREMENT_PROPERTY_ID", MEASUREMENT_PROPERTY_ID);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS", MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_SAMPLING_RATE", MEASUREMENT_PROPERTY_SAMPLING_RATE);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_START_CHANNEL", MEASUREMENT_PROPERTY_START_CHANNEL);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_END_CHANNEL", MEASUREMENT_PROPERTY_END_CHANNEL);
		System.out.println();

		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ID", BIOMEDICAL_RESOURCE_ID);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_TITLE", BIOMEDICAL_RESOURCE_TITLE);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS", BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_SAMPLING_RATE", BIOMEDICAL_RESOURCE_SAMPLING_RATE);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_UTILIZATION", BIOMEDICAL_RESOURCE_UTILIZATION);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID", BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL", BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);
		System.out.println();

		PrintMessage.showValue("CHANNEL_LOCATIONS_ID", CHANNEL_LOCATIONS_ID);
		PrintMessage.showValue("CHANNEL_LOCATIONS_TITLE", CHANNEL_LOCATIONS_TITLE);
		PrintMessage.showValue("CHANNEL_LOCATIONS_UTILIZATION", CHANNEL_LOCATIONS_UTILIZATION);
		PrintMessage.showValue("CHANNEL_LOCATIONS_ACCESS_METHOD_ID", CHANNEL_LOCATIONS_ACCESS_METHOD_ID);
		PrintMessage.showValue("CHANNEL_LOCATIONS_ACCESS_METHOD_URL", CHANNEL_LOCATIONS_ACCESS_METHOD_URL);
		System.out.println();

		PrintMessage.showValue("EVENT_INSTANCE_FILE_ID", EVENT_INSTANCE_FILE_ID);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_TITLE", EVENT_INSTANCE_FILE_TITLE);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_UTILIZATION", EVENT_INSTANCE_FILE_UTILIZATION);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_ACCESS_METHOD_ID", EVENT_INSTANCE_FILE_ACCESS_METHOD_ID);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_ACCESS_METHOD_URL", EVENT_INSTANCE_FILE_ACCESS_METHOD_URL);
		System.out.println();

	}
}
