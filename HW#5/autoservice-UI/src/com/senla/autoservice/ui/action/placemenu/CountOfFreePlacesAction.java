package com.senla.autoservice.ui.action.placemenu;

import java.io.IOException;
import java.util.Date;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class CountOfFreePlacesAction implements IAction {

	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			String date = Reader.readline();
			Date curDate = Convert.fromStrToDate(date);
			autoservice.getCountOfFreePlacesOnDate(curDate);
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
