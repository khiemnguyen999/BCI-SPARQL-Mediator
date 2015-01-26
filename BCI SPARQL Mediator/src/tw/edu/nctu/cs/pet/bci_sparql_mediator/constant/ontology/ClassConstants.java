package tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.ontology;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.FilePathConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class ClassConstants {

	public static final String BCI_STUDY;
	public static final String BCI_SESSION;
	public static final String BCI_EEG_RECORD;
	
	public static final String BCI_EYE_GAZE_RECORD;
	public static final String BCI_HAND_GESTURE_RECORD;
	public static final String BCI_KEYBOARD_HIT_RECORD;
	public static final String BCI_MOUSE_CLICK_RECORD;
	
	
	public static final String BCI_MOTION_CAPTURE_RECORD;
	public static final String BCI_SUBJECT;
	public static final String BCI_RECORDED_SUBJECT_AT_SESSION;
	public static final String BCI_EEG_DEVICE;
	
	public static final String BCI_EYE_GAZE_DEVICE;
	public static final String BCI_HAND_GESTURE_DEVICE;
	public static final String BCI_KEYBOARD_HIT_DEVICE;
	public static final String BCI_MOUSE_CLICK_DEVICE;
	
	public static final String BCI_MOTION_CAPTURE_DEVICE;
	public static final String BCI_RECORDED_PARAMETER_SET;
	public static final String BCI_RECORDED_MODALITY;
	public static final String BCI_RESOURCE;
	public static final String BCI_ACCESS_METHOD_HTTP;
	public static final String BCI_BIOMEDICAL_RESOURCE;
	public static final String BCI_MEASUREMENT_PROPERTY;

	public static final String SSN_MEASUREMENT_CAPABILITY;
	
	public static final String XSD_DATE_TIME;

	public static final String BCI_EYE_GAZE_CHANNEL;
	public static final String BCI_HAND_GESTURE_CHANNEL;
	public static final String BCI_KEYBOARD_CHANNEL;
	public static final String BCI_MOUSE_CLICK_CHANNEL;
	
	static {
		System.out.println(StringConstants.LOAD_ONTOLOGY_CLASS_CONSTANT);

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_INSIDE,
				FilePathConstants.CONSTANT_FILE);

		BCI_STUDY                       = xmlReader.getString("/constant/ontology/class/BCI/STUDY/text()");
		BCI_SESSION                     = xmlReader.getString("/constant/ontology/class/BCI/SESSION/text()");
		BCI_EEG_RECORD                  = xmlReader.getString("/constant/ontology/class/BCI/EEG_RECORD/text()");
		
		BCI_EYE_GAZE_RECORD		        = xmlReader.getString("/constant/ontology/class/BCI/EYE_GAZE_RECORD/text()");
		BCI_HAND_GESTURE_RECORD			= xmlReader.getString("/constant/ontology/class/BCI/HAND_GESTURE_RECORD/text()");
		BCI_KEYBOARD_HIT_RECORD			= xmlReader.getString("/constant/ontology/class/BCI/KEYBOARD_HIT_RECORD/text()");
		BCI_MOUSE_CLICK_RECORD			= xmlReader.getString("/constant/ontology/class/BCI/MOUSE_CLICK_RECORD/text()");
		
		
		BCI_MOTION_CAPTURE_RECORD       = xmlReader.getString("/constant/ontology/class/BCI/MOTION_CAPTURE_RECORD/text()");
		BCI_SUBJECT                     = xmlReader.getString("/constant/ontology/class/BCI/SUBJECT/text()");
		BCI_RECORDED_SUBJECT_AT_SESSION = xmlReader.getString("/constant/ontology/class/BCI/RECORDED_SUBJECT_AT_SESSION/text()");
		BCI_RECORDED_PARAMETER_SET      = xmlReader.getString("/constant/ontology/class/BCI/RECORDED_PARAMETER_SET/text()");
		BCI_RECORDED_MODALITY           = xmlReader.getString("/constant/ontology/class/BCI/RECORDED_MODALITY/text()");
		BCI_EEG_DEVICE                  = xmlReader.getString("/constant/ontology/class/BCI/EEG_DEVICE/text()");
		
		BCI_EYE_GAZE_DEVICE				= xmlReader.getString("/constant/ontology/class/BCI/EYE_GAZE_DEVICE/text()");
		BCI_HAND_GESTURE_DEVICE			= xmlReader.getString("/constant/ontology/class/BCI/HAND_GESTURE_DEVICE/text()");
		BCI_KEYBOARD_HIT_DEVICE			= xmlReader.getString("/constant/ontology/class/BCI/KEYBOARD_HIT_DEVICE/text()");
		BCI_MOUSE_CLICK_DEVICE			= xmlReader.getString("/constant/ontology/class/BCI/MOUSE_CLICK_DEVICE/text()");
		
		
		BCI_MOTION_CAPTURE_DEVICE       = xmlReader.getString("/constant/ontology/class/BCI/MOTION_CAPTURE_DEVICE/text()");
		BCI_RESOURCE                    = xmlReader.getString("/constant/ontology/class/BCI/RESOURCE/text()");
		BCI_ACCESS_METHOD_HTTP          = xmlReader.getString("/constant/ontology/class/BCI/ACCESS_METHOD_HTTP/text()");
		BCI_BIOMEDICAL_RESOURCE         = xmlReader.getString("/constant/ontology/class/BCI/BIOMEDICAL_RESOURCE/text()");
		BCI_MEASUREMENT_PROPERTY        = xmlReader.getString("/constant/ontology/class/BCI/MEASUREMENT_PROPERTY/text()");
		
		SSN_MEASUREMENT_CAPABILITY      = xmlReader.getString("/constant/ontology/class/SSN/MEASUREMENT_CAPABILITY/text()");
		
		XSD_DATE_TIME                   = xmlReader.getString("/constant/ontology/class/XSD/DATE_TIME/text()");
	
		BCI_EYE_GAZE_CHANNEL			= xmlReader.getString("/constant/ontology/class/BCI/EYE_GAZE_CHANNEL/text()");
		BCI_HAND_GESTURE_CHANNEL		= xmlReader.getString("/constant/ontology/class/BCI/HAND_GESTURE_CHANNEL/text()");
		BCI_KEYBOARD_CHANNEL			= xmlReader.getString("/constant/ontology/class/BCI/KEYBOARD_HIT_CHANNEL/text()");
		BCI_MOUSE_CLICK_CHANNEL			= xmlReader.getString("/constant/ontology/class/BCI/MOUSE_CLICK_CHANNEL/text()");

	}
	
	public static void printValue() {
		System.out.println(StringConstants.ONTOLOGY_CLASS_CONSTANT_VALUE);

		PrintMessage.showValue("BCI_STUDY", BCI_STUDY);
		PrintMessage.showValue("BCI_SESSION", BCI_SESSION);
		PrintMessage.showValue("BCI_EEG_RECORD", BCI_EEG_RECORD);
		PrintMessage.showValue("BCI_MOTION_CAPTURE_RECORD", BCI_MOTION_CAPTURE_RECORD);
		PrintMessage.showValue("BCI_SUBJECT", BCI_SUBJECT);
		PrintMessage.showValue("BCI_RECORDED_SUBJECT_AT_SESSION", BCI_RECORDED_SUBJECT_AT_SESSION);
		PrintMessage.showValue("BCI_EEG_DEVICE", BCI_EEG_DEVICE);
		PrintMessage.showValue("BCI_MOTION_CAPTURE_DEVICE", BCI_MOTION_CAPTURE_DEVICE);
		PrintMessage.showValue("BCI_RECORDED_PARAMETER_SET", BCI_RECORDED_PARAMETER_SET);
		PrintMessage.showValue("BCI_RECORDED_MODALITY", BCI_RECORDED_MODALITY);
		PrintMessage.showValue("BCI_RESOURCE", BCI_RESOURCE);
		PrintMessage.showValue("BCI_ACCESS_METHOD_HTTP", BCI_ACCESS_METHOD_HTTP);
		PrintMessage.showValue("BCI_BIOMEDICAL_RESOURCE", BCI_BIOMEDICAL_RESOURCE);
		PrintMessage.showValue("BCI_MEASUREMENT_PROPERTY", BCI_MEASUREMENT_PROPERTY);
		System.out.println();
		
		PrintMessage.showValue("SSN_MEASUREMENT_CAPABILITY", SSN_MEASUREMENT_CAPABILITY);
		System.out.println();
		
		PrintMessage.showValue("XSD_DATE_TIME", XSD_DATE_TIME);
		System.out.println();
	}

}
