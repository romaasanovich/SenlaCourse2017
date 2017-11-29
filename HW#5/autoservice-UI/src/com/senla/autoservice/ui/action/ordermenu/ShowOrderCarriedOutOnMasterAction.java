package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class ShowOrderCarriedOutOnMasterAction implements IAction {
	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			Printer.printMessage(autoservice.getMastersByAlpha());
			Printer.printMessage("Choose order");
			int id = Reader.readInt();
			Master master = autoservice.getCurMaster(id);
			autoservice.getOrderCarriedOutByMaster(master);
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
