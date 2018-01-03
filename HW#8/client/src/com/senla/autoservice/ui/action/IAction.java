package com.senla.autoservice.ui.action;

import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.client.Client;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.utills.request.Request;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public interface IAction {
	Autoservice autoservice = Autoservice.getInstance();

	Client client = new Client(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);

	Request request = new RequestBuilder().create();

	Response response = new Response();

	public void excute();
}
