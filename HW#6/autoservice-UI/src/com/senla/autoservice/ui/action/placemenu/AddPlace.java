package com.senla.autoservice.ui.action.placemenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservie.properties.Prop;

public class AddPlace implements IAction {

	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			String name = "";
			Prop prop = new Prop();
			String temp = prop.getProp().getProperty("toAddPlaces");
			Boolean flag = Boolean.parseBoolean(temp);
			if (flag) {
				name = Reader.readline();
			}
			Printer.printMessage(autoservice.addPlace(name));
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}

	}
}
