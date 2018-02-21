package com.senla.autoservice.ui.action.mastermenu;

import com.senla.autoservice.client.Client;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.constants.MethodsName;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class ExportMasterAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute(Client client) {
		client.sendRequest(new RequestBuilder().setMethod(MethodsName.EXPORT_MASTER_CSV).create());
		final Response resp = client.getResponce();
		Printer.printMessage(resp.getOutput());
	}
}
