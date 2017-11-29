package com.senla.autoservice.ui.action.placemenu;

import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;

public class ShowFreePlacesAction implements IAction {

	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		Printer.printMessage(autoservice.getAllFreePlaces());
	}
}
