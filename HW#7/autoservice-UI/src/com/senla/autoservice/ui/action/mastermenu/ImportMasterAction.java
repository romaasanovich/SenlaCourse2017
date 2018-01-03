package com.senla.autoservice.ui.action.mastermenu;

import java.lang.reflect.InvocationTargetException;

import com.senla.autoservice.api.RepositoryType;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;
import com.senla.autoservice.utills.Printer;

public class ImportMasterAction implements IAction {
	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		try {
			Printer.printMessage(autoservice.importFromCSV(RepositoryType.MasterRepository));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
