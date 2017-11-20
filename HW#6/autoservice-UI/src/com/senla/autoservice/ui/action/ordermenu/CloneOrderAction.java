package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class CloneOrderAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			Printer.printMessage(autoservice.showOrdersByPrice() + "\n");
			Printer.printMessage("Choose order");
			int id = Reader.readInt();
			String input;
			Order ord = null;
			try {
				ord = autoservice.cloneOrder(id);
			} catch (CloneNotSupportedException e) {
				Printer.printMessage(Constants.CLONED_IMPOSSIBLE);
			}
			
			ord.setId(IdGenerator.getFreeID(ord.getMaster().getOrders()));
			
			Printer.printMessage("Input status");
			input= Reader.readline();
			ord.setStatus(Convert.fromStrToStatus(input));
			
			Printer.printArray(ord.getMaster().getWorks());
			Printer.printMessage("Input id work");
			id=Reader.readInt();
			ord.setId(id);
			
			Printer.printMessage(autoservice.showAllFreePlaces());
			Printer.printMessage("Input id place");
			id =Reader.readInt();
			ord.setPlace(autoservice.getCurPlace(id));
			
			
			Printer.printMessage(autoservice.addOrderToMaster(ord.getMaster().getId(), ord));
			
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}

}
