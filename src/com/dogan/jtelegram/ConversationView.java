package com.dogan.jtelegram;

import javax.microedition.io.HttpsConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class ConversationView extends Form implements CommandListener {
	private JTelegramMIDlet midlet; // Root View
	private Displayable caller;    // Caller View
//	private AboutView aboutView;
//	private SettingsView settingsView;
//	private ChatView chatView;
	private Command commandExit, commandSelect, commandSettings, commandAbout;
	private Command commandAddConversation, commandRemoveConversation, commandRefreshList;
	private Display display;

	public ConversationView(JTelegramMIDlet midlet, Displayable caller, String title) throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
//		aboutView = new AboutView(midlet, this, System.getProperty("MIDlet-Name"));
//		settingsView = new SettingsView(midlet, this, "Ayarlar");
//		chatView = new ChatView(midlet, this, "Mesajlar");
		display = Display.getDisplay(midlet);
		
		initializeMenu(this);
		initializeForm(this);
	}
	
	private void initializeMenu(Form form) {
		commandExit = new Command("��k��", Command.EXIT, 0);
		commandSelect = new Command("Se�", Command.ITEM, 0);
		commandAddConversation = new Command("Konu�ma Ekle", Command.SCREEN, 0);
		commandRemoveConversation = new Command("Konu�ma Sil", Command.SCREEN, 0);
		commandRefreshList = new Command("Yenile", Command.SCREEN, 0);
		commandSettings = new Command("Ayarlar", Command.SCREEN, 0);
		commandAbout = new Command("Hakk�nda", Command.SCREEN, 0);
		
		addCommand(commandSelect);
		addCommand(commandAddConversation);
		addCommand(commandRemoveConversation);
		addCommand(commandRefreshList);
		addCommand(commandSettings);
		addCommand(commandAbout);
		addCommand(commandExit);
		setCommandListener(this);
	}

	private void initializeForm(Form form) {
		// setup ui
		// basically conversation selector ui.
		// add user-id to list
		// delete user id from list
		// show if there is new messages to added user-ids
		// select user-id for chatview
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandExit) {
			try {
				midlet.destroyApp(false);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        midlet.notifyDestroyed();
	    } else if (arg0 == commandSelect) {
	    	// get item name and pass it to chatView, delete from ctor
			// chatView = new ChatView(midlet, this, user);
			display.setCurrent(JTelegramMIDlet.chatView);
	    } else if (arg0 == commandAbout) {
			display.setCurrent(JTelegramMIDlet.aboutView);
	    } else if (arg0 == commandSettings) {
			display.setCurrent(JTelegramMIDlet.settingsView);
	    }
	}
}
