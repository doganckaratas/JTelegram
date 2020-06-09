package com.dogan.jtelegram;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDletStateChangeException;

public class LoginView extends Form implements CommandListener {
	private JTelegramMIDlet midlet; // Root View
	private Displayable caller;    // Caller View
	private AboutView aboutView;
	private SettingsView settingsView;
	private Command commandExit, commandSelect, commandSettings, commandAbout;
	private Display display;
	private Alert infoAlert;

	public LoginView(JTelegramMIDlet midlet, Displayable caller, String title) {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
		aboutView = new AboutView(midlet, this, "Hakkýnda");
		settingsView = new SettingsView(midlet, this, "Ayarlar");
		display = Display.getDisplay(midlet);
		
		initializeMenu(this);
		initializeForm(this);
	}
	
	private void initializeMenu(Form form) {
		commandExit = new Command("Çýkýþ", Command.EXIT, 0);
		commandSelect = new Command("Seç", Command.ITEM, 0);
		commandSettings = new Command("Ayarlar", Command.SCREEN, 0);
		commandAbout = new Command("Hakkýnda", Command.SCREEN, 0);
		
		addCommand(commandSelect);
		addCommand(commandSettings);
		addCommand(commandAbout);
		addCommand(commandExit);
		setCommandListener(this);
	}
	
	private void initializeForm(Form form) {
		// setup ui
		
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
	    	infoAlert = new Alert("Bildirim", "Select wrapper", null, AlertType.INFO);
	    	infoAlert.setTimeout(Alert.FOREVER);
	    	display.setCurrent(infoAlert, this);
	    } else if (arg0 == commandAbout) {
	    	display.setCurrent(aboutView);
	    } else if (arg0 == commandSettings) {
	    	display.setCurrent(settingsView);
	    }
	}
}
