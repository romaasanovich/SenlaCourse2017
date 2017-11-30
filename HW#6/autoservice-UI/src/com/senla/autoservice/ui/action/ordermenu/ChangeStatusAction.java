package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class ChangeStatusAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		int id = 0;
		Printer.printMessage(autoservice.getOrdersByPrice() + "\n");
		Printer.printMessage("Choose order");
		try {
			id = Reader.readInt();
			Printer.printMessage("Input Status:");
			String str = Reader.readline();
			StatusOrder status = Convert.fromStrToStatus(str);
			Printer.printMessage(Autoservice.getInstance().changeStatus(id, status));
		} catch (IOException | NumberFormatException e) {
			Printer.printMessage("Wrong input!!!");
		}
	}
}