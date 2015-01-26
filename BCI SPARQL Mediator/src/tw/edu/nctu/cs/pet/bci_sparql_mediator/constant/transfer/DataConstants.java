package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class DataConstants {

	public static final String ANYID;
	
	public static final String STUDY_ID;
	public static final String STUDY_TITLE;
	public static final String STUDY_PURPOSE;
	public static final String STUDY_UUID;
	public static final String STUDY_ROOT_URI;
	public static final String STUDY_START_TIME;
	public static final String STUDY_END_TIME;
	

	public static final String SESSION_S;
	public static final String SESSION_ID;
	public static final String SESSION_ID_NUMBER;
	public static final String SESSION_TASK_LABEL;
	public static final String SESSION_LAB_ID;
	public static final String SESSION_PURPOSE;
	public static final String SESSION_START_TIME;
	public static final String SESSION_END_TIME;

	public static final String RECORD_ID;
	public static final String RECORD_CHANNEL_FORMAT;
	public static final String RECORD_NUMBER_OF_CHANNELS;
	public static final String RECORD_NUMBER_OF_CHANNELS_MIN;
	public static final String RECORD_NUMBER_OF_CHANNELS_MAX;
	public static final String RECORD_SAMPLING_RATE_MIN;
	public static final String RECORD_SAMPLING_RATE_MAX;
	public static final String RECORD_SAMPLING_RATE;
	public static final String RECORD_START_TIME;
	public static final String RECORD_END_TIME;

	public static final String EEG_RECORD_S;
	public static final String EEG_RECORD_ID;
	public static final String EEG_RECORD_NUMBER_OF_CHANNELS;
	public static final String EEG_RECORD_NUMBER_OF_CHANNELS_MIN;
	public static final String EEG_RECORD_NUMBER_OF_CHANNELS_MAX;
	public static final String EEG_RECORD_SAMPLING_RATE;
	public static final String EEG_RECORD_SAMPLING_RATE_MIN;
	public static final String EEG_RECORD_SAMPLING_RATE_MAX;
	public static final String EEG_RECORD_START_TIME;
	public static final String EEG_RECORD_END_TIME;
	
	public static final String EYE_GAZE_RECORD_S;
	public static final String EYE_GAZE_RECORD_ID;
	public static final String EYE_GAZE_RECORD_CHANNEL_FORMAT;
	public static final String EYE_GAZE_RECORD_SAMPLING_RATE;
	public static final String EYE_GAZE_RECORD_START_TIME;
	public static final String EYE_GAZE_RECORD_END_TIME;
	
	public static final String EYE_GAZE_CHANNEL_S;
	public static final String EYE_GAZE_CHANNEL_ID;
	public static final String EYE_GAZE_CHANNEL_TYPE;
	public static final String EYE_GAZE_CHANNEL_REFER_TO;
	public static final String EYE_GAZE_CHANNEL_UNIT;
	
	
	public static final String HAND_GESTURE_RECORD_S;
	public static final String HAND_GESTURE_RECORD_ID;
	public static final String HAND_GESTURE_RECORD_CHANNEL_FORMAT;
	public static final String HAND_GESTURE_RECORD_SAMPLING_RATE;
	public static final String HAND_GESTURE_RECORD_START_TIME;
	public static final String HAND_GESTURE_RECORD_END_TIME;
	
	public static final String HAND_GESTURE_CHANNEL_S;
	public static final String HAND_GESTURE_CHANNEL_ID;
	public static final String HAND_GESTURE_CHANNEL_TYPE;
	public static final String HAND_GESTURE_CHANNEL_UNIT;
	
	
	public static final String KEYBOARD_HIT_RECORD_S;
	public static final String KEYBOARD_HIT_RECORD_ID;
	public static final String KEYBOARD_HIT_RECORD_START_TIME;
	public static final String KEYBOARD_HIT_RECORD_END_TIME;
	
	public static final String MOUSE_CLICK_RECORD_S;
	public static final String MOUSE_CLICK_RECORD_ID;
	public static final String MOUSE_CLICK_RECORD_START_TIME;
	public static final String MOUSE_CLICK_RECORD_END_TIME;
	
	public static final String MOUSE_CLICK_CHANNEL_S;
	public static final String MOUSE_CLICK_CHANNEL_ID;
	public static final String MOUSE_CLICK_CHANNEL_BOTTON;
	public static final String MOUSE_CLICK_CHANNEL_TYPE;

	public static final String MOTION_CAPTURE_RECORD_S;
	public static final String MOTION_CAPTURE_RECORD_ID;
	public static final String MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS;
	public static final String MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MIN;
	public static final String MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MAX;
	public static final String MOTION_CAPTURE_RECORD_SAMPLING_RATE;
	public static final String MOTION_CAPTURE_RECORD_SAMPLING_RATE_MIN;
	public static final String MOTION_CAPTURE_RECORD_SAMPLING_RATE_MAX;
	public static final String MOTION_CAPTURE_RECORD_START_TIME;
	public static final String MOTION_CAPTURE_RECORD_END_TIME;

	public static final String SUBJECT_S;
	public static final String SUBJECT_ID;
	public static final String SUBJECT_GENDER;
	public static final String SUBJECT_YEAR_OF_BIRTH;
	public static final String SUBJECT_YEAR_OF_BIRTH_MIN;
	public static final String SUBJECT_YEAR_OF_BIRTH_MAX;
	public static final String SUBJECT_HANDEDNESS;

	public static final String RECORDED_SUBJECT_AT_SESSION_S;
	public static final String RECORDED_SUBJECT_AT_SESSION_ID;
	public static final String RECORDED_SUBJECT_AT_SESSION_LAB_ID;
	public static final String RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER;
	public static final String RECORDED_SUBJECT_AT_SESSION_GROUP;
	public static final String RECORDED_SUBJECT_AT_SESSION_AGE;
	public static final String RECORDED_SUBJECT_AT_SESSION_AGE_MIN;
	public static final String RECORDED_SUBJECT_AT_SESSION_AGE_MAX;
	public static final String RECORDED_SUBJECT_AT_SESSION_VISION;
	public static final String RECORDED_SUBJECT_AT_SESSION_HEARING;
	public static final String RECORDED_SUBJECT_AT_SESSION_HEIGHT;
	public static final String RECORDED_SUBJECT_AT_SESSION_WEIGHT;
	public static final String RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE;
	public static final String RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL;
	
	public static final String RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE;

	public static final String EEG_DEVICE_S;
	public static final String EEG_DEVICE_ID;
	
	public static final String EYE_GAZE_DEVICE_S;
	public static final String EYE_GAZE_DEVICE_ID;
	
	public static final String HAND_GESTURE_DEVICE_S;
	public static final String HAND_GESTURE_DEVICE_ID;
	
	
	
	public static final String KEYBOARD_HIT_DEVICE_S;
	public static final String KEYBOARD_HIT_DEVICE_ID;
	
	public static final String MOUSE_CLICK_DEVICE_S;
	public static final String MOUSE_CLICK_DEVICE_ID;
	
	public static final String EEG_DEVICE_HARDWARE_MANUFACTURER;
	public static final String EYE_GAZE_DEVICE_HARDWARE_MANUFACTURER;
	public static final String HAND_GESTURE_DEVICE_HARDWARE_MANUFACTURER;
	public static final String KEYBOARD_HIT_DEVICE_HARDWARE_MANUFACTURER;
	public static final String MOUSE_CLICK_DEVICE_HARDWARE_MANUFACTURER;

	public static final String MOTION_CAPTURE_DEVICE_S;
	public static final String MOTION_CAPTURE_DEVICE_ID;

	public static final String RECORDED_PARAMETER_SET_ID;

	public static final String RECORDED_MODALITY_S;
	public static final String RECORDED_MODALITY_ID;
	public static final String RECORDED_MODALITY_MODALITY_TYPE;
	public static final String RECORDED_MODALITY_MODALITY_SIGNAL_TYPE;
	public static final String RECORDED_MODALITY_SAMPLING_RATE;
	public static final String RECORDED_MODALITY_HARDWARE_MANUFACTURER;
	public static final String RECORDED_MODALITY_START_CHANNEL;
	public static final String RECORDED_MODALITY_END_CHANNEL;
	public static final String RECORDED_MODALITY_REFERENCE_LOCATION;
	public static final String RECORDED_MODALITY_REFERENCE_LABEL;
	public static final String RECORDED_MODALITY_CHANNEL_LOCATION_TYPE;
	public static final String RECORDED_MODALITY_CHANNEL_LABEL;

	public static final String MEASUREMENT_CAPABILITY_ID;

	public static final String MEASUREMENT_PROPERTY_ID;
	public static final String MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS;
	public static final String MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN;
	public static final String MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX;
	public static final String MEASUREMENT_PROPERTY_START_CHANNEL;
	public static final String MEASUREMENT_PROPERTY_END_CHANNEL;
	public static final String MEASUREMENT_PROPERTY_SAMPLING_RATE;
	public static final String MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN;
	public static final String MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX;

	public static final String BIOMEDICAL_RESOURCE_S;
	public static final String BIOMEDICAL_RESOURCE_ID;
	public static final String BIOMEDICAL_RESOURCE_TITLE;
	public static final String BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS;
	public static final String BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN;
	public static final String BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX;
	public static final String BIOMEDICAL_RESOURCE_SAMPLING_RATE;
	public static final String BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN;
	public static final String BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX;
	public static final String BIOMEDICAL_RESOURCE_UTILIZATION;
	public static final String BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID;
	public static final String BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL;

	public static final String CHANNEL_LOCATIONS_S;
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

	public static final String ESS_XML;

	static {
		System.out.println(StringConstants.LOAD_DATA_TRANSFER);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.TRANSFER_FILE);
		
		ANYID                                             = xmlReader.getString("/transfer/data/ALL/ANYID/ID/text()");
		STUDY_ID                                          = xmlReader.getString("/transfer/data/ALL/STUDY/ID/text()");
		STUDY_TITLE                                       = xmlReader.getString("/transfer/data/ALL/STUDY/TITLE/text()");
		STUDY_PURPOSE                                     = xmlReader.getString("/transfer/data/ALL/STUDY/PURPOSE/text()");
		STUDY_UUID                                        = xmlReader.getString("/transfer/data/ALL/STUDY/UUID/text()");
		STUDY_ROOT_URI                                    = xmlReader.getString("/transfer/data/ALL/STUDY/ROOT_URI/text()");
		STUDY_START_TIME                                  = xmlReader.getString("/transfer/data/ALL/STUDY/START_TIME/text()");
		STUDY_END_TIME                                    = xmlReader.getString("/transfer/data/ALL/STUDY/END_TIME/text()");

		SESSION_S                                         = xmlReader.getString("/transfer/data/ALL/SESSION/S/text()");
		SESSION_ID                                        = xmlReader.getString("/transfer/data/ALL/SESSION/ID/text()");
		SESSION_ID_NUMBER                                 = xmlReader.getString("/transfer/data/ALL/SESSION/ID_NUMBER/text()");
		SESSION_TASK_LABEL                                = xmlReader.getString("/transfer/data/ALL/SESSION/TASK_LABEL/text()");
		SESSION_LAB_ID                                    = xmlReader.getString("/transfer/data/ALL/SESSION/LAB_ID/text()");
		SESSION_PURPOSE                                   = xmlReader.getString("/transfer/data/ALL/SESSION/PURPOSE/text()");
		SESSION_START_TIME                                = xmlReader.getString("/transfer/data/ALL/SESSION/START_TIME/text()");
		SESSION_END_TIME                                  = xmlReader.getString("/transfer/data/ALL/SESSION/END_TIME/text()");

		RECORD_ID                                         = xmlReader.getString("/transfer/data/ALL/RECORD/ID/text()");
		RECORD_NUMBER_OF_CHANNELS                         = xmlReader.getString("/transfer/data/ALL/RECORD/NUMBER_OF_CHANNELS/text()");
		RECORD_CHANNEL_FORMAT                             = xmlReader.getString("/transfer/data/ALL/RECORD/CHANNEL_FORMAT/text()");
		
		RECORD_NUMBER_OF_CHANNELS_MIN                     = xmlReader.getString("/transfer/data/ALL/RECORD/NUMBER_OF_CHANNELS_MIN/text()");
		RECORD_NUMBER_OF_CHANNELS_MAX                     = xmlReader.getString("/transfer/data/ALL/RECORD/NUMBER_OF_CHANNELS_MAX/text()");
		RECORD_SAMPLING_RATE_MIN                          = xmlReader.getString("/transfer/data/ALL/RECORD/SAMPLING_RATE_MIN/text()");
		RECORD_SAMPLING_RATE_MAX                          = xmlReader.getString("/transfer/data/ALL/RECORD/SAMPLING_RATE_MAX/text()");
		RECORD_SAMPLING_RATE                              = xmlReader.getString("/transfer/data/ALL/RECORD/SAMPLING_RATE/text()");
		RECORD_START_TIME                                 = xmlReader.getString("/transfer/data/ALL/RECORD/START_TIME/text()");
		RECORD_END_TIME                                   = xmlReader.getString("/transfer/data/ALL/RECORD/END_TIME/text()");

		EEG_RECORD_S                                      = xmlReader.getString("/transfer/data/EEG/RECORD/S/text()");
		EEG_RECORD_ID                                     = xmlReader.getString("/transfer/data/EEG/RECORD/ID/text()");
		EEG_RECORD_NUMBER_OF_CHANNELS                     = xmlReader.getString("/transfer/data/EEG/RECORD/NUMBER_OF_CHANNELS/text()");
		EEG_RECORD_NUMBER_OF_CHANNELS_MIN                 = xmlReader.getString("/transfer/data/EEG/RECORD/NUMBER_OF_CHANNELS_MIN/text()");
		EEG_RECORD_NUMBER_OF_CHANNELS_MAX                 = xmlReader.getString("/transfer/data/EEG/RECORD/NUMBER_OF_CHANNELS_MAX/text()");
		EEG_RECORD_SAMPLING_RATE                          = xmlReader.getString("/transfer/data/EEG/RECORD/SAMPLING_RATE/text()");
		EEG_RECORD_SAMPLING_RATE_MIN                      = xmlReader.getString("/transfer/data/EEG/RECORD/SAMPLING_RATE_MIN/text()");
		EEG_RECORD_SAMPLING_RATE_MAX                      = xmlReader.getString("/transfer/data/EEG/RECORD/SAMPLING_RATE_MAX/text()");
		EEG_RECORD_START_TIME                             = xmlReader.getString("/transfer/data/EEG/RECORD/START_TIME/text()");
		EEG_RECORD_END_TIME                               = xmlReader.getString("/transfer/data/EEG/RECORD/END_TIME/text()");
        
		EYE_GAZE_RECORD_S								  = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/S/text()");
	    EYE_GAZE_RECORD_ID								  = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/ID/text()");
        EYE_GAZE_RECORD_CHANNEL_FORMAT                    = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/CHANNEL_FORMAT/text()");
        EYE_GAZE_RECORD_SAMPLING_RATE					  = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/SAMPLING_RATE/text()");
        EYE_GAZE_RECORD_START_TIME                        = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/START_TIME/text()");
        EYE_GAZE_RECORD_END_TIME						  = xmlReader.getString("/transfer/data/EYE_GAZE/RECORD/END_TIME/text()");
        
        EYE_GAZE_CHANNEL_S    							  = xmlReader.getString("/transfer/data/EYE_GAZE/CHANNEL/S/text()");
        EYE_GAZE_CHANNEL_ID							      = xmlReader.getString("/transfer/data/EYE_GAZE/CHANNEL/ID/text()");
        EYE_GAZE_CHANNEL_TYPE							  = xmlReader.getString("/transfer/data/EYE_GAZE/CHANNEL/TYPE/text()");
        EYE_GAZE_CHANNEL_REFER_TO						  = xmlReader.getString("/transfer/data/EYE_GAZE/CHANNEL/REFERTO/text()");
        EYE_GAZE_CHANNEL_UNIT							  = xmlReader.getString("/transfer/data/EYE_GAZE/CHANNEL/UNIT/text()");

        
		HAND_GESTURE_RECORD_S							  = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/S/text()");
		HAND_GESTURE_RECORD_ID							  = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/ID/text()");
		HAND_GESTURE_RECORD_CHANNEL_FORMAT                = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/CHANNEL_FORMAT/text()");
		HAND_GESTURE_RECORD_SAMPLING_RATE			      = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/SAMPLING_RATE/text()");
		HAND_GESTURE_RECORD_START_TIME                    = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/START_TIME/text()");
		HAND_GESTURE_RECORD_END_TIME					  = xmlReader.getString("/transfer/data/HAND_GESTURE/RECORD/END_TIME/text()");
		
		HAND_GESTURE_CHANNEL_S							  = xmlReader.getString("/transfer/data/HAND_GESTURE/CHANNEL/S/text()");
		HAND_GESTURE_CHANNEL_ID							  = xmlReader.getString("/transfer/data/HAND_GESTURE/CHANNEL/ID/text()");
		HAND_GESTURE_CHANNEL_TYPE     					  = xmlReader.getString("/transfer/data/HAND_GESTURE/CHANNEL/TYPE/text()");
		HAND_GESTURE_CHANNEL_UNIT     					  = xmlReader.getString("/transfer/data/HAND_GESTURE/CHANNEL/UNIT/text()");
		
		KEYBOARD_HIT_RECORD_S							  = xmlReader.getString("/transfer/data/KEYBOARD_HIT/RECORD/S/text()");
		KEYBOARD_HIT_RECORD_ID							  = xmlReader.getString("/transfer/data/KEYBOARD_HIT/RECORD/ID/text()");
		KEYBOARD_HIT_RECORD_START_TIME                    = xmlReader.getString("/transfer/data/KEYBOARD_HIT/RECORD/START_TIME/text()");
		KEYBOARD_HIT_RECORD_END_TIME					  = xmlReader.getString("/transfer/data/KEYBOARD_HIT/RECORD/END_TIME/text()");
 
		MOUSE_CLICK_RECORD_S							  = xmlReader.getString("/transfer/data/MOUSE_CLICK/RECORD/S/text()");
		MOUSE_CLICK_RECORD_ID							  = xmlReader.getString("/transfer/data/MOUSE_CLICK/RECORD/ID/text()");
		MOUSE_CLICK_RECORD_START_TIME                     = xmlReader.getString("/transfer/data/MOUSE_CLICK/RECORD/START_TIME/text()");
		MOUSE_CLICK_RECORD_END_TIME					      = xmlReader.getString("/transfer/data/MOUSE_CLICK/RECORD/END_TIME/text()");
		
		MOUSE_CLICK_CHANNEL_S							  = xmlReader.getString("/transfer/data/MOUSE_CLICK/CHANNEL/S/text()");
		MOUSE_CLICK_CHANNEL_ID							  = xmlReader.getString("/transfer/data/MOUSE_CLICK/CHANNEL/ID/text()");
		MOUSE_CLICK_CHANNEL_BOTTON   				      = xmlReader.getString("/transfer/data/MOUSE_CLICK/CHANNEL/BUTTON/text()");
		MOUSE_CLICK_CHANNEL_TYPE     				      = xmlReader.getString("/transfer/data/MOUSE_CLICK/CHANNEL/TYPE/text()");
		
		MOTION_CAPTURE_RECORD_S                           = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/S/text()");
		MOTION_CAPTURE_RECORD_ID                          = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/ID/text()");
		MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS          = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/NUMBER_OF_CHANNELS/text()");
		MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MIN      = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/NUMBER_OF_CHANNELS_MIN/text()");
		MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MAX      = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/NUMBER_OF_CHANNELS_MAX/text()");
		MOTION_CAPTURE_RECORD_SAMPLING_RATE               = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/SAMPLING_RATE/text()");
		MOTION_CAPTURE_RECORD_SAMPLING_RATE_MIN           = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/SAMPLING_RATE_MIN/text()");
		MOTION_CAPTURE_RECORD_SAMPLING_RATE_MAX           = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/SAMPLING_RATE_MAX/text()");
		MOTION_CAPTURE_RECORD_START_TIME                  = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/START_TIME/text()");
		MOTION_CAPTURE_RECORD_END_TIME                    = xmlReader.getString("/transfer/data/MOTION_CAPTURE/RECORD/END_TIME/text()");

		SUBJECT_S                                         = xmlReader.getString("/transfer/data/ALL/SUBJECT/S/text()");
		SUBJECT_ID                                        = xmlReader.getString("/transfer/data/ALL/SUBJECT/ID/text()");
		SUBJECT_GENDER                                    = xmlReader.getString("/transfer/data/ALL/SUBJECT/GENDER/text()");
		SUBJECT_YEAR_OF_BIRTH                             = xmlReader.getString("/transfer/data/ALL/SUBJECT/YEAR_OF_BIRTH/text()");
		SUBJECT_YEAR_OF_BIRTH_MIN                         = xmlReader.getString("/transfer/data/ALL/SUBJECT/YEAR_OF_BIRTH_MIN/text()");
		SUBJECT_YEAR_OF_BIRTH_MAX                         = xmlReader.getString("/transfer/data/ALL/SUBJECT/YEAR_OF_BIRTH_MAX/text()");
		SUBJECT_HANDEDNESS                                = xmlReader.getString("/transfer/data/ALL/SUBJECT/HANDEDNESS/text()");

		RECORDED_SUBJECT_AT_SESSION_S                     = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/S/text()");
		RECORDED_SUBJECT_AT_SESSION_ID                    = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/ID/text()");
		RECORDED_SUBJECT_AT_SESSION_LAB_ID                = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/LAB_ID/text()");
		RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER     = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/IN_SESSION_NUMBER/text()");
		RECORDED_SUBJECT_AT_SESSION_GROUP                 = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/GROUP/text()");
		RECORDED_SUBJECT_AT_SESSION_AGE                   = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/AGE/text()");
		RECORDED_SUBJECT_AT_SESSION_AGE_MIN               = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/AGE_MIN/text()");
		RECORDED_SUBJECT_AT_SESSION_AGE_MAX               = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/AGE_MAX/text()");
		RECORDED_SUBJECT_AT_SESSION_VISION                = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/VISION/text()");
		RECORDED_SUBJECT_AT_SESSION_HEARING               = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/HEARING/text()");
		RECORDED_SUBJECT_AT_SESSION_HEIGHT                = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/HEIGHT/text()");
		RECORDED_SUBJECT_AT_SESSION_WEIGHT                = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/WEIGHT/text()");
		RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE   = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/MEDICATION_CAFFEINE/text()");
		RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL    = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/MEDICATION_ALCOHOL/text()");
		RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE = xmlReader.getString("/transfer/data/ALL/RECORDED_SUBJECT_AT_SESSION/CHANNEL_LOCATION_TYPE/text()");

		EEG_DEVICE_S                                      = xmlReader.getString("/transfer/data/EEG/DEVICE/S/text()");
		EEG_DEVICE_ID                                     = xmlReader.getString("/transfer/data/EEG/DEVICE/ID/text()");
		
		EYE_GAZE_DEVICE_S								  = xmlReader.getString("/transfer/data/EYE_GAZE/DEVICE/S/text()");
		EYE_GAZE_DEVICE_ID								  =	xmlReader.getString("/transfer/data/EYE_GAZE/DEVICE/ID/text()");

		HAND_GESTURE_DEVICE_S							  = xmlReader.getString("/transfer/data/HAND_GESTURE/DEVICE/S/text()");
		HAND_GESTURE_DEVICE_ID							  =	xmlReader.getString("/transfer/data/HAND_GESTURE/DEVICE/ID/text()");
		
		KEYBOARD_HIT_DEVICE_S							  = xmlReader.getString("/transfer/data/KEYBOARD_HIT/DEVICE/S/text()");
		KEYBOARD_HIT_DEVICE_ID							  =	xmlReader.getString("/transfer/data/KEYBOARD_HIT/DEVICE/ID/text()");

		MOUSE_CLICK_DEVICE_S							  = xmlReader.getString("/transfer/data/MOUSE_CLICK/DEVICE/S/text()");
		MOUSE_CLICK_DEVICE_ID							  =	xmlReader.getString("/transfer/data/MOUSE_CLICK/DEVICE/ID/text()");

		EEG_DEVICE_HARDWARE_MANUFACTURER                  = xmlReader.getString("/transfer/data/EEG/DEVICE/HARDWARE_MANUFACTURER/text()");
		EYE_GAZE_DEVICE_HARDWARE_MANUFACTURER             = xmlReader.getString("/transfer/data/EYE_GAZE/DEVICE/HARDWARE_MANUFACTURER/text()");
		HAND_GESTURE_DEVICE_HARDWARE_MANUFACTURER         = xmlReader.getString("/transfer/data/HAND_GESTURE/DEVICE/HARDWARE_MANUFACTURER/text()");
		KEYBOARD_HIT_DEVICE_HARDWARE_MANUFACTURER         = xmlReader.getString("/transfer/data/KEYBOARD_HIT/DEVICE/HARDWARE_MANUFACTURER/text()");
		MOUSE_CLICK_DEVICE_HARDWARE_MANUFACTURER          = xmlReader.getString("/transfer/data/MOUSE_CLICK/DEVICE/HARDWARE_MANUFACTURER/text()");

		MOTION_CAPTURE_DEVICE_S                           = xmlReader.getString("/transfer/data/MOTION_CAPTURE/DEVICE/S/text()");
		MOTION_CAPTURE_DEVICE_ID                          = xmlReader.getString("/transfer/data/MOTION_CAPTURE/DEVICE/ID/text()");
		
		RECORDED_PARAMETER_SET_ID                         = xmlReader.getString("/transfer/data/ALL/RECORDED_PARAMETER_SET/ID/text()");

		RECORDED_MODALITY_S                               = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/S/text()");
		RECORDED_MODALITY_ID                              = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/ID/text()");
		RECORDED_MODALITY_MODALITY_TYPE                   = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/MODALITY_TYPE/text()");
		RECORDED_MODALITY_MODALITY_SIGNAL_TYPE            = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/MODALITY_SIGNAL_TYPE/text()");

		
		RECORDED_MODALITY_SAMPLING_RATE                   = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/SAMPLING_RATE/text()");
		RECORDED_MODALITY_HARDWARE_MANUFACTURER           = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/HARDWARE_MANUFACTURER/text()");
		RECORDED_MODALITY_START_CHANNEL                   = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/START_CHANNEL/text()");
		RECORDED_MODALITY_END_CHANNEL                     = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/END_CHANNEL/text()");
		RECORDED_MODALITY_REFERENCE_LOCATION              = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/REFERENCE_LOCATION/text()");
		RECORDED_MODALITY_REFERENCE_LABEL                 = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/REFERENCE_LABEL/text()");
		RECORDED_MODALITY_CHANNEL_LOCATION_TYPE           = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/CHANNEL_LOCATION_TYPE/text()");
		RECORDED_MODALITY_CHANNEL_LABEL                   = xmlReader.getString("/transfer/data/ALL/RECORDED_MODALITY/CHANNEL_LABEL/text()");
		
		MEASUREMENT_CAPABILITY_ID                         = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_CAPABILITY/ID/text()");

		MEASUREMENT_PROPERTY_ID                           = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/ID/text()");
		MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS           = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/NUMBER_OF_CHANNELS/text()");
		MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN       = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/NUMBER_OF_CHANNELS_MIN/text()");
		MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX       = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/NUMBER_OF_CHANNELS_MAX/text()");
		MEASUREMENT_PROPERTY_START_CHANNEL                = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/START_CHANNEL/text()");
		MEASUREMENT_PROPERTY_END_CHANNEL                  = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/END_CHANNEL/text()");
		MEASUREMENT_PROPERTY_SAMPLING_RATE                = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/SAMPLING_RATE/text()");
		MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN            = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/SAMPLING_RATE_MIN/text()");
		MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX            = xmlReader.getString("/transfer/data/ALL/MEASUREMENT_PROPERTY/SAMPLING_RATE_MAX/text()");

		BIOMEDICAL_RESOURCE_S                             = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/S/text()");
		BIOMEDICAL_RESOURCE_ID                            = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/ID/text()");
		BIOMEDICAL_RESOURCE_TITLE                         = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/TITLE/text()");
		BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS            = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/NUMBER_OF_CHANNELS/text()");
		BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN        = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/NUMBER_OF_CHANNELS_MIN/text()");
		BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX        = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/NUMBER_OF_CHANNELS_MAX/text()");
		BIOMEDICAL_RESOURCE_SAMPLING_RATE                 = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/SAMPLING_RATE/text()");
		BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN             = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/SAMPLING_RATE_MIN/text()");
		BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX             = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/SAMPLING_RATE_MAX/text()");
		BIOMEDICAL_RESOURCE_UTILIZATION                   = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/UTILIZATION/text()");
		BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID              = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/ACCESS_METHOD/ID/text()");
		BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL             = xmlReader.getString("/transfer/data/ALL/BIOMEDICAL_RESOURCE/ACCESS_METHOD/URL/text()");

		CHANNEL_LOCATIONS_S                               = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/S/text()");
		CHANNEL_LOCATIONS_ID                              = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/ID/text()");
		CHANNEL_LOCATIONS_TITLE                           = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/TITLE/text()");
		CHANNEL_LOCATIONS_UTILIZATION                     = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/UTILIZATION/text()");
		CHANNEL_LOCATIONS_ACCESS_METHOD_ID                = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/ACCESS_METHOD/ID/text()");
		CHANNEL_LOCATIONS_ACCESS_METHOD_URL               = xmlReader.getString("/transfer/data/ALL/CHANNEL_LOCATIONS/ACCESS_METHOD/URL/text()");

		EVENT_INSTANCE_FILE_ID                            = xmlReader.getString("/transfer/data/ALL/EVENT_INSTANCE_FILE/ID/text()");
		EVENT_INSTANCE_FILE_TITLE                         = xmlReader.getString("/transfer/data/ALL/EVENT_INSTANCE_FILE/TITLE/text()");
		EVENT_INSTANCE_FILE_UTILIZATION                   = xmlReader.getString("/transfer/data/ALL/EVENT_INSTANCE_FILE/UTILIZATION/text()");
		EVENT_INSTANCE_FILE_ACCESS_METHOD_ID              = xmlReader.getString("/transfer/data/ALL/EVENT_INSTANCE_FILE/ACCESS_METHOD/ID/text()");
		EVENT_INSTANCE_FILE_ACCESS_METHOD_URL             = xmlReader.getString("/transfer/data/ALL/EVENT_INSTANCE_FILE/ACCESS_METHOD/URL/text()");
		
		ESS_XML                                           = xmlReader.getString("/transfer/data/ALL/ESS_XML/text()");
		
		

	}

	public static void printValue() {
		System.out.println(StringConstants.DATA_TRANSFER_VALUE);

		PrintMessage.showValue("STUDY_ID", STUDY_ID);
		PrintMessage.showValue("STUDY_TITLE", STUDY_TITLE);
		PrintMessage.showValue("STUDY_PURPOSE", STUDY_PURPOSE);
		PrintMessage.showValue("STUDY_UUID", STUDY_UUID);
		PrintMessage.showValue("STUDY_ROOT_URI", STUDY_ROOT_URI);
		PrintMessage.showValue("STUDY_START_TIME", STUDY_START_TIME);
		PrintMessage.showValue("STUDY_END_TIME", STUDY_END_TIME);
		System.out.println();

		PrintMessage.showValue("SESSION_S", SESSION_S);
		PrintMessage.showValue("SESSION_ID", SESSION_ID);
		PrintMessage.showValue("SESSION_ID_NUMBER", SESSION_ID_NUMBER);
		PrintMessage.showValue("SESSION_TASK_LABEL", SESSION_TASK_LABEL);
		PrintMessage.showValue("SESSION_LAB_ID", SESSION_LAB_ID);
		PrintMessage.showValue("SESSION_PURPOSE", SESSION_PURPOSE);
		PrintMessage.showValue("SESSION_START_TIME", SESSION_START_TIME);
		PrintMessage.showValue("SESSION_END_TIME", SESSION_END_TIME);
		System.out.println();

		PrintMessage.showValue("RECORD_ID", RECORD_ID);
		PrintMessage.showValue("RECORD_NUMBER_OF_CHANNELS", RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("RECORD_SAMPLING_RATE", RECORD_SAMPLING_RATE);
		PrintMessage.showValue("RECORD_START_TIME", RECORD_START_TIME);
		PrintMessage.showValue("RECORD_END_TIME", RECORD_END_TIME);

		PrintMessage.showValue("EEG_RECORD_S", EEG_RECORD_S);
		PrintMessage.showValue("EEG_RECORD_ID", EEG_RECORD_ID);
		PrintMessage.showValue("EEG_RECORD_NUMBER_OF_CHANNELS", EEG_RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("EEG_RECORD_NUMBER_OF_CHANNELS_MIN", EEG_RECORD_NUMBER_OF_CHANNELS_MIN);
		PrintMessage.showValue("EEG_RECORD_NUMBER_OF_CHANNELS_MAX", EEG_RECORD_NUMBER_OF_CHANNELS_MAX);
		PrintMessage.showValue("EEG_RECORD_SAMPLING_RATE", EEG_RECORD_SAMPLING_RATE);
		PrintMessage.showValue("EEG_RECORD_SAMPLING_RATE_MIN", EEG_RECORD_SAMPLING_RATE_MIN);
		PrintMessage.showValue("EEG_RECORD_SAMPLING_RATE_MAX", EEG_RECORD_SAMPLING_RATE_MAX);
		PrintMessage.showValue("EEG_RECORD_START_TIME", EEG_RECORD_START_TIME);
		PrintMessage.showValue("EEG_RECORD_END_TIME", EEG_RECORD_END_TIME);
		System.out.println();
		
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_S", MOTION_CAPTURE_RECORD_S);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_ID", MOTION_CAPTURE_RECORD_ID);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS", MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MIN", MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MIN);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MAX", MOTION_CAPTURE_RECORD_NUMBER_OF_CHANNELS_MAX);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_SAMPLING_RATE", MOTION_CAPTURE_RECORD_SAMPLING_RATE);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_SAMPLING_RATE_MIN", MOTION_CAPTURE_RECORD_SAMPLING_RATE_MIN);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_SAMPLING_RATE_MAX", MOTION_CAPTURE_RECORD_SAMPLING_RATE_MAX);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_START_TIME", MOTION_CAPTURE_RECORD_START_TIME);
		PrintMessage.showValue("MOTION_CAPTURE_RECORD_END_TIME", MOTION_CAPTURE_RECORD_END_TIME);
		System.out.println();

		PrintMessage.showValue("SUBJECT_S", SUBJECT_S);
		PrintMessage.showValue("SUBJECT_ID", SUBJECT_ID);
		PrintMessage.showValue("SUBJECT_GENDER", SUBJECT_GENDER);
		PrintMessage.showValue("SUBJECT_YEAR_OF_BIRTH", SUBJECT_YEAR_OF_BIRTH);
		PrintMessage.showValue("SUBJECT_HANDEDNESS", SUBJECT_HANDEDNESS);
		System.out.println();

		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_S", RECORDED_SUBJECT_AT_SESSION_S);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_ID", RECORDED_SUBJECT_AT_SESSION_ID);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_LAB_ID", RECORDED_SUBJECT_AT_SESSION_LAB_ID);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER", RECORDED_SUBJECT_AT_SESSION_IN_SESSION_NUMBER);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_GROUP", RECORDED_SUBJECT_AT_SESSION_GROUP);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_AGE", RECORDED_SUBJECT_AT_SESSION_AGE);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_AGE_MIN", RECORDED_SUBJECT_AT_SESSION_AGE_MIN);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_AGE_MAX", RECORDED_SUBJECT_AT_SESSION_AGE_MAX);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_VISION", RECORDED_SUBJECT_AT_SESSION_VISION);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_HEARING", RECORDED_SUBJECT_AT_SESSION_HEARING);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_HEIGHT", RECORDED_SUBJECT_AT_SESSION_HEIGHT);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_WEIGHT", RECORDED_SUBJECT_AT_SESSION_WEIGHT);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE", RECORDED_SUBJECT_AT_SESSION_MEDICATION_CAFFEINE);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL", RECORDED_SUBJECT_AT_SESSION_MEDICATION_ALCOHOL);
		PrintMessage.showValue("RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE", RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE);
		System.out.println();

		PrintMessage.showValue("EEG_DEVICE_S", EEG_DEVICE_S);
		PrintMessage.showValue("EEG_DEVICE_ID", EEG_DEVICE_ID);
		PrintMessage.showValue("EEG_DEVICE_HARDWARE_MANUFACTURER", EEG_DEVICE_HARDWARE_MANUFACTURER);
		PrintMessage.showValue("MOTION_CAPTURE_DEVICE_S", MOTION_CAPTURE_DEVICE_S);
		PrintMessage.showValue("MOTION_CAPTURE_DEVICE_ID", MOTION_CAPTURE_DEVICE_ID);
		System.out.println();

		PrintMessage.showValue("RECORDED_PARAMETER_SET_ID", RECORDED_PARAMETER_SET_ID);

		PrintMessage.showValue("RECORDED_MODALITY_S", RECORDED_MODALITY_S);
		PrintMessage.showValue("RECORDED_MODALITY_ID", RECORDED_MODALITY_ID);
		PrintMessage.showValue("RECORDED_MODALITY_TYPE", RECORDED_MODALITY_MODALITY_TYPE);
		PrintMessage.showValue("RECORDED_MODALITY_SAMPLING_RATE", RECORDED_MODALITY_SAMPLING_RATE);
		PrintMessage.showValue("RECORDED_MODALITY_HARDWARE_MANUFACTURER", RECORDED_MODALITY_HARDWARE_MANUFACTURER);
		PrintMessage.showValue("RECORDED_MODALITY_START_CHANNEL", RECORDED_MODALITY_START_CHANNEL);
		PrintMessage.showValue("RECORDED_MODALITY_END_CHANNEL", RECORDED_MODALITY_END_CHANNEL);
		PrintMessage.showValue("RECORDED_MODALITY_REFERENCE_LOCATION", RECORDED_MODALITY_REFERENCE_LOCATION);
		PrintMessage.showValue("RECORDED_MODALITY_REFERENCE_LABEL", RECORDED_MODALITY_REFERENCE_LABEL);
		PrintMessage.showValue("RECORDED_MODALITY_CHANNEL_LOCATION_TYPE", RECORDED_MODALITY_CHANNEL_LOCATION_TYPE);
		PrintMessage.showValue("RECORDED_MODALITY_CHANNEL_LABEL", RECORDED_MODALITY_CHANNEL_LABEL);
		System.out.println();

		PrintMessage.showValue("MEASUREMENT_CAPABILITY_ID", MEASUREMENT_CAPABILITY_ID);
		System.out.println();

		PrintMessage.showValue("MEASUREMENT_PROPERTY_ID", MEASUREMENT_PROPERTY_ID);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS", MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN", MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MIN);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX", MEASUREMENT_PROPERTY_NUMBER_OF_CHANNELS_MAX);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_START_CHANNEL", MEASUREMENT_PROPERTY_START_CHANNEL);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_END_CHANNEL", MEASUREMENT_PROPERTY_END_CHANNEL);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_SAMPLING_RATE", MEASUREMENT_PROPERTY_SAMPLING_RATE);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN", MEASUREMENT_PROPERTY_SAMPLING_RATE_MIN);
		PrintMessage.showValue("MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX", MEASUREMENT_PROPERTY_SAMPLING_RATE_MAX);
		System.out.println();

		PrintMessage.showValue("BIOMEDICAL_RESOURCE_S", BIOMEDICAL_RESOURCE_S);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ID", BIOMEDICAL_RESOURCE_ID);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_TITLE", BIOMEDICAL_RESOURCE_TITLE);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS", BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN", BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MIN);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX", BIOMEDICAL_RESOURCE_NUMBER_OF_CHANNELS_MAX);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_SAMPLING_RATE", BIOMEDICAL_RESOURCE_SAMPLING_RATE);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN", BIOMEDICAL_RESOURCE_SAMPLING_RATE_MIN);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX", BIOMEDICAL_RESOURCE_SAMPLING_RATE_MAX);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_UTILITY", BIOMEDICAL_RESOURCE_UTILIZATION);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID", BIOMEDICAL_RESOURCE_ACCESS_METHOD_ID);
		PrintMessage.showValue("BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL", BIOMEDICAL_RESOURCE_ACCESS_METHOD_URL);
		System.out.println();

		PrintMessage.showValue("CHANNEL_LOCATIONS_S", CHANNEL_LOCATIONS_S);
		PrintMessage.showValue("CHANNEL_LOCATIONS_ID", CHANNEL_LOCATIONS_ID);
		PrintMessage.showValue("CHANNEL_LOCATIONS_TITLE", CHANNEL_LOCATIONS_TITLE);
		PrintMessage.showValue("CHANNEL_LOCATIONS_UTILITY", CHANNEL_LOCATIONS_UTILIZATION);
		PrintMessage.showValue("CHANNEL_LOCATIONS_ACCESS_METHOD_ID", CHANNEL_LOCATIONS_ACCESS_METHOD_ID);
		PrintMessage.showValue("CHANNEL_LOCATIONS_ACCESS_METHOD_URL", CHANNEL_LOCATIONS_ACCESS_METHOD_URL);
		System.out.println();

		PrintMessage.showValue("EVENT_INSTANCE_FILE_ID", EVENT_INSTANCE_FILE_ID);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_TITLE", EVENT_INSTANCE_FILE_TITLE);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_UTILIZATION", EVENT_INSTANCE_FILE_UTILIZATION);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_ACCESS_METHOD_ID", EVENT_INSTANCE_FILE_ACCESS_METHOD_ID);
		PrintMessage.showValue("EVENT_INSTANCE_FILE_ACCESS_METHOD_URL", EVENT_INSTANCE_FILE_ACCESS_METHOD_URL);
		System.out.println();

		PrintMessage.showValue("ESS_XML", ESS_XML);
		System.out.println();

	}
	
}
