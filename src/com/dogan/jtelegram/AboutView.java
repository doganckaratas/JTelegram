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

public class AboutView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	private Command commandBack;
	private Display display;

	public AboutView(JTelegramMIDlet midlet, Displayable caller, String title) {
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
		try {
			Image icon = Image.createImage("/icon.png");
			ImageItem iconItem = new ImageItem(null, icon, ImageItem.LAYOUT_CENTER, "appicon");
			form.append(iconItem);
			StringItem title = new StringItem(null, "\n" + midlet.getAppProperty("MIDlet-Name") + " " + midlet.getAppProperty("MIDlet-Version") + "\n");
			title.setLayout(StringItem.LAYOUT_CENTER);
			form.append(title);
			StringItem description = new StringItem(null, "Java cihazlar için Telegram istemcisi\n\n\n");
			description.setLayout(StringItem.LAYOUT_DEFAULT);
			form.append(description);
			StringItem author = new StringItem(null, "Yazar: Doðan C. Karataþ\nTarih: Haziran 2020\nhttp://github.com/doganckaratas");
			author.setLayout(StringItem.LAYOUT_DEFAULT);
			form.append(author);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(this.caller);
		}
	}
}
