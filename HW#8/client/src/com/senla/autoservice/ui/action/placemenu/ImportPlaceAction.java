package com.senla.autoservice.ui.action.placemenu;

import com.senla.autoservice.api.RepositoryType;
import com.senla.autoservice.api.constants.MethodsName;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class ImportPlaceAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		client.sendRequest(new RequestBuilder().setMethod(MethodsName.IMPORT_FROM_CSV)
				.setArgument(RepositoryType.GarageRepository).create());
		final Response resp = client.getResponce();
		Printer.printMessage(resp.getOutput());
	}

}
