package com.senla.autoservice.ui.action.mastermenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class ShowMasterCarrOutOnMasterAction implements IAction {

	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			Printer.printMessage(autoservice.getOrdersByPrice());
			Printer.printMessage("Choose order");
			int id = Reader.readInt();
			Order ord = autoservice.getCurrentOrder(id);
			autoservice.getMasterCarriedOutOrder(ord);
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}

	}
}
