package com.senla.autoservice.ui.action;

import com.senla.autoservice.client.Client;
import com.senla.autoservice.utills.request.Request;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public interface IAction {
	Request request = new RequestBuilder().create();

	Response response = new Response();

	public void excute(Client client);
}
