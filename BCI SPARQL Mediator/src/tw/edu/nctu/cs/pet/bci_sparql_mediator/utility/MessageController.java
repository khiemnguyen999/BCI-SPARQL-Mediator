package tw.edu.nctu.cs.pet.bci_sparql_mediator.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import tw.edu.nctu.cs.pet.bci_sparql_mediator.constant.transfer.SettingConstants;

public class MessageController {

	Socket mSocket = null;
	BufferedReader in = null;
	BufferedWriter out = null;

	public MessageController(Socket socket) {
		mSocket = socket;

		try {
			in = new BufferedReader(new InputStreamReader(
					mSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(
					mSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendResponse(String response) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(SettingConstants.RESULT, response);

		sendMessage(jsonObject.toString());
	}

	public JSONObject receiveMessage() {
		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(in.readLine());
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}
}
