package com.dogan.jtelegram;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

public class MessageView extends Form implements CommandListener {
	private JTelegramMIDlet midlet;
	private Displayable caller;
	private Command commandBack, commandSend;
	private Display display;
	private TextField replyTextField;
	
	public MessageView(JTelegramMIDlet midlet, Displayable caller, String title) throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		super(title);
		this.midlet = midlet;
		this.caller = caller;
		display = Display.getDisplay(midlet);
		
		initializeMenu(this);
		initializeForm(this);
	}
	
	private void initializeMenu(Form form) {
		commandBack = new Command("Ýptal", Command.BACK, 0);
		commandSend = new Command("Gönder", Command.ITEM, 0);
		
		addCommand(commandBack);
		addCommand(commandSend);
		setCommandListener(this);
	}
	
	private void initializeForm(Form form) {
		// may be not needed for this if we can show both message view and reply box in same view
		replyTextField = new TextField("Cevapla", "", 1024, TextField.ANY);
		form.append(replyTextField);
	}

//	public String getTextField() {
//		return this.replyTextField.getString();
//	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if (arg0 == commandBack) {
			display.setCurrent(JTelegramMIDlet.chatView);
		} else if (arg0 == commandSend) {
			ChatView.setMessage(replyTextField.getString());
			display.setCurrent(JTelegramMIDlet.chatView);
//			if (this.getTextField() != "") {
//				chatView.setReplyFilled(true);
//				Runtime.getRuntime().gc();
//				display.setCurrent(caller);
//			}
		}
	}
}
