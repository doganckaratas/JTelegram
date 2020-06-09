package com.dogan.jtelegram;

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

public class SettingsView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	private Command commandBack;
	private Display display;

	public SettingsView(JTelegramMIDlet midlet, Displayable caller, String title) {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
		display = Display.getDisplay(midlet);
		
		initializeMenu(this);
		initializeForm(this);
	}
	
	private void initializeMenu(Form form) {
		commandBack = new Command("Geri", Command.BACK, 0);
		
		addCommand(commandBack);
		setCommandListener(this);
	}
	
	private void initializeForm(Form form) {
		// setup ui
		
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(this.caller);
		}
	}
}
