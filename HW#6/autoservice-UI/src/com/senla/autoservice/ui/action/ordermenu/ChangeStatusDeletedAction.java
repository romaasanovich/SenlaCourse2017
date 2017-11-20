package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservie.properties.Prop;

public class ChangeStatusDeletedAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			int id = 0;
			Prop prop = new Prop();
			String temp = prop.getProp().getProperty("toDeleteOrders");
			Boolean flag = Boolean.parseBoolean(temp);
			if (flag) {
				Printer.printMessage(autoservice.showOrdersByPrice() + "\n");
				Printer.printMessage("Choose order");
				id = Reader.readInt();
			}
			Printer.printMessage(autoservice.changeStatusDeleted(id));
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
