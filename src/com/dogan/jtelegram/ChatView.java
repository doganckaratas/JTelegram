package com.dogan.jtelegram;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;
import javax.microedition.io.SecureConnection;
import javax.microedition.io.SecurityInfo;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.json.me.JSONStringer;
import org.json.me.StringWriter;

public class ChatView extends TextBox implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
//	private SettingsView settingsView;
//	private AboutView aboutView;
//	private MessageView messageView;
	private Command commandSettings, commandBack, commandSend, commandAbout;
	private static String message;
	private Display display;

	public ChatView(JTelegramMIDlet midlet, Displayable caller, String title) throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		super(title, "", 65536, TextField.UNEDITABLE);
		this.midlet = midlet;
		this.caller = caller;
		display = Display.getDisplay(midlet);

		initializeMenu();
		initializeForm(this);
	}

	private void initializeMenu() {
		commandSend = new Command("Yaz", Command.ITEM, 0);
		commandSettings = new Command("Ayarlar", Command.SCREEN, 0);
		commandAbout = new Command("Hakk�nda", Command.SCREEN, 0);
		commandBack = new Command("Geri", Command.BACK, 0);

		addCommand(commandSend);
		addCommand(commandSettings);
		addCommand(commandAbout);
		addCommand(commandBack);
		setCommandListener(this);
	}

	private void initializeForm(TextBox textBox) {
		// setup ui
		// if back pressed, go back to loginview
		// this view has only 1 conversation, which is chat-id
		// select new chat from loginview
	}

	private void processMessage() throws Exception {
		String apikey;
		RecordStore rs = RecordStore.openRecordStore("apikey", true);
		RecordEnumeration re = rs.enumerateRecords(null, null, false);
		re.reset();
		if (re.hasNextElement()) {
			apikey = new String(re.nextRecord());
		} else {
			throw new Exception("Anahtar Bo�");
		}
		if (re != null) {
			re.rebuild();
			re.destroy();
		}
		if (rs != null) {
			rs.closeRecordStore();
		}
		String proxy = "http://https-passthrough.herokuapp.com:80/?url=";
		String url = proxy + "https%3A%2F%2Fapi.telegram.org%2Fbot" + apikey + "%2F";
		String getUpdates = "getUpdates";
		InputStream stream = null;
		HttpConnection http = (HttpConnection) Connector.open(url + getUpdates);

		stream = http.openInputStream();
		if (http.getResponseCode() == HttpConnection.HTTP_OK) {
			int len = (int) http.getLength();
			if (len > 0) {
			    int actual = 0;
			    int bytesread = 0 ;
			    byte[] data = new byte[len];
			    while ((bytesread != len) && (actual != -1)) {
			       actual = stream.read(data, bytesread, len - bytesread);
			       bytesread += actual;
			    }
			    String s = new String(data);
			    JSONObject json = new JSONObject(s);
			    JSONArray resArr = json.getJSONArray("result");
			    for (int i = 0; i < resArr.length(); i++) {
					JSONObject resObj = resArr.getJSONObject(i);
					this.setString(this.getString() + "\n" + resObj.getString("update_id"));
					JSONObject mesObj = resObj.getJSONObject("message");
					this.setString(this.getString() + "\n" + mesObj.getJSONObject("from").getString("username"));
					this.setString(this.getString() + "\n" + mesObj.getString("text"));
			    }
			}
		}

        if (stream != null)
            stream.close();
        if (http != null)
            http.close();
	}

	public static String getMessage() {
		return ChatView.message;
	}

	public static void setMessage(String msg) {
		ChatView.message = msg;
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(JTelegramMIDlet.conversationView);
		} else if (arg0 == commandSend) {
//			try {
//				this.processMessage();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				Alert a = new Alert("Exception", e.toString(), null, AlertType.ERROR);
//				display.setCurrent(a);
//			}
			display.setCurrent(JTelegramMIDlet.messageView);
			this.setString(this.getString() + "\n" + ChatView.getMessage());
		} else if (arg0 == commandSettings) {
			display.setCurrent(JTelegramMIDlet.settingsView);
		} else if (arg0 == commandAbout) {
			display.setCurrent(JTelegramMIDlet.aboutView);
		}
	}
}
