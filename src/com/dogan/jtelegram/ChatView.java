package com.dogan.jtelegram;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

public class ChatView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	private SettingsView settingsView;
	private Command commandSettings, commandBack, commandSend, commandAbout;
	private Display display;

	public ChatView(JTelegramMIDlet midlet, Displayable caller, String title) {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
		settingsView = new SettingsView(midlet, this, "Ayarlar");
		display = Display.getDisplay(midlet);

		initializeMenu(this);
		initializeForm(this);
	}

	private void initializeMenu(Form form) {
		commandSend = new Command("Yaz", Command.ITEM, 0);
		commandSettings = new Command("Ayarlar", Command.SCREEN, 0);
		commandAbout = new Command("Hakkýnda", Command.SCREEN, 0);
		commandBack = new Command("Geri", Command.BACK, 0);

		addCommand(commandSend);
		addCommand(commandSettings);
		addCommand(commandAbout);
		addCommand(commandBack);
		setCommandListener(this);
	}

	private void initializeForm(Form form) {
		// setup ui
		// if back pressed, go back to loginview
		// this view has only 1 conversation, which is chat-id
		// select new chat from loginview
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(this.caller);
		}
	}
}
