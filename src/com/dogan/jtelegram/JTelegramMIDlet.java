package com.dogan.jtelegram;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class JTelegramMIDlet extends MIDlet {

	private ConversationView conversationView; // Initial View
	private Display display;

	public JTelegramMIDlet() {
		// Constructor
		display = Display.getDisplay(this);
		conversationView = new ConversationView(this, display.getCurrent(), "Konuþma Listesi");
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
