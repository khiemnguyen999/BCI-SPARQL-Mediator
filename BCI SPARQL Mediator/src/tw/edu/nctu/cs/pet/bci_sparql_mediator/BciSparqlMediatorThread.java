package tw.edu.nctu.cs.pet.bci_sparql_mediator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.DebugConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.OperationConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.SettingConstants;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.BciSparqlMediatorUtility;
import tw.edu.nctu.cs.pet.bci_sparql_mediator.utility.MessageController;

public class BciSparqlMediatorThread extends Thread {

	private Socket mSocket = null;

	public BciSparqlMediatorThread(Socket socket) {
		super("SparqlProxyThread");
		mSocket = socket;
	}

	@Override
	public void run() {

		System.out.println("Connected: " + mSocket.getRemoteSocketAddress());

		MessageController messageController = new MessageController(mSocket);

		do {
			JSONObject jsonObject = messageController.receiveMessage();

			if (DebugConstants.DEBUG_SPARQL_PROXY_CONSTANTS) {
				System.out.println(jsonObject.toString());
			}

			if (jsonObject.isNull(SettingConstants.OPERATION)) {
				messageController.sendResponse(SettingConstants.FAILED);
				continue;
			}

			int operation = Integer.parseInt(jsonObject
					.getString(SettingConstants.OPERATION));

			if (operation == OperationConstants.EXIT) {
				messageController.sendResponse(SettingConstants.SUCCESSFULLY);
				break;
			} else if (operation == OperationConstants.QUERY_ALL_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.search(jsonObject));
			} else if (operation == OperationConstants.QUERY_STUDY_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryStudy(jsonObject));
			} else if (operation == OperationConstants.QUERY_SESSION_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.querySession(jsonObject));
			} else if (operation == OperationConstants.QUERY_SESSION_TASK_LABEL) {
				messageController.sendMessage(BciSparqlRepository
						.listSessionTaskLabel());
			} else if (operation == OperationConstants.QUERY_SESSION_PURPOSE) {
				messageController.sendMessage(BciSparqlRepository
						.listSessionPurpose());
			} else if (operation == OperationConstants.QUERY_EEG_RECORD_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryEegRecord(jsonObject));
			} else if (operation == OperationConstants.QUERY_MOTION_CAPTURE_RECORD_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryMotionCaptureRecord(jsonObject));
			} else if (operation == OperationConstants.QUERY_SUBJECT_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.querySubject(jsonObject));
			} else if (operation == OperationConstants.QUERY_SUBJECT_GENDER) {
				messageController.sendMessage(BciSparqlRepository
						.listSubjectGender());
			} else if (operation == OperationConstants.QUERY_SUBJECT_HANDEDNESS) {
				messageController.sendMessage(BciSparqlRepository
						.listSubjectHandedness());
			} else if (operation == OperationConstants.QUERY_RECORDED_SUBJECT_AT_SESSION_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryRecordedSubjectAtSession(jsonObject));
			} else if (operation == OperationConstants.QUERY_RECORDED_SUBJECT_AT_SESSION_GROUP) {
				messageController.sendMessage(BciSparqlRepository
						.listRecordedSubjectAtSessionGroup());
			} else if (operation == OperationConstants.QUERY_RECORDED_SUBJECT_AT_SESSION_CHANNEL_LOCATION_TYPE) {
				messageController.sendMessage(BciSparqlRepository
						.listRecordedSubjectAtSessionChannelLocationType());
			} else if (operation == OperationConstants.QUERY_EEG_DEVICE_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryEegDevice(jsonObject));
			} else if (operation == OperationConstants.QUERY_MOTION_CAPTURE_DEVICE_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryMotionCaptureDevice(jsonObject));
			} else if (operation == OperationConstants.QUERY_RECORDED_MODALITY_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryRecordedModality(jsonObject));
			} else if (operation == OperationConstants.QUERY_RECORDED_MODALITY_MODALITY_TYPE) {
				messageController.sendMessage(BciSparqlRepository
						.listRecordedModalityType());
			} else if (operation == OperationConstants.QUERY_BIOMEDICAL_RESOURCE_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryBiomedicalResource(jsonObject));
					
			} else if (operation == OperationConstants.QUERY_CHANNEL_LOCATIONS_ALL) {
				messageController.sendMessage(BciSparqlRepository
						.queryChannelLocations(jsonObject));
			} else if (operation == OperationConstants.UPDATE_STUDY) {
				messageController.sendMessage(BciSparqlRepository
						.updateStudy(jsonObject));
			} else if (operation == OperationConstants.UPDATE_SESSION) {
				messageController.sendMessage(BciSparqlRepository
						.updateSession(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_EYE_GAZE_CHANNEL) {
				messageController.sendMessage(BciSparqlRepository
						.updateEyeGazeChannel(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_HAND_GESTURE_CHANNEL) {
				messageController.sendMessage(BciSparqlRepository
						.updateHandGestureChannel(jsonObject));
			
			} else if (operation == OperationConstants.UPDATE_MOUSE_CLICK_CHANNEL) {
				messageController.sendMessage(BciSparqlRepository
						.updateMouseClickChannel(jsonObject));
			
			} else if (operation == OperationConstants.UPDATE_EEG_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateEegRecord(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_EYE_GAZE_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateEyeGazeRecord(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_HAND_GESTURE_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateHandGestureRecord(jsonObject));
			
			} else if (operation == OperationConstants.UPDATE_KEYBOARD_HIT_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateKeyBoardHitRecord(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_MOUSE_CLICK_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateMouseClickRecord(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_MOTION_CAPTURE_RECORD) {
				messageController.sendMessage(BciSparqlRepository
						.updateMotionCaptureRecord(jsonObject));
			} else if (operation == OperationConstants.UPDATE_SUBJECT) {
				messageController.sendMessage(BciSparqlRepository
						.updateSubject(jsonObject));
			} else if (operation == OperationConstants.UPDATE_RECORDED_SUBJECT_AT_SESSION) {
				messageController.sendMessage(BciSparqlRepository
						.updateRecordedSubjectAtSession(jsonObject));
			} else if (operation == OperationConstants.UPDATE_RECORDED_PARAMETER_SET) {
				messageController.sendMessage(BciSparqlRepository
						.updateRecordedParameterSet(jsonObject));
			} else if (operation == OperationConstants.UPDATE_EEG_DEVICE) {
				messageController.sendMessage(BciSparqlRepository
						.updateEegDevice(jsonObject));
			}
			else if (operation == OperationConstants.UPDATE_EYE_GAZE_DEVICE) {
					messageController.sendMessage(BciSparqlRepository
							.updateEyeGazeDevice(jsonObject));
			}
			else if (operation == OperationConstants.UPDATE_HAND_GESTURE_DEVICE) {
						messageController.sendMessage(BciSparqlRepository
								.updateHandGestureDevice(jsonObject));
			}
			else if (operation == OperationConstants.UPDATE_KEYBOARD_HIT_DEVICE) {
							messageController.sendMessage(BciSparqlRepository
									.updateKeyBoardHitDevice(jsonObject));
			}
			else if (operation == OperationConstants.UPDATE_MOUSE_CLICK_DEVICE) {
				messageController.sendMessage(BciSparqlRepository
						.updateMouseClickDevice(jsonObject));
				
			} else if (operation == OperationConstants.UPDATE_GENERAL_BIOMEDICAL_RESOURCE) {
					messageController.sendMessage(BciSparqlRepository
							.updateGeneralBiomedicalResource(jsonObject));
	
				
			} else if (operation == OperationConstants.UPDATE_MOTION_CAPTURE_DEVICE) {
				messageController.sendMessage(BciSparqlRepository
						.updateMotionCaptureDevice(jsonObject));
			} else if (operation == OperationConstants.UPDATE_RECORDED_MODALITY) {
				messageController.sendMessage(BciSparqlRepository
						.updateRecordedModality(jsonObject));
			} else if (operation == OperationConstants.UPDATE_BIOMEDICAL_RESOURCE) {
				messageController.sendMessage(BciSparqlRepository
						.updateBiomedicalResource(jsonObject));
			} else if (operation == OperationConstants.UPDATE_CHANNEL_LOCATIONS) {
				messageController.sendMessage(BciSparqlRepository
						.updateChannelLocations(jsonObject));
			} else if (operation == OperationConstants.UPDATE_EVENT_INSTANCE_FILE) {
				messageController.sendMessage(BciSparqlRepository
						.updateEventInstanceFile(jsonObject));
			} else if (operation == OperationConstants.UPDATE_ESS_1) {
				messageController.sendMessage(BciSparqlRepository
						.updateEss1(jsonObject));
			} else if (operation == OperationConstants.UPDATE_ESS_2) {
				messageController.sendMessage(BciSparqlRepository
						.updateEss2(jsonObject));
				
			} else if (operation == OperationConstants.DESCRIBE) {
				messageController.sendMessage(BciSparqlRepository
						.describeURI(jsonObject));
			
			} else {
				messageController.sendResponse(SettingConstants.FAILED);
				break;
			}

			log(mSocket.getRemoteSocketAddress().toString(),
					jsonObject.getString(SettingConstants.OPERATION));

		} while (true);

		System.out.println("Leave: " + mSocket.getRemoteSocketAddress());
	}

	private void log(String socketAddress, String operation) {

		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter("log", true)))) {
			String timeStamp = BciSparqlMediatorUtility.getCurrentTime();
			out.println(timeStamp + " " + socketAddress + " " + operation);
		} catch (IOException e) {
		}
	}

}
