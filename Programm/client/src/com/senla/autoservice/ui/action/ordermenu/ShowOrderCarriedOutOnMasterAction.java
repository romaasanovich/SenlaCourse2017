package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.client.Client;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class ShowOrderCarriedOutOnMasterAction implements IAction {
	private Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute(Client client) {
		try {
			client.sendRequest(new RequestBuilder().setMethod(MethodsName.GET_MASTER_ALPHA).create());
			Response resp = client.getResponce();
			Printer.printMessage(resp.getOutput());
			Printer.printMessage("Choose order");
			int id = Reader.readInt();
			client.sendRequest(new RequestBuilder().setMethod(MethodsName.GET_ORDER_BY_MASTER).setArgument(id).create());
			resp = client.getResponce();
			Printer.printMessage(resp.getOutput());
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
