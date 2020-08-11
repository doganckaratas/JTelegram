package com.dogan.jtelegram;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class SettingsView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	TextField apiKeyTextField;
	private Command commandBack, commandSave;
	private Display display;

	public SettingsView(JTelegramMIDlet midlet, Displayable caller, String title) throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
		String key;
		RecordStore rs = RecordStore.openRecordStore("apikey", true);
		RecordEnumeration re = rs.enumerateRecords(null, null, false);
		re.reset();
		if (re.hasNextElement()) {
			key = new String(re.nextRecord());
		} else {
			key = "";
		}
		if (re != null) {
			re.rebuild();
			re.destroy();
		}
		if (rs != null) {
			rs.closeRecordStore();
		}
		apiKeyTextField = new TextField("Anahtar", key, 96, TextField.ANY);
		display = Display.getDisplay(midlet);

		initializeMenu(this);
		initializeForm(this);
	}

	private void initializeMenu(Form form) {
		commandBack = new Command("�ptal", Command.BACK, 0);
		commandSave = new Command("Kaydet", Command.ITEM, 0);
		
		addCommand(commandBack);
		addCommand(commandSave);
		setCommandListener(this);
	}

	private void initializeForm(Form form) {
		// setup ui
		// this menu is accessible from everywhere
		// enable disable notification light (TODO)
		// bot settings
		// chat settings, set/get offset for messages
		// set api-key [ok]
		form.append(apiKeyTextField);
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(this.caller);
		} else if (arg0 == commandSave) {
			try {
				RecordStore rs = RecordStore.openRecordStore("apikey", true);
				RecordEnumeration re = rs.enumerateRecords(null, null, false);
				re.reset();
				if (re.hasNextElement()) {
					rs.setRecord(rs.getNextRecordID() - 1, apiKeyTextField.getString().getBytes(), 0, apiKeyTextField.getString().length());
				} else {
					rs.addRecord(apiKeyTextField.getString().getBytes(), 0, apiKeyTextField.getString().length());
				}
				if (re != null) {
					re.rebuild();
					re.destroy();
				}
				if (rs != null) {
					rs.closeRecordStore();
				}
				display.setCurrent(this.caller);
			} catch (RecordStoreFullException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RecordStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
