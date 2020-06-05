package com.dogan.jtelegram;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class JTelegramMIDlet extends MIDlet {

	private LoginView loginView; // Initial View
	private Display display;

	public JTelegramMIDlet() {
		// Constructor
		display = Display.getDisplay(this);
		loginView = new LoginView(this, display.getCurrent(), System.getProperty("MIDlet-Name"));
	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		display.setCurrent(loginView);
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
