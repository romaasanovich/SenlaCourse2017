package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;
import java.util.Date;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;

public class StatusOpenedAction implements IAction {
	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			String date = Reader.readline();
			Date fDate = Convert.fromStrToDate(date);
			date = Reader.readline();
			Date sDate = Convert.fromStrToDate(date);
			Printer.printMessage(autoservice.getOrdersForPeriodTime(StatusOrder.Opened, fDate, sDate));
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
