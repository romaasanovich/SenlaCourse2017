package com.senla.autoservice.ui.action.placemenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class AddPlace implements IAction {

	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			String name = Reader.readline();
			autoservice.addPlace(name);
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}

	}
}
