package com.dogan.jtelegram;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class JTelegramMIDlet extends MIDlet {

	public static ConversationView conversationView; // Initial View
	public static SettingsView settingsView;
	public static AboutView aboutView;
	public static MessageView messageView;
	public static ChatView chatView;

	private Display display;

	public JTelegramMIDlet() throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		// Constructor
		display = Display.getDisplay(this);
		conversationView = new ConversationView(this, display.getCurrent(), "Konu�ma Listesi");
		aboutView = new AboutView(this, display.getCurrent(), System.getProperty("MIDlet-Name"));
		settingsView = new SettingsView(this, display.getCurrent(), "Ayarlar");
		chatView = new ChatView(this, display.getCurrent(), "Mesajlar");
		messageView = new MessageView(this, display.getCurrent(), "Cevapla");
	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		display.setCurrent(conversationView);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		this.notifyDestroyed();
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
		this.notifyPaused();
	}
}
