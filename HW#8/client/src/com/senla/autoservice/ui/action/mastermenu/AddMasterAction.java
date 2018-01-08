package com.senla.autoservice.ui.action.mastermenu;

import java.io.IOException;

import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.client.Client;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class AddMasterAction implements IAction {


	@Override
	public void excute(Client client) {
		try {
			String name = Reader.readline();
			client.sendRequest(new RequestBuilder().setMethod(MethodsName.ADD_MASTER)
					.setArgument(name).create());
			final Response resp = client.getResponce();
			Printer.printMessage(resp.getOutput());
		} catch (final IOException e) {
			Printer.printMessage(Constants.ERROR_WRONG_INPUT);
		}
	}
}
