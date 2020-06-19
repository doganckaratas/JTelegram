package com.dogan.jtelegram;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

public class SettingsView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	private Command commandBack, commandSave;
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
		// set api-key
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(this.caller);
		}
	}
}
