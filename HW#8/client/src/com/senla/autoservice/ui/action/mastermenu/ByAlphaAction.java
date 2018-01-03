package com.senla.autoservice.ui.action.mastermenu;

import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class ByAlphaAction implements IAction {

	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		Printer.printMessage(autoservice.getMastersByBusying());
		client.sendRequest(new RequestBuilder().setMethod(MethodsName.GET_MASTER_ALPHA).create());
		final Response resp = client.getResponce();
		Printer.printMessage(resp.getOutput());
	}
}
