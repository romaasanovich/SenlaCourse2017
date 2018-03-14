package com.senla.autoservice.ui.action.ordermenu;

import java.io.IOException;

import com.senla.autoservice.bean.StatusOrder.StatusOrder;
import com.senla.autoservice.client.Client;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Convert;
import com.senla.autoservice.utills.Printer;
import com.senla.autoservice.utills.Reader;
import com.senla.autoservice.utills.constants.MethodsName;
import com.senla.autoservice.utills.request.RequestBuilder;
import com.senla.autoservice.utills.response.Response;

public class ChangeStatusAction implements IAction {

    public void excute(Client client) {
        int id = 0;
        Printer.printMessage("Choose order");
        try {
            id = Reader.readInt();
            Printer.printMessage("Input Status:");
            String str = Reader.readline();
            StatusOrder status = Convert.fromStrToStatus(str);
            client.sendRequest(new RequestBuilder().setMethod(MethodsName.CHANGE_STATUS).setArgument(id).setArgument(status).create());
            final Response resp = client.getResponce();
            Printer.printMessage(resp.getOutput());
        } catch (IOException e) {
            Printer.printMessage("Wrong input!!!");
        } catch (NumberFormatException e) {
            Printer.printMessage("Wrong input!!!");
        }
    }
}