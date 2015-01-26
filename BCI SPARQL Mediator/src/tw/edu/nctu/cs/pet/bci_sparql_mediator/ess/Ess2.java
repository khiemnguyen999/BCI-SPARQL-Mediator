package tw.edu.nctu.cs.pet.bci_sparql_mediator.ess;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class Ess2 {

	public Study STUDY;

	public Ess2(String src) {
		System.out.println(StringConstants.LOAD_ESS_TAG);
		
		src = src.replaceAll("\t", "");

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_STRING, src);

		STUDY = new Study();
		STUDY.TITLE                    = xmlReader.getString("/study/title/text()");
		STUDY.SHORT_DESCRIPTION        = xmlReader.getString("/study/shortDescription/text()");
		STUDY.UUID                     = xmlReader.getString("/study/uuid/text()");
		STUDY.ROOT_URI                 = xmlReader.getString("/study/rootURI/text()");
		
		STUDY.RECORDING_PARAMETER_SETS = new ArrayList<RecordingParameterSet>();
		NodeList recordingParameterSetList = xmlReader.getNodeList("/study/recordingParameterSets/recordingParameterSet");
		for (int i = 0; i < recordingParameterSetList.getLength(); i++) {
			Node recordingParameterSetNode = recordingParameterSetList.item(i);
			RecordingParameterSet recordingParameterSet = new RecordingParameterSet();
			
			recordingParameterSet.RECORDING_PARAMETER_SET_LABEL = xmlReader.getString("recordingParameterSetLabel/text()", recordingParameterSetNode);
			recordingParameterSet.CHANNEL_TYPE = new ArrayList<Modality>();
			
			NodeList modalityList = xmlReader.getNodeList("channelType/modality", recordingParameterSetNode);
			for (int k = 0; k < modalityList.getLength(); k++) {
				Node modalityNode = modalityList.item(k);
				Modality modality = new Modality();
				
				modality.TYPE                      = xmlReader.getString("type/text()", modalityNode);
				modality.SAMPLING_RATE             = xmlReader.getString("samplingRate/text()", modalityNode);
				modality.NAME                      = xmlReader.getString("name/text()", modalityNode);
				modality.DESCRIPTION               = xmlReader.getString("description/text()", modalityNode);
				modality.START_CHANNEL             = xmlReader.getString("startChannel/text()", modalityNode);
				modality.END_CHANNEL               = xmlReader.getString("endChannel/text()", modalityNode);
				modality.SUBJECT_IN_SESSION_NUMBER = xmlReader.getString("subjectInSessionNumber/text()", modalityNode);
				modality.REFERENCE_LOCATION        = xmlReader.getString("referenceLocation/text()", modalityNode);
				modality.REFERENCE_LABEL           = xmlReader.getString("referenceLabel/text()", modalityNode);
				
				recordingParameterSet.CHANNEL_TYPE.add(modality);
			}
			
			STUDY.RECORDING_PARAMETER_SETS.add(recordingParameterSet);
		}
				
		STUDY.SESSIONS                 = new ArrayList<Session>();
		NodeList sessionList = xmlReader.getNodeList("/study/sessions/session");
		for (int i = 0; i < sessionList.getLength(); i++) {
			Node sessionNode = sessionList.item(i);
			Session session = new Session();

			session.NUMBER                        = xmlReader.getString("number/text()", sessionNode);
			session.TASK_LABEL                    = xmlReader.getString("taskLabel/text()", sessionNode);
			session.PURPOSE                       = xmlReader.getString("purpose/text()", sessionNode);
			session.LAB_ID                        = xmlReader.getString("labId/text()", sessionNode);

			session.SUBJECT = new Subject();
			
			session.SUBJECT.LAB_ID                = xmlReader.getString("subject/labId/text()", sessionNode);
			session.SUBJECT.IN_SESSION_NUMBER     = xmlReader.getString("subject/inSessionNumber/text()", sessionNode);
			session.SUBJECT.GROUP                 = xmlReader.getString("subject/group/text()", sessionNode);
			session.SUBJECT.GENDER                = xmlReader.getString("subject/gender/text()", sessionNode);
			session.SUBJECT.YOB                   = xmlReader.getString("subject/YOB/text()", sessionNode);
			session.SUBJECT.AGE                   = xmlReader.getString("subject/age/text()", sessionNode);
			session.SUBJECT.HAND                  = xmlReader.getString("subject/hand/text()", sessionNode);
			session.SUBJECT.VISION                = xmlReader.getString("subject/vision/text()", sessionNode);
			session.SUBJECT.HEARING               = xmlReader.getString("subject/hearing/text()", sessionNode);
			session.SUBJECT.HEIGHT                = xmlReader.getString("subject/height/text()", sessionNode);
			session.SUBJECT.WEIGHT                = xmlReader.getString("subject/weight/text()", sessionNode);
			
			session.SUBJECT.MEDICATION            = new Medication();
			session.SUBJECT.MEDICATION.CAFFEINE   = xmlReader.getString("subject/medication/caffeine/text()", sessionNode);
			session.SUBJECT.MEDICATION.ALCOHOL    = xmlReader.getString("subject/medication/alcohol/text()", sessionNode);
			
			session.SUBJECT.CHANNEL_LOCATIONS     = xmlReader.getString("subject/channelLocations/text()", sessionNode);
			session.SUBJECT.CHANNEL_LOCATION_TYPE = xmlReader.getString("subject/channelLocationType/text()", sessionNode);
			
			session.DATA_RECORDINGS = new ArrayList<DataRecording>();
			NodeList dataRecordingList = xmlReader.getNodeList("dataRecordings/dataRecording", sessionNode);
			for (int k = 0; k < dataRecordingList.getLength(); k++) {
				Node dataRecordingNode = dataRecordingList.item(k);
				DataRecording dataRecording = new DataRecording();
				
				dataRecording.FILE_NAME                     = xmlReader.getString("filename/text()", dataRecordingNode);
				dataRecording.START_DATE_TIME               = xmlReader.getString("startDateTime/text()", dataRecordingNode);
				dataRecording.RECORDING_PARAMETER_SET_LABEL = xmlReader.getString("recordingParameterSetLabel/text()", dataRecordingNode);
				dataRecording.EVENT_INSTANCE_FILE           = xmlReader.getString("eventInstanceFile/text()", dataRecordingNode);
				
				session.DATA_RECORDINGS.add(dataRecording);
			}

			STUDY.SESSIONS.add(session);
		}
	}

	public class Study {
		public String TITLE;
		public String SHORT_DESCRIPTION;
		public String UUID;
		public String ROOT_URI;
		public ArrayList<RecordingParameterSet> RECORDING_PARAMETER_SETS;
		public ArrayList<Session> SESSIONS;
	}
	
	public class RecordingParameterSet {
		public String RECORDING_PARAMETER_SET_LABEL;
		public ArrayList<Modality> CHANNEL_TYPE;
	}

	public class Modality {
		public String TYPE;
		public String SAMPLING_RATE;
		public String NAME;
		public String DESCRIPTION;
		public String START_CHANNEL;
		public String END_CHANNEL;
		public String SUBJECT_IN_SESSION_NUMBER;
		public String REFERENCE_LOCATION;
		public String REFERENCE_LABEL;
	}

	public class Session {
		public String NUMBER;
		public String TASK_LABEL;
		public String PURPOSE;
		public String LAB_ID;

		public Subject SUBJECT;

		public ArrayList<DataRecording> DATA_RECORDINGS;
	}

	public class Subject {
		public String LAB_ID;
		public String IN_SESSION_NUMBER;
		public String GROUP;
		public String GENDER;
		public String YOB;
		public String AGE;
		public String HAND;
		public String VISION;
		public String HEARING;
		public String HEIGHT;
		public String WEIGHT;
		public Medication MEDICATION;
		public String CHANNEL_LOCATIONS;
		public String CHANNEL_LOCATION_TYPE;
	}
	
	public class Medication {
		public String CAFFEINE;
		public String ALCOHOL;
	}
	
	public class DataRecording {
		public String FILE_NAME;
		public String START_DATE_TIME;
		public String RECORDING_PARAMETER_SET_LABEL;
		public String EVENT_INSTANCE_FILE;
	}

}
