package com.senla.autoservice.ui.action.ordermenu;

import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.IAction;
import com.senla.autoservice.utills.Printer;

public class ByDateOfCompletionAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();
	@Override
	public void excute() {
		Printer.printMessage(autoservice.showOrdersByDateOfCompletion());
	}
}