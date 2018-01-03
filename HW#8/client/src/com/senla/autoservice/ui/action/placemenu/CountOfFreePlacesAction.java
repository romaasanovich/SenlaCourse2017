package com.senla.autoservice.ui.action.placemenu;

import java.io.IOException;
import java.util.Date;

import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class CountOfFreePlacesAction implements IAction {


	@Override
	public void excute() {
		try {
			String date = Reader.readline();
			Date curDate = Convert.fromStrToDate(date);
			client.sendRequest(new RequestBuilder().setMethod(MethodsName.GET_COUNT_FREE_PLACES_ON_DATE).setArgument(curDate).create());
			final Response resp = client.getResponce();
			Printer.printMessage(resp.getOutput());
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
