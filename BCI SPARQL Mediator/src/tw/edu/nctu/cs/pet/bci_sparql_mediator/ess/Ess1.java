package tw.edu.nctu.cs.pet.bci_sparql_mediator.ess;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.StringConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.XmlReaderConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.PrintMessage;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.XmlReader;

public class Ess1 {

	public Study STUDY;

	public Ess1(String src) {
		System.out.println(StringConstants.LOAD_ESS_TAG);
		
		src = src.replaceAll("\t", "");

		XmlReader xmlReader = new XmlReader(XmlReaderConstants.LOAD_STRING, src);
		NodeList nodeList = null;

		STUDY = new Study();
		STUDY.TITLE = xmlReader.getString("/study/title/text()");
		STUDY.DESCRIPTION = xmlReader.getString("/study/description/text()");
		STUDY.SESSIONS = new ArrayList<Session>();

		nodeList = xmlReader.getNodeList("/study/sessions/session");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Session session = new Session();
			session.SUBJECT = new Subject();

			session.NUMBER                        = xmlReader.getString("number/text()", node);
			session.TASK_LABEL                    = xmlReader.getString("taskLabel/text()", node);
			session.PURPOSE                       = xmlReader.getString("purpose/text()", node);
			session.LAB_ID                        = xmlReader.getString("labId/text()", node);

			session.SUBJECT.LAB_ID                = xmlReader.getString("subject/labId/text()", node);
			session.SUBJECT.GROUP                 = xmlReader.getString("subject/group/text()", node);
			session.SUBJECT.GENDER                = xmlReader.getString("subject/gender/text()", node);
			session.SUBJECT.YOB                   = xmlReader.getString("subject/YOB/text()", node);
			session.SUBJECT.AGE                   = xmlReader.getString("subject/age/text()", node);
			session.SUBJECT.HAND                  = xmlReader.getString("subject/hand/text()", node);
			session.SUBJECT.CHANNEL_LOCATIONS     = xmlReader.getString("subject/channelLocations/text()", node);
			session.SUBJECT.CHANNEL_LOCATION_TYPE = xmlReader.getString("subject/channelLocationType/text()", node);

			session.CHANNELS                      = xmlReader.getString("channels/text()", node);
			session.EEG_SAMPLING_RATE             = xmlReader.getString("eegSamplingRate/text()", node);

			session.EEG_RECORDINGS                = xmlReader.getStringList("eegRecordings/eegRecording/text()", node);

			STUDY.SESSIONS.add(session);
		}

		STUDY.SUMMARY = new Summary();
		STUDY.SUMMARY.RECORDED_MODALITIES = new ArrayList<Modality>();

		nodeList = xmlReader.getNodeList("/study/summary/recordedModalities/modality");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Modality modality = new Modality();

			modality.NAME             = xmlReader.getString("name/text()", node);
			modality.RECORDING_DEVICE = xmlReader.getString("recordingDevice/text()", node);

			STUDY.SUMMARY.RECORDED_MODALITIES.add(modality);
		}
	}
	
	public void printValue() {
		System.out.println(StringConstants.ESS_TAG_VALUE);

		PrintMessage.showValue("STUDY.TITLE", STUDY.TITLE);
		PrintMessage.showValue("STUDY.DESCRIPTION", STUDY.DESCRIPTION);
		
		for (int i = 0; i < STUDY.SESSIONS.size(); i++) {
			PrintMessage.showValue("STUDY.SESSION.NUMBER", STUDY.SESSIONS.get(i).NUMBER);
			PrintMessage.showValue("STUDY.SESSION.TASK_LABEL", STUDY.SESSIONS.get(i).TASK_LABEL);
			PrintMessage.showValue("STUDY.SESSION.PURPOSE", STUDY.SESSIONS.get(i).PURPOSE);
			PrintMessage.showValue("STUDY.SESSION.LAB_ID", STUDY.SESSIONS.get(i).LAB_ID);

			PrintMessage.showValue("SESSION.SUBJECT.LAB_ID", STUDY.SESSIONS.get(i).SUBJECT.LAB_ID);
			PrintMessage.showValue("SESSION.SUBJECT.GROUP", STUDY.SESSIONS.get(i).SUBJECT.GROUP);
			PrintMessage.showValue("SESSION.SUBJECT.GENDER", STUDY.SESSIONS.get(i).SUBJECT.GENDER);
			PrintMessage.showValue("SESSION.SUBJECT.YOB", STUDY.SESSIONS.get(i).SUBJECT.YOB);
			PrintMessage.showValue("SESSION.SUBJECT.AGE", STUDY.SESSIONS.get(i).SUBJECT.AGE);
			PrintMessage.showValue("SESSION.SUBJECT.HAND", STUDY.SESSIONS.get(i).SUBJECT.HAND);
			PrintMessage.showValue("SESSION.SUBJECT.CHANNEL_LOCATIONS", STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATIONS);
			PrintMessage.showValue("SESSION.SUBJECT.CHANNEL_LOCATION_TYPE", STUDY.SESSIONS.get(i).SUBJECT.CHANNEL_LOCATION_TYPE);

			PrintMessage.showValue("STUDY.SESSION.CHANNELS", STUDY.SESSIONS.get(i).CHANNELS);
			PrintMessage.showValue("STUDY.SESSION.EEG_SAMPLING_RATE", STUDY.SESSIONS.get(i).EEG_SAMPLING_RATE);
			PrintMessage.showValue("STUDY.SESSION.EEG_RECORDINGS", STUDY.SESSIONS.get(i).EEG_RECORDINGS);
		}
		
		for (int i = 0; i < STUDY.SUMMARY.RECORDED_MODALITIES.size(); i++) {
			PrintMessage.showValue("STUDY.SUMMARY.RECORDED_MODALITIES.NAME", STUDY.SUMMARY.RECORDED_MODALITIES.get(i).NAME);
			PrintMessage.showValue("STUDY.SUMMARY.RECORDED_MODALITIES.RECORDING_DEVICE", STUDY.SUMMARY.RECORDED_MODALITIES.get(i).RECORDING_DEVICE);
		}
	}

	public class Study {
		public String TITLE;
		public String DESCRIPTION;
		public ArrayList<Session> SESSIONS;
		public Summary SUMMARY;
	}

	public class Session {
		public String NUMBER;
		public String TASK_LABEL;
		public String PURPOSE;
		public String LAB_ID;

		public Subject SUBJECT;

		public String CHANNELS;
		public String EEG_SAMPLING_RATE;

		public ArrayList<String> EEG_RECORDINGS;
	}

	public class Subject {
		public String LAB_ID;
		public String GROUP;
		public String GENDER;
		public String YOB;
		public String AGE;
		public String HAND;
		public String CHANNEL_LOCATIONS;
		public String CHANNEL_LOCATION_TYPE;
	}

	public class Summary {
		public ArrayList<Modality> RECORDED_MODALITIES;
	}

	public class Modality {
		public String NAME;
		public String RECORDING_DEVICE;
	}
}
