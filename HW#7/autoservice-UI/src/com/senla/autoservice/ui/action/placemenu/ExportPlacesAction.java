package com.senla.autoservice.ui.action.placemenu;

import com.senla.autoservice.api.RepositoryType;
import com.senla.autoservice.facade.Autoservice;
import com.senla.autoservice.ui.action.IAction;

public class ExportPlacesAction implements IAction {

	Autoservice autoservice = Autoservice.getInstance();

	@Override
	public void excute() {
		autoservice.exportToCSV(RepositoryType.GarageRepository);
	}

}
