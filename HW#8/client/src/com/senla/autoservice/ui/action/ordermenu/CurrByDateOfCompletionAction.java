package com.senla.autoservice.ui.action.ordermenu;

import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.client.Client;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class CurrByDateOfCompletionAction implements IAction {
	@Override
	public void excute(Client client) {
		client.sendRequest(new RequestBuilder().setMethod(MethodsName.GET_CUR_ORDER_BY_COMPLETION_DATE).create());
		final Response resp = client.getResponce();
		Printer.printMessage(resp.getOutput());
	}
}
